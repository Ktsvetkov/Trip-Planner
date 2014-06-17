package edu.gatech.cs2340.trip.model;

import javax.security.auth.login.AccountException;

/**
 * Created by dheavern on 6/3/14.
 */
public interface AccountDAO {
    Account getAccount(String username) throws AccountException;
    boolean insertAccount(Account newAccount) throws AccountException;
    boolean updateAccount(Account updatedAccount) throws AccountException;
    boolean deleteAccount(Account accountToDelete) throws AccountException;
}
