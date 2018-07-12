package taryfa;

import org.apache.poi.hssf.usermodel.*;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import java.io.*;
import java.sql.Connection;
import java.text.*;
import java.security.*;
import java.util.TreeMap;

public class TaryfaMain {
    public static void main(String[] args) {
        HSSFWorkbook workbook = new HSSFWorkbook(); //utworzenie czystego skoroszytu
        HSSFSheet sheet = workbook.createSheet("TT_centr"); //utworzenie nowego arkusza
        Map<String, Object[]> data = new TreeMap<String, Object[]>(); //te dane potrzebują być zapisane w Object[]

        ConnectionToDatabase ConnectionToCelina = new ConnectionToDatabase();
        Connection ActiveConnection = ConnectionToCelina.connectToDatabase();

        // Wykonanie zapytania.
        ReadingResult ReadingTTCentr = new ReadingResult();
        data = ReadingTTCentr.executeQuery(ActiveConnection);

        // Iterowanie pobranych danych i zapisanie do arkusza.
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset) {
            Row row = sheet.createRow(rownum++);

            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr)
            {
                Cell cell = row.createCell(cellnum++);
                if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
        try {
            // Zmienne do przechowywania nazw plików.
            String part1 = "taryfa";
            String part2 = "_" + getCurrentTimeStamp();
            String part3 = "";
            String part4 = ".xls";

            // Utworzenie pliku xls z danymi bez hasha w nazwie.
            FileOutputStream out = new FileOutputStream(new File(part1 + part2 + part4));
            workbook.write(out);
            out.close();

            // Stworzenie hasha dla pliku.
            part3 = "_" + hashFile(part1 + part2 + part4);

            // Ponowne utworzenie kolejnego pliku z hashem.
            out = new FileOutputStream(new File(part1 + part2 + part3 + part4));
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Bieżąca data jest podana w formacie 20151010.
     */
    private static String getCurrentTimeStamp() {
        Date CurrentTime = new Date();
        SimpleDateFormat CustomizedDateFormat = new SimpleDateFormat("yyyyMMdd");
        String CustomizedDate = CustomizedDateFormat.format(CurrentTime);
        return CustomizedDate;
    }

    // Metoda hashuje plik dyskowy, któego nazwa przesłąna jest parametrem
    private static String hashFile(String file) {
        String datafile = file;
        StringBuffer sb = new StringBuffer("");

        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            FileInputStream fis = new FileInputStream(datafile);
            byte[] dataBytes = new byte[1024];

            int nread = 0;

            while ((nread = fis.read(dataBytes)) != -1) {
                md.update(dataBytes, 0, nread);
            }

            byte[] mdbytes = md.digest();

            for (int i = 0; i < mdbytes.length; i++) {
                sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
            }
        } catch (Exception e) {}

        return sb.toString();
    }
}
