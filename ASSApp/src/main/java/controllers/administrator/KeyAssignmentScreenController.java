package controllers.administrator;

import dtos.rowmodels.KeyAssignmentRowModelDto;
import enums.ModuleType;
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
import requests.KeyAssignmentRequest;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class KeyAssignmentScreenController implements Initializable {

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
    private Button confirmButton;

    @FXML
    private Button confirmLabelButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button deleteLabelButton;


    @FXML
    private TableView<KeyAssignmentRowModelDto> keyAssignmentListDataTable;
    @FXML
    private TableColumn<KeyAssignmentRowModelDto, String> valueColumn;
    @FXML
    private TableColumn<KeyAssignmentRowModelDto, ModuleType> moduleTypeColumn;
    @FXML
    private TableColumn<KeyAssignmentRowModelDto, Date> licenseExpiredDateColumn;
    @FXML
    private TableColumn<KeyAssignmentRowModelDto, String> farmNameColumn;
    @FXML
    private TableColumn<KeyAssignmentRowModelDto, String> farmNumberColumn;
    @FXML
    private TableColumn<KeyAssignmentRowModelDto, String> purchaserNameColumn;
    @FXML
    private TableColumn<KeyAssignmentRowModelDto, Boolean> confirmationIndicatorColumn;

    public ObservableList<KeyAssignmentRowModelDto> data;
    private KeyAssignmentRequest keyAssignmentRequest;

    private void deleteSelected() {
        if (keyAssignmentListDataTable.getSelectionModel().getSelectedItem() != null) {
//            AnimalToDeleteDto animalToDeleteDto = new AnimalToDeleteDto(keyListDataTable.getSelectionModel().getSelectedItem().getId(), keyListDataTable.getSelectionModel().getSelectedItem().getAnimalTypeBK());
//            animalHallRequest.deleteAnimalByIdAndType(animalToDeleteDto);
        }

//        data = FXCollections.observableArrayList(animalHallRequest.getAnimalListByFarmId(1000000L));
        keyAssignmentListDataTable.setItems(data);
        keyAssignmentListDataTable.refresh();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        editButton.disableProperty().bind(keyAssignmentListDataTable.getSelectionModel().selectedItemProperty().isNull());
        editLabelButton.disableProperty().bind(keyAssignmentListDataTable.getSelectionModel().selectedItemProperty().isNull());
        confirmButton.disableProperty().bind(keyAssignmentListDataTable.getSelectionModel().selectedItemProperty().isNull());
        confirmLabelButton.disableProperty().bind(keyAssignmentListDataTable.getSelectionModel().selectedItemProperty().isNull());
        deleteButton.disableProperty().bind(keyAssignmentListDataTable.getSelectionModel().selectedItemProperty().isNull());
        deleteLabelButton.disableProperty().bind(keyAssignmentListDataTable.getSelectionModel().selectedItemProperty().isNull());

        valueColumn.setCellValueFactory(new PropertyValueFactory<KeyAssignmentRowModelDto, String>("value"));
        moduleTypeColumn.setCellValueFactory(new PropertyValueFactory<KeyAssignmentRowModelDto, ModuleType>("moduleType"));
        licenseExpiredDateColumn.setCellValueFactory(new PropertyValueFactory<KeyAssignmentRowModelDto, Date>("licenseExpiredDate"));
        farmNameColumn.setCellValueFactory(new PropertyValueFactory<KeyAssignmentRowModelDto, String>("farmName"));
        farmNumberColumn.setCellValueFactory(new PropertyValueFactory<KeyAssignmentRowModelDto, String>("farmNumber"));
        purchaserNameColumn.setCellValueFactory(new PropertyValueFactory<KeyAssignmentRowModelDto, String>("purchaserName"));
        confirmationIndicatorColumn.setCellValueFactory(new PropertyValueFactory<KeyAssignmentRowModelDto, Boolean>("confirmationIndicator"));


        keyAssignmentRequest = new KeyAssignmentRequest();
        data = FXCollections.observableArrayList(keyAssignmentRequest.findAllKeysWithFarmAndPurchaserData());

        keyAssignmentListDataTable.setItems(data);
        keyAssignmentListDataTable.getSelectionModel();

        keyAssignmentListDataTable.setOnMousePressed((event) -> {
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
    void onEditKey(ActionEvent event) {

    }

    @FXML
    void onConfirmKeyAssignment(ActionEvent event) {
        if (keyAssignmentListDataTable.getSelectionModel().getSelectedItem() != null) {
            if (!keyAssignmentListDataTable.getSelectionModel().getSelectedItem().getConfirmationIndicator()) {
                keyAssignmentRequest.confirmKeyAssignment(keyAssignmentListDataTable.getSelectionModel().getSelectedItem().getId());

                data = FXCollections.observableArrayList(keyAssignmentRequest.findAllKeysWithFarmAndPurchaserData());
                keyAssignmentListDataTable.setItems(data);
                keyAssignmentListDataTable.refresh();
            }
        }
    }

    @FXML
    void onDeleteKey(ActionEvent event) {

    }

}
