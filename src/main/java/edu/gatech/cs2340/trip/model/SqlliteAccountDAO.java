package edu.gatech.cs2340.trip.model;

import javax.security.auth.login.AccountException;
import javax.xml.transform.Result;
import java.sql.*;

/**
 * An Sqllite DAO for user accounts
 * Created by dheavern on 6/3/14.
 */
public class SqlliteAccountDAO implements AccountDAO {
    private Connection dbConnection;
    public SqlliteAccountDAO() {
        try {
            openDb();
            String createAccountTableQuery = "CREATE TABLE IF NOT EXISTS accounts"
                    + "  (name TEXT,"
                    + "   email TEXT,"
                    + "   passwordHash TEXT,"
                    + "   tripData TEXT)";
            Statement sqlStatement = dbConnection.createStatement();
            sqlStatement.execute(createAccountTableQuery);
            sqlStatement.close();
            dbConnection.commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            closeQuietly(dbConnection);
        }
        System.out.println("Opened database successfully");

    }

    @Override
    public Account getAccount(String accountName) throws AccountException {
        openDb();
        String selectUserStatement = "SELECT * FROM Accounts WHERE name = ? ; ";
        ResultSet accountQueryResults = null;
        PreparedStatement preparedSelectStatement = null;
        try {
            preparedSelectStatement = dbConnection.prepareStatement(selectUserStatement);
            preparedSelectStatement.setString(1, accountName);
            accountQueryResults = preparedSelectStatement.executeQuery();
            if (accountQueryResults.next()) {
                return new Account(accountQueryResults.getString("name"), accountQueryResults.getString("email"),
                        accountQueryResults.getString("passwordHash"),
                        accountQueryResults.getString("tripData"));
            }
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        } finally {
            closeQuietly(preparedSelectStatement);
            closeQuietly(accountQueryResults);
            closeQuietly(dbConnection);
        }
        return null;
    }

    @Override
    public boolean deleteAccount(Account accountToDelete) throws AccountException {
        openDb();
        String deleteStatement = "DELETE FROM Accounts WHERE name =? AND email = ? AND password = ?";
        PreparedStatement preparedDeleteStatement = null;
        try {
            preparedDeleteStatement = dbConnection.prepareStatement(deleteStatement);
            preparedDeleteStatement.setString(1, accountToDelete.getName());
            preparedDeleteStatement.setString(2, accountToDelete.getEmail());
            preparedDeleteStatement.setString(3, accountToDelete.getPasswordHash());
            preparedDeleteStatement.executeUpdate();
            dbConnection.commit();
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return false;
        }  finally {
            closeQuietly(preparedDeleteStatement);
            closeQuietly(dbConnection);
        }
        return true;
    }

    @Override
    public boolean insertAccount(Account newAccount) throws AccountException {
        openDb();
        String insertUserStatement = "INSERT INTO Accounts (name, email, passwordHash, tripData) "
                                    + "VALUES (?, ?, ?, ?);";
        PreparedStatement preparedInsertStatement = null;
        try {
            preparedInsertStatement = dbConnection.prepareStatement(insertUserStatement);
            preparedInsertStatement.setString(1, newAccount.getName());
            preparedInsertStatement.setString(2, newAccount.getEmail());
            preparedInsertStatement.setString(3, newAccount.getPasswordHash());
            try {
                preparedInsertStatement.setString(4, newAccount.getTripData().toString());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            preparedInsertStatement.executeUpdate();
            preparedInsertStatement.close();
            dbConnection.commit();
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        } finally {
            closeQuietly(preparedInsertStatement);
            closeQuietly(dbConnection);
        }
        return false;
    }

    @Override
    public boolean updateAccount(Account updatedAccount) throws AccountException {
        openDb();
        String updateUserStatement = "UPDATE Accounts SET email = ?, passwordHash = ?, tripData = ?" +
                                     "WHERE name = ?";
        PreparedStatement preparedUpdateStatement = null;
        try {
            preparedUpdateStatement = dbConnection.prepareStatement(updateUserStatement);
            preparedUpdateStatement.setString(1, updatedAccount.getEmail());
            preparedUpdateStatement.setString(2, updatedAccount.getPasswordHash());
            preparedUpdateStatement.setString(3, updatedAccount.getTripData().getAsString());
            preparedUpdateStatement.setString(4, updatedAccount.getName());
            preparedUpdateStatement.executeUpdate();
            dbConnection.commit();
            return true;
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        } finally {
            closeQuietly(preparedUpdateStatement);
            closeQuietly(dbConnection);
        }
        return false;
    }

    private void openDb() {
        try {
            Class.forName("org.sqlite.JDBC");
            if (dbConnection == null || dbConnection.isClosed()) {
                dbConnection = DriverManager.getConnection("jdbc:sqlite:" +
                        System.getenv("CATALINA_HOME") + "/webapps/Accounts.db");
                dbConnection.setAutoCommit(false);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void closeQuietly(Connection con) {
        try {
            if (con != null){
                con.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void closeQuietly(ResultSet resultSet) {
        try {
            if (resultSet != null){
                resultSet.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void closeQuietly(PreparedStatement preparedStatement) {
        try {
            if (preparedStatement != null){
                preparedStatement.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
