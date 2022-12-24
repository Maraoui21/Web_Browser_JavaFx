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
import ma.browser.enset.Dao.SingletoConnexionDb;
import ma.browser.enset.Presentation.Controllers.BrowserController;

public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        SingletoConnexionDb single;

        TabPane root = FXMLLoader.load(getClass().getResource("view/Browser.fxml"));

        // access controller functions
        FXMLLoader fxmlLoader = new FXMLLoader();
        TabPane p = fxmlLoader.load(getClass().getResource("view/Browser.fxml").openStream());
        BrowserController browserController = fxmlLoader.getController();


        primaryStage.setTitle("Project");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("Style/Browser.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);


        // to handle enter click
        scene.setOnKeyPressed(e->{
            if( e.getCode() == KeyCode.ENTER ) {
                browserController.visite();
            }
        });
        primaryStage.show();

    }
}