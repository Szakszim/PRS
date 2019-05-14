package controllers.manager;

import entities.Connection;
import enums.ModulePurchasePopupEventType;
import enums.ModuleType;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ResourceBundle;

public class ModulePurchasePopupScreenController implements Initializable {

    @FXML
    private ImageView notificationIcon;

    @FXML
    private Label notificationMessage;

    @FXML
    private Button selectButton;

    @FXML
    private Button selectLabelButton;

    @FXML
    private Button createButton;

    @FXML
    private Button createLabelButton;

    @FXML
    private Button closeButton;

    @FXML
    private Button closeLabelButton;

    @Getter
    @Setter
    private ModulePurchasePopupEventType modulePurchasePopupEventType;

    @Getter
    @Setter
    private ModuleType moduleType;

    @FXML
    @Setter
    private Stage stage;

    @FXML
    @Setter
    private Scene scene;

    ObservableList<Connection> connection;

    @FXML
    private void onSelect() {
        modulePurchasePopupEventType = ModulePurchasePopupEventType.PURCHASE;
        stage.close();
    }

    @FXML
    private void onCreate() {
        modulePurchasePopupEventType = ModulePurchasePopupEventType.ENTER;
        stage.close();
    }

    @FXML
    private void onClose() {
        modulePurchasePopupEventType = ModulePurchasePopupEventType.CLOSE;
        stage.close();
    }

    public void init(ModuleType moduleType) {
        moduleType = moduleType;
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

    public void setMessage(String message) {
        notificationMessage.setText(message);
    }

    public void setImage(String path) {
        Image image = new Image(path);
        notificationIcon.setImage(image);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.modulePurchasePopupEventType = null;
//        connection = FXCollections.observableArrayList(connectionList);
//
//        list.setItems(connection);
    }

//    public void disableSecondButton(){
//        negativeButton.setVisible(Boolean.FALSE);
//        negativeLabelButton.setVisible(Boolean.FALSE);
//    }

}
