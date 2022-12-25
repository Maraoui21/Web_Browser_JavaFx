package ma.browser.enset.Dao.Entities;

import ma.browser.enset.Dao.Dao;
import ma.browser.enset.Dao.SingletoConnexionDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class History{
    private String url;
    private String Date;
    private int userID;

    // SETTERS AND GETTERS
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

}
