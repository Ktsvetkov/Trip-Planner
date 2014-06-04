package edu.gatech.cs2340.trip.model;

import javax.security.auth.login.AccountException;

/**
 * Created by dheavern on 6/3/14.
 */
public class AccountManagementModel {
    private AccountDAO accountDAOImpl;

    public AccountManagementModel() {
        this.accountDAOImpl = new SqlliteAccountDAO();
    }

    public Account attemptLogin(String userName) throws AccountException
    {
        return null;
    }

    public boolean register(Account newAccount) {
        return false;
    }
}
