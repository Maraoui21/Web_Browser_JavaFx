package ma.browser.enset.Presentation.Controllers;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class BrowserController implements Initializable {
    public Tab firstTab;
    public ProgressBar Loading;
    public HBox HoriZontalBox;
    public Button refresh;
    @FXML
    private TextField tfTitle;
    @FXML
    private Tab addNewTab;
    @FXML
    public WebView webViewF;
    @FXML
    public TabPane tabPan;

    @FXML
    public void visite() {
        String url = tfTitle.getText();
        if(url.contains("www.") || url.contains("http://") || url.contains("https://")) {
            webViewF.getEngine().load(url);

        }else{
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
        webViewF.getEngine().load("http://google.com");
        addNewTab.setOnSelectionChanged(new EventHandler<Event>(){
            @Override
            public void handle(Event event){
                if(addNewTab.isSelected()){
                    try {
                        Tab tab = new Tab("New tab");
                        Tab toAdd = FXMLLoader.load(getClass().getResource("DefaultPage.fxml"));
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
                        firstTab.setText(titleText);
                    }
                }
                // to change the url inside text-field
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
}
