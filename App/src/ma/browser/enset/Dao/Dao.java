package ma.browser.enset.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface Dao<T>{
    T findOne(T o) throws SQLException;
    T insert(T o) throws SQLException;
    boolean delete(T o) throws SQLException;
    T update(T o) throws SQLException;
}
