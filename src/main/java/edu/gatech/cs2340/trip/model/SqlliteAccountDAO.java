package edu.gatech.cs2340.trip.model;

import javax.security.auth.login.AccountException;
import java.sql.*;

/**
 * Created by dheavern on 6/3/14.
 */
public class SqlliteAccountDAO implements AccountDAO {
    private Connection dbConnection;
    public SqlliteAccountDAO() {
        try {
            Class.forName("org.sqlite.JDBC");
            dbConnection = DriverManager.getConnection("jdbc:sqlite:Accounts.db");
            dbConnection.setAutoCommit(false);
            String createAccountTableQuery = "CREATE TABLE IF NOT EXISTS accounts"
                    + "  (name TEXT,"
                    + "   passwordHash TEXT)";
            Statement sqlStatement = dbConnection.createStatement();
            sqlStatement.execute(createAccountTableQuery);
        } catch (Exception e) {
        }
        System.out.println("Opened database successfully");

    }

    @Override
    public Account getAccount(String accountName) throws AccountException {
        String selectUserStatement = "SELECT * FROM Accounts WHERE name = ? ; ";
        try {
            PreparedStatement preparedSelectStatement = dbConnection.prepareStatement(selectUserStatement);
            preparedSelectStatement.setString(1, accountName);
            ResultSet accountQueryResults = preparedSelectStatement.executeQuery();
            if (accountQueryResults.next()) {
                return new Account(accountQueryResults.getString("name"), accountQueryResults.getString("email"),
                        accountQueryResults.getString("passwordHash"));
            }
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return null;
    }

    @Override
    public boolean deleteAccount(Account accountToDelete) throws AccountException {
        return false;
    }

    @Override
    public boolean insertAccount(Account newAccount) throws AccountException {
        String insertUserStatement = "INSERT INTO accounts (name, email, password) "
                                    + "VALUES (?, ?, ?);";
        try {
            PreparedStatement preparedInsertStatement = dbConnection.prepareStatement(insertUserStatement);
            preparedInsertStatement.setString(1, newAccount.getName());
            preparedInsertStatement.setString(2, newAccount.getEmail());
            preparedInsertStatement.setString(3, newAccount.getPasswordHash());
            preparedInsertStatement.executeUpdate();
            preparedInsertStatement.close();
            dbConnection.commit();
            return true;
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return false;
    }

    @Override
    public boolean updateAccount(Account updatedAccount) throws AccountException {
        return false;
    }
}
