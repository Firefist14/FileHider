package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    public static Connection connection;
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/FileHider?useSSL=false","root", "watch@dogs*24");
        System.out.println("Connection Established");
        return connection;
    }

    public static void closeConnection(){
        try{
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
