package controllers.general;

import context.ContextHandler;
import controllers.administrator.AdministratorMainScreenController;
import controllers.manager.OwnerModuleSelectScreenController;
import dtos.other.AuthenticationContextDto;
import entities.Connection;
import enums.AccountType;
import enums.ConnectionType;
import enums.MessageType;
import enums.SelectionPopupEventType;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import lombok.Getter;
import lombok.Setter;
import others.SelectionPopupEvent;
import requests.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginScreenController implements Initializable {

    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginButton;
    @FXML
    private Button loginLabelButton;
    @FXML
    private Button remindButton;
    @FXML
    private Button remindLabelButton;
    @FXML
    private Button registerButton;
    @FXML
    private Button registerLabelButton;
    @FXML
    private Label title;
    @FXML
    @Setter
    private Stage stage;
    @FXML
    @Setter
    private Scene scene;
    @Getter
    @Setter
    private ContextHandler contextHandler;

    private ConnectionRequest connectionRequest;

    private Boolean loading;//TODO for a moment
    LoadingBarScreenController controller;

    private static final String ACCOUNT_INFORMATION_FAIL_MSG = "We were unable to obtain the account information you are attempting to log in to. Please try again later or contact the administrator.";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contextHandler = new ContextHandler();
        connectionRequest = new ConnectionRequest();

        showLoadingBar.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t) {
               controller = (LoadingBarScreenController) showLoadingBar.getValue();
            }
        });


    }

    @FXML
    private void onLogin() {
//        LoadingBarScreenController controller = callLoadingBar();//TODO for a moment
        loading = Boolean.TRUE;
//        new Thread(showLoadingBar).start();
        callLoadingBar();
//        controller.getStage().close();
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        AccountRequest accountRequest = new AccountRequest();
        PersonRequest personRequest = new PersonRequest();
        FarmRequest farmRequest = new FarmRequest();

        AuthenticationContextDto authenticationContextDto = authenticationRequest.loginUser(login.getText(), password.getText());
        if (authenticationContextDto.getLoginStatus() == Boolean.TRUE) {

            //TODO ask server about details data for account, person and farm
            contextHandler.setCurrentAccount(accountRequest.findById(authenticationContextDto.getAccountId()));
            contextHandler.setCurrentPerson(personRequest.getPersonDataByAccountId(authenticationContextDto.getAccountId()));

            if (authenticationContextDto.getAccountType().equals(AccountType.ADMINISTRATOR)) {
                loading = Boolean.FALSE;//TODO for a moment
//                controller.setLoadingState(false);
//                controller.getStage().close();

                goToAdminPanel();
            } else {
                List<Connection> connectionsList = connectionRequest.getAllConnectionsByUserId(contextHandler.getCurrentAccount().getId());
                loading = Boolean.FALSE;//TODO for a moment
//                controller.setLoadingState(false);
//                controller.getStage().close();

                if (connectionsList.isEmpty()) {

                    Boolean answer = callPopup(MessageType.QUESTION, "Dla tego konta nie zostala przypisana zadna farma ani zatrudnienie, Czy chcesz stworzyć własne gospodarstwo? Wciśniecie przycisku nie spowoduje autmatyczne wylogowanie.");
                    if (answer == Boolean.TRUE) {
                        goToFarmCreationPage();
                    } else {
                       //TODO GO TO LOGIN PAGE
                        //logout alg
//                   goToLoginPage();
                    }
                } else {

                    if(connectionsList.size()==1){
                        contextHandler.setCurrentFarm(connectionsList.get(0).getFarm());
                        if(connectionsList.get(0).getConnectionType().equals(ConnectionType.MANAGER)){
                            goToOwnerPanel();
                        }else{
//                            goToWorkerPanel();
                        }
                    }else{
                        SelectionPopupEvent selectionPopupEvent = callSelectionPopup("Select a farm to manage or work on it.", connectionsList);
                        if(selectionPopupEvent.getSelectionPopupEventType().equals(SelectionPopupEventType.SELECT)){
                            contextHandler.setCurrentFarm(connectionsList.get(selectionPopupEvent.getSelectedRow()).getFarm());
                            if(connectionsList.get(selectionPopupEvent.getSelectedRow()).getConnectionType().equals(ConnectionType.MANAGER)){
                                goToOwnerPanel();
                            }else{
//                                goToWorkPanel();
                            }
                        }else{
                            if(selectionPopupEvent.getSelectionPopupEventType().equals(SelectionPopupEventType.CREATE)){
                                goToFarmCreationPage();
                            }else{
//                                goToLoginScreen();
                            }
                        }
                    }
                }
            }

        } else {
            loading = Boolean.FALSE;//TODO for a moment
//            controller.setLoadingState(false);
//            controller.getStage().close();

            if (callPopup(MessageType.ERROR, authenticationContextDto.getMessage())) {
                clearFields();
            }
        }
    }

    @FXML
    private void onRemindPassword() {
//        FXMLLoader innerLoader = new FXMLLoader();
//        innerLoader.setLocation(this.getClass().getResource("/views/administrator/AdministratorMainScreen.fxml"));
//        ResourceBundle bundle = ResourceBundle.getBundle("lang");
//        innerLoader.setResources(bundle);
//        try {
//            Parent innerRoot = innerLoader.load();
//            AdministratorMainScreenController controller = innerLoader.getController();
//
//            scene.setRoot(innerRoot);
//            stage.setScene(scene);
//            stage.setMaximized(true);
//            controller.setScene(scene);
//            controller.setStage(stage);
//            stage.show();
//
//            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//                public void handle(WindowEvent we) {
//                    System.out.println("Stage is closing");
////                    checkConnection = Boolean.FALSE;
//                }
//            });
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        Boolean test = Boolean.TRUE;
//
//        Platform.runLater(() ->callLoadingBar(test));

        LoadingBarScreenController controller = callLoadingBar();
//        controller.getStage().close();

    }

    @FXML
    private void onSignUp() {
        FXMLLoader innerLoader = new FXMLLoader();
        innerLoader.setLocation(this.getClass().getResource("/views/general/RegistrationScreen.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("lang");
        innerLoader.setResources(bundle);
        try {
            Parent innerRoot = innerLoader.load();
            RegistrationScreenController registrationScreenController = innerLoader.getController();

            scene.setRoot(innerRoot);
            stage.setScene(scene);
            stage.setMaximized(true);
            registrationScreenController.setScene(scene);
            registrationScreenController.setStage(stage);
            stage.show();

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    System.out.println("Stage is closing");
//                    checkConnection = Boolean.FALSE;
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkKeyAction(KeyEvent keyEvent) {
        //TODO CHANGE KEYS
        if (keyEvent.getEventType().equals(KeyEvent.KEY_PRESSED)) {
            switch (keyEvent.getCode()) {
                case ENTER:
                    onLogin();
//                    callPopup("Aplikacja przestała działać poprawnie. W celu zgłoszenia usterki skontaktuj sie z administratorem.");
                    break;
                case A:
                    onRemindPassword();
                    break;
                case B:
                    onSignUp();
                    break;
            }
        }
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
                popupScreenController.disableSecondButton();
                break;
            case WARNING:
                popupScreenController.setImage("icons/warning_symbol_icon.png");
//                popupScreenController.disableSecondButton();
                break;
            case CONFIRMATION:
                popupScreenController.setImage("icons/confirmation_symbol_icon.png");
                popupScreenController.disableSecondButton();
                break;
            case QUESTION:
                popupScreenController.setImage("icons/error_symbol_icon.png");
                break;
        }

        newWindow.showAndWait();

        return popupScreenController.getAnswer();
    }

    public SelectionPopupEvent callSelectionPopup(String message, Object list) {
        Parent root = null;

        FXMLLoader innerLoader = new FXMLLoader();
        innerLoader.setLocation(this.getClass().getResource("/views/general/ConnectionSelectPopupScreen.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("lang");
        innerLoader.setResources(bundle);
        try {
            root = innerLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ConnectionSelectPopupScreenController popupScreenController;
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
        popupScreenController.init((List<Connection>) list);

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

        return popupScreenController.getSelectionPopupEvent();
    }

    private void clearFields() {
        login.clear();
        password.clear();
    }

    private void goToOwnerPanel() {
        FXMLLoader innerLoader = new FXMLLoader();
        innerLoader.setLocation(this.getClass().getResource("/views/manager/OwnerModuleSelectScreen.fxml"));

//        ResourceBundle bundle = ResourceBundle.getBundle("gui.resources.lang");
//        innerLoader.setResources(bundle);
        try {
            Parent innerRoot = innerLoader.load();
            OwnerModuleSelectScreenController moduleSelectScreenController = innerLoader.getController();
//            mainScreenController.setClient(client);
//            mainScreenController.setLocalDatabase(localDatabase);
//            stage.setTitle("PasswordsManager 1.0.0");
//            Scene scene = new Scene()
//                    String a = accountDto.getEmployee().getCompany().getName1() + " "+accountDto.getEmployee().getCompany().getName2();
//                    String b = accountDto.getEmployee().getDivision().getName1();
//            String c = accountDto.getEmployee().getForename()+ " "+accountDto.getEmployee().getSurname();
//                    mainScreenController.setContextView(a, b, c);
            scene.setRoot(innerRoot);
            stage.setScene(scene);
            stage.setMaximized(true);
            moduleSelectScreenController.setScene(scene);
            moduleSelectScreenController.setStage(stage);
            moduleSelectScreenController.setContextHandler(contextHandler);
            moduleSelectScreenController.initData();
            moduleSelectScreenController.callPopup(MessageType.CONFIRMATION, "Hello " + contextHandler.getCurrentPerson().getFirstName() + ", enjoy Your work!");

            stage.show();

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    System.out.println("Stage is closing");
//                    checkConnection = Boolean.FALSE;
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void goToAdminPanel() {
        FXMLLoader innerLoader = new FXMLLoader();
        innerLoader.setLocation(this.getClass().getResource("/views/administrator/AdministratorMainScreen.fxml"));

        try {
            Parent innerRoot = innerLoader.load();
            AdministratorMainScreenController controller = innerLoader.getController();

            scene.setRoot(innerRoot);
            stage.setScene(scene);
            stage.setMaximized(true);
            controller.setScene(scene);
            controller.setStage(stage);
            stage.show();

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    System.out.println("Stage is closing");
//                    checkConnection = Boolean.FALSE;
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void goToFarmCreationPage() {
        FXMLLoader innerLoader = new FXMLLoader();
        innerLoader.setLocation(this.getClass().getResource("/views/general/FarmAddEditScreen.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("lang");
        innerLoader.setResources(bundle);
        try {
            Parent innerRoot = innerLoader.load();
            FarmAddEditScreenController controller = innerLoader.getController();

            scene.setRoot(innerRoot);
            stage.setScene(scene);
            stage.setMaximized(true);
            controller.setScene(scene);
            controller.setStage(stage);
            controller.setContextHandler(this.contextHandler);
            stage.show();

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    System.out.println("Stage is closing");
//                    checkConnection = Boolean.FALSE;
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Task showLoadingBar = new Task<LoadingBarScreenController>() {
        @Override
        protected LoadingBarScreenController call() throws Exception {

            LoadingBarScreenController controller = callLoadingBar();
//            while(loading){
//                System.out.println("ksdncl");
//            }
//            System.out.println("koniec");
//            controller.getStage().close();
            return controller;
        }
    };

    Task<Void> task = new Task<Void>() {
        @Override
        public Void call() throws InterruptedException {
            while (loading) {
                for (int i = 1; i <= 50000000; i++) {
                    updateProgress(i, 50000000);
//                    Thread.sleep(200);
                    if(!loading){
                        break;
                    }
                }
                updateProgress(50000000,50000000);
            }
            updateProgress(0, 50000000);
                    return null ;
        }
    };


    public LoadingBarScreenController callLoadingBar() {
        Parent root = null;

        FXMLLoader innerLoader = new FXMLLoader();
        innerLoader.setLocation(this.getClass().getResource("/views/general/LoadingBarScreen.fxml"));
        try {
            root = innerLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

//       LoadingBarScreenController controller;
        controller = innerLoader.getController();

        Scene popupScene = new Scene(root);
        Stage newWindow = new Stage();

        newWindow.initStyle(StageStyle.UNDECORATED);
        newWindow.initStyle(StageStyle.TRANSPARENT);
        popupScene.setFill(Color.TRANSPARENT);
        newWindow.setMaximized(Boolean.TRUE);
//        newWindow.setFullScreen(Boolean.TRUE);

//       this.stage.getScene();
        newWindow.initOwner(scene.getWindow());
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.setScene(popupScene);
        newWindow.setResizable(false);

        controller.setScene(popupScene);
        controller.setStage(newWindow);
        controller.activateProgressBar(task);
        task.setOnSucceeded(event -> {
//            controller.
            controller.getStage().close();
//            controller=null;
            loading = Boolean.FALSE;
//            startButton.setDisable(false);
        });

        Thread thread = new Thread(task);
        thread.start();


        newWindow.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.out.println("Stage is closing3");
//                    loginScreenController.checkConnection=Boolean.FALSE;
            }
        });

//        controller.setLoadingState(cond);

//        popupScreenController.setMessage(message);

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

        newWindow.show();

//        return popupScreenController.getAnswer();
        return controller;
    }

}
