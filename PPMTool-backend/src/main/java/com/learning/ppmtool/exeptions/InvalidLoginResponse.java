package com.learning.ppmtool.exeptions;

public class InvalidLoginResponse {
    private String username;
    private String password;

    public InvalidLoginResponse() {
        this.username = "invalid user name";
        this.password = "invalid password";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
