package edu.gatech.cs2340.trip.model;

import javax.security.auth.login.AccountException;
import javax.security.auth.login.CredentialException;
import javax.xml.bind.ValidationEvent;
import java.util.Vector;

/**
 * Created by dheavern on 6/4/14.
 */
public class RegistrationManager {
    private static AccountDAO database = new SqlliteAccountDAO();

    public static boolean registerAccount(String name, String email, String password, String confirmedPassword)
        throws AccountException, CredentialException {
        if (name == null || database.getAccount(name) != null) {
            throw new AccountException("An account with this username already exists");
        } else if (password == null || !password.equals(confirmedPassword)) {
            throw new CredentialException("The supplied passwords do not match");
        } else if (!InputValidator.isValidUsername(name)) {
            throw new CredentialException("The supplied username is invalid");
        } else if (email == null || !InputValidator.isValidEmail(email)) {
            throw new CredentialException("The supplied email is invalid");
        }
        Account newAccount = new Account(name, email, password);
        database.insertAccount(newAccount);
        return true;
    }
}
