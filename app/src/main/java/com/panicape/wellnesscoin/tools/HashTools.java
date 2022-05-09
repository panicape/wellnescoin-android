package com.panicape.wellnesscoin.tools;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * HashTools class
 *
 * @author panicape
 * @version 1.01 November 2020
 */
public class HashTools {

    /** Message digest component */
    private static MessageDigest md;

    // Methods

    /**
     *
     * @param original
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String getHash(String original) throws NoSuchAlgorithmException {
        md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(original.getBytes(StandardCharsets.UTF_8));

        return bytesToHex(hash);
    }

    /**
     *
     * @param bytes
     * @return
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
