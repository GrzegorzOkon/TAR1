package taryfa;

public interface FileBuilder {
    default void buildFile() {};
    default void buildSheet(String sheetName) {};
    default void buildRow(Object[] data) {};
    default TAR1 getFile() {return null;};
}
