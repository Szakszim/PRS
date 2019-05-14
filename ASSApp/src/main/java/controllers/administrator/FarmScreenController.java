package controllers.administrator;

import dtos.rowmodels.FarmRowModelDto;
import dtos.rowmodels.FarmRowModelDto;
import entities.Address;
import enums.OwnerType;
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
import requests.FarmRequest;
import requests.KeyRequest;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class FarmScreenController implements Initializable {

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
    private TableView<FarmRowModelDto> farmListDataTable;
    @FXML
    private TableColumn<FarmRowModelDto, String> farmName1Column;
    @FXML
    private TableColumn<FarmRowModelDto, String> farmName2Column;
    @FXML
    private TableColumn<FarmRowModelDto, String> farmNumberColumn;
    @FXML
    private TableColumn<FarmRowModelDto, Address> farmAddressColumn;
    @FXML
    private TableColumn<FarmRowModelDto, String> ownerUniqueIdColumn;
    @FXML
    private TableColumn<FarmRowModelDto, OwnerType> ownerTypeColumn;
    @FXML
    private TableColumn<FarmRowModelDto, String> ownerName1Column;
    @FXML
    private TableColumn<FarmRowModelDto, String> ownerName2Column;
    @FXML
    private TableColumn<FarmRowModelDto, String> ownerName3Column;
    @FXML
    private TableColumn<FarmRowModelDto, String> ownerNipColumn;

    public ObservableList<FarmRowModelDto> data;
    private FarmRequest farmRequest;

    private void deleteSelected() {
        if (farmListDataTable.getSelectionModel().getSelectedItem() != null) {
//            AnimalToDeleteDto animalToDeleteDto = new AnimalToDeleteDto(farmListDataTable.getSelectionModel().getSelectedItem().getId(), farmListDataTable.getSelectionModel().getSelectedItem().getAnimalTypeBK());
//            animalHallRequest.deleteAnimalByIdAndType(animalToDeleteDto);
        }

//        data = FXCollections.observableArrayList(animalHallRequest.getAnimalListByFarmId(1000000L));
        farmListDataTable.setItems(data);
        farmListDataTable.refresh();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        editButton.disableProperty().bind(farmListDataTable.getSelectionModel().selectedItemProperty().isNull());
        editLabelButton.disableProperty().bind(farmListDataTable.getSelectionModel().selectedItemProperty().isNull());
        deleteButton.disableProperty().bind(farmListDataTable.getSelectionModel().selectedItemProperty().isNull());
        deleteLabelButton.disableProperty().bind(farmListDataTable.getSelectionModel().selectedItemProperty().isNull());

        farmName1Column.setCellValueFactory(new PropertyValueFactory<FarmRowModelDto, String>("farmName1"));
        farmName2Column.setCellValueFactory(new PropertyValueFactory<FarmRowModelDto, String>("farmName2"));
        farmNumberColumn.setCellValueFactory(new PropertyValueFactory<FarmRowModelDto, String>("farmNumber"));
        farmAddressColumn.setCellValueFactory(new PropertyValueFactory<FarmRowModelDto, Address>("farmAddress"));
        ownerUniqueIdColumn.setCellValueFactory(new PropertyValueFactory<FarmRowModelDto,String>("uniqueId"));
        ownerTypeColumn.setCellValueFactory(new PropertyValueFactory<FarmRowModelDto, OwnerType>("ownerType"));
        ownerName1Column.setCellValueFactory(new PropertyValueFactory<FarmRowModelDto, String>("name1"));
        ownerName2Column.setCellValueFactory(new PropertyValueFactory<FarmRowModelDto, String>("name2"));
        ownerName3Column.setCellValueFactory(new PropertyValueFactory<FarmRowModelDto, String>("name3"));
        ownerNipColumn.setCellValueFactory(new PropertyValueFactory<FarmRowModelDto, String>("nip"));


        farmRequest = new FarmRequest();
        data = FXCollections.observableArrayList(farmRequest.findAllFarmsWithOwnerData());

        farmListDataTable.setItems(data);
        farmListDataTable.getSelectionModel();

        farmListDataTable.setOnMousePressed((event) -> {
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
