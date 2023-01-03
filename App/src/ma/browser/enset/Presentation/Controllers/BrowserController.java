package ma.browser.enset.Presentation.Controllers;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import ma.browser.enset.Dao.Entities.History;
import ma.browser.enset.Dao.Implementation.HistoryDaoImpl;
import ma.browser.enset.Services.Implimentation.HistoryServiceImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class BrowserController implements Initializable {
    public Tab firstTab;
    public ProgressBar Loading;
    public HBox HoriZontalBox;
    public Button refresh;
    public Button LoginBtn;
    public HBox ToolsBox;
    @FXML
    private TextField tfTitle;
    @FXML
    private Tab addNewTab;
    @FXML
    public WebView webViewF;
    public static TabPane GeneraleTabPan;
    @FXML
    public TabPane tabPan;
    public static Boolean isLogged(){
        return (Login.LoggedUser!=null);
    }
    public static HistoryServiceImpl HistoryService(){
        HistoryDaoImpl Hdoa = new HistoryDaoImpl();
        return new HistoryServiceImpl(Hdoa);
    }
    public static void saveHistory(String url){
        if(isLogged()){
            if(!url.equals("https://www.google.com/?gws_rd=ssl")){
                History toAdd = new History();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                Date today = new Date();
                toAdd.setUrl(url);
                toAdd.setDate(formatter.format(today));
                toAdd.setUserID(Login.LoggedUser.getId());
                HistoryService().addOne(toAdd);
            }
        }
    }
    @FXML
    public void visite() {
        String url = tfTitle.getText();
        if(url.contains("www.") || url.contains("http://") || url.contains("https://")) {
            saveHistory(url);
            webViewF.getEngine().load(url);
        }else{
            saveHistory("https://www.google.com/search?q="+url);
            webViewF.getEngine().load("https://www.google.com/search?q="+url);
        }
    }

    @FXML
    void back(){
        webViewF.getEngine().executeScript("window.history.back()");
    }

    @FXML
    void forward(){
        webViewF.getEngine().executeScript("window.history.forward()");
    }

    @FXML
    void refresh(ActionEvent refresh){
        webViewF.getEngine().executeScript("window.location.reload()");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        if(isLogged()){
            ToolsBox.getChildren().remove(LoginBtn);
        }
        // loading default url
        webViewF.getEngine().load("http://google.com");
        // handle enter click inside url textField
        tfTitle.setOnKeyPressed((new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)){
                    visite();
                }
            }
        }));
        // adding new tab
        addNewTab.setOnSelectionChanged(new EventHandler<Event>(){
            @Override
            public void handle(Event event){
                if(addNewTab.isSelected()){
                    try {
                        Tab tab = new Tab("New tab");
                        File file = new File("App/src/ma/browser/enset/Presentation/view/DefaultPage.fxml");
                        Tab toAdd = FXMLLoader.load(file.toURL());
                        toAdd.setText("New Tab");
                        tabPan.getTabs().add(tabPan.getTabs().size() - 1,toAdd);
                        tabPan.getSelectionModel().select(tabPan.getTabs().size() - 2);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });
        // handle webView changes like clicked url inside webView and put it in the textField
        webViewF.getEngine().getLoadWorker().stateProperty().addListener((observable,oldVal,newVal)->{
            if(Worker.State.SUCCEEDED.equals(newVal)){
                Document doc = webViewF.getEngine().getDocument();
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
                        firstTab.setText(titleText);
                    }
                }
                // to change the url inside text-field and save new navigation
                saveHistory(webViewF.getEngine().getLocation());
                tfTitle.setText(webViewF.getEngine().getLocation());
                // close tab
                tabPan.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
                // make loader empty
                Loading.setProgress(1.0);
                Loading.setProgress(0.0);
            }
            else {
                    double x = Loading.getProgress();
                    Loading.setProgress(x+=0.3);
            }
        });
        // handle download links
        webViewF.getEngine().locationProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldLink, String newLink) {
                if(newLink.endsWith(".pdf")){
                    try {
                        File file = new File("App/src/ma/browser/enset/Presentation/view/Download.fxml");
                        AnchorPane DownLoadUi = FXMLLoader.load(file.toURL());
                        Stage stage = new Stage();
                        Scene DownloadScene = new Scene(DownLoadUi);
//                        DownloadScene.getStylesheets().add(getClass().getResource("../Style/DownloadUi.css").toExternalForm());
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
    }


    public void LoginHandler(ActionEvent actionEvent) {
        try {
            Tab tab = new Tab("Login");
            File file = new File("App/src/ma/browser/enset/Presentation/view/Login.fxml");
            Tab toAdd = FXMLLoader.load(file.toURL());
            toAdd.setText("Login");
            tabPan.getTabs().add(tabPan.getTabs().size() - 1,toAdd);
            tabPan.getSelectionModel().select(tabPan.getTabs().size() - 2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openDownload(ActionEvent actionEvent) {
        DefaultTab.openDonwloadFolder();
    }
    public void OpenHistory(ActionEvent actionEvent) {
        if(isLogged()){
            GeneraleTabPan = this.tabPan;
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
