package com.challenge.user.controllers;

import com.challenge.user.exceptions.EmptyParamException;
import com.challenge.user.exceptions.ExistParamException;
import com.challenge.user.exceptions.InvalidParamException;
import com.challenge.user.models.ErrorMessage;
import com.challenge.user.models.User;
import com.challenge.user.models.requests.UserRequest;
import com.challenge.user.models.responses.UserResponse;
import com.challenge.user.services.UserService;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.challenge.user.utils.PasswordUtils.setJWTToken;
import static com.challenge.user.utils.PasswordUtils.setTokenableString;


@RestController
@RequestMapping("/api/v1/")
public class UserRestController {
    private static Logger log = LoggerFactory.getLogger(UserRestController.class);

    @Getter
    private UserService userService;

    @Autowired
    private void setUserService(UserService userService) { this.userService = userService; }



    @PostMapping("users")
    public ResponseEntity<?> create (@RequestBody UserRequest request) {

        ErrorMessage error = new ErrorMessage();

        try {
            User user = getUserService().createUser(request);
            UserResponse userResponse = new UserResponse();
            userResponse.setId(user.getId());
            userResponse.setLastLogin(user.getTimestamps().getCreatedAt());
            userResponse.setName(user.getName());
            userResponse.setToken(setJWTToken(setTokenableString(user.getEmail())));
            userResponse.setIsActive(user.getIsActive());
            userResponse.setTimestamps(user.getTimestamps());
            return new ResponseEntity<>(userResponse, HttpStatus.OK);

        }catch(EmptyParamException ep) {
            log.error(ep.getLocalizedMessage(), ep);
            return new ResponseEntity<>(new ErrorMessage(ep.getCode(),
                    ep.getMessage()), HttpStatus.NOT_FOUND);
        }catch (ExistParamException ep) {
            log.error(ep.getLocalizedMessage(), ep);
            return new ResponseEntity<>(new ErrorMessage(ep.getCode(), ep.getMessage()),
                    HttpStatus.NOT_FOUND);
        }catch(InvalidParamException ip) {
            log.error(ip.getLocalizedMessage(), ip);
            return new ResponseEntity<>(new ErrorMessage(ip.getCode(), ip.getMessage()),
                    HttpStatus.NOT_FOUND);
        } catch(Exception e){
            log.error(e.getLocalizedMessage(), e);
            return new ResponseEntity<>(new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
