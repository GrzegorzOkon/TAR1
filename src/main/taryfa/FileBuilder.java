package taryfa;

import org.apache.poi.POIDocument;

public interface FileBuilder {
    default void buildFile() {};
    default void buildSheet(String sheetName) {};
    default void buildRow(Object[] data) {};
    default POIDocument getFile() { return null; };
}
