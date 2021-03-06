package edu.gatech.cs2340.trip.model.accounts;

import edu.gatech.cs2340.trip.util.PasswordHash;

import javax.security.auth.login.AccountException;

/**
 * Created by dheavern on 6/4/14.
 */
public class LoginManager {
    private static AccountDAO database = new SqlliteAccountDAO();

    public static Account attemptLogin(String accountName,
                                       String passwordPlaintext)
            throws AccountException {
        Account matchedAccount;
        if (accountName == null || passwordPlaintext == null) {
            return null;
        }
        matchedAccount = database.getAccount(accountName);
        try {
            if (PasswordHash.validatePassword(passwordPlaintext,
                    matchedAccount.getPasswordHash())) {
                return matchedAccount;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
