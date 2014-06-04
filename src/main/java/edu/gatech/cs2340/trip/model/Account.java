package edu.gatech.cs2340.trip.model;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by dheavern on 6/3/14.
 * Edited by Kamen Tsvetkov on 6/4/14.
 */
public class Account {
    private String name;
    private String email;
    private String passwordHash;

    public Account(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.passwordHash = hashPassword(password);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPassword(String password) {
        try {
            this.passwordHash = PasswordHash.createHash(password);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error Hashing");
        } catch (InvalidKeySpecException e) {
            System.out.println("Error Hashing");
        }
    }

    private String hashPassword(String password) {
        try {
            return PasswordHash.createHash(password);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error Hashing");
            return null;
        } catch (InvalidKeySpecException e) {
            System.out.println("Error Hashing");
            return null;
        }
    }

    @Override
    public String toString() {
        return name + "  :  " + passwordHash;
    }
}
