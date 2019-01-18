package com.hello.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具类
 * @Title: EncryptUtil
 * @Auther: Michael
 * @Date: 2018/11/16
 */
public class EncryptUtil {

    private final static Logger logger = LoggerFactory.getLogger(EncryptUtil.class);
    /**
     * 全局数组
     */
    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    /**
     * 将摘要信息转化为相应的编码
     * @param code 编码类型
     * @param data 摘要信息
     * @return 相应编码后的字符串
     */
    public static String encrypt(String code, String data) {
        String encode = null;
        try {
            MessageDigest md = MessageDigest.getInstance(code);
            md.update(data.getBytes());
            encode = byteArrayToHexString(md.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return encode;
    }

    /**
     * 将摘要信息转换成MD5编码
     * @param data 摘要信息
     * @return MD5编码之后的字符串
     */
    public static String encryptMD5(String data) {
        return encrypt("MD5", data);
    }

    /**
     * 将摘要信息转换成SHA编码
     * @param data 摘要信息
     * @return SHA编码之后的字符串
     */
    public static String encryptSHA(String data) {
        return encrypt("SHA", data);
    }

    /**
     * 将摘要信息转换成SHA-256编码
     * @param data 摘要信息
     * @return SHA-256编码之后的字符串
     */
    public static String encryptSHA256(String data) {
        return encrypt("SHA-256", data);
    }

    /**
     * 将摘要信息转换成SHA-512编码
     * @param data 摘要信息
     * @return SHA-512编码之后的字符串
     */
    public static String encryptSHA512(String data) {
        return encrypt("SHA-512", data);
    }

    /**
     * 转换字节数组为十六进制字符串
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    private static String byteArrayToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for(byte b: bytes) {
            sb.append(byteToHexString(b));
        }
        return sb.toString();
    }
    /**
     * 将一个字节转化成十六进制形式的字符串
     * @param b 字节数组
     * @return 字符串
     */
    private static String byteToHexString(byte b) {
        int ret = b;
        if (ret < 0) ret += 256;
        int m = ret / 16;
        int n = ret % 16;
        return hexDigits[m] + hexDigits[n];
    }
}
