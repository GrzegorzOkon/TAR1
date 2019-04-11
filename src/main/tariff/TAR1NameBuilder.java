package tariff;

import org.apache.poi.POIDocument;
import utils.Sha1Util;

import java.io.ByteArrayOutputStream;
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
            byte[] data = bos.toByteArray();

            hash.append(Sha1Util.sha1Hex(data));
        } catch (Exception e) {
            throw new ApiException(e);
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