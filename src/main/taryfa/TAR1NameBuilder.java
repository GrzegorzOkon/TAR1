package taryfa;

import org.apache.poi.POIDocument;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TAR1NameBuilder implements NameBuilder {
    private StringBuilder currentName = new StringBuilder();

    @Override
    public void buildFirstPartName() {
        currentName.append("taryfa");
    }

    @Override
    public void buildSecondPartName() {
        Date currentTime = new Date();
        SimpleDateFormat customizedDateFormat = new SimpleDateFormat("yyyyMMdd");
        String customizedDate = customizedDateFormat.format(currentTime);

        currentName.append(customizedDate);
    }

    @Override
    public void buildThirdPartName(POIDocument document) {
        if (document == null) {
            throw new IllegalArgumentException();
        }

        StringBuilder hash = new StringBuilder("");

        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            document.write(bos);
            byte[] bytes = bos.toByteArray();

            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(bytes);

            byte[] mdbytes = md.digest();

            for (int i = 0; i < mdbytes.length; i++) {
                hash.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        currentName.append(hash);
    }

    @Override
    public void buildSplitPartName() {
        currentName.append("_");
    }

    @Override
    public void buildExtensionPartName() {
        currentName.append(".xls");
    }

    @Override
    public String getName() {
        return currentName.toString();
    }
}