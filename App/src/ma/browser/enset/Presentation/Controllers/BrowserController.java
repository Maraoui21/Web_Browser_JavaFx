package ma.browser.enset.Presentation.Controllers;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;
import ma.browser.enset.Dao.Entities.History;
import ma.browser.enset.Dao.Implementation.HistoryDaoImpl;
import ma.browser.enset.Services.Implimentation.HistoryServiceImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Handler;

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
        webViewF.getEngine().load("http://google.com");
        tfTitle.setOnKeyPressed((new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)){
                    visite();
                }
            }
        }));
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
}
