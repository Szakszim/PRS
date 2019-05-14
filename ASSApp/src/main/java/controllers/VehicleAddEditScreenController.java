package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class VehicleAddEditScreenController extends AbstractScreenController {
    @FXML
    private AnchorPane notificationBackground;

    @FXML
    private Label notificationMessage;

    @FXML
    private Button save;

    @FXML
    private Button saveAndExit;

    @FXML
    private Button resign;

    @FXML
    private MenuButton printDocumentButton;

    @FXML
    private TextField vin;

    @FXML
    private TextField registrationNumber;

    @FXML
    private TextField productionYear;

    @FXML
    private ComboBox coowner;

    @FXML
    private ComboBox owner;

    @FXML
    private TextField model;

    @FXML
    private TextField brand;

    @FXML
    private TextField fuelType;

    @FXML
    private TextField engineCapacity;

    @FXML
    private TextField power;

    @FXML
    private TextField meterState;

    @FXML
    private TextField description;

    @FXML
    private DatePicker expiredDate;

    @FXML
    private CheckBox active;

//    ObservableList<CustomerFileRowModelDto> customers;
//
//    private Customer ownerShortcut;
//    private Customer coownerShortcut;
//    private Vehicle vehicle;
//    private RegistrationCard registrationCard;

    @FXML
    public void initialize() {

//        CustomerFileRepository customerFileRepository = new CustomerFileRepository();
//        customers = FXCollections.observableArrayList(customerFileRepository.getAll());
//
//        owner.setItems(customers);
//        coowner.setItems(customers);
//
//        this.ownerShortcut = new Customer();
//        this.coownerShortcut = new Customer();
//        this.vehicle = new Vehicle();
//        this.registrationCard = new RegistrationCard();
    }

    @FXML
    void onResign() {
///nachwile
//        navigateTo(ViewPath.VEHICLE_FILES_SCREEN);
    }

    @FXML
    void onSave() {
        save();
    }

    @FXML
    void onSaveAndExit() {
        save();
//        navigateTo(ViewPath.VEHICLE_FILES_SCREEN);
    }

    private void save() {
//
    }

    @Override
    public void initData(Integer objectId) {
//        VehicleFileRepository vehicleFileRepository = new VehicleFileRepository();
//        registrationCard = vehicleFileRepository.findREgistrationCardByVehicleId(objectId);
//        vehicle =registrationCard.getVehicle();

//        owner.getSelectionModel().select(registrationCard.getOwner()+" "+registrationCard.getOwner().getAddress().toString());
//        coowner.getSelectionModel().select(registrationCard.getCoowner()+" "+registrationCard.getCoowner().getAddress().toString());
//
//        vin.setText(registrationCard.getVin());
//        productionYear.setText(String.valueOf(registrationCard.getProductionYear()));
//        registrationNumber.setText(registrationCard.getRegistrationNumber());
//
//        brand.setText(registrationCard.getBrand());
//        model.setText(registrationCard.getModel());
//        engineCapacity.setText(registrationCard.getEngineCapacity().toString());
//        fuelType.setText(registrationCard.getFuelType());
//        power.setText(registrationCard.getPower().toString());
//        if(registrationCard.getVehicle().getMeterState()!=null){
//            meterState.setText(registrationCard.getVehicle().getMeterState().toString());
//        }
//        description.setText(registrationCard.getVehicle().getDescription());
//
//        Date date = registrationCard.getExpiredDate();
//        Instant instant = date.toInstant();
//        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
//        expiredDate.setValue(localDate);
//
//        active.setSelected(registrationCard.getActive());
    }

    public void navigateTo(String viewPath) {
        FXMLLoader innerLoader = new FXMLLoader();
        innerLoader.setLocation(this.getClass().getResource(viewPath));
//        ResourceBundle bundle = ResourceBundle.getBundle("gui.resources.lang");
//        loader.setResources(bundle);

        GridPane gridPane = null;
        try {
            gridPane = innerLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        VehicleFileScreenController controller = innerLoader.getController();
//        controller.setMainScreenController(mainScreenController);
//        set objects here

        mainScreenController.setView(gridPane);
    }

}
