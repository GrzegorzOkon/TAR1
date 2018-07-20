package taryfa;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class File {
    public static void createFile(Excel excel) {
        try {
            // Zmienne do przechowywania nazw plików.
            String part1 = "taryfa";
            String part2 = "_" + getCurrentTimeStamp();
            String part3 = "";
            String part4 = ".xls";

            // Utworzenie pliku xls z danymi bez hasha w nazwie.
            FileOutputStream out = new FileOutputStream(new java.io.File(part1 + part2 + part4));
            excel.getWorkbook().write(out);
            out.close();

            part3 = "_" + Hash.createSHA1(part1 + part2 + part4);

            // Ponowne utworzenie kolejnego pliku z hashem.
            out = new FileOutputStream(new java.io.File(part1 + part2 + part3 + part4));
            excel.getWorkbook().write(out);
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
}
