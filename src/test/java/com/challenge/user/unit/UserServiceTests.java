package com.challenge.user.unit;

import com.challenge.user.database.entitites.PhoneEntity;
import com.challenge.user.database.entitites.UserEntity;
import com.challenge.user.models.Phone;
import com.challenge.user.models.User;
import com.challenge.user.models.requests.UserRequest;
import com.challenge.user.services.UserServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class UserServiceTests {

    private Logger log = LoggerFactory.getLogger(UserServiceTests.class);

    private UserServiceImpl userService = new UserServiceImpl();
    private UserRequest userRequest = new UserRequest();
    private User user = new User();
    private UserEntity userEntity = new UserEntity();
    private Phone phone = new Phone();

    @Test
    public void create_user(){

        //{
            userRequest.setName("Juan Rodriguez");
            userRequest.setEmail("juan@rodriguez.org");
            userRequest.setPassword("AZazaz00");

            List<Phone> phones = new ArrayList();
            Phone newPhone = new Phone();
            newPhone.setNumber("23422323");
            newPhone.setCountrycode("56");
            newPhone.setCitycode("1");
            phones.add(newPhone);
            userRequest.setPhones(phones);

            userService.createUser(userRequest);
        //}

    }

}
