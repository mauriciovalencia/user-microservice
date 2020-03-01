package com.challenge.user.unit.utils;

import com.challenge.user.utils.MailUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class MailUtilsTests {

    private Logger log = LoggerFactory.getLogger(MailUtilsTests.class);

    @Test
    public void test_email() {

        log.info("test_email");
        {
            Assert.assertEquals("should be true with 'mail@mail.com' param", MailUtils.isValidEmail("mail@mail.com"), true);
        }
        {
            Assert.assertEquals("should be false with null param", MailUtils.isValidEmail(null), false);
        }
        {
            Assert.assertEquals("should be false with '' param", MailUtils.isValidEmail(""), false);
        }
        {
            Assert.assertEquals("should be false with ' ' param", MailUtils.isValidEmail(" "), false);
        }
        {
            Assert.assertEquals("should be false with '@' param", MailUtils.isValidEmail("@"), false);
        }
        {
            Assert.assertEquals("should be false with '@.com' param", MailUtils.isValidEmail("@.com"), false);
        }
        {
            Assert.assertEquals("should be false with 'mail@.com' param", MailUtils.isValidEmail("mail@.com"), false);
        }
        log.info("test_email end");
    }

}
