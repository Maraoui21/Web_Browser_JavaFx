package ma.browser.enset.Services.Implimentation;

import ma.browser.enset.Dao.Entities.History;
import ma.browser.enset.Dao.Entities.User;
import ma.browser.enset.Dao.Implementation.HistoryDaoImpl;
import ma.browser.enset.Services.HistoryService;

import java.util.ArrayList;

public class HistoryServiceImpl implements HistoryService {
    HistoryDaoImpl Hdoa;
    public HistoryServiceImpl(HistoryDaoImpl Hdao){
        this.Hdoa = Hdao;
    }
    @Override
    public History addOne(History history) {
        return Hdoa.insert(history);
    }

    @Override
    public ArrayList<History> findAll(User user) {
        return Hdoa.findAll(user);
    }
}
