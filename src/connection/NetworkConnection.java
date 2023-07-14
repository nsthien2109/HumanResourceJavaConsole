package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NetworkConnection {

    public Connection conn;
    static Connection connection;
    private static String URL = "jdbc:mysql://localhost:3306/carrers";
    private static String PASSWORD = "Bame12345@";
    private static String USERNAME = "root";


    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Connection established successfully.");
            } catch (SQLException e) {
                System.out.println("Connection failed. Error: " + e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed successfully.");
            } catch (SQLException e) {
                System.out.println("Error closing connection. Error: " + e.getMessage());
            }
        }
    }

}
