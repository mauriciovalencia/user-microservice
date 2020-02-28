package com.challenge.user.services;

import com.challenge.user.models.User;
import com.challenge.user.models.requests.UserRequest;

public interface UserService {
    User createUser(UserRequest request);
    Boolean isEmailExist(String email);
}
