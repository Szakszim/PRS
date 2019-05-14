package controllers.manager.plants;

import context.ContextHandler;
import controllers.TemplateScreenController;
import controllers.TemplateScreenController2;
import controllers.TemplateScreenController3;
import controllers.general.LoginScreenController;
import controllers.manager.OwnerModuleSelectScreenController;
import enums.MessageType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OwnerPlantsModuleMainScreenController implements Initializable {
    public static final String LOGOUT_CONFIRMATION = "You have successfully logged out!";

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
    private Button button7;

    @FXML
    private Button button8;

    @FXML
    private Button button9;

    @FXML
    private Button button10;

    @FXML
    private Button button11;

    @FXML
    private Button button12;

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

    @Setter
    @Getter
    private ContextHandler contextHandler;

    public void setView(GridPane gridPane) {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(gridPane);
    }

    @FXML
    void onField() {

        FXMLLoader innerLoader = new FXMLLoader();
        innerLoader.setLocation(this.getClass().getResource("/views/manager/plants/FieldScreen.fxml"));
//        FXMLLoader innerLoader = new FXMLLoader(getClass().getResource("O:\\Adrian\\Projekty\\ASS\\ASSApp\\src\\main\\resources\\views\\TemplateScreen.fxml"));
//        ResourceBundle bundle = ResourceBundle.getBundle("gui.resources.lang");
//        loader.setResources(bundle);

        GridPane gridPane = null;
        try {
            gridPane = innerLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FieldScreenController controller = innerLoader.getController();
        controller.setOwnerPlantsModuleMainScreenController(this);
//        set objects here

//        controller.setMainScreenController(this);
        setView(gridPane);
//        serviceRequestScreenController.setMainScreenController(this);
//        setView(gridPane);
    }

    @FXML
    void onSecondButton() {

//        button2.setStyle("-fx-background-color: rgba(255,255,255,.3)");
//        button2.setStyle("-fx-border-width: 0 1 8 0;");
//        button2.setStyle("-fx-border-color: white white green white;");
//        button2.setStyle("-fx-border-insets: -1,-1,-1,-1");

        FXMLLoader innerLoader = new FXMLLoader();
        innerLoader.setLocation(this.getClass().getResource("/views/TemplateScreen.fxml"));
//        FXMLLoader innerLoader = new FXMLLoader(getClass().getResource("O:\\Adrian\\Projekty\\ASS\\ASSApp\\src\\main\\resources\\views\\TemplateScreen.fxml"));
//        ResourceBundle bundle = ResourceBundle.getBundle("gui.resources.lang");
//        loader.setResources(bundle);

        GridPane gridPane = null;
        try {
            gridPane = innerLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TemplateScreenController controller = innerLoader.getController();

//        set objects here

//        controller.setMainScreenController(this);
        setView(gridPane);
//        serviceRequestScreenController.setMainScreenController(this);
//        setView(gridPane);
    }

    @FXML
    void onThirdButton() {

//        button2.setStyle("-fx-background-color: rgba(255,255,255,.3)");
//        button2.setStyle("-fx-border-width: 0 1 8 0;");
//        button2.setStyle("-fx-border-color: white white green white;");
//        button2.setStyle("-fx-border-insets: -1,-1,-1,-1");

        FXMLLoader innerLoader = new FXMLLoader();
        innerLoader.setLocation(this.getClass().getResource("/views/TemplateScreen2.fxml"));
//        FXMLLoader innerLoader = new FXMLLoader(getClass().getResource("O:\\Adrian\\Projekty\\ASS\\ASSApp\\src\\main\\resources\\views\\TemplateScreen.fxml"));
//        ResourceBundle bundle = ResourceBundle.getBundle("gui.resources.lang");
//        loader.setResources(bundle);

        GridPane gridPane = null;
        try {
            gridPane = innerLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TemplateScreenController2 controller = innerLoader.getController();
//        set objects here

//        controller.setMainScreenController(this);
        setView(gridPane);
//        serviceRequestScreenController.setMainScreenController(this);
//        setView(gridPane);
    }

    @FXML
    void onFourthButton() {

//        button2.setStyle("-fx-background-color: rgba(255,255,255,.3)");
//        button2.setStyle("-fx-border-width: 0 1 8 0;");
//        button2.setStyle("-fx-border-color: white white green white;");
//        button2.setStyle("-fx-border-insets: -1,-1,-1,-1");

        FXMLLoader innerLoader = new FXMLLoader();
        innerLoader.setLocation(this.getClass().getResource("/views/TemplateScreen3.fxml"));
//        FXMLLoader innerLoader = new FXMLLoader(getClass().getResource("O:\\Adrian\\Projekty\\ASS\\ASSApp\\src\\main\\resources\\views\\TemplateScreen.fxml"));
//        ResourceBundle bundle = ResourceBundle.getBundle("gui.resources.lang");
//        loader.setResources(bundle);

        GridPane gridPane = null;
        try {
            gridPane = innerLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TemplateScreenController3 controller = innerLoader.getController();
//        set objects here

//        controller.setMainScreenController(this);
        setView(gridPane);
//        serviceRequestScreenController.setMainScreenController(this);
//        setView(gridPane);
    }

    @FXML
    void onNotification() {

//        button2.setStyle("-fx-background-color: rgba(255,255,255,.3)");
//        button2.setStyle("-fx-border-width: 0 1 8 0;");
//        button2.setStyle("-fx-border-color: white white green white;");
//        button2.setStyle("-fx-border-insets: -1,-1,-1,-1");

        FXMLLoader innerLoader = new FXMLLoader();
        innerLoader.setLocation(this.getClass().getResource("/views/manager/plants/PlantsNotificationScreen.fxml"));
//        FXMLLoader innerLoader = new FXMLLoader(getClass().getResource("O:\\Adrian\\Projekty\\ASS\\ASSApp\\src\\main\\resources\\views\\TemplateScreen.fxml"));
//        ResourceBundle bundle = ResourceBundle.getBundle("gui.resources.lang");
//        loader.setResources(bundle);

        GridPane gridPane = null;
        try {
            gridPane = innerLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PlantsNotificationScreenController controller = innerLoader.getController();
//        set objects here

//        controller.setMainScreenController(this);
        setView(gridPane);
//        serviceRequestScreenController.setMainScreenController(this);
//        setView(gridPane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void onBackToModuleSelection() {
        FXMLLoader innerLoader = new FXMLLoader();
        innerLoader.setLocation(this.getClass().getResource("/views/manager/OwnerModuleSelectScreen.fxml"));

//        ResourceBundle bundle = ResourceBundle.getBundle("gui.resources.lang");
//        innerLoader.setResources(bundle);
        try {
//            stage


            Parent innerRoot = innerLoader.load();
            OwnerModuleSelectScreenController moduleSelectScreenController = innerLoader.getController();
//            mainScreenController.setClient(client);
//            mainScreenController.setLocalDatabase(localDatabase);
//            stage.setTitle("PasswordsManager 1.0.0");
//            Scene scene = new Scene()
            scene.setRoot(innerRoot);
            stage.setScene(scene);
            stage.setMaximized(true);
            moduleSelectScreenController.setScene(scene);
            moduleSelectScreenController.setStage(stage);
            moduleSelectScreenController.setContextHandler(this.contextHandler);
            moduleSelectScreenController.initData();
            stage.show();
//            primaryStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onLogout() {

        //TODO Logout mechanism

        FXMLLoader innerLoader = new FXMLLoader();
        innerLoader.setLocation(this.getClass().getResource("/views/general/LoginScreen.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("lang");
        innerLoader.setResources(bundle);

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
            loginScreenController.callPopup(MessageType.CONFIRMATION, LOGOUT_CONFIRMATION);
//            primaryStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkKeyAction(KeyEvent keyEvent) {
        if (keyEvent.getEventType().equals(KeyEvent.KEY_PRESSED)) {
            switch (keyEvent.getCode()) {
                case S:
                    onBackToModuleSelection();
                    break;
                case Q:
                    onLogout();
                    break;
            }
        }

        //TO DO do it better
//        if (keyEvent.getCharacter().equals("s")) {
//            onBackToModuleSelection();
//        } else {
//            if (keyEvent.getCharacter().equals("q")) {
//                onLogout();
//            }

    }

}

