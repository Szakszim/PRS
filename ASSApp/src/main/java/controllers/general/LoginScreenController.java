package controllers.general;

import context.ContextHandler;
import dtos.LecturerDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import requests.LecturerRequest;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreenController implements Initializable {


    @FXML
    private GridPane loginGridPane;
    @FXML
    private TextField eMailField;
    @FXML
    private PasswordField passwordField;

    private LecturerRequest lecturerRequest;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeRequests();
    }

    private void initializeRequests() {
        lecturerRequest = new LecturerRequest();
    }

    @FXML
    public void onLoginButtonAction(ActionEvent actionEvent) throws IOException {
        login();
    }

    public void handleKeyEvent(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getEventType().equals(KeyEvent.KEY_PRESSED)) {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                login();
            }
        }
    }

    private void login() throws IOException {
        String email = eMailField.getText();
        String password = passwordField.getText();
        if (validateData(email, password)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/general/MainScreen.fxml"));
            AnchorPane anchorPane = loader.load();
            Scene scene = new Scene(anchorPane);
            Stage stage = (Stage) loginGridPane.getScene().getWindow();
            stage.setResizable(false);
            stage.setTitle("PRS");
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Wprowadzono błędne dane");
            alert.showAndWait();
        }
    }

    private boolean validateData(String email, String password) {
        LecturerDto lecturerDto = lecturerRequest.get(email, password);
        if (lecturerDto != null) {
            ContextHandler.setLecturerDto(lecturerDto);
            return true;
        } else {
            return false;
        }
    }
}
