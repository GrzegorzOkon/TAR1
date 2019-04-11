package tariff;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelFileBuilder implements FileBuilder {
    private HSSFWorkbook currentFile;

    @Override
    public void buildFile() {
        currentFile = new HSSFWorkbook();
    }

    @Override
    public void buildSheet(String sheetName) {
        currentFile.createSheet(sheetName);
    }

    @Override
    public void buildRow(Object[] data) {
        Row row = getFirstSheet().createRow(getRowCount());
        int cellNum = 0;

        for(Object obj : data) {
            Cell cell = row.createCell(cellNum++);

            if(obj instanceof String)
                cell.setCellValue((String)obj);
            else if(obj instanceof Integer)
                cell.setCellValue((Integer)obj);
        }
    }

    @Override
    public HSSFWorkbook getFile() { return currentFile; }

    private HSSFSheet getFirstSheet() { return currentFile.getSheetAt(0); }

    private int getRowCount() { return getFirstSheet().getPhysicalNumberOfRows(); }
}