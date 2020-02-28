package com.challenge.user.utils;

import java.util.regex.Pattern;

public class PasswordUtils {

    private static String passwordRegexPattern = "^[A-Z].*[a-z][0-9][0-9]";

    public static boolean isValidPassword(String password){
        Pattern pat = Pattern.compile(passwordRegexPattern);
        if (password == null) {
            return false;
        }
        return pat.matcher(password).matches();
    }

}
