package tariff;

import java.io.FileOutputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.POIDocument;

public class TAR1App {

    private static final String DB_URL_TEMPLATE = "jdbc:sybase:Tds:%s:%s/TT_Centr";

    private final ConnectionFactory connectionfactory;
    private final FileBuilder fileBuilder;
    private final NameBuilder nameBuilder;

    public TAR1App() {
        this(new ConnectionFactory(), new ExcelFileBuilder(), new TAR1NameBuilder());
    }

    public TAR1App(ConnectionFactory connectionFactory, FileBuilder fileBuilder, NameBuilder nameBuilder) {
        this.connectionfactory = connectionFactory;
        this.fileBuilder = fileBuilder;
        this.nameBuilder = nameBuilder;
    }

    public static void main(String[] args) {
        TAR1App tar1 = new TAR1App();

        Map<String, Object[]> data = tar1.getTaricData("xx.xx.xx.xx", "xxxx", "xxxxxx", "xxxxxxx");

        tar1.createTaricFile(tar1.fileBuilder, data);
        POIDocument file = tar1.fileBuilder.getFile();

        tar1.createTaricFileName(tar1.nameBuilder, file);
        String name = tar1.nameBuilder.getName();

        tar1.saveFile(file, name);
    }

    public Map<String, Object[]> getTaricData(String ip, String port, String login, String password) {
        String requestUrl = String.format(DB_URL_TEMPLATE, ip, port);

        Properties properties = new Properties();
        properties.put("user", login );
        properties.put("password", password);

        try (JDBCConnection connection = connectionfactory.build(requestUrl, properties)) {
            return connection.response();
        }
    }

    public void createTaricFile(FileBuilder builder, Map<String, Object[]> data) {
        builder.buildFile();
        builder.buildSheet("TT_centr");

        for (Object[] row : data.values()) {
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

    public void saveFile(POIDocument file, String fileName) {
        try (FileOutputStream out = new FileOutputStream(new java.io.File(fileName))) {
            file.write(out);
        } catch (Exception e) {
            throw new ApiException(e);
        }
    }
}