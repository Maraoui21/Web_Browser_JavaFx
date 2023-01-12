package ma.browser.enset.Presentation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ma.browser.enset.Dao.Entities.History;
import ma.browser.enset.Dao.Entities.User;
import ma.browser.enset.Dao.Implementation.HistoryDaoImpl;
import ma.browser.enset.Dao.SingletoConnexionDb;
import ma.browser.enset.Presentation.Controllers.BrowserController;
import java.sql.Connection;
import java.util.ArrayList;


public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        TabPane root = FXMLLoader.load(getClass().getResource("view/Browser.fxml"));
        SingletoConnexionDb.getConnection();
        primaryStage.setTitle("JAVA Project");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("Style/Browser.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}