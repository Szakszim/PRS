package controllers.general;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

public class NewPopupScreenController {

    @FXML
    private ImageView notificationIcon;

    @FXML
    private Label notificationMessage;

    @FXML
    private Button positiveButton;

    @FXML
    private Button positiveLabelButton;

    @FXML
    private Button negativeButton;

    @FXML
    private Button negativeLabelButton;

    @Getter
    @Setter
    private Boolean answer;

    @FXML
    @Setter
    private Stage stage;

    @FXML
    @Setter
    private Scene scene;

    @FXML
    private void onPositive(){
        answer=Boolean.TRUE;
        stage.close();
    }

    @FXML
    private void onNegative(){
        answer=Boolean.FALSE;
        stage.close();
    }

    public void checkKeyAction(KeyEvent keyEvent) {
        if (keyEvent.getEventType().equals(KeyEvent.KEY_PRESSED)) {
            switch (keyEvent.getCode()) {
                case ENTER:
                    stage.close();
                    break;
                case ESCAPE:
                    stage.close();
                    break;
            }
        }
    }

    public void setMessage(String message){
        notificationMessage.setText(message);
    }

    public void setImage(String path){
        Image image = new Image(path);
        notificationIcon.setImage(image);
    }

    public void disableSecondButton(){
        negativeButton.setVisible(Boolean.FALSE);
        negativeLabelButton.setVisible(Boolean.FALSE);
    }

}
