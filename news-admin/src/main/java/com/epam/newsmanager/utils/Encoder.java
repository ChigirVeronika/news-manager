package com.epam.newsmanager.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Create password using MD5 hash algorithm
 */
public class Encoder {
    /**
     * @param text,    text in plain format
     * @param hashType MD5 OR SHA1
     * @return hash in hashType
     */
    public static String getHash(String text, String hashType) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(hashType);
        byte[] array = md.digest(text.getBytes());
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            stringBuffer.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return stringBuffer.toString();

    }

    public static String md5(String txt) throws NoSuchAlgorithmException {
        return Encoder.getHash(txt, "MD5");
    }

    public static String sha1(String txt) throws NoSuchAlgorithmException {
        return Encoder.getHash(txt, "SHA1");
    }
}
