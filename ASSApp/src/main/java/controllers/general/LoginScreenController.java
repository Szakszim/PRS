package controllers.general;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginScreenController {


    @FXML
    private GridPane loginGridPane;
    @FXML
    private TextField eMailField;
    @FXML
    private PasswordField passwordField;

    @FXML
    public void onLoginButtonAction(ActionEvent actionEvent) throws IOException {

        if(validateData()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/general/MainScreen.fxml"));
            AnchorPane anchorPane = loader.load();
            Scene scene = new Scene(anchorPane);
            Stage stage = (Stage) loginGridPane.getScene().getWindow();
            stage.setResizable(false);
            stage.setTitle("PRS");
            stage.setScene(scene);
            stage.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Wprowadzono błędne dane");
            alert.showAndWait();
        }


    }

    private boolean validateData() {
        return eMailField.getText().equals("imie.nazwisko@put.poznan.pl") && passwordField.getText().equals("123");
    }
}
