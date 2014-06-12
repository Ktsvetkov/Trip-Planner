package edu.gatech.cs2340.trip.model;

import edu.gatech.cs2340.trip.util.InputValidator;
import edu.gatech.cs2340.trip.util.PasswordHash;

import javax.security.auth.login.AccountException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by dheavern on 6/4/14.
 */
public class UpdateAccountManger {
    private static AccountDAO database = new SqlliteAccountDAO();

    public static void updateEmail(Account account,String newEmail) throws AccountException {
        if (!InputValidator.isValidEmail(newEmail)) {
            throw new AccountException("The supplied email is invalid");
        }
        String oldEmail = account.getEmail();
        account.setEmail(newEmail);
        try {
            database.updateAccount(account);
        } catch (Exception e) {
            account.setEmail(oldEmail);
            throw new AccountException("There was an error updating your email.");
        }
    }

    public static void updatePassword(Account account,
                                         String oldPassword,
                                         String newPassword,
                                         String confirmPassword) throws AccountException {
        String oldPasswordHash = account.getPasswordHash();
        try {
            if (!PasswordHash.validatePassword(oldPassword, oldPasswordHash)) {
                throw new AccountException("Your old password is not correct");
            }
        } catch (NoSuchAlgorithmException e) {
            throw new AccountException("There was an internal error" + e.getMessage());
        } catch (InvalidKeySpecException e) {
            throw new AccountException("There was an internal error" + e.getMessage());
        }
        if (!InputValidator.isValidPassword(newPassword)) {
            throw new AccountException("Your password must be atleast 5 characters long");
        }
        if (!newPassword.equals(confirmPassword)) {
            throw new AccountException("Your passwords do not match");
        }
        account.setPassword(newPassword);
        try {
            database.updateAccount(account);
        } catch (Exception e) {
            account.setPassword(oldPassword);
            throw new AccountException("There was an error changing your password.");
        }
    }

    public static void saveTripChanges(Account account) {
        try {
            database.updateAccount(account);
        } catch (Exception e) {

        }
    }
}
