package karazin.parallelcomputing.indiv1.util;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DatabaseUtil {
    private static String url;
    private static String username;
    private static String password;
    private static String driverClassName;

    static {
        try (InputStream input = DatabaseUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find db.properties");
                throw new RuntimeException("db.properties not found in classpath");
            }
            prop.load(input);

            url = prop.getProperty("jdbc.url");
            username = prop.getProperty("jdbc.username");
            password = prop.getProperty("jdbc.password");
            driverClassName = prop.getProperty("jdbc.driverClassName");

            // Load the JDBC driver
            Class.forName(driverClassName);
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to initialize DatabaseUtil", ex);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

}