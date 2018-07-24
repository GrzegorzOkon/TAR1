package taryfa;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class File {
    private String fileNamePrefix;
    private String fileNameTimeStamp;
    private String fileNameHash;
    private String fileNameExtension;

    public File() {
        fileNamePrefix = "taryfa";
        fileNameTimeStamp = "_" + getCurrentTimeStamp() + "_";
        fileNameExtension = ".xls";
    }

    public String getFileNamePrefix() {
        return fileNamePrefix;
    }

    public void setFileNamePrefix(String fileNamePrefix) {
        this.fileNamePrefix = fileNamePrefix;
    }

    public String getFileNameTimeStamp() {
        return fileNameTimeStamp;
    }

    public void setFileNameTimeStamp(String fileNameTimeStamp) {
        this.fileNameTimeStamp = fileNameTimeStamp;
    }

    public String getFileNameHash() {
        return fileNameHash;
    }

    public void setFileNameHash(String fileNameHash) {
        this.fileNameHash = fileNameHash;
    }

    public String getFileNameExtension() {
        return fileNameExtension;
    }

    public void setFileNameExtension(String fileNameExtension) {
        this.fileNameExtension = fileNameExtension;
    }

    public String getFileName() {
        return "" + getFileNamePrefix() + getFileNameTimeStamp() + getFileNameExtension();
    }

    public String getFileNameWithHash() {
        return "" + getFileNamePrefix() + getFileNameTimeStamp() + getFileNameHash() + getFileNameExtension();
    }

    public void saveFile(Excel excel) {
        try (FileOutputStream out = new FileOutputStream(new java.io.File(getFileName()))) {
            excel.getWorkbook().write(out);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void saveFile(Excel excel, String hash) {
        setFileNameHash(hash);

        try (FileOutputStream out = new FileOutputStream(new java.io.File(getFileNameWithHash()))) {
            excel.getWorkbook().write(out);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String getCurrentTimeStamp() {
        Date CurrentTime = new Date();
        SimpleDateFormat CustomizedDateFormat = new SimpleDateFormat("yyyyMMdd");
        String CustomizedDate = CustomizedDateFormat.format(CurrentTime);
        return CustomizedDate;
    }
}