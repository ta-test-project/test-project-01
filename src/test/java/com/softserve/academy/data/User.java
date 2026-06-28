package com.softserve.academy.data;

public class User {
    private final String email;
    private final String name;
    private final String password;
    private final String repeatPassword;

    public User(String email, String name, String password, String repeatPassword) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    public String getEmail() { return email; }
    public String getName() { return name; }
    public String getPassword() { return password; }
    public String getRepeatPassword() { return repeatPassword; }
}