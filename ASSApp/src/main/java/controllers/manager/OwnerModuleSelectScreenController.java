package controllers.manager;

import context.ContextHandler;
import controllers.general.LoginScreenController;
import controllers.general.NewPopupScreenController;
import controllers.manager.animals.OwnerAnimalsModuleMainScreenController;
import controllers.manager.machinery.OwnerMachineryModuleMainScreenController;
import controllers.manager.plants.OwnerPlantsModuleMainScreenController;
import dtos.base.PurchaseDto;
import dtos.other.ModulePrivilegeDto;
import enums.MessageType;
import enums.ModulePurchasePopupEventType;
import enums.ModuleType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import lombok.Setter;
import requests.AuthenticationRequest;
import requests.PurchaseRequest;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OwnerModuleSelectScreenController implements Initializable {

    @FXML
    private Button plantsButton;

    @FXML
    private Button animalsButton;

    @FXML
    private Button machineryButton;

    @FXML
    private Button accountanceButton;

    @FXML
    private Button selectButton;

    @FXML
    private Button selectLabelButton;

    @FXML
    private Button buyButton;

    @FXML
    private Button buyLabelButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button logoutLabelButton;

    @FXML
    private Label title;

    @FXML
    @Setter
    private Stage stage;

    @FXML
    @Setter
    private Scene scene;

    @Setter
    @Getter
    private ContextHandler contextHandler;

    private PurchaseRequest purchaseRequest;

    private Boolean plantsModuleSelect = Boolean.FALSE;
    private Boolean animalsModuleSelect = Boolean.FALSE;
    private Boolean machineryModuleSelect = Boolean.FALSE;
    private Boolean accountantModuleSelect = Boolean.FALSE;

    private Boolean hasPlantsModulePrivilege;
    private Boolean hasAnimalsModulePrivilege;
    private Boolean hasMachineryModulePrivilege;
    private Boolean hasAccountantModulePrivilege;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        purchaseRequest = new PurchaseRequest();
    }

    public void initData() {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();

        ModulePrivilegeDto privileges = authenticationRequest.getModulePrivilegesByFarmId(contextHandler.getCurrentFarm().getId());

        hasPlantsModulePrivilege = privileges.getPlantsModulePrivilege();
        hasAnimalsModulePrivilege = privileges.getAnimalsModulePrivilege();
        hasMachineryModulePrivilege = privileges.getMachineryModulePrivilege();
        hasAccountantModulePrivilege = privileges.getAccountantModulePrivilege();
    }

    public Boolean callPopup(MessageType messageType, String message) {
        Parent root = null;

        FXMLLoader innerLoader = new FXMLLoader();
        innerLoader.setLocation(this.getClass().getResource("/views/general/NewPopupScreen.fxml"));
        try {
            root = innerLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        NewPopupScreenController popupScreenController;
        popupScreenController = innerLoader.getController();

        Scene popupScene = new Scene(root);
        Stage newWindow = new Stage();

        newWindow.initStyle(StageStyle.UNDECORATED);
        newWindow.initStyle(StageStyle.TRANSPARENT);
        popupScene.setFill(Color.TRANSPARENT);

//       this.stage.getScene();
        newWindow.initOwner(scene.getWindow());
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.setScene(popupScene);
        newWindow.setResizable(false);

        popupScreenController.setScene(popupScene);
        popupScreenController.setStage(newWindow);

        popupScreenController.setMessage(message);

        switch (messageType) {
            case ERROR:
                popupScreenController.setImage("icons/error_symbol_icon.png");
                break;
            case WARNING:
                popupScreenController.setImage("icons/warning_symbol_icon.png");
                break;
            case CONFIRMATION:
                popupScreenController.setImage("icons/confirmation_symbol_icon.png");
                break;
            case QUESTION:
                popupScreenController.setImage("icons/error_symbol_icon.png");
                break;
        }

        newWindow.showAndWait();

        return popupScreenController.getAnswer();
    }


    @FXML
    private void onPlantsModuleSelect() {
        //TODO Set styles to button (select effect)
//        plantsButton.setStyle("-fx-background-color: rgba(255,255,255,.3)");
//        plantsButton.setStyle("-fx-background-radius: 0,0,0");
//        plantsButton.setStyle("-fx-border-width: 1 1 8 1");
//        plantsButton.setStyle("-fx-border-color: gray gray green gray");
//        plantsButton.setStyle("-fx-border-insets: -1,-1,-1,-1");
//        plantsButton.setStyle("-fx-position-shape: absolute");

        unselectAllModules();
        plantsModuleSelect = Boolean.TRUE;
        if (hasPlantsModulePrivilege) {
            switchToSelectionMode();
        } else {
            switchToPurchaseMode();
        }
    }

    @FXML
    private void onAnimalsModuleSelect() {
//        plantsButton.setStyle("-fx-background-color: rgba(255,255,255,.3);");
//        plantsButton.setStyle("-fx-background-radius: 0,0,0;");
//        plantsButton.setStyle("-fx-border-width: 1 1 8 1;");
//        plantsButton.setStyle("-fx-border-color: gray gray green gray;");
//        plantsButton.setStyle("-fx-border-insets: -1,-1,-1,-1;");
//        plantsButton.setStyle("-fx-position-shape: absolute;");
        unselectAllModules();
        animalsModuleSelect = Boolean.TRUE;
        if (hasAnimalsModulePrivilege) {
            switchToSelectionMode();
        } else {
            switchToPurchaseMode();
        }
    }

    @FXML
    private void onMachineryModuleSelect() {
        unselectAllModules();
        machineryModuleSelect = Boolean.TRUE;
        if (hasMachineryModulePrivilege) {
            switchToSelectionMode();
        } else {
            switchToPurchaseMode();
        }
    }

    @FXML
    private void onAccountantModuleSelect() {
        unselectAllModules();
        accountantModuleSelect = Boolean.TRUE;
        if (hasAccountantModulePrivilege) {
            switchToSelectionMode();
        } else {
            switchToPurchaseMode();
        }
    }

    private void unselectAllModules() {
        plantsModuleSelect = Boolean.FALSE;
        animalsModuleSelect = Boolean.FALSE;
        machineryModuleSelect = Boolean.FALSE;
        accountantModuleSelect = Boolean.FALSE;
    }

    private void switchToSelectionMode() {
        selectButton.setDisable(Boolean.FALSE);
        selectLabelButton.setDisable(Boolean.FALSE);
        buyButton.setDisable(Boolean.TRUE);
        buyLabelButton.setDisable(Boolean.TRUE);
    }

    private void switchToPurchaseMode() {
        selectButton.setDisable(Boolean.TRUE);
        selectLabelButton.setDisable(Boolean.TRUE);
        buyButton.setDisable(Boolean.FALSE);
        buyLabelButton.setDisable(Boolean.FALSE);
    }

    @FXML
    private void onSelectModule() {
        if (plantsModuleSelect) {
            FXMLLoader innerLoader = new FXMLLoader();
            innerLoader.setLocation(this.getClass().getResource("/views/manager/plants/OwnerPlantsModuleMainScreen.fxml"));
            ResourceBundle bundle = ResourceBundle.getBundle("lang");
            innerLoader.setResources(bundle);
            try {
//            stage


                Parent innerRoot = innerLoader.load();
                OwnerPlantsModuleMainScreenController mainScreenController = innerLoader.getController();
//            mainScreenController.setClient(client);
//            mainScreenController.setLocalDatabase(localDatabase);
//            stage.setTitle("PasswordsManager 1.0.0");
//            Scene scene = new Scene()
                scene.setRoot(innerRoot);
                stage.setScene(scene);
                stage.setMaximized(true);
                mainScreenController.setScene(scene);
                mainScreenController.setStage(stage);
                mainScreenController.setContextHandler(this.contextHandler);
                stage.show();
//            primaryStage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (animalsModuleSelect) {
                FXMLLoader innerLoader = new FXMLLoader();
                innerLoader.setLocation(this.getClass().getResource("/views/manager/animals/OwnerAnimalsModuleMainScreen.fxml"));

//        ResourceBundle bundle = ResourceBundle.getBundle("gui.resources.lang");
//        innerLoader.setResources(bundle);
                try {
//            stage


                    Parent innerRoot = innerLoader.load();
                    OwnerAnimalsModuleMainScreenController mainScreenController = innerLoader.getController();
//            mainScreenController.setClient(client);
//            mainScreenController.setLocalDatabase(localDatabase);
//            stage.setTitle("PasswordsManager 1.0.0");
//            Scene scene = new Scene()
                    scene.setRoot(innerRoot);
                    stage.setScene(scene);
                    stage.setMaximized(true);
                    mainScreenController.setScene(scene);
                    mainScreenController.setStage(stage);
                    mainScreenController.setContextHandler(this.contextHandler);
                    stage.show();
//            primaryStage.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                if (machineryModuleSelect) {
                    //go to machinery module
                    FXMLLoader innerLoader = new FXMLLoader();
                    innerLoader.setLocation(this.getClass().getResource("/views/manager/machinery/OwnerMachineryModuleMainScreen.fxml"));

//        ResourceBundle bundle = ResourceBundle.getBundle("gui.resources.lang");
//        innerLoader.setResources(bundle);
                    try {
//            stage


                        Parent innerRoot = innerLoader.load();
                        OwnerMachineryModuleMainScreenController mainScreenController = innerLoader.getController();
//            mainScreenController.setClient(client);
//            mainScreenController.setLocalDatabase(localDatabase);
//            stage.setTitle("PasswordsManager 1.0.0");
//            Scene scene = new Scene()
                        scene.setRoot(innerRoot);
                        stage.setScene(scene);
                        stage.setMaximized(true);
                        mainScreenController.setScene(scene);
                        mainScreenController.setStage(stage);
                        mainScreenController.setContextHandler(this.contextHandler);
                        stage.show();
//            primaryStage.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (accountantModuleSelect) {
                        //go to accountant module
                    } else {
                        //callpopup
                    }
                }
            }
        }
    }

    @FXML
    private void onBuyModule() {
        ModuleType moduleType = null;

        if (plantsModuleSelect) {
            moduleType = ModuleType.PLANTS;
        } else if (animalsModuleSelect) {
            moduleType = ModuleType.ANIMALS;
        } else if (machineryModuleSelect) {
            moduleType = ModuleType.MACHINERY;
        } else if (accountantModuleSelect) {
            moduleType = ModuleType.ACCOUNTANT;
        }

        if (moduleType != null) {
            ModulePurchasePopupEventType modulePurchasePopupEventType = callModulePurchasePopup(moduleType);
            if (modulePurchasePopupEventType.equals(ModulePurchasePopupEventType.PURCHASE)) {
                //send zamowienie

                PurchaseDto purchaseDto = new PurchaseDto();
                purchaseDto.setPurchaser(contextHandler.getCurrentAccount());
                purchaseDto.setFarm(contextHandler.getCurrentFarm());
                purchaseDto.setModuleType(moduleType);

                purchaseRequest.save(purchaseDto.toEntity());
                callPopup(MessageType.CONFIRMATION, "Wyslano zamowienie na modul. Na adres email zostal przeslany indywidualny numerrachunku oraz kwota, ktroa nalezy wplacic aby uzyskac dostep.");
            } else if (modulePurchasePopupEventType.equals(ModulePurchasePopupEventType.ENTER)) {
                //gotoKEYENTERSCREEN

                callEnterKeyPopup();
            }
        }

    }

    @FXML
    private void onLogout() {

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

    public void checkKeyAction(KeyEvent keyEvent) {
        if (keyEvent.getEventType().equals(KeyEvent.KEY_PRESSED)) {
            switch (keyEvent.getCode()) {
                case NUMPAD1:
                    onPlantsModuleSelect();
                    break;
                case NUMPAD2:
                    onAnimalsModuleSelect();
                    break;
                case NUMPAD3:
                    onMachineryModuleSelect();
                    break;
                case NUMPAD4:
                    onAccountantModuleSelect();
                    break;
                case S:
                case ENTER:
                    onSelectModule();
                    break;
                case B:
                    onBuyModule();
                    break;
                case Q:
                    onLogout();
                    break;
            }
        }
    }

    public ModulePurchasePopupEventType callModulePurchasePopup(ModuleType moduleType) {
        Parent root = null;

        FXMLLoader innerLoader = new FXMLLoader();
        innerLoader.setLocation(this.getClass().getResource("/views/manager/ModulePurchasePopupScreen.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("lang");
        innerLoader.setResources(bundle);
        try {
            root = innerLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ModulePurchasePopupScreenController popupScreenController;
        popupScreenController = innerLoader.getController();

        Scene popupScene = new Scene(root);
        Stage newWindow = new Stage();

        newWindow.initStyle(StageStyle.UNDECORATED);
        newWindow.initStyle(StageStyle.TRANSPARENT);
        popupScene.setFill(Color.TRANSPARENT);

//       this.stage.getScene();
        newWindow.initOwner(scene.getWindow());
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.setScene(popupScene);
        newWindow.setResizable(false);

        popupScreenController.setScene(popupScene);
        popupScreenController.setStage(newWindow);

//        popupScreenController.setMessage(message);
        popupScreenController.init(moduleType);

//        switch (messageType) {
//            case ERROR:
//                popupScreenController.setImage("icons/error_symbol_icon.png");
//                popupScreenController.disableSecondButton();
//                break;
//            case WARNING:
//                popupScreenController.setImage("icons/warning_symbol_icon.png");
////                popupScreenController.disableSecondButton();
//                break;
//            case CONFIRMATION:
//                popupScreenController.setImage("icons/confirmation_symbol_icon.png");
//                popupScreenController.disableSecondButton();
//                break;
//            case QUESTION:
//                popupScreenController.setImage("icons/error_symbol_icon.png");
//                break;
//        }

        newWindow.showAndWait();

        return popupScreenController.getModulePurchasePopupEventType();
    }

    public ModulePurchasePopupEventType callEnterKeyPopup() {
        Parent root = null;

        FXMLLoader innerLoader = new FXMLLoader();
        innerLoader.setLocation(this.getClass().getResource("/views/manager/EnterLicenseKeyPopupScreen.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("lang");
        innerLoader.setResources(bundle);
        try {
            root = innerLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        EnterLicenseKeyPopupScreenController popupScreenController;
        popupScreenController = innerLoader.getController();

        Scene popupScene = new Scene(root);
        Stage newWindow = new Stage();

        newWindow.initStyle(StageStyle.UNDECORATED);
        newWindow.initStyle(StageStyle.TRANSPARENT);
        popupScene.setFill(Color.TRANSPARENT);

//       this.stage.getScene();
        newWindow.initOwner(scene.getWindow());
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.setScene(popupScene);
        newWindow.setResizable(false);

        popupScreenController.setScene(popupScene);
        popupScreenController.setStage(newWindow);

//        popupScreenController.setMessage(message);
//        popupScreenController.init(moduleType);


        newWindow.showAndWait();

        return popupScreenController.getModulePurchasePopupEventType();
    }
}
