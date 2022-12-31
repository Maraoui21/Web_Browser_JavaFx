package ma.browser.enset.Services;

import ma.browser.enset.Dao.Entities.History;
import ma.browser.enset.Dao.Entities.User;
import ma.browser.enset.Dao.Implementation.UserDaoImpl;

import java.util.ArrayList;

public interface HistoryService {
    History addOne(History history);
    ArrayList<History> findAll(User user);
}
