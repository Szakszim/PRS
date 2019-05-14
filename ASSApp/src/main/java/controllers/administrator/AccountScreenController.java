package controllers.administrator;

import dtos.other.AnimalToDeleteDto;
import dtos.rowmodels.AccountRowModelDto;
import dtos.rowmodels.AnimalRowModelDto;
import enums.AccountType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import requests.AccountRequest;
import requests.AnimalHallRequest;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class AccountScreenController implements Initializable {

    @FXML
    private Spinner<?> statusSpinner;

    @FXML
    private Spinner<?> moduleSpinner;

    @FXML
    private Button addButton;

    @FXML
    private Button addLabelButton;

    @FXML
    private Button editButton;

    @FXML
    private Button editLabelButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button deleteLabelButton;


    @FXML
    private TableView<AccountRowModelDto> accountListDataTable;
    @FXML
    private TableColumn<AccountRowModelDto, String> uniqueIdColumn;
    @FXML
    private TableColumn<AccountRowModelDto, String> mailColumn;
    @FXML
    private TableColumn<AccountRowModelDto, String> loginColumn;
    @FXML
    private TableColumn<AccountRowModelDto, AccountType> accountTypeColumn;
    @FXML
    private TableColumn<AccountRowModelDto, Date> passwordExpiredDateColumn;
    @FXML
    private TableColumn<AccountRowModelDto, String> userDataColumn;
    @FXML
    private TableColumn<AccountRowModelDto, Date> creationDateColumn;
    @FXML
    private TableColumn<AccountRowModelDto, Boolean> registrationIndicatorColumn;
    @FXML
    private TableColumn<AccountRowModelDto, Boolean> activeIndicatorColumn;

    public ObservableList<AccountRowModelDto> data;
    private AccountRequest accountRequest;

    private void deleteSelected() {
        if (accountListDataTable.getSelectionModel().getSelectedItem() != null) {
//            AnimalToDeleteDto animalToDeleteDto = new AnimalToDeleteDto(accountListDataTable.getSelectionModel().getSelectedItem().getId(), accountListDataTable.getSelectionModel().getSelectedItem().getAnimalTypeBK());
//            animalHallRequest.deleteAnimalByIdAndType(animalToDeleteDto);
        }

//        data = FXCollections.observableArrayList(animalHallRequest.getAnimalListByFarmId(1000000L));
        accountListDataTable.setItems(data);
        accountListDataTable.refresh();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        editButton.disableProperty().bind(accountListDataTable.getSelectionModel().selectedItemProperty().isNull());
        editLabelButton.disableProperty().bind(accountListDataTable.getSelectionModel().selectedItemProperty().isNull());
        deleteButton.disableProperty().bind(accountListDataTable.getSelectionModel().selectedItemProperty().isNull());
        deleteLabelButton.disableProperty().bind(accountListDataTable.getSelectionModel().selectedItemProperty().isNull());

        uniqueIdColumn.setCellValueFactory(new PropertyValueFactory<AccountRowModelDto, String>("uniqueId"));
        mailColumn.setCellValueFactory(new PropertyValueFactory<AccountRowModelDto, String>("mail"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<AccountRowModelDto, String>("login"));
        accountTypeColumn.setCellValueFactory(new PropertyValueFactory<AccountRowModelDto, AccountType>("accountType"));
        passwordExpiredDateColumn.setCellValueFactory(new PropertyValueFactory<AccountRowModelDto, Date>("passwordExpiredDate"));
        userDataColumn.setCellValueFactory(new PropertyValueFactory<AccountRowModelDto, String>("userData"));
        creationDateColumn.setCellValueFactory(new PropertyValueFactory<AccountRowModelDto, Date>("creationDate"));
        registrationIndicatorColumn.setCellValueFactory(new PropertyValueFactory<AccountRowModelDto, Boolean>("registrationConfirmationIndicator"));
        activeIndicatorColumn.setCellValueFactory(new PropertyValueFactory<AccountRowModelDto, Boolean>("activityIndicator"));

        accountRequest = new AccountRequest();
        data = FXCollections.observableArrayList(accountRequest.findAllAccountsWithPersonData());

        accountListDataTable.setItems(data);
        accountListDataTable.getSelectionModel();

        accountListDataTable.setOnMousePressed((event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (event.getClickCount() == 2) {
//                    onModifyPositionButton();
                }
            }
        });
    }

    @FXML
    void onAddKey(ActionEvent event) {

    }

    @FXML
    void onDeleteKey(ActionEvent event) {

    }

    @FXML
    void onEditKey(ActionEvent event) {

    }

}
