package ma.browser.enset.Services.Implimentation;

import ma.browser.enset.Dao.Entities.DownloadHistory;
import ma.browser.enset.Dao.Implementation.DownloadImpl;
import ma.browser.enset.Services.DownloadService;

import java.sql.SQLException;

public class DownloadHistoryServiceImp implements DownloadService {
    private DownloadImpl DDao;
    public DownloadHistoryServiceImp(DownloadImpl DDao){
        this.DDao = DDao;
    }
    @Override
    public Boolean add(DownloadHistory add){
        return DDao.insertHistory(add);
    }
}
