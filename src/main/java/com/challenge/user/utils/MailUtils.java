package com.challenge.user.utils;

import java.util.regex.Pattern;

public class MailUtils {

    private static String emailRegexPattern = "^[a-zA-Z0-9_+&*-]+(?:\\."+
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";

    public static boolean isValidEmail(String email){
        Pattern pat = Pattern.compile(emailRegexPattern);
        if (email == null) {
            return false;
        }
        return pat.matcher(email).matches();
    }

}
