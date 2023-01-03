package ma.browser.enset.Presentation.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import ma.browser.enset.Dao.Entities.DownloadHistory;
import ma.browser.enset.Dao.Implementation.DownloadImpl;
import ma.browser.enset.Dao.Implementation.HistoryDaoImpl;
import ma.browser.enset.Services.Implimentation.DownloadHistoryServiceImp;
import ma.browser.enset.Services.Implimentation.HistoryServiceImpl;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;


public class Download implements Initializable {
    public static URL Link;
    @FXML
    public ProgressBar DownloadProgress;
    public Button DownloadBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public DownloadHistoryServiceImp DownloadService(){
        DownloadImpl hdao = new DownloadImpl();
        return new DownloadHistoryServiceImp(hdao);
    }

    public void DownloadEvent(){
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call(){
                String home = System.getProperty("user.home");
                if(!new File(home+"/Downloads/Fx_Download/").exists()){
                    boolean created = new File(home+"/Downloads/Fx_Download/").mkdir();
                };
                File file = new File(home+"/Downloads/Fx_Download/" + Link.getFile().replace("/",""));
                try (InputStream in = Download.Link.openStream();
                     ReadableByteChannel rbc = Channels.newChannel(in);
                     FileOutputStream fos = new FileOutputStream(file)) {
                    fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                }catch (IOException e){
                    e.getStackTrace();
                }
                return null;
            };
        };
        task.stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observableValue, Worker.State state, Worker.State t1) {
                if (Worker.State.SUCCEEDED.equals(t1)){
                    // Fin de telechargement
                    if(Login.LoggedUser!=null){
                        DownloadHistory DownHistory = new DownloadHistory();
                        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        Date date = new Date();
                        DownHistory.setName(Link.getFile().replace("/",""));
                        DownHistory.setUrl(Link.toString());
                        DownHistory.setDate_Download(dateFormat.format(date));
                        DownHistory.setUser(Login.LoggedUser);
                        DownloadService().add(DownHistory);
                    }
                    DownloadProgress.progressProperty().set(1.0);
                    DownloadBtn.setText("Open");
                    DownloadBtn.setOnAction(e->{
                        Desktop desktop = Desktop.getDesktop();
                        try {
                            String home = System.getProperty("user.home");
                            desktop.open(new File(home+"\\Downloads\\Fx_Download"));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
                }else{
                    System.out.println(task.progressProperty().get());
                    DownloadProgress.progressProperty().set(task.progressProperty().get());
                }
            }
        });
        new Thread(task).start();

    }
}
