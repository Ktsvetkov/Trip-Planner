package edu.gatech.cs2340.trip.model;

import javax.security.auth.login.AccountException;

/**
 * Created by dheavern on 6/3/14.
 */
public interface AccountDAO {
    public Account getAccount(String username) throws AccountException;
    public boolean insertAccount(Account newAccount) throws AccountException;
    public boolean updateAccount(Account updatedAccount) throws AccountException;
    public boolean deleteAccount(Account accountToDelete) throws AccountException;
}
