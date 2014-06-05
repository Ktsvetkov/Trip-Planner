package edu.gatech.cs2340.trip.model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
            dbConnection = DriverManager.getConnection("jdbc:sqlite:/../databases/Accounts.db");
            dbConnection.setAutoCommit(false);
            String createAccountTableQuery = "CREATE TABLE IF NOT EXISTS accounts"
                    + "  (name TEXT,"
                    + "   email TEXT,"
                    + "   passwordHash TEXT"
                    + "   tripData TEXT)";
            Statement sqlStatement = dbConnection.createStatement();
            sqlStatement.execute(createAccountTableQuery);
            dbConnection.commit();
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
                        accountQueryResults.getString("passwordHash"),
                        accountQueryResults.getString("tripData"));
            }
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return null;
    }

    @Override
    public boolean deleteAccount(Account accountToDelete) throws AccountException {
        String deleteStatement = "DELETE FROM Accounts WHERE name =? AND email = ? AND password = ?";
        try {
            PreparedStatement preparedDeleteStatement = dbConnection.prepareStatement(deleteStatement);
            preparedDeleteStatement.setString(1, accountToDelete.getName());
            preparedDeleteStatement.setString(2, accountToDelete.getEmail());
            preparedDeleteStatement.setString(3, accountToDelete.getPasswordHash());
            preparedDeleteStatement.executeUpdate();
            preparedDeleteStatement.close();
            dbConnection.commit();
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return false;
        }
        return true;
    }

    @Override
    public boolean insertAccount(Account newAccount) throws AccountException {
        String insertUserStatement = "INSERT INTO Accounts (name, email, password, tripData) "
                                    + "VALUES (?, ?, ?, ?);";
        try {
            PreparedStatement preparedInsertStatement = dbConnection.prepareStatement(insertUserStatement);
            preparedInsertStatement.setString(1, newAccount.getName());
            preparedInsertStatement.setString(2, newAccount.getEmail());
            preparedInsertStatement.setString(3, newAccount.getPasswordHash());
            preparedInsertStatement.setString(4, newAccount.getTripData().getAsString());
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
        String updateUserStatement = "UPDATE Accounts SET email = ?, password = ?, tripData = ?" +
                                     "WHERE name = ?";
        try {
            PreparedStatement preparedUpdateStatement = dbConnection.prepareStatement(updateUserStatement);
            preparedUpdateStatement.setString(1, updatedAccount.getEmail());
            preparedUpdateStatement.setString(2, updatedAccount.getPasswordHash());
            preparedUpdateStatement.setString(3, updatedAccount.getTripData().getAsString());
            preparedUpdateStatement.setString(4, updatedAccount.getName());
            preparedUpdateStatement.executeUpdate();
            preparedUpdateStatement.close();
            dbConnection.commit();
            return true;
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return false;
    }
}
