package ma.browser.enset.Dao.Implementation;

import ma.browser.enset.Dao.Entities.User;
import ma.browser.enset.Dao.Interfaces.UserDao;
import ma.browser.enset.Dao.SingletoConnexionDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public User findOne(User user){
       try {
           Connection con = SingletoConnexionDb.getConnection();
           PreparedStatement query = con.prepareStatement("SELECT * FROM USERS WHERE Email = ? AND Password = ?");
           query.setString(1,user.getEmail());
           query.setString(2,user.getPassword());
           ResultSet QueryResult = query.executeQuery();
           User fUser = new User();
           while (QueryResult.next()){
               fUser.setId(QueryResult.getInt("id"));
               fUser.setFullName(QueryResult.getString("Full_Name"));
               fUser.setEmail(QueryResult.getString("Email"));
               fUser.setPassword(QueryResult.getString("Password"));
           }
           return fUser;
       }catch (SQLException e){
           e.getStackTrace();
       }
       return null;
    }
    @Override
    public User insert(User user) {
        return null;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User findById(int Id) {
        try {
            Connection con = SingletoConnexionDb.getConnection();
            PreparedStatement query = con.prepareStatement("SELECT * FROM USERS WHERE id= ?");
            query.setInt(1,Id);
            ResultSet QueryResult = query.executeQuery();
            User fUser = new User();
            while (QueryResult.next()){
                fUser.setId(QueryResult.getInt("id"));
                fUser.setFullName(QueryResult.getString("Full_Name"));
                fUser.setEmail(QueryResult.getString("Email"));
                fUser.setPassword(QueryResult.getString("Password"));
            }
            return fUser;
        }catch (SQLException e){
            e.getStackTrace();
        }
        return null;
    }
}
