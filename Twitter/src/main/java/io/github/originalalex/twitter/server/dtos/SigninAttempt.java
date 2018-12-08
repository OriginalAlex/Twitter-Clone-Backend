package io.github.originalalex.twitter.server.dtos;

import javax.validation.constraints.NotEmpty;

public class SigninAttempt {

    @NotEmpty
    private String usernameOrEmail;

    @NotEmpty
    private String password;

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
