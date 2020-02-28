package com.challenge.user.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PasswordUtils {

    private static String passwordRegexPattern = "^[A-Z].*[a-z][0-9][0-9]";

    public static boolean isValidPassword(String password){
        Pattern pat = Pattern.compile(passwordRegexPattern);
        if (password == null) {
            return false;
        }
        return pat.matcher(password).matches();
    }

    public static String setTokenableString (String param){
        return param.concat("_").concat(UUID.randomUUID().toString());
    }

    public static String setJWTToken(String param) {
        String secretKey = "mySecretKey"; //This secret key can be set on secret rotated, maybe AWS-KMS or something.
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(param)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return token;
    }

}
