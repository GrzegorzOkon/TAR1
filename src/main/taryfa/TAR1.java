package taryfa;

import connection.ReadingResult;
import connection.SybaseConnection;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class TAR1 {
    private String fileNamePrefix;
    private String fileNameTimeStamp;
    private String fileNameHash;
    private String fileNameExtension;

    public TAR1() {
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

    public static void main(String[] args) {
        Map<String, Object[]> data = new TreeMap<String, Object[]>();

        SybaseConnection connectionToCelina = new SybaseConnection();
        Connection activeConnection = connectionToCelina.connectToDatabase();

        // Wykonanie zapytania.
        ReadingResult ReadingTTCentr = new ReadingResult();
        data = ReadingTTCentr.executeQuery(activeConnection);

        HSSFWorkbook file;
        TAR1 tar1 = new TAR1();
        ExcelFileBuilder builder = new ExcelFileBuilder();

        tar1.createTaricFile(builder);



        excel.saveAsExcel(data);
        file.saveFile(excel);
        file.saveFile(excel, Hash.createSHA1(file.getFileName()));
    }

    public void createTaricFile(FileBuilder builder) {
        builder.buildFile();
        builder.buildSheet("TT_centr");


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