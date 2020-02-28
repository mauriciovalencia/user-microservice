package com.challenge.user.services;

import com.challenge.user.database.entitites.PhoneEntity;
import com.challenge.user.database.entitites.UserEntity;
import com.challenge.user.database.repositories.PhoneRepository;
import com.challenge.user.database.repositories.UserRepository;
import com.challenge.user.exceptions.EmptyParamException;
import com.challenge.user.exceptions.ExistParamException;
import com.challenge.user.exceptions.InvalidParamException;
import com.challenge.user.models.Phone;
import com.challenge.user.models.User;
import com.challenge.user.models.requests.UserRequest;
import lombok.AccessLevel;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static com.challenge.user.utils.MailUtils.isValidEmail;
import static com.challenge.user.utils.PasswordUtils.isValidPassword;

@Service
public class UserServiceImpl implements UserService {

    private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource @Getter(AccessLevel.PROTECTED)
    private UserRepository userRepository;

    @Resource @Getter(AccessLevel.PROTECTED)
    private PhoneRepository phoneRepository;

    @Override
    public User createUser(UserRequest request) {

        // =============== Settings =============

        //Set Country Time Zone
        ZoneId ZCL = ZoneId.of("Chile/Continental");

        //Get Current Date-Time
        LocalDateTime currentDateTime = LocalDateTime.now(ZCL);

        // =============== Validations =============

        // is name null or empty
        if(null == request.getName() || request.getName().isEmpty()){
            throw new EmptyParamException("name");
        }

        // is email null or empty
        if(null == request.getEmail() || request.getEmail().isEmpty()){
            throw new EmptyParamException("email");
        }

        // is valid email
        if(!isValidEmail(request.getEmail())){
            throw new InvalidParamException("email "+ request.getEmail(),"");
        }

        // is email exist?
        if(this.isEmailExist(request.getEmail())){
            throw new ExistParamException("e-mail "+request.getEmail(),"dataBase");
        }

        // is password null or empty
        if(null == request.getPassword() || request.getPassword().isEmpty()){
            throw new EmptyParamException("password");
        }

        if(!isValidPassword(request.getPassword())){
            throw new InvalidParamException("password "+ request.getPassword(), "it has to be this pattern in this order: " +
                    "1 UpperCase Character(A-z) " +
                    "2 or more LowerCase Character (a-z) " +
                    "and 2 numbers (0-9) like this AZazaz00");
        }

        // is phone array null or empty?
        if(null == request.getPhones() || request.getPhones().isEmpty()){
            throw new EmptyParamException("phones");
        }

        // =============== Create Records =============

        //save user
        UserEntity newUser = new UserEntity();
        newUser.setName(request.getName());
        newUser.setPassword(request.getPassword());
        newUser.setEmail(request.getEmail());
        newUser.setCreatedAt(currentDateTime);
        newUser.setUpdatedAt(currentDateTime);
        newUser.setIsActive(true);
        newUser.setLastLogin(currentDateTime);

        User userResult = User.entityToUser(getUserRepository().save(newUser));
        Long userId = userResult.getId();

        //save phones from list
        List<Phone> phones = request.getPhones();
        List<Phone> phonesResult = new ArrayList();
        for (int i = 0; i < phones.size(); i++) {

            PhoneEntity newPhone = new PhoneEntity();
            newPhone.setUserId(userId);
            newPhone.setNumber(phones.get(i).getNumber());
            newPhone.setContrycode(phones.get(i).getCountrycode());
            newPhone.setCitycode(phones.get(i).getCitycode());
            newPhone.setCreatedAt(currentDateTime);
            newPhone.setUpdatedAt(currentDateTime);

            phonesResult.add(Phone.entityToPhone(getPhoneRepository().save(newPhone)));
        }

        // set phone list saved on userResult Object
        userResult.setPhones(phonesResult);

        return userResult;
    }

    @Override
    public Boolean isEmailExist(String email) {
        return getUserRepository().isEmailExist(email) == null ? false : true;
    }


}
