package controllers.manager.animals;

import dtos.other.AnimalToDeleteDto;
import dtos.rowmodels.AnimalRowModelDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import requests.AnimalHallRequest;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class CowScreenController implements Initializable {

    @FXML
    private Label screenHeader;

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
    private Button showButton;

    @FXML
    private Button showLabelButton;

    @FXML
    private Spinner<?> hallFilterSpinner;

    @FXML
    private Spinner<?> typeFilterSpinner;

    @FXML
    private Label typeFilterLabel;

    @FXML
    private Label hallFilterLabel;

    @FXML
    private TableView<AnimalRowModelDto> animalListDataTable;
    @FXML
    private TableColumn<AnimalRowModelDto, String> numberColumn;
    @FXML
    private TableColumn<AnimalRowModelDto, String> sexColumn;
    @FXML
    private TableColumn<AnimalRowModelDto, String> raceColumn;
    @FXML
    private TableColumn<AnimalRowModelDto, String> typeColumn;
    @FXML
    private TableColumn<AnimalRowModelDto, String> motherNumberColumn;
    @FXML
    private TableColumn<AnimalRowModelDto, Date> birthDateColumn;
    @FXML
    private TableColumn<AnimalRowModelDto, String> hallNumberColumn;
    @FXML
    private TableColumn<AnimalRowModelDto, String> descriptionColumn;


    public ObservableList<AnimalRowModelDto> data;

    @FXML
    void onAddKey() {

    }

    @FXML
    void onDeleteKey() {
        deleteSelected();
    }

    @FXML
    void onEditKey() {

    }

    @FXML
    void onShowKey() {

    }

    private AnimalHallRequest animalHallRequest;

    private void deleteSelected() {
        if (animalListDataTable.getSelectionModel().getSelectedItem() != null) {
            AnimalToDeleteDto animalToDeleteDto = new AnimalToDeleteDto(animalListDataTable.getSelectionModel().getSelectedItem().getAnimalId(), animalListDataTable.getSelectionModel().getSelectedItem().getAnimalType());
            animalHallRequest.deleteAnimalByIdAndType(animalToDeleteDto);
        }

        data = FXCollections.observableArrayList(animalHallRequest.getAnimalListByFarmId(1000000L));
        animalListDataTable.setItems(data);
        animalListDataTable.refresh();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        editButton.disableProperty().bind(animalListDataTable.getSelectionModel().selectedItemProperty().isNull());
        editLabelButton.disableProperty().bind(animalListDataTable.getSelectionModel().selectedItemProperty().isNull());
        deleteButton.disableProperty().bind(animalListDataTable.getSelectionModel().selectedItemProperty().isNull());
        deleteLabelButton.disableProperty().bind(animalListDataTable.getSelectionModel().selectedItemProperty().isNull());
        showButton.disableProperty().bind(animalListDataTable.getSelectionModel().selectedItemProperty().isNull());
        showLabelButton.disableProperty().bind(animalListDataTable.getSelectionModel().selectedItemProperty().isNull());

        numberColumn.setCellValueFactory(new PropertyValueFactory<AnimalRowModelDto, String>("number"));
        sexColumn.setCellValueFactory(new PropertyValueFactory<AnimalRowModelDto, String>("sexTypeBK"));
        raceColumn.setCellValueFactory(new PropertyValueFactory<AnimalRowModelDto, String>("raceTypeBK"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<AnimalRowModelDto, String>("animalTypeBK"));
        motherNumberColumn.setCellValueFactory(new PropertyValueFactory<AnimalRowModelDto, String>("motherNumber"));
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<AnimalRowModelDto, Date>("birthDate"));
        hallNumberColumn.setCellValueFactory(new PropertyValueFactory<AnimalRowModelDto, String>("hallNumber"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<AnimalRowModelDto, String>("description"));

        animalHallRequest = new AnimalHallRequest();
        data = FXCollections.observableArrayList(animalHallRequest.getAnimalListByFarmId(1000000L));

        animalListDataTable.setItems(data);
        animalListDataTable.getSelectionModel();

        animalListDataTable.setOnMousePressed((event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (event.getClickCount() == 2) {
//                    onModifyPositionButton();
                }
            }
        });
    }
}
