package edu.gatech.cs2340.trip.model;

import javax.security.auth.login.AccountException;
import javax.security.auth.login.CredentialException;

/**
 * Created by dheavern on 6/4/14.
 */
public class RegistrationManager {
    private static AccountDAO database = new SqlliteAccountDAO();

    public static boolean registerAccount(String name, String email, String password, String confirmedPassword)
        throws AccountException, CredentialException {
        if (database.getAccount(name) != null) {
            throw new AccountException("An account with this username already exists");
        } else if (!password.equals(confirmedPassword)) {
            throw new CredentialException("The supplied passwords do not match");
        }
        Account newAccount = new Account(name, email, password);
        database.insertAccount(newAccount);
        return true;
    }
}
