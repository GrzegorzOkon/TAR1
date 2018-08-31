package taryfa;

import connection.ReadingResult;
import connection.SybaseConnection;
import org.apache.poi.POIDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.Map;
import java.util.TreeMap;

public class TAR1 {
    Map<String, Object[]> data;

    public TAR1() {
        data = new TreeMap<String, Object[]>();
    }

    public static void main(String[] args) {
        TAR1 tar1 = new TAR1();
        ExcelFileBuilder fileBuilder = new ExcelFileBuilder();
        TAR1NameBuilder nameBuilder = new TAR1NameBuilder();

        SybaseConnection connectionToCelina = new SybaseConnection();
        Connection activeConnection = connectionToCelina.connectToDatabase();

        // Wykonanie zapytania.
        ReadingResult ReadingTTCentr = new ReadingResult();
        tar1.data = ReadingTTCentr.executeQuery(activeConnection);

        tar1.createTaricFile(fileBuilder);
        HSSFWorkbook file = fileBuilder.getFile();

        tar1.createTaricFileName(nameBuilder, file);
        String name = nameBuilder.getName();

        tar1.saveFile(file, name);
    }

    public void createTaricFile(FileBuilder builder) {
        builder.buildFile();
        builder.buildSheet("TT_centr");

        for(Object[] row : data.values()) {
            builder.buildRow(row);
        }
    }

    public void createTaricFileName(NameBuilder builder, POIDocument document) {
        builder.buildFirstPartName();
        builder.buildSplitPartName();
        builder.buildSecondPartName();
        builder.buildSplitPartName();
        builder.buildThirdPartName(document);
        builder.buildExtensionPartName();
    }

    public void saveFile(HSSFWorkbook file, String fileName) {
        try (FileOutputStream out = new FileOutputStream(new java.io.File(fileName))) {
            file.write(out);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}