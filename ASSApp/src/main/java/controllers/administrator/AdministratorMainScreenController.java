package controllers.administrator;

import controllers.general.LoginScreenController;
import entities.KeyAssignment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.IOException;
import java.util.ResourceBundle;

public class AdministratorMainScreenController {

    @FXML
    private Button selectModuleButton;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button10;

    @FXML
    private Button logoutButton;

    @FXML
    private StackPane stackPane;

    @FXML
    @Setter
    private Stage stage;

    @FXML
    @Setter
    private Scene scene;

    @FXML
    void checkKeyAction(KeyEvent event) {

    }

    @FXML
    void onBackToModuleSelection(ActionEvent event) {

    }

    @FXML
    void onFifthButton(ActionEvent event) {

    }

    @FXML
    void onUser() {
        FXMLLoader innerLoader = new FXMLLoader();
        innerLoader.setLocation(this.getClass().getResource("/views/administrator/AccountScreen.fxml"));
//        FXMLLoader innerLoader = new FXMLLoader(getClass().getResource("O:\\Adrian\\Projekty\\ASS\\ASSApp\\src\\main\\resources\\views\\TemplateScreen.fxml"));
//        ResourceBundle bundle = ResourceBundle.getBundle("gui.resources.lang");
//        loader.setResources(bundle);

        GridPane gridPane = null;
        try {
            gridPane = innerLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AccountScreenController controller = innerLoader.getController();
//        set objects here

//        controller.setMainScreenController(this);
        setView(gridPane);
//        serviceRequestScreenController.setMainScreenController(this);
    }

    @FXML
    void onFourthButton() {
        FXMLLoader innerLoader = new FXMLLoader();
        innerLoader.setLocation(this.getClass().getResource("/views/administrator/KeyScreen.fxml"));
//        FXMLLoader innerLoader = new FXMLLoader(getClass().getResource("O:\\Adrian\\Projekty\\ASS\\ASSApp\\src\\main\\resources\\views\\TemplateScreen.fxml"));
//        ResourceBundle bundle = ResourceBundle.getBundle("gui.resources.lang");
//        loader.setResources(bundle);

        GridPane gridPane = null;
        try {
            gridPane = innerLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        KeyScreenController controller = innerLoader.getController();
//        set objects here

//        controller.setMainScreenController(this);
        setView(gridPane);
//        serviceRequestScreenController.setMainScreenController(this);
    }

    @FXML
    void onLogout(ActionEvent event) {
//TODO Logout mechanism

        FXMLLoader innerLoader = new FXMLLoader();
        innerLoader.setLocation(this.getClass().getResource("/views/general/LoginScreen.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("lang");
        innerLoader.setResources(bundle);
//        ResourceBundle bundle = ResourceBundle.getBundle("gui.resources.lang");
//        innerLoader.setResources(bundle);
        try {
//            stage


            Parent innerRoot = innerLoader.load();
            LoginScreenController loginScreenController = innerLoader.getController();
//            mainScreenController.setClient(client);
//            mainScreenController.setLocalDatabase(localDatabase);
//            stage.setTitle("PasswordsManager 1.0.0");
//            Scene scene = new Scene()
            scene.setRoot(innerRoot);
            stage.setScene(scene);
            stage.setMaximized(true);
            loginScreenController.setScene(scene);
            loginScreenController.setStage(stage);
            stage.show();
//            primaryStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onFarm(ActionEvent event) {
        FXMLLoader innerLoader = new FXMLLoader();
        innerLoader.setLocation(this.getClass().getResource("/views/administrator/FarmScreen.fxml"));
//        FXMLLoader innerLoader = new FXMLLoader(getClass().getResource("O:\\Adrian\\Projekty\\ASS\\ASSApp\\src\\main\\resources\\views\\TemplateScreen.fxml"));
//        ResourceBundle bundle = ResourceBundle.getBundle("gui.resources.lang");
//        loader.setResources(bundle);

        GridPane gridPane = null;
        try {
            gridPane = innerLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FarmScreenController controller = innerLoader.getController();
//        set objects here

//        controller.setMainScreenController(this);
        setView(gridPane);
//        serviceRequestScreenController.setMainScreenController(this);
    }

    @FXML
    void onKey(ActionEvent event) {
        FXMLLoader innerLoader = new FXMLLoader();
        innerLoader.setLocation(this.getClass().getResource("/views/administrator/KeyScreen.fxml"));
//        FXMLLoader innerLoader = new FXMLLoader(getClass().getResource("O:\\Adrian\\Projekty\\ASS\\ASSApp\\src\\main\\resources\\views\\TemplateScreen.fxml"));
//        ResourceBundle bundle = ResourceBundle.getBundle("gui.resources.lang");
//        loader.setResources(bundle);

        GridPane gridPane = null;
        try {
            gridPane = innerLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        KeyScreenController controller = innerLoader.getController();
//        set objects here

//        controller.setMainScreenController(this);
        setView(gridPane);
//        serviceRequestScreenController.setMainScreenController(this);
    }

    @FXML
    void onPurchase(ActionEvent event) {
        FXMLLoader innerLoader = new FXMLLoader();
        innerLoader.setLocation(this.getClass().getResource("/views/administrator/PurchaseScreen.fxml"));
//        FXMLLoader innerLoader = new FXMLLoader(getClass().getResource("O:\\Adrian\\Projekty\\ASS\\ASSApp\\src\\main\\resources\\views\\TemplateScreen.fxml"));
//        ResourceBundle bundle = ResourceBundle.getBundle("gui.resources.lang");
//        loader.setResources(bundle);

        GridPane gridPane = null;
        try {
            gridPane = innerLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PurchaseScreenController controller = innerLoader.getController();
//        set objects here

//        controller.setMainScreenController(this);
        setView(gridPane);
//        serviceRequestScreenController.setMainScreenController(this);
    }

    @FXML
    void onKeyAssignment(ActionEvent event) {
        FXMLLoader innerLoader = new FXMLLoader();
        innerLoader.setLocation(this.getClass().getResource("/views/administrator/KeyAssignmentScreen.fxml"));
//        FXMLLoader innerLoader = new FXMLLoader(getClass().getResource("O:\\Adrian\\Projekty\\ASS\\ASSApp\\src\\main\\resources\\views\\TemplateScreen.fxml"));
//        ResourceBundle bundle = ResourceBundle.getBundle("gui.resources.lang");
//        loader.setResources(bundle);

        GridPane gridPane = null;
        try {
            gridPane = innerLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        KeyAssignmentScreenController controller = innerLoader.getController();
//        set objects here

//        controller.setMainScreenController(this);
        setView(gridPane);
//        serviceRequestScreenController.setMainScreenController(this);
    }

    public void setView(GridPane gridPane) {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(gridPane);
    }

}
