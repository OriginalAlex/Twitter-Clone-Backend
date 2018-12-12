package io.github.originalalex.twitter.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HashUtils {

    private static final String SALT = "SUYdgfydfhg idhfgq394sdfghdsfgs34t";

    public static String SHA256Hash(String input) {
        String saltedInput = input + "-" + SALT;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(saltedInput.getBytes(StandardCharsets.UTF_8));
            String decoded = Base64.getEncoder().encodeToString(hash);
            System.out.println(decoded);
            return decoded;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
