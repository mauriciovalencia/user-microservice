package com.challenge.user.unit.utils;

import com.challenge.user.utils.PasswordUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class PasswordUtilsTests {

    private Logger log = LoggerFactory.getLogger(PasswordUtilsTests.class);

    @Test
    public void test_password() {

        log.info("test_password");
        {
            Assert.assertEquals("should be true with 'Agent07' param", PasswordUtils.isValidPassword("Agent07"), true);
        }
        {
            Assert.assertEquals("should be true with 'AZazaz00' param", PasswordUtils.isValidPassword("AZazaz00"), true);
        }
        {
            Assert.assertEquals("should be false with 'hunter2' param", PasswordUtils.isValidPassword("hunter2"), false);
        }
        {
            Assert.assertEquals("should be false with null param", PasswordUtils.isValidPassword(null), false);
        }
        {
            Assert.assertEquals("should be false with '' param", PasswordUtils.isValidPassword(""), false);
        }
        {
            Assert.assertEquals("should be false with ' ' param", PasswordUtils.isValidPassword(" "), false);
        }
        log.info("test_password end");
    }

    @Test
    public void test_token() {

        log.info("test_token");
        String text_for_token = "is_a_test";
        Integer text_for_token_length = 46;
        Integer token_length = 291;
        Integer token_lenght_when_is_null = 218;

        {
            Integer tokenLength = PasswordUtils.setTokenableString(text_for_token).length();
            Assert.assertEquals(
                    "should be true with " + text_for_token_length + " and " + text_for_token + " param",
                    tokenLength,
                    text_for_token_length);
        }
        {
            Integer tokenLength = PasswordUtils.setJWTToken(PasswordUtils.setTokenableString(text_for_token)).length();
            Assert.assertEquals(
                    "should be true with " + token_length + " and tokenized " + text_for_token + " param",
                    tokenLength,
                    token_length);
        }
        {
            Integer tokenLength = PasswordUtils.setJWTToken(null).length();
            Assert.assertEquals(
                    "should be true with " + token_lenght_when_is_null + " and tokenized " + text_for_token + " param",
                    tokenLength,
                    token_lenght_when_is_null);
        }
        log.info("test_token end");

    }

}
