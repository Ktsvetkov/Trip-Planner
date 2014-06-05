package edu.gatech.cs2340.trip.model;

import java.security.NoSuchAlgorithmException;

/**
 * Created by dheavern on 6/4/14.
 */
public class UpdateAccountManger {
    private static AccountDAO database = new SqlliteAccountDAO();

    public static boolean updateEmail(Account account,String newEmail) {
        if (!InputValidator.isValidEmail(newEmail)) {
            return false;
        }
        String oldEmail = account.getEmail();
        account.setEmail(newEmail);
        try {
            database.updateAccount(account);
        } catch (Exception e) {
            account.setEmail(oldEmail);
            return false;
        }
        return true;
    }

    public static boolean updatePassword(Account account,
                                         String oldPassword,
                                         String newPassword,
                                         String confirmPassword) {
        String oldPasswordHash = account.getPasswordHash();
        try {
            if (!PasswordHash.validatePassword(oldPassword, oldPasswordHash)) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        if (!newPassword.equals(oldPassword)) {
            return false;
        }
        account.setPassword(newPassword);
        try {
            database.updateAccount(account);
        } catch (Exception e) {
            account.setPassword(oldPassword);
            return false;
        }
        return true;
    }

    public static boolean saveTripChanges(Account account) {
        try {
            database.updateAccount(account);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
