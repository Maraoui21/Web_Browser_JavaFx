package ma.browser.enset.Services;

import ma.browser.enset.Dao.Entities.User;
import ma.browser.enset.Dao.Implementation.UserDaoImpl;

public interface UserService {
    User findOne(User user);
    User AddOne(User user);
}
