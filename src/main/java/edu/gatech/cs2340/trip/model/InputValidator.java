package edu.gatech.cs2340.trip.model;

import java.util.regex.Pattern;

/**
 * Created by dheavern on 6/5/14.
 */
public class InputValidator {
    private static final Pattern validUsername = Pattern.compile("^[A-Za-z0-9_-]{3,15}$");
    private static final Pattern validEmail = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");


    public static boolean isValidUsername(String username) {
        return validUsername.matcher(username).matches();
    }

    public static boolean isValidEmail(String email) {
        return validEmail.matcher(email).matches();
    }

    public static String stripXss(String input) {
        Pattern[] patterns = new Pattern[]{
                // Script fragments
                Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE),
                // src='...'
                Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
                Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
                // lonely script tags
                Pattern.compile("</script>", Pattern.CASE_INSENSITIVE),
                Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
                // eval(...)
                Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
                // expression(...)
                Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
                // javascript:...
                Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE),
                // vbscript:...
                Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE),
                // onload(...)=...
                Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL)
        };
        if (input != null) {
            input = input.replaceAll("\0", "");

            // Remove all sections that match a pattern
            for (Pattern scriptPattern : patterns){
                input = scriptPattern.matcher(input).replaceAll("");
            }
        }
        return input;
    }
}
