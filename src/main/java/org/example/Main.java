package org.example;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String host = "";
        String port = "18828";
        String databaseName = "defaultdb";
        String userName = "";
        String password = "";

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + databaseName + "?sslmode=require", userName, password);

            // Create a statement, which allows you to run queries
            Statement stmt = conn.createStatement();

            // Run a create table query
            stmt.execute("CREATE TABLE IF NOT EXISTS users ("
                                  + "id INT AUTO_INCREMENT PRIMARY KEY, "
                                  + "name VARCHAR(50) NOT NULL, "
                                  + "age INT NOT NULL)");

            // Run an insert query
            stmt.executeUpdate("INSERT INTO users (name, age) VALUES ('Alice', 30)");

            // Execute a query
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            // Process the results, a loop is useful when it's multiple results. In our case, it's just one result.
            while (rs.next()) {
                String name = rs.getString("name");
                Integer age = rs.getInt("age");
                System.out.println("Name: " + name + ", Age: " + age);
            }

            // Close resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}