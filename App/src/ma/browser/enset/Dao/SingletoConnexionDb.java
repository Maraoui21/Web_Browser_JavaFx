package ma.browser.enset.Dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletoConnexionDb {
    private static Connection con = null;
    static {
        String url = "jdbc:mysql:// localhost:3306/browser";
        String user = "mara8";
        String pass = "mara8";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return con;
    }
}
