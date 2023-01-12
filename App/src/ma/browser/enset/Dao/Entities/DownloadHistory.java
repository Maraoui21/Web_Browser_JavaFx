package ma.browser.enset.Dao.Entities;

import java.sql.Date;

public class DownloadHistory {
    private int id;
    private String name;
    private String url;
    private String date_Download;
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate_Download() {
        return date_Download;
    }

    public void setDate_Download(String date_Download) {
        this.date_Download = date_Download;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
