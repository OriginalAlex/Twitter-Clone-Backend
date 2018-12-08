package io.github.originalalex.twitter.server.controller;

import io.github.originalalex.twitter.server.dtos.AccountRegistration;
import io.github.originalalex.twitter.server.dtos.SigninAttempt;
import io.github.originalalex.twitter.server.models.user.User;
import io.github.originalalex.twitter.server.repositories.UserRepository;
import io.github.originalalex.twitter.server.verification.RegistrationVerification;
import io.github.originalalex.twitter.utils.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
                    .body("Username already in use");
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
    public ResponseEntity signin(@Valid @RequestBody SigninAttempt credentials) {
        String usernameOrEmail = credentials.getUsernameOrEmail();
        String password = credentials.getPassword();
        String passwordHash = HashUtils.SHA256Hash(password);
        User user = userRepository.findByName(usernameOrEmail);
        if (user != null) {
            if (user.getPasswordHash().equals(password)) {
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
}
