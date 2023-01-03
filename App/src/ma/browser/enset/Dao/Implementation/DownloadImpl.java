package ma.browser.enset.Dao.Implementation;

import ma.browser.enset.Dao.Entities.DownloadHistory;
import ma.browser.enset.Dao.Entities.History;
import ma.browser.enset.Dao.Entities.User;
import ma.browser.enset.Dao.Interfaces.DownloadDao;
import ma.browser.enset.Dao.SingletoConnexionDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DownloadImpl implements DownloadDao {
    @Override
    public Boolean insertHistory(DownloadHistory o){
        try {
            Connection con = SingletoConnexionDb.getConnection();
            PreparedStatement Query = con.prepareStatement("INSERT INTO `download`(`id`, `filename`, `url`, `Date_Down`, `useId`) VALUES (null,?,?,?,?)");
            Query.setString(1,o.getName());
            Query.setString(2,o.getUrl());
            Query.setString(3,o.getDate_Download());
            Query.setInt(4,o.getUser().getId());
            int QueryResult = Query.executeUpdate();
            return (QueryResult!=0);
        }catch (SQLException e){
            e.getStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<DownloadHistory> findByUser(User user) {
        try {
            Connection con = SingletoConnexionDb.getConnection();
            PreparedStatement query = con.prepareStatement("SELECT * from download where useId = ?");
            query.setInt(1,user.getId());
            ResultSet QueryResult = query.executeQuery();
            ArrayList<DownloadHistory> DownloadsHistory = new ArrayList<>();
            while (QueryResult.next()){
                DownloadHistory toAdd = new DownloadHistory();
                toAdd.setId(QueryResult.getInt("id"));
                toAdd.setName(QueryResult.getString("filename"));
                toAdd.setUrl(QueryResult.getString("url"));
                toAdd.setDate_Download("Date_Down");
                toAdd.setUser(new UserDaoImpl().findById(QueryResult.getInt("useId")));
                DownloadsHistory.add(toAdd);
            }
            return DownloadsHistory;
        }catch (SQLException e){
            e.getStackTrace();
        }
        return null;
    }

    @Override
    public DownloadHistory findOne(DownloadHistory o) {
        return null;
    }

    @Override
    public DownloadHistory insert(DownloadHistory o) {
        return null;
    }

    @Override
    public boolean delete(DownloadHistory o) {
        return false;
    }

    @Override
    public DownloadHistory update(DownloadHistory o) {
        return null;
    }


}
