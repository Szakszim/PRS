package controllers.manager.animals;

import dtos.other.AnimalToDeleteDto;
import dtos.rowmodels.AnimalRowModelDto;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import requests.AnimalHallRequest;

import java.net.URL;
import java.util.ResourceBundle;

public class AnimalsNotificationScreenController implements Initializable {

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
    private TableView<AnimalRowModelDto> animalsNotificationDataTable;
    @FXML
    private TableColumn<AnimalRowModelDto, String> dateColumn;
    @FXML
    private TableColumn<AnimalRowModelDto, String> senderColumn;
    @FXML
    private TableColumn<AnimalRowModelDto, String> topicColumn;
    @FXML
    private TableColumn<AnimalRowModelDto, String> statusColumn;
    @FXML
    private TableColumn<AnimalRowModelDto, String> extraColumn;


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
        if (animalsNotificationDataTable.getSelectionModel().getSelectedItem() != null) {
            AnimalToDeleteDto animalToDeleteDto = new AnimalToDeleteDto(animalsNotificationDataTable.getSelectionModel().getSelectedItem().getAnimalId(),animalsNotificationDataTable.getSelectionModel().getSelectedItem().getAnimalType());
            animalHallRequest.deleteAnimalByIdAndType(animalToDeleteDto);
        }

//        data = FXCollections.observableArrayList(animalHallRequest.getAnimalListByFarmId(1000000L));
        animalsNotificationDataTable.setItems(data);
        animalsNotificationDataTable.refresh();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        editButton.disableProperty().bind(animalsNotificationDataTable.getSelectionModel().selectedItemProperty().isNull());
        editLabelButton.disableProperty().bind(animalsNotificationDataTable.getSelectionModel().selectedItemProperty().isNull());
        deleteButton.disableProperty().bind(animalsNotificationDataTable.getSelectionModel().selectedItemProperty().isNull());
        deleteLabelButton.disableProperty().bind(animalsNotificationDataTable.getSelectionModel().selectedItemProperty().isNull());
        showButton.disableProperty().bind(animalsNotificationDataTable.getSelectionModel().selectedItemProperty().isNull());
        showLabelButton.disableProperty().bind(animalsNotificationDataTable.getSelectionModel().selectedItemProperty().isNull());

        dateColumn.setCellValueFactory(new PropertyValueFactory<AnimalRowModelDto, String>("number"));
        senderColumn.setCellValueFactory(new PropertyValueFactory<AnimalRowModelDto, String>("sexTypeBK"));
        topicColumn.setCellValueFactory(new PropertyValueFactory<AnimalRowModelDto, String>("raceTypeBK"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<AnimalRowModelDto, String>("raceTypeBK"));
        extraColumn.setCellValueFactory(new PropertyValueFactory<AnimalRowModelDto, String>("animalTypeBK"));

        animalHallRequest = new AnimalHallRequest();
//        data = FXCollections.observableArrayList(animalHallRequest.getAnimalListByFarmId(1000000L));

        animalsNotificationDataTable.setItems(data);
        animalsNotificationDataTable.getSelectionModel();

        animalsNotificationDataTable.setOnMousePressed((event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (event.getClickCount() == 2) {
//                    onModifyPositionButton();
                }
            }
        });
    }
}
