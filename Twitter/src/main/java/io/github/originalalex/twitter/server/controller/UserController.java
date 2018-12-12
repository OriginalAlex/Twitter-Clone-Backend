package io.github.originalalex.twitter.server.controller;

import io.github.originalalex.twitter.server.dtos.AccountRegistration;
import io.github.originalalex.twitter.server.dtos.SigninAttempt;
import io.github.originalalex.twitter.server.models.user.User;
import io.github.originalalex.twitter.server.repositories.UserRepository;
import io.github.originalalex.twitter.server.verification.RegistrationVerification;
import io.github.originalalex.twitter.utils.HashUtils;
import io.github.originalalex.twitter.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity registerAccount(@Valid @RequestBody AccountRegistration registration) {
        System.out.println("received");
        if (userRepository.findByName(registration.getUsername()) != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Username already exists");
        }
        if (userRepository.findByEmail(registration.getEmail()) != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Email already in use");
        }
        String password = registration.getPassword();
        String verdict = RegistrationVerification.isPasswordValid(password);
        if (!verdict.equals("Valid")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(verdict);
        }
        String passwordHash = HashUtils.SHA256Hash(password);
        User user = new User(registration.getUsername(), passwordHash, registration.getEmail());
        userRepository.save(user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Account Created");
    }

    @RequestMapping(value = "/findUser/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable(value = "id") Long userId) {
        System.out.println("received");
        return userRepository.findById(userId).orElse(null);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<User> getAll() {
        System.out.println(userRepository.findAll().size());
        return userRepository.findAll();
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity signin(@Valid @RequestBody SigninAttempt credentials, HttpServletResponse response) {
        String usernameOrEmail = credentials.getUsernameOrEmail();
        String password = credentials.getPassword();
        String passwordHash = HashUtils.SHA256Hash(password);
        User user = userRepository.findByName(usernameOrEmail);
        System.out.println(usernameOrEmail);
        System.out.println(user);
        if (user != null) {
            if (user.getPasswordHash().equals(user.getPasswordHash())) {
                String token = TokenUtils.getToken(user.getUsername(), user.getRole());
                response.addCookie(new Cookie("token", token));
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body("Signed in");
            }
        } else {
            User userFromEmail = userRepository.findByEmail(usernameOrEmail);
            if (userFromEmail.getPasswordHash().equals(password)) {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body("Signed in");
            }
        }
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Invalid username or password");
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity test(HttpServletResponse response) {
        response.addCookie(new Cookie("test", "123"));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("here's a cookie");
    }

    @RequestMapping(value = "cookieCheck", method = RequestMethod.GET)
    public ResponseEntity testCookie(HttpServletRequest request) {
        System.out.println(request.getCookies().length);
        for (Cookie c : request.getCookies()) {
            System.out.println(c.getName() + ":" + c.getValue());
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("checked");
    }

}
