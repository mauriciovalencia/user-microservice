package com.challenge.user.controllers;

import com.challenge.user.exceptions.EmptyParamException;
import com.challenge.user.exceptions.ExistParamException;
import com.challenge.user.exceptions.InvalidParamException;
import com.challenge.user.models.ErrorMessage;
import com.challenge.user.models.User;
import com.challenge.user.models.requests.UserRequest;
import com.challenge.user.models.responses.UserResponse;
import com.challenge.user.services.UserService;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/")
public class UserRestController {
    private static Logger log = LoggerFactory.getLogger(UserRestController.class);

    @Getter
    private UserService userService;

    @Autowired
    public void setUserService (UserService userService){
        this.userService = userService;
    }

    @PostMapping("login")
    public User login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
        // the password "pwd" can be performed for search on db register.
        String token = getJWTToken(username);
        User user = new User();
        user.setUser(username);
        user.setToken(token);
        return user;
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey"; //This secret key can be set on secret rotated, maybe AWS-KMS or something.
        /* List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact(); */

        //Can be performed store token before on data-base only reading by custom key of user and password.
        // return "Bearer " + token;
        return null;
    }

    @PostMapping("users")
    public ResponseEntity<?> create (@RequestBody UserRequest request) {

        ErrorMessage error = new ErrorMessage();

        try {
            User user = getUserService().createUser(request);
            UserResponse userResponse = new UserResponse();
            userResponse.setId(user.getId());
            userResponse.setLastLogin(user.getTimestamps().getCreatedAt());
            userResponse.setName(user.getName());
            userResponse.setToken("7jAqdUPWuM66XYqBjq0K");
            userResponse.setIsActive(user.getIsActive());
            userResponse.setTimestamps(user.getTimestamps());
            return new ResponseEntity<>(userResponse, HttpStatus.OK);

        }catch(EmptyParamException ep) {
            log.error(ep.getLocalizedMessage(), ep);
            return new ResponseEntity<>(new ErrorMessage(ep.getCode(),
                    ep.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (ExistParamException ep) {
            log.error(ep.getLocalizedMessage(), ep);
            return new ResponseEntity<>(new ErrorMessage(ep.getCode(), ep.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }catch(InvalidParamException ip) {
            log.error(ip.getLocalizedMessage(), ip);
            return new ResponseEntity<>(new ErrorMessage(ip.getCode(), ip.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception e){
            log.error(e.getLocalizedMessage(), e);
            return new ResponseEntity<>(new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
