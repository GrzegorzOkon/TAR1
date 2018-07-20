package taryfa;

import connection.ReadingResult;
import connection.SybaseConnection;
import org.apache.poi.hssf.usermodel.*;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import java.sql.Connection;
import java.util.TreeMap;

public class Excel {
    private HSSFWorkbook workbook;
    private HSSFSheet sheet;

    public Excel() {
        this.workbook = new HSSFWorkbook();  //utworzenie czystego skoroszytu
        this.sheet = workbook.createSheet("TT_centr");  //utworzenie nowego arkusza
    }

    public HSSFWorkbook getWorkbook() {
        return workbook;
    }

    public HSSFSheet getSheet() {
        return sheet;
    }

    public static void main(String[] args) {
        Excel excel = new Excel();
        Map<String, Object[]> data = new TreeMap<String, Object[]>();

        SybaseConnection connectionToCelina = new SybaseConnection();
        Connection activeConnection = connectionToCelina.connectToDatabase();

        // Wykonanie zapytania.
        ReadingResult ReadingTTCentr = new ReadingResult();
        data = ReadingTTCentr.executeQuery(activeConnection);

        excel.saveAsExcel(data);
        File.createFile(excel);
    }

    public void saveAsExcel(Map<String, Object[]> data) {
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
    }
}
