package edu.gatech.cs2340.trip.model;

import edu.gatech.cs2340.trip.model.accounts.LoginManager;
import edu.gatech.cs2340.trip.model.accounts.RegistrationManager;

/**
 * Created by dheavern on 6/3/14.
 */
public class AccountManagementTest {
    public static void main(String[] args) {
        try {
            RegistrationManager.registerAccount("test", "test", "test", "test");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(LoginManager.attemptLogin("test", "test") != null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Done");
    }
}
