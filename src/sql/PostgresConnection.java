package sql;//package sql;

import java.sql.*;
import java.util.*;

public class PostgresConnection {

    private static String serveur = "localhost";
    private static String baseName = "projet";
    private static String user = "thomas";
    private static String password = "postgres";

    private static Connection connection;

    public static Connection getInstance() {

        if (connection == null) {
            String url = "jdbc:postgresql://localhost/projet";
            Properties props = new Properties();
            props.setProperty("user","thomas");
            props.setProperty("password","postgres");
            try {
                connection = DriverManager.getConnection(url, props);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return connection;
    }
}