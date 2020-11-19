package ru.eatthefrog.hatterBot.MD5StringHasher;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5StringHasher {
    MessageDigest messageDigest = null;

    @PostConstruct
    void getMD5Instance() {

        try {
            messageDigest = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String getHash(String string) {

        if (messageDigest == null)
            return null;

        messageDigest.reset();

        messageDigest.update(string.getBytes());

        byte[] digest = messageDigest.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        String hashtext = bigInt.toString(16);
        while(hashtext.length() < 32 ){
            hashtext = "0" + hashtext;
        }

        return hashtext;
    }
}
