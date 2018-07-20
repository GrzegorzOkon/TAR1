package taryfa;

import java.io.FileInputStream;
import java.security.MessageDigest;

public class Hash {
    public static String createSHA1(String fileName) {
        StringBuilder hash = new StringBuilder("");

        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            FileInputStream fis = new FileInputStream(fileName);
            byte[] dataBytes = new byte[1024];

            int nread = 0;

            while ((nread = fis.read(dataBytes)) != -1) {
                md.update(dataBytes, 0, nread);
            }

            byte[] mdbytes = md.digest();

            for (int i = 0; i < mdbytes.length; i++) {
                hash.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
            }
        } catch (Exception e) {}

        return hash.toString();
    }
}