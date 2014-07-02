package edu.gatech.cs2340.trip.model.accounts;

import edu.gatech.cs2340.trip.util.InputValidator;

import javax.security.auth.login.AccountException;
import javax.security.auth.login.CredentialException;

/**
 * Created by dheavern on 6/4/14.
 */
public class RegistrationManager {
    private static AccountDAO database = new SqlliteAccountDAO();

    public static boolean registerAccount(String name,
                                          String email,
                                          String password,
                                          String confirmedPassword)
        throws AccountException, CredentialException {
        if (name == null || name.equals("")) {
            throw new AccountException("You must enter a username");
        } else if (email == null || email.equals("")) {
            throw new AccountException("You must enter an email");
        } else if (password == null || password.equals("")) {
            throw new AccountException("You must enter a password");
        } else if (!InputValidator.isValidPassword(password)) {
            throw new AccountException(
                    "Your password must be at least 5 characters long");
        } else if (confirmedPassword == null || confirmedPassword.equals("")) {
            throw new AccountException("You must confirm your password");
        } else if (database.getAccount(name) != null) {
            throw new AccountException(
                    "An account with this username already exists");
        } else if (!password.equals(confirmedPassword)) {
            throw new CredentialException(
                    "The supplied passwords do not match");
        } else if (!InputValidator.isValidUsername(name)) {
            throw new CredentialException(
                    "Usernames must be 3 to 15 characters long"
                  + "and only contain valid characters");
        } else if (!InputValidator.isValidEmail(email)) {
            throw new CredentialException("The supplied email is invalid");
        }
        Account newAccount = new Account(name, email, password);
        database.insertAccount(newAccount);
        return true;
    }
}
