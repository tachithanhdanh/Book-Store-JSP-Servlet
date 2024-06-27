package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    public static Connection getConnection() {
        Connection con = null;
        try {
            // Step 1: Load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Initialize some variables
            String connectionUrl = "jdbc:mysql://localhost:3306/book_store";
            String username = "root";
            String password = "123456";

            // Step 2: Establish the connection
            con = DriverManager.getConnection(connectionUrl, username, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
        } catch (SQLException e) {
            System.out.println("Connection failed");
            System.out.println(e.getMessage());
        }
        return con;
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Connection close failed");
            System.out.println(e.getMessage());
        }
    }

    public static void printInfo(Connection con) {
        try {
            DatabaseMetaData metaData = con.getMetaData();
            System.out.println("Database name: " + con.getCatalog());
            System.out.println("Database URL: " + metaData.getURL());
            System.out.println("Database username: " + metaData.getUserName());
            System.out.println("Database product name: " + metaData.getDatabaseProductName());
            System.out.println("Database product version: " + metaData.getDatabaseProductVersion());1
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Connection is null");
        }
    }
}
