package ma.browser.enset.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface Dao<T>{
    T findOne(T o);
    T insert(T o);
    boolean delete(T o);
    T update(T o);
}
