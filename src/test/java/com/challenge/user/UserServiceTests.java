package com.challenge.user;

import com.challenge.user.exceptions.EmptyParamException;
import com.challenge.user.exceptions.UserApiException;
import com.challenge.user.models.Phone;
import com.challenge.user.models.User;
import com.challenge.user.models.requests.UserRequest;
import com.challenge.user.services.UserService;
import lombok.Getter;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.challenge.user.constants.Errors.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
@ActiveProfiles("test")
public class UserServiceTests {

    // DATA, would be better a file.json
    String userNameOk = "Juan Rodriguez";
    String userEmailOk = "juan@rodriguez.org";
    String userPasswordOk = "AZazaz00";
    String userPhNumberOk = "23422323";
    String userPhCountryCodeOk = "56";
    String userPhCityCodeOk = "1";

    private Logger log = LoggerFactory.getLogger(UserServiceTests.class);

    @Getter
    private UserService userService;

    @Autowired
    private UserService getUserService() { return userService; }

    @Autowired
    private void setUserService(UserService userService) { this.userService = userService; }

    private UserRequest buildUserRequestData(
            String userName,
            String userEmail,
            String userPassword,
            String userPhNumber,
            String userPhCountryCode,
            String userPhCityCode) {

        UserRequest userRequest = new UserRequest();

        // set values
        userRequest.setName(userName);
        userRequest.setEmail(userEmail);
        userRequest.setPassword(userPassword);

        List<Phone> phones = new ArrayList();
        Phone newPhone = new Phone();
        newPhone.setNumber(userPhNumber);
        newPhone.setCountrycode(userPhCountryCode);
        newPhone.setCitycode(userPhCityCode);
        phones.add(newPhone);
        userRequest.setPhones(phones);

        return userRequest;
    }

    private UserRequest buildUserRequestDataWithCustomPhoneParam(
            String userName,
            String userEmail,
            String userPassword,
            List<Phone> phones) {

        UserRequest userRequest = new UserRequest();

        // set values
        userRequest.setName(userName);
        userRequest.setEmail(userEmail);
        userRequest.setPassword(userPassword);
        userRequest.setPhones(phones);

        return userRequest;
    }


    @Test
    public void create_user() {

        log.info("create_user");

        {
            //CREATING USE OK
            // set data on db
            User userResult = getUserService().createUser(
                    buildUserRequestData(userNameOk, userEmailOk, userPasswordOk, userPhNumberOk, userPhCountryCodeOk, userPhCityCodeOk));

            //asserts
            Assert.assertEquals("should be true, if same email with " + userEmailOk + " param", userResult.getEmail(), userEmailOk);
            Assert.assertEquals("should be true, if same name with " + userNameOk + " param", userResult.getName(), userNameOk);
            Assert.assertEquals("should be true, if same password with " + userPasswordOk + " param", userResult.getPassword(), userPasswordOk);
            Assert.assertEquals("should be true, if is_active param is true", userResult.getIsActive(), true);

            List<Phone> phoneList = userResult.getPhones();
            Phone phone = phoneList.get(0);
            UUID userId = userResult.getId();
            Assert.assertEquals("should be true, if same user_id with " + userId + " param", phone.getUserId(), userId);
            Assert.assertEquals("should be true, if same phone_number with " + userPhNumberOk + " param", phone.getNumber(), userPhNumberOk);
            Assert.assertEquals("should be true, if same phone_country_code with " + userPhCountryCodeOk + " param", phone.getCountrycode(), userPhCountryCodeOk);
            Assert.assertEquals("should be true, if same phone_city_code with " + userPhCityCodeOk + " param", phone.getCitycode(), userPhCityCodeOk);
            Assert.assertEquals("should be true, if same qty phones is 1 ", phoneList.size(), 1);
        }
        {
            //TRYING CREATE USER WITH SAME DATA, USER_EMAIL ALREADY EXIST
            try {
                // set data on db
                getUserService().createUser(
                        buildUserRequestData(userNameOk, userEmailOk, userPasswordOk, userPhNumberOk, userPhCountryCodeOk, userPhCityCodeOk));
                Assert.fail("DON´T ENTER HERE");
            } catch (UserApiException ex) {
                log.info("UserApiException: " + ex.getMessage());
                Assert.assertEquals("should be true, if user_email already exist on db", ex.getCode(), PARAM_ALREADY_EXIST.getValue());
            }

        }

        log.info("create_user end");
    }

    @Test
    public void create_user_testing_name() {

        log.info("create_user_testing_name");
        {
            try {
                // set data on db
                getUserService().createUser(
                        buildUserRequestData("", userEmailOk, userPasswordOk, userPhNumberOk, userPhCountryCodeOk, userPhCityCodeOk));
                Assert.fail("DON´T ENTER HERE");
            } catch (EmptyParamException ex) {
                log.info("EmptyParamException: " + ex.getMessage());
                Assert.assertEquals("should be true, if user_name param is empty", ex.getCode(), PARAM_CANT_BE_EMPTY_OR_NULL.getValue());
            }
        }
        {
            try {
                // set data on db
                getUserService().createUser(
                        buildUserRequestData(null, userEmailOk, userPasswordOk, userPhNumberOk, userPhCountryCodeOk, userPhCityCodeOk));
                Assert.fail("DON´T ENTER HERE");
            } catch (EmptyParamException ex) {
                log.info("EmptyParamException: " + ex.getMessage());
                Assert.assertEquals("should be true, if user_name param is null", ex.getCode(), PARAM_CANT_BE_EMPTY_OR_NULL.getValue());
            }
        }
        log.info("create_user_testing_name end");
    }

    @Test
    public void create_user_testing_email() {

        log.info("create_user_testing_email");

        {
            try {
                // set data on db
                getUserService().createUser(
                        buildUserRequestData(userNameOk, "", userPasswordOk, userPhNumberOk, userPhCountryCodeOk, userPhCityCodeOk));
                Assert.fail("DON´T ENTER HERE");
            } catch (EmptyParamException ex) {
                log.info("EmptyParamException: " + ex.getMessage());
                Assert.assertEquals("should be true, if user_email param is empty", ex.getCode(), PARAM_CANT_BE_EMPTY_OR_NULL.getValue());
            }
        }
        {
            try {
                // set data on db
                getUserService().createUser(
                        buildUserRequestData(userNameOk, null, userPasswordOk, userPhNumberOk, userPhCountryCodeOk, userPhCityCodeOk));
                Assert.fail("DON´T ENTER HERE");
            } catch (EmptyParamException ex) {
                log.info("EmptyParamException: " + ex.getMessage());
                Assert.assertEquals("should be true, if user_email param is null", ex.getCode(), PARAM_CANT_BE_EMPTY_OR_NULL.getValue());
            }
        }
        {
            try {
                // set data on db
                getUserService().createUser(
                        buildUserRequestData(userNameOk, " ", userPasswordOk, userPhNumberOk, userPhCountryCodeOk, userPhCityCodeOk));
                Assert.fail("DON´T ENTER HERE");
            } catch (UserApiException ex) {
                log.info("UserApiException: " + ex.getMessage());
                Assert.assertEquals("should be true, if user_email param, has bad email format", ex.getCode(), PARAM_IS_NOT_VALID.getValue());
            }
        }
        {
            try {
                // set data on db
                getUserService().createUser(
                        buildUserRequestData(userNameOk, "@", userPasswordOk, userPhNumberOk, userPhCountryCodeOk, userPhCityCodeOk));
                Assert.fail("DON´T ENTER HERE");
            } catch (UserApiException ex) {
                log.info("UserApiException: " + ex.getMessage());
                Assert.assertEquals("should be true, if user_email param, has bad email format", ex.getCode(), PARAM_IS_NOT_VALID.getValue());
            }
        }
        {
            try {
                // set data on db
                getUserService().createUser(
                        buildUserRequestData(userNameOk, "@.com", userPasswordOk, userPhNumberOk, userPhCountryCodeOk, userPhCityCodeOk));
                Assert.fail("DON´T ENTER HERE");
            } catch (UserApiException ex) {
                log.info("UserApiException: " + ex.getMessage());
                Assert.assertEquals("should be true, if user_email param, has bad email format", ex.getCode(), PARAM_IS_NOT_VALID.getValue());
            }
        }
        {
            try {
                // set data on db
                getUserService().createUser(
                        buildUserRequestData(userNameOk, "mail@.com", userPasswordOk, userPhNumberOk, userPhCountryCodeOk, userPhCityCodeOk));
                Assert.fail("DON´T ENTER HERE");
            } catch (UserApiException ex) {
                log.info("UserApiException: " + ex.getMessage());
                Assert.assertEquals("should be true, if user_email param, has bad email format", ex.getCode(), PARAM_IS_NOT_VALID.getValue());
            }
        }
        log.info("create_user_testing_email end");
    }

    @Test
    public void create_user_testing_password() {

        log.info("create_user_testing_password");
        {
            try {
                // set data on db
                getUserService().createUser(
                        buildUserRequestData(userNameOk, userEmailOk, "", userPhNumberOk, userPhCountryCodeOk, userPhCityCodeOk));
                Assert.fail("DON´T ENTER HERE");
            } catch (EmptyParamException ex) {
                log.info("EmptyParamException: " + ex.getMessage());
                Assert.assertEquals("should be true, if user_password param is empty", ex.getCode(), PARAM_CANT_BE_EMPTY_OR_NULL.getValue());
            }
        }
        {
            try {
                // set data on db
                getUserService().createUser(
                        buildUserRequestData(userNameOk, userEmailOk, null, userPhNumberOk, userPhCountryCodeOk, userPhCityCodeOk));
                Assert.fail("DON´T ENTER HERE");
            } catch (EmptyParamException ex) {
                log.info("EmptyParamException: " + ex.getMessage());
                Assert.assertEquals("should be true, if user_password param is null", ex.getCode(), PARAM_CANT_BE_EMPTY_OR_NULL.getValue());
            }
        }
        {
            try {
                // set data on db
                getUserService().createUser(
                        buildUserRequestData(userNameOk, userEmailOk, " ", userPhNumberOk, userPhCountryCodeOk, userPhCityCodeOk));
                Assert.fail("DON´T ENTER HERE");
            } catch (UserApiException ex) {
                log.info("UserApiException: " + ex.getMessage());
                Assert.assertEquals("should be true, if user_password param, has bad password format", ex.getCode(), PARAM_IS_NOT_VALID.getValue());
            }
        }
        {
            try {
                // set data on db
                getUserService().createUser(
                        buildUserRequestData(userNameOk, userEmailOk, "hunter2", userPhNumberOk, userPhCountryCodeOk, userPhCityCodeOk));
                Assert.fail("DON´T ENTER HERE");
            } catch (UserApiException ex) {
                log.info("UserApiException: " + ex.getMessage());
                Assert.assertEquals("should be true, if user_password param, has bad password format", ex.getCode(), PARAM_IS_NOT_VALID.getValue());
            }
        }

        log.info("create_user_testing_password end");
    }

    @Test
    public void create_user_testing_phones() {

        log.info("create_user_testing_phones");
        List<Phone> phones = new ArrayList();
        {
            try {
                // set data on db
                getUserService().createUser(
                        buildUserRequestDataWithCustomPhoneParam(userNameOk, userEmailOk, userPasswordOk, null));
                Assert.fail("DON´T ENTER HERE");
            } catch (EmptyParamException ex) {
                log.info("EmptyParamException: " + ex.getMessage());
                Assert.assertEquals("should be true, if user_phones param is null", ex.getCode(), PARAM_CANT_BE_EMPTY_OR_NULL.getValue());
            }
        }

        {
            try {
                // set data on db
                getUserService().createUser(
                        buildUserRequestDataWithCustomPhoneParam(userNameOk, userEmailOk, userPasswordOk, phones));
                Assert.fail("DON´T ENTER HERE");
            } catch (EmptyParamException ex) {
                log.info("EmptyParamException: " + ex.getMessage());
                Assert.assertEquals("should be true, if user_phones param is empty", ex.getCode(), PARAM_CANT_BE_EMPTY_OR_NULL.getValue());
            }
        }

        log.info("create_user_testing_phones end");
    }

}
