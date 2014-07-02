package edu.gatech.cs2340.trip.model.accounts;

import edu.gatech.cs2340.trip.model.Itinerary;
import edu.gatech.cs2340.trip.util.PasswordHash;

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
    private Itinerary tripData;

    public Account(String name, String email, String password) {
        this.name = name;
        this.email = email;
        try {
            this.tripData = new Itinerary();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        setPassword(password);
    }

    //Note the password is pre-hashed
    public Account(String name,
                   String email,
                   String passwordHash,
                   Itinerary tripData) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.tripData = tripData;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPassword(String password) {
        try {
            this.passwordHash = PasswordHash.createHash(password);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error Hashing " + e.getMessage());
        } catch (InvalidKeySpecException e) {
            System.out.println("Error Hashing " + e.getMessage());
        }
    }

    public Itinerary getTripData() {
        return tripData;
    }

    public void setTripData(Itinerary tripData) {
        this.tripData = tripData;
    }

    @Override
    public String toString() {
        return name + ":" + passwordHash;
    }
}
