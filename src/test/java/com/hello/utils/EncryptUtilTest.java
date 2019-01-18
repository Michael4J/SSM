package com.hello.utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title: EncryptUtilTest
 * @Auther: Michael
 * @Date: 2018/11/16
 */
public class EncryptUtilTest {

    private final static Logger logger = LoggerFactory.getLogger(EncryptUtilTest.class);

    @Test
    public void encryptMD5Test() {
        logger.info(EncryptUtil.encryptMD5("1"));
    }

    @Test
    public void encryptSHATest() {
        logger.info(EncryptUtil.encryptSHA("1"));
    }
}
