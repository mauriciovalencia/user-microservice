package com.challenge.user.unit.rest;


import com.challenge.user.controllers.UserRestController;
import com.challenge.user.models.Phone;
import com.challenge.user.models.User;
import com.challenge.user.models.requests.UserRequest;
import com.challenge.user.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(UserRestController.class)
@WithMockUser(username = "user", password = "pwd", roles = "ADMIN")
public class UserRestControllerTests {

    // DATA, would be better a file.json
    String userNameOk = "Juan Rodriguez";
    String userEmailOk = "juan@rodriguez.org";
    String userPasswordOk = "AZazaz00";
    String userPhNumberOk = "23422323";
    String userPhCountryCodeOk = "56";
    String userPhCityCodeOk = "1";

    private Logger log = LoggerFactory.getLogger(UserRestControllerTests.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private ObjectMapper mapper = new ObjectMapper();

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

    private User buildMockUser(            String userName,
                                           String userEmail,
                                           String userPassword,
                                           String userPhNumber,
                                           String userPhCountryCode,
                                           String userPhCityCode) {
        User mockUser = new User();
        mockUser.setName(userNameOk);
        mockUser.setEmail(userEmailOk);
        mockUser.setPassword(userPasswordOk);
        List<Phone> phones = new ArrayList();
        Phone newPhone = new Phone();
        newPhone.setNumber(userPhNumber);
        newPhone.setCountrycode(userPhCountryCode);
        newPhone.setCitycode(userPhCityCode);
        phones.add(newPhone);
        mockUser.setPhones(phones);
        mockUser.setIsActive(true);

        return mockUser;
    }


    @Ignore
    @Test
    public void setNewUser() throws Exception {

        try {
            //User userResult = getUserService().createUser(
            UserRequest userRequest = buildUserRequestData(userNameOk,userEmailOk,userPasswordOk,
                    userPhNumberOk,userPhCountryCodeOk,userPhCityCodeOk);

            User mockUser = buildMockUser(userNameOk,userEmailOk,userPasswordOk,
                    userPhNumberOk,userPhCountryCodeOk,userPhCityCodeOk);

            Mockito.when(userService.createUser(Mockito.any(UserRequest.class))).thenReturn(mockUser);

            //System.out.println("REQUEST: "+mapper.writeValueAsString(userRequest));

            /*RequestBuilder requestBuilder = MockMvcRequestBuilders
                    //.post("/api/v1/users")
                    .post(new URI("http://localhost:8080/api/v1/users"))
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(userRequest));

            MvcResult result = mockMvc.perform(requestBuilder).andReturn();*/

            //MvcResult result = mockMvc.perform(post(new URI("http://localhost:8080/api/v1/users"))
            /*MvcResult result = mockMvc.perform(post("/api/v1/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(userRequest))
                    .accept(MediaType.APPLICATION_JSON)).andReturn();

            MockHttpServletResponse response = result.getResponse();
            System.out.println("RESPONSE_STATUS: "+response.getStatus());
            System.out.println("RESPONSE_ERROR_DESC: "+response.getErrorMessage());*/

            MvcResult result = mockMvc.perform(post("/api/v1/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(userRequest))
                    .accept(MediaType.APPLICATION_JSON)).andReturn();

            System.out.println("RESPONSE_STATUS: "+result.getResponse().getStatus());
            System.out.println("RESPONSE_ERROR_DESC: "+result.getRequest().toString());

            //Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());

            //Assert.assertEquals("http://localhost:8080/api/v1/users", response.getHeader(HttpHeaders.LOCATION));

        }catch (Exception ex){
            System.out.println(ex);
        }

    }

}
