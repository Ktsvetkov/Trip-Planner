package edu.gatech.cs2340.trip.model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
    private JsonObject tripData;

    public Account(String name, String email, String password) {
        this.name = name;
        this.email = email;
        try {
            this.tripData = new JsonObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        setPassword(password);
    }

    //Note the password is pre-hashed
    public Account(String name, String email,  String passwordHash, String tripData) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        if (tripData == null) {
            this.tripData = new JsonObject();
        } else {
            JsonParser tripDataParser = new JsonParser();
            this.tripData = tripDataParser.parse(tripData).getAsJsonObject();
        }
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

    public JsonObject getTripData() {
        return tripData;
    }

    public void setTripData(JsonObject tripData) {
        this.tripData = tripData;
    }

    @Override
    public String toString() {
        return name + ":" + passwordHash;
    }
}
