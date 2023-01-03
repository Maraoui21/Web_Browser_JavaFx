package ma.browser.enset.Services;

import ma.browser.enset.Dao.Entities.DownloadHistory;

import java.sql.SQLException;

public interface DownloadService {
    Boolean add(DownloadHistory add) throws SQLException;
}
