package tariff;

import java.util.Properties;

public class ConnectionFactory {
    public JDBCConnection build(String url, Properties properties) {
        return new JDBCConnection(url, properties);
    }
}