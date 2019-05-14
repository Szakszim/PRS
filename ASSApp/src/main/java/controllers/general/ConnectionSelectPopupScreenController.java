package controllers.general;

import entities.Connection;
import enums.SelectionPopupEventType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import others.SelectionPopupEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ConnectionSelectPopupScreenController implements Initializable {

    @FXML
    private ImageView notificationIcon;

    @FXML
    private Label notificationMessage;

    @FXML
    private ComboBox<Connection> list;

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
    private SelectionPopupEvent selectionPopupEvent;

    @FXML
    @Setter
    private Stage stage;

    @FXML
    @Setter
    private Scene scene;

    ObservableList<Connection> connection;

    @FXML
    private void onSelect(){
        selectionPopupEvent.setSelectionPopupEventType(SelectionPopupEventType.SELECT);
        selectionPopupEvent.setSelectedRow(list.getSelectionModel().getSelectedIndex());
        stage.close();
    }

    @FXML
    private void onCreate(){
        selectionPopupEvent.setSelectionPopupEventType(SelectionPopupEventType.CREATE);
        selectionPopupEvent.setSelectedRow(null);
        stage.close();
    }

    @FXML
    private void onClose(){
        selectionPopupEvent.setSelectionPopupEventType(SelectionPopupEventType.CLOSE);
        selectionPopupEvent.setSelectedRow(null);
        stage.close();
    }

    public void init(List<Connection> connectionList){
        connection = FXCollections.observableArrayList(connectionList);

        list.setItems(connection);

        list.getSelectionModel().selectFirst();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectionPopupEvent = new SelectionPopupEvent();
//        connection = FXCollections.observableArrayList(connectionList);
//
//        list.setItems(connection);
    }

//    public void disableSecondButton(){
//        negativeButton.setVisible(Boolean.FALSE);
//        negativeLabelButton.setVisible(Boolean.FALSE);
//    }

}
