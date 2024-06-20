package service;

import dao.UserDAO;
import model.User;

import java.sql.SQLException;

public class UserService {
    public static Integer saveUser(User user){
        try {
            if(UserDAO.Exists(user.getEmail())) {
                return 0;
            } else {
                return UserDAO.saveUser(user);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
