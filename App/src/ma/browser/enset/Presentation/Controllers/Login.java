package ma.browser.enset.Presentation.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ma.browser.enset.Dao.Entities.User;
import ma.browser.enset.Dao.Implementation.UserDaoImpl;
import ma.browser.enset.Services.Implimentation.UserServiceImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {
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
    public void LoginEvent() {
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
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sommething wrong");
            alert.setContentText("Password ou l'email est incorrect");
            alert.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        passwordField.setOnKeyPressed((new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)){
                    LoginEvent();
                }
            }
        }));
    }
}
