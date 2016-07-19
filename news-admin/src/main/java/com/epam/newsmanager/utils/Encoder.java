package com.epam.newsmanager.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Create password using MD5 hash algorithm
 */
public class Encoder {

    private static String MD5 = "MD5";

    /**
     * Gets hash with ingoing algorithm, forms hex string and returns it.
     *
     * @param input    text in plain format
     * @param hashType MD5 OR SHA1
     * @return hash in hashType
     */
    private static String getHash(String input, String hashType) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(hashType);
        messageDigest.update(input.getBytes(), 0, input.length());

        byte[] byteData = messageDigest.digest();

        StringBuffer hexString = new StringBuffer();
        //convert byte format to hex format
        for (byte oneByte : byteData) {
            hexString.append(Integer.toHexString(0xFF & oneByte));
        }
        return hexString.toString();
    }

    /**
     * Get hash according to md5 algorithm
     *
     * @param input
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String getMD5HashString(String input) throws NoSuchAlgorithmException {
        return Encoder.getHash(input, MD5);
    }

}
