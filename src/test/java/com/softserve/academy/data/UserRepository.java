package com.softserve.academy.data;

public final class UserRepository {

    private UserRepository() {
    }

    public static User createWithUniqueEmail(String rawEmail, String name, String password, String repeatPassword) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            prefix.append(chars.charAt((int) (Math.random() * chars.length())));
        }

        String uniqueEmail = prefix.toString() + "_" + rawEmail;

        return new User(uniqueEmail, name, password, repeatPassword);
    }
}