package ma.browser.enset.Presentation.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DefaultTab implements Initializable {
    @FXML
    public TextField tfTitle1;
    public ProgressBar Loading1;
    public VBox verticalBox1;
    public HBox ToolsBox2;
    public Button LoginBtn2;
    @FXML
    private WebView webViewF1;
    @FXML
    private Tab currentTab;

    @FXML
    public void visite1() {
        String url = tfTitle1.getText();
        if(url.contains("www.") || url.contains("http://") || url.contains("https://")){
            BrowserController.saveHistory(url);
            webViewF1.getEngine().load(url);
            currentTab.setText(url);
        }else{
            BrowserController.saveHistory("https://www.google.com/search?q="+url);
            webViewF1.getEngine().load("https://www.google.com/search?q="+url);
            currentTab.setText(url);
        }
    }

    @FXML
    void back1(){
        webViewF1.getEngine().executeScript("window.history.back()");
    }

    @FXML
    void forward1(){
        webViewF1.getEngine().executeScript("window.history.forward()");
    }

    @FXML
    void refresh1(ActionEvent refresh){
        webViewF1.getEngine().executeScript("window.location.reload()");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(BrowserController.isLogged()){
            ToolsBox2.getChildren().remove(LoginBtn2);
        }
        webViewF1.getEngine().load("http://google.com");
        tfTitle1.setOnKeyPressed((new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)){
                    visite1();
                }
            }
        }));
        webViewF1.getEngine().locationProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldLink, String newLink) {
                if(newLink.endsWith(".pdf")){
                    try {
                        File file = new File("App/src/ma/browser/enset/Presentation/view/Download.fxml");
                        AnchorPane DownLoadUi = FXMLLoader.load(file.toURL());
                        Stage stage = new Stage();
                        Scene DownloadScene = new Scene(DownLoadUi);
                        stage.setScene(DownloadScene);
                        stage.setTitle("Download");
                        Download.Link = new URL(newLink);
                        stage.show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        updateUrl();
    }

    public void updateUrl(){
        webViewF1.getEngine().getLoadWorker().stateProperty().addListener((observable,oldVal,newVal)->{
            if(Worker.State.SUCCEEDED.equals(newVal)){
                Document doc = webViewF1.getEngine().getDocument();
                NodeList heads = doc.getElementsByTagName("head");
                // to get document title then add it to tab title
                if(heads.getLength()>0){
                    Element head = (Element)heads.item(0);
                    NodeList titles = head.getElementsByTagName("title");
                    if (titles.getLength() > 0) {
                        Node title = titles.item(0);
                        String titleText = title.getTextContent();
                        if(titleText.length()>15){
                            titleText=titleText.substring(0,15);
                        }
                        currentTab.setText(titleText);
                    }
                }
                // to change the url inside text-field
                BrowserController.saveHistory(webViewF1.getEngine().getLocation());
                tfTitle1.setText(webViewF1.getEngine().getLocation());
                // make loader empty
                Loading1.setProgress(0.0);
            }
            else {
                double x = Loading1.getProgress();
                Loading1.setProgress(x+=0.3);
            }
        });
    }

    public void LoginHandler(ActionEvent actionEvent) {
        try {
            Tab tab = new Tab("Login");
            File file = new File("App/src/ma/browser/enset/Presentation/view/Login.fxml");
            Tab toAdd = FXMLLoader.load(file.toURL());
            toAdd.setText("Login");
            TabPane tabPan = currentTab.getTabPane();
            tabPan.getTabs().add(tabPan.getTabs().size() - 1,toAdd);
            tabPan.getSelectionModel().select(tabPan.getTabs().size() - 2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void  openDonwloadFolder() {
        Desktop desktop = Desktop.getDesktop();
        try {
            String home = System.getProperty("user.home");
            desktop.open(new File(home+"\\Downloads\\Fx_Download"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void openDonwloadFolder(ActionEvent actionEvent) {
        openDonwloadFolder();
    }
    public void OpenHistory1(ActionEvent actionEvent) {
        BrowserController.GeneraleTabPan = currentTab.getTabPane();
        if(Login.LoggedUser!=null){
            try {
                File file = new File("App/src/ma/browser/enset/Presentation/view/History.fxml");
                AnchorPane HisoryUi = FXMLLoader.load(file.toURL());
                Stage stage = new Stage();
                Scene Historyscene = new Scene(HisoryUi);
                stage.setScene(Historyscene);
                stage.setTitle("Download");
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Please Login first !");
            alert.setContentText("l'authentification est obligé pour acceder a l'historique");
            alert.show();
        }
    }
}
