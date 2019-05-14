package controllers.administrator;

import dtos.rowmodels.PurchaseRowModelDto;
import entities.Purchase;
import enums.ModuleType;
import javafx.beans.property.ReadOnlyStringWrapper;
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
import requests.PurchaseRequest;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class PurchaseScreenController implements Initializable {

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
    private TableView<PurchaseRowModelDto> purchaseListDataTable;
    @FXML
    private TableColumn<PurchaseRowModelDto, String> farmNameColumn;
    @FXML
    private TableColumn<PurchaseRowModelDto, String> farmNumberColumn;
    @FXML
    private TableColumn<PurchaseRowModelDto, String> purchaserNameColumn;
    @FXML
    private TableColumn<PurchaseRowModelDto, String> uniqueIdColumn;
    @FXML
    private TableColumn<PurchaseRowModelDto, ModuleType> moduleTypeColumn;
    @FXML
    private TableColumn<PurchaseRowModelDto, Date> purchaseDateColumn;
    @FXML
    private TableColumn<PurchaseRowModelDto, String> paymentIndicatorColumn;
    @FXML
    private TableColumn<PurchaseRowModelDto, Date> paymentDateColumn;


    public ObservableList<PurchaseRowModelDto> data;
    private PurchaseRequest purchaseRequest;

    private void deleteSelected() {
        if (purchaseListDataTable.getSelectionModel().getSelectedItem() != null) {
//            AnimalToDeleteDto animalToDeleteDto = new AnimalToDeleteDto(purchaseListDataTable.getSelectionModel().getSelectedItem().getId(), purchaseListDataTable.getSelectionModel().getSelectedItem().getAnimalTypeBK());
//            animalHallRequest.deleteAnimalByIdAndType(animalToDeleteDto);
        }

//        data = FXCollections.observableArrayList(animalHallRequest.getAnimalListByFarmId(1000000L));
        purchaseListDataTable.setItems(data);
        purchaseListDataTable.refresh();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        editButton.disableProperty().bind(purchaseListDataTable.getSelectionModel().selectedItemProperty().isNull());
        editLabelButton.disableProperty().bind(purchaseListDataTable.getSelectionModel().selectedItemProperty().isNull());
        confirmButton.disableProperty().bind(purchaseListDataTable.getSelectionModel().selectedItemProperty().isNull());
        confirmLabelButton.disableProperty().bind(purchaseListDataTable.getSelectionModel().selectedItemProperty().isNull());
        deleteButton.disableProperty().bind(purchaseListDataTable.getSelectionModel().selectedItemProperty().isNull());
        deleteLabelButton.disableProperty().bind(purchaseListDataTable.getSelectionModel().selectedItemProperty().isNull());

        farmNameColumn.setCellValueFactory(new PropertyValueFactory<PurchaseRowModelDto, String>("farmName"));
        farmNumberColumn.setCellValueFactory(new PropertyValueFactory<PurchaseRowModelDto, String>("farmNumber"));
        purchaserNameColumn.setCellValueFactory(new PropertyValueFactory<PurchaseRowModelDto, String>("purchaserName"));
        uniqueIdColumn.setCellValueFactory(new PropertyValueFactory<PurchaseRowModelDto, String>("uniqueId"));
        moduleTypeColumn.setCellValueFactory(new PropertyValueFactory<PurchaseRowModelDto, ModuleType>("moduleType"));
        purchaseDateColumn.setCellValueFactory(new PropertyValueFactory<PurchaseRowModelDto, Date>("purchaseDate"));

//        paymentIndicatorColumn = new TableColumn<PurchaseRowModelDto,Boolean>("paymentIndicator");
        paymentIndicatorColumn.setCellValueFactory(cellData -> {
            boolean indicator = cellData.getValue().getPaymentIndicator();
            String indicatorAsString;
            indicatorAsString = indicator == Boolean.TRUE ? "TAK" : "NIE";
//            if (indicator == true) {
//                indicatorAsString = "Male";
//            } else {
//                indicatorAsString = "Female";
//            }
            return new ReadOnlyStringWrapper(indicatorAsString);
        });
//        paymentIndicatorColumn.setCellValueFactory(new PropertyValueFactory<PurchaseRowModelDto, Boolean>("paymentIndicator"));


        paymentDateColumn.setCellValueFactory(new PropertyValueFactory<PurchaseRowModelDto, Date>("paymentDate"));


        purchaseRequest = new PurchaseRequest();
//        try{
        data = FXCollections.observableArrayList(purchaseRequest.findAllPurchasesWithPurchaserAndFarmData());
//        }catch (Exception e){
//            System.out.println(e.getStackTrace());
//            System.out.println("UPS");
//            throw e;
//        }

        purchaseListDataTable.setItems(data);
        purchaseListDataTable.getSelectionModel();

        purchaseListDataTable.setOnMousePressed((event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (event.getClickCount() == 2) {
//                    onModifyPositionButton();
                }
            }
        });
    }

    @FXML
    void onAddPurchase(ActionEvent event) {

    }

    @FXML
    void onConfirmPayment(ActionEvent event) {
        if (purchaseListDataTable.getSelectionModel().getSelectedItem() != null) {
            if (!purchaseListDataTable.getSelectionModel().getSelectedItem().getPaymentIndicator()) {
                purchaseRequest.confirmPurchasePayment(purchaseListDataTable.getSelectionModel().getSelectedItem().getId());

                data = FXCollections.observableArrayList(purchaseRequest.findAllPurchasesWithPurchaserAndFarmData());
                purchaseListDataTable.setItems(data);
                purchaseListDataTable.refresh();
            }
        }
    }

    @FXML
    void onEditPurchase(ActionEvent event) {

    }

    @FXML
    void onDeletePurchase(ActionEvent event) {

    }

}
