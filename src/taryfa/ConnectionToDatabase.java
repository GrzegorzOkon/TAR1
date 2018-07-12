package taryfa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class ConnectionToDatabase {
    private String db_ip;
    private String db_port;
    private String db_user;
    private String db_pswd;
    private String URL;
    private Properties LoginInformation;
    private Connection ActiveConnection;

    Connection connectToDatabase() {
        db_ip = "xx.xx.xx.xx";
        db_port = "yyyy";
        db_user = "aa";
        db_pswd = "bb";
        URL = "jdbc:sybase:Tds:" + db_ip + ":" + db_port + "/TT_Centr";
        LoginInformation = new Properties();
        LoginInformation.put( "user", db_user );
        LoginInformation.put( "password", db_pswd);

        try {
            ActiveConnection = DriverManager.getConnection(URL, LoginInformation);
        } catch (SQLException e) {
        }

        return ActiveConnection;
    }
}
