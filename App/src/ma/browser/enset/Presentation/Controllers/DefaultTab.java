package ma.browser.enset.Presentation.Controllers;

import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.URL;
import java.util.ResourceBundle;

public class DefaultTab implements Initializable {
    @FXML
    public TextField tfTitle1;
    public ProgressBar Loading;
    public VBox verticalBox1;
    @FXML
    private WebView webViewF1;
    @FXML
    private Tab currentTab;

    @FXML
    public void visite1(ActionEvent event) {
        String url = tfTitle1.getText();
        if(url.contains("www.") || url.contains("http://") || url.contains("https://")){
            webViewF1.getEngine().load(url);
            currentTab.setText(url);
        }else{
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
        webViewF1.getEngine().load("http://google.com");
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
                        currentTab.setText(titleText);
                    }
                }
                // to change the url inside text-field
                tfTitle1.setText(webViewF1.getEngine().getLocation());
                // make loader empty
                Loading.setProgress(0.0);
            }
            else {
                double x = Loading.getProgress();
                Loading.setProgress(x+=0.3);
            }
        });
    }

}
