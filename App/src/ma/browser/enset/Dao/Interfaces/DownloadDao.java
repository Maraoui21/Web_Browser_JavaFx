package ma.browser.enset.Dao.Interfaces;

import ma.browser.enset.Dao.Dao;
import ma.browser.enset.Dao.Entities.DownloadHistory;
import ma.browser.enset.Dao.Entities.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DownloadDao extends Dao<DownloadHistory> {
    Boolean insertHistory(DownloadHistory o);
    ArrayList<DownloadHistory> findByUser(User user);
}
