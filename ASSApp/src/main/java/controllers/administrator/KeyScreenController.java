package controllers.administrator;

import dtos.rowmodels.KeyAssignmentRowModelDto;
import dtos.rowmodels.KeyRowModelDto;
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
import requests.KeyRequest;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class KeyScreenController implements Initializable {

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
    private TableView<KeyRowModelDto> keyListDataTable;
    @FXML
    private TableColumn<KeyRowModelDto, String> valueColumn;
    @FXML
    private TableColumn<KeyRowModelDto, Date> creationDateColumn;
    @FXML
    private TableColumn<KeyRowModelDto, Boolean> activityIndicatorColumn;
    @FXML
    private TableColumn<KeyRowModelDto, Date> keyExpiredDateColumn;
    @FXML
    private TableColumn<KeyRowModelDto, Boolean> usageIndicatorColumn;
    @FXML
    private TableColumn<KeyRowModelDto, Date> usageDateColumn;


    public ObservableList<KeyRowModelDto> data;
    private KeyRequest keyRequest;

    private void deleteSelected() {
        if (keyListDataTable.getSelectionModel().getSelectedItem() != null) {
//            AnimalToDeleteDto animalToDeleteDto = new AnimalToDeleteDto(keyListDataTable.getSelectionModel().getSelectedItem().getId(), keyListDataTable.getSelectionModel().getSelectedItem().getAnimalTypeBK());
//            animalHallRequest.deleteAnimalByIdAndType(animalToDeleteDto);
        }

//        data = FXCollections.observableArrayList(animalHallRequest.getAnimalListByFarmId(1000000L));
        keyListDataTable.setItems(data);
        keyListDataTable.refresh();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        editButton.disableProperty().bind(keyListDataTable.getSelectionModel().selectedItemProperty().isNull());
        editLabelButton.disableProperty().bind(keyListDataTable.getSelectionModel().selectedItemProperty().isNull());
        deleteButton.disableProperty().bind(keyListDataTable.getSelectionModel().selectedItemProperty().isNull());
        deleteLabelButton.disableProperty().bind(keyListDataTable.getSelectionModel().selectedItemProperty().isNull());

        valueColumn.setCellValueFactory(new PropertyValueFactory<KeyRowModelDto, String>("value"));
        creationDateColumn.setCellValueFactory(new PropertyValueFactory<KeyRowModelDto, Date>("creationDate"));
        activityIndicatorColumn.setCellValueFactory(new PropertyValueFactory<KeyRowModelDto, Boolean>("activityIndicator"));
        keyExpiredDateColumn.setCellValueFactory(new PropertyValueFactory<KeyRowModelDto, Date>("keyExpiredDate"));
        usageIndicatorColumn.setCellValueFactory(new PropertyValueFactory<KeyRowModelDto, Boolean>("usageIndicator"));
        usageDateColumn.setCellValueFactory(new PropertyValueFactory<KeyRowModelDto, Date>("usageData"));

        keyRequest = new KeyRequest();
        data = FXCollections.observableArrayList(keyRequest.findAllKeys());

        keyListDataTable.setItems(data);
        keyListDataTable.getSelectionModel();

        keyListDataTable.setOnMousePressed((event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (event.getClickCount() == 2) {
//                    onModifyPositionButton();
                }
            }
        });
    }

    @FXML
    void onAddKey(ActionEvent event) {
        keyRequest.generateKey();
        data = FXCollections.observableArrayList(keyRequest.findAllKeys());
        keyListDataTable.setItems(data);
        keyListDataTable.refresh();
    }

    @FXML
    void onDeleteKey(ActionEvent event) {

    }

    @FXML
    void onEditKey(ActionEvent event) {

    }

}
