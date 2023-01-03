package ma.browser.enset.Presentation.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import ma.browser.enset.Dao.Entities.History;
import ma.browser.enset.Dao.Implementation.HistoryDaoImpl;
import ma.browser.enset.Services.Implimentation.HistoryServiceImpl;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {
    public TableView<History> TabHistory;
    public TableColumn<History,Integer> Idcol;
    public TableColumn<History,String> urlcol;
    public TableColumn<History,String> datecol;
    public HistoryServiceImpl historyService(){
        HistoryDaoImpl hdao = new HistoryDaoImpl();
        return new HistoryServiceImpl(hdao);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // chargement des history
        ObservableList<History> list = FXCollections.observableList(historyService().findAll(Login.LoggedUser));
        Idcol.setCellValueFactory(new PropertyValueFactory<History,Integer>("id"));
        urlcol.setCellValueFactory(new PropertyValueFactory<History,String>("url"));
        datecol.setCellValueFactory(new PropertyValueFactory<History,String>("Date"));
        TabHistory.setItems(list);
        // handle history double click to open Link
        TabHistory.setRowFactory( tv -> {
            TableRow<History> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    History history = row.getItem();
                    try {
                        Tab tab = new Tab("New tab");
                        File file = new File("App/src/ma/browser/enset/Presentation/view/DefaultPage.fxml");
                        Tab toAdd = FXMLLoader.load(file.toURL());
                        toAdd.setText(history.getUrl().substring(1,15));
                        BorderPane selectedBorderPane = (BorderPane) toAdd.getContent();
                        WebView WebView = (WebView) selectedBorderPane.getCenter();
                        WebView.getEngine().load(history.getUrl());
                        BrowserController.GeneraleTabPan.getTabs().add(BrowserController.GeneraleTabPan.getTabs().size() - 1,toAdd);
                        BrowserController.GeneraleTabPan.getSelectionModel().select(BrowserController.GeneraleTabPan.getTabs().size() - 2);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            return row ;
        });
    }
}
