package edu.gatech.cs2340.trip.model;

/**
 * Created by dheavern on 6/3/14.
 */
public class Account {
    private String accountName;
    private String accountPasswordHash;

    public Account(String accountName, String accountPasswordHash) {
        this.accountName = accountName;
        this.accountPasswordHash = accountPasswordHash;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountPasswordHash() {
        return accountPasswordHash;
    }

    public void setAccountPass(String accountPasswordHash) {
        this.accountPasswordHash = accountPasswordHash;
    }

    @Override
    public String toString() {
        return accountName + "  :  " + accountPasswordHash;
    }
}
