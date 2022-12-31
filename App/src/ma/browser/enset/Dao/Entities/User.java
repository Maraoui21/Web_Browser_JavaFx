package ma.browser.enset.Dao.Entities;

public class User {
    private int id;
    private String FullName;
    private String email;
    private String password;


    // SETTERS AND GETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        User user = (User) obj;
        return (user.email.equals(this.email) && user.password.equals(this.password));
    }
}
