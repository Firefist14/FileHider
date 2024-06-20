package dao;

import db.MyConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public static boolean Exists(String email) throws SQLException, ClassNotFoundException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("select email from Users");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            String mail = rs.getString(1);
            if(mail.equals(email)){
                return true;
            }
        }
        return false;
    }
    public static int saveUser(User user) throws SQLException, ClassNotFoundException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("INSERT INTO Users values(default, ?, ?)");
        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());
        return ps.executeUpdate();
    }
}
