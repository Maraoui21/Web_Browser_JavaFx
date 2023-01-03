package ma.browser.enset.Dao.Implementation;

import ma.browser.enset.Dao.Entities.History;
import ma.browser.enset.Dao.Entities.User;
import ma.browser.enset.Dao.Interfaces.HistoryDao;
import ma.browser.enset.Dao.SingletoConnexionDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HistoryDaoImpl implements HistoryDao {

    // DAO METHODS
    public ArrayList<History> findAll(User user)  {
        try {
            Connection con = SingletoConnexionDb.getConnection();
            PreparedStatement query = con.prepareStatement("SELECT * from HISTORY where user = ?");
            query.setInt(1,user.getId());
            ResultSet QueryResult = query.executeQuery();
            ArrayList<History> HistoryList = new ArrayList<>();
            while (QueryResult.next()){
                History toAdd = new History();
                toAdd.setId(QueryResult.getInt("id"));
                toAdd.setUrl(QueryResult.getString("url"));
                toAdd.setDate(QueryResult.getString("date"));
                toAdd.setUserID(QueryResult.getInt("user"));
                HistoryList.add(toAdd);
            }
            return HistoryList;
        }catch (SQLException e){
            e.getStackTrace();
        }
        return null;
    }

    @Override
    public History findOne(History o) {
        return null;
    }

    @Override
    public History insert(History o){
        try {
            Connection con = SingletoConnexionDb.getConnection();
            PreparedStatement Query = con.prepareStatement("INSERT INTO `history`(`id`, `url`, `date`, `user`) VALUES (null,?,?,?)");
            Query.setString(1,o.getUrl());
            Query.setString(2,o.getDate());
            Query.setInt(3,o.getUserID());
            int QueryResult = Query.executeUpdate();
            return (QueryResult!=0)?o:null;
        }
        catch (SQLException e){
            e.getStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(History o) {
        return false;
    }

    @Override
    public History update(History o) {
        return null;
    }
}
