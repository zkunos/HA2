package edu.corvinus.ha2;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helpers {
    private final static String SALT = "NT2022";

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            input += SALT;

            byte[] messageDigest = md.digest(input.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getDayText() {
        return new SimpleDateFormat("EEEE").format(new Date()).toLowerCase();
    }

    public static String getDate() {
        return new SimpleDateFormat("yyyy.MM.dd").format(new Date());
    }
}