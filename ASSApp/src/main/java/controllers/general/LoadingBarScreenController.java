package controllers.general;

import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ResourceBundle;

public class LoadingBarScreenController implements Initializable {

    @FXML
    private ProgressBar loadingBar;


    @FXML
    @Setter
    @Getter
    private Stage stage;

    @FXML
    @Setter
    @Getter
    private Scene scene;

    @Setter
    @Getter
    private Boolean loadingState;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadingState = Boolean.TRUE;
//        loadingBar.progressProperty().bind(task.progressProperty());

        this.afterInit();
    }

    public void afterInit() {
//        System.out.println("ROZPOCZECIE LADOWANIA");
//        new Thread(task).start();


//        task.setOnSucceeded((Event event) -> {
////            loadingBar.setProgress(0.0);
//            stage.close();
//        });

    }


    public void activateProgressBar(final Task<?> task)  {
        loadingBar.progressProperty().bind(task.progressProperty());
//        pin.progressProperty().bind(task.progressProperty());
//        dialogStage.show();
    }

//    final Task task = new Task<Void>() {
//        @Override
//        protected Void call() throws Exception {
//            while(loadingState){
//                for (int i = 1; i <= 50000000; i++) {
//                    updateProgress(i, 50000000);
//                    if(!loadingState){
//                        System.out.println("END1");
//                        break;
//                    }
//                }
//            }
//            System.out.println("END");
////            while (true) {
////
////            }
//
//            return null;
//        }
//    };
}
