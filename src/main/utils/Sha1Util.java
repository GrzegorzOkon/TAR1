package utils;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;

import tariff.ApiException;

public class Sha1Util {
    public static String sha1Hex(byte[] data) {
        if (data != null) {
            try {
                MessageDigest sha1 = MessageDigest.getInstance("SHA1");
                byte[] digest = sha1.digest(data);

                return Hex.encodeHexString(digest);
            } catch (Exception e) {
                throw new ApiException(e);
            }
        }

        return null;
    }
}
