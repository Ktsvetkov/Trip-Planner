package edu.gatech.cs2340.trip.model;

//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by dheavern on 6/3/14.
 * Edited by Kamen Tsvetkov on 6/4/14.
 */
public class UserManagement {


    public static boolean createUser(String username, String email, String password) throws IOException {


        String passwordHash = password;
        //try {
            //passwordHash = PasswordHash.createHash(password);
        //} catch (NoSuchAlgorithmException e) {

        //} catch (InvalidKeySpecException e) {

        //}
        File userFile = null;

        userFile = new File(username + ".txt");



        if(userFile.exists()) {
            return false;
        } else {

            try {

            FileWriter writer = new FileWriter(userFile, true);

            PrintWriter output = new PrintWriter(writer);

            output.println(username);
            output.println(email);
            output.println(passwordHash);
            output.close();

            } catch (FileNotFoundException e) {

            } catch (UnsupportedEncodingException e) {

            } catch (IOException e) {

            }


        }
        return true;

    }


   // public static void main(String[] args) {
   //     try {
   //     createUser("Kamen", "Kamen", "Kamen");
   //     } catch (IOException e) {
//
   //     }
   // }

}
