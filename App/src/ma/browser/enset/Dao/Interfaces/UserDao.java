package ma.browser.enset.Dao.Interfaces;

import ma.browser.enset.Dao.Entities.User;
import ma.browser.enset.Dao.Dao;

public interface UserDao extends Dao<User> {
    User findById(int Id);
}
