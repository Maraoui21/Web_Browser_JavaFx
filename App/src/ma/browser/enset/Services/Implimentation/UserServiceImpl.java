package ma.browser.enset.Services.Implimentation;

import ma.browser.enset.Dao.Entities.User;
import ma.browser.enset.Dao.Implementation.UserDaoImpl;
import ma.browser.enset.Services.UserService;

public class UserServiceImpl implements UserService {
    UserDaoImpl Udao;
    public UserServiceImpl(UserDaoImpl Udao){
        this.Udao=Udao;
    }
    @Override
    public User findOne(User user) {
        return Udao.findOne(user);
    }

    @Override
    public User AddOne(User user) {
        return Udao.insert(user);
    }
}
