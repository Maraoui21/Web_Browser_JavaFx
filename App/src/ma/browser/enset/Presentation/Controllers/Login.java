package ma.browser.enset.Presentation.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ma.browser.enset.Dao.Entities.User;
import ma.browser.enset.Dao.Implementation.UserDaoImpl;
import ma.browser.enset.Services.Implimentation.UserServiceImpl;

public class Login {
    static User LoggedUser = null;
    @FXML
    public TextField emailField;
    @FXML
    public PasswordField passwordField;
    public Tab LoginTab;

    public UserServiceImpl UserService(){
        UserDaoImpl Udao = new UserDaoImpl();
        return new UserServiceImpl(Udao);
    }
    public void Login(ActionEvent actionEvent) {
        User user = new User();
        user.setEmail(emailField.getText());
        user.setPassword(passwordField.getText());
        User toFind = UserService().findOne(user);
        if(toFind.equals(user)){
            LoggedUser = toFind;
            LoginTab.getTabPane().getTabs().remove(LoginTab);
            System.out.println("Logged !");
        }else{
            // alert password or email is incorrect
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.WARNING);
        }
    }
}
