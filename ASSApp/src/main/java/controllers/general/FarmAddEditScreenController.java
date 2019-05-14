package controllers.general;

//import com.google.gson.Gson;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.GMapMouseEvent;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.shapes.Polygon;
import com.lynden.gmapsfx.shapes.PolygonOptions;
import context.ContextHandler;
import controllers.manager.animals.HallScreenController;
import controllers.manager.animals.OwnerAnimalsModuleMainScreenController;
import dtos.base.*;
import entities.*;
import enums.*;
import enums.DictionaryType;
import helpers.LangHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;
import providers.DictionaryProvider;
import requests.*;
import utils.DateUtil;
import utils.StringUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

//import com.lynden.gmapsfx.javascript.object.MapType;


public class FarmAddEditScreenController implements Initializable, MapComponentInitializedListener {

    public static final String RESIGN_WARNING_QUESTION = "Unsaved changes will be lost. Are you sure you want to resign?";

    @FXML
    private Label breadcrumb;

    @FXML
    private Button resetButton;

    @FXML
    private Button resetButtonLabel;

    @FXML
    private GoogleMapView mapView;

    @FXML
    private TextField farmName1;

    @FXML
    private TextField farmName2;

    @FXML
    private TextField farmNumber;

    @FXML
    private TextField farmCountry;

    @FXML
    private TextField farmCity;

    @FXML
    private Spinner<Dictionary> farmStreetType;

    @FXML
    private TextField farmStreet;

    @FXML
    private TextField farmHouseNumber;

    @FXML
    private TextField farmLocalNumber;

    @FXML
    private TextField farmPostCode;

    @FXML
    private Spinner<OwnerType> ownerType;

    @FXML
    private TextField ownerName1;

    @FXML
    private TextField ownerName2;

    @FXML
    private TextField ownerName3;

    @FXML
    private TextField ownerNip;

    @FXML
    private TextField ownerCountry;

    @FXML
    private TextField ownerCity;

    @FXML
    private Spinner<Dictionary> ownerStreetType;

    @FXML
    private TextField ownerStreet;

    @FXML
    private TextField ownerHouseNumber;

    @FXML
    private TextField ownerLocalNumber;

    @FXML
    private TextField ownerPostCode;

    @FXML
    private TextField ownerEmail;

    @FXML
    private TextField ownerCountryCodePhone;

    @FXML
    private TextField ownerCodePhone;

    @FXML
    private TextField ownerPhone;

    @FXML
    private TextField ownerCountryCodeFax;

    @FXML
    private TextField ownerCodeFax;

    @FXML
    private TextField ownerFax;

    @FXML
    private TextField ownerCountryCodeCellphone;

    @FXML
    private TextField ownerCellphone;

    @FXML
    @Setter
    private Stage stage;

    @FXML
    @Setter
    private Scene scene;

    @Setter
    @Getter
    private ContextHandler contextHandler;

    private GoogleMap map;

    public ObservableList<OwnerType> list = FXCollections.observableArrayList();
    public ObservableList<Dictionary> dictionaryPositionList = FXCollections.observableArrayList();

    private FarmDto farmDto;
    private AddressDto farmAddressDto;
    private OwnerDto ownerDto;
    private AddressDto ownerAddressDto;
    private ContactDto ownerContactDto;

    private ConnectionDto connectionDto;

    private AddressRequest addressRequest;
    private ContactRequest contactRequest;
    private OwnerRequest ownerRequest;
    private FarmRequest farmRequest;
    private ConnectionRequest connectionRequest;
    private AuthenticationRequest authenticationRequest;

    private LatLong latLong;


    //    @Getter
//    @Setter
    ArrayList<LatLong> points = new ArrayList<>();
    //    @Getter
//    @Setter
//    MVCArray pmvc = new MVCArray();
    PolygonOptions polygOpts;
    Polygon pg;


//    @Setter
//    private OwnerAnimalsModuleMainScreenController ownerAnimalsModuleMainScreenController;

//    @FXML
//    @Setter
//    private Stage stage;
//
//    @FXML
//    @Setter
//    private Scene scene;
//
//    public void setView(GridPane gridPane) {
//        stackPane.getChildren().clear();
//        stackPane.getChildren().add(gridPane);
//    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ownerCountryCodePhone.textProperty().addListener((observable, oldValue, newValue) -> {
            ownerCountryCodeFax.setText(newValue);
            ownerCountryCodeCellphone.setText(newValue);
        });
        ownerCountryCodeFax.textProperty().addListener((observable, oldValue, newValue) -> {
            ownerCountryCodePhone.setText(newValue);
            ownerCountryCodeCellphone.setText(newValue);
        });
        ownerCountryCodeCellphone.textProperty().addListener((observable, oldValue, newValue) -> {
            ownerCountryCodePhone.setText(newValue);
            ownerCountryCodeFax.setText(newValue);
        });

        ownerCodePhone.textProperty().addListener((observable, oldValue, newValue) -> {
            ownerCodeFax.setText(newValue);
        });
        ownerCodeFax.textProperty().addListener((observable, oldValue, newValue) -> {
            ownerCodePhone.setText(newValue);
        });

        ownerType.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(OwnerType.INDYVIDUAL)) {
                Boolean answer = callPopup(MessageType.QUESTION, "Czy chcesz automatycznie uzupelnić dane wlasciciela gospodarstwa Twoimi danymi konta?");
                if (answer == Boolean.TRUE) {
                    fillOwnersDataFromCurrentUserData();
                }
            }
        });


        list = FXCollections.observableArrayList(OwnerType.INDYVIDUAL, OwnerType.CONCERN);
        SpinnerValueFactory<OwnerType> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<OwnerType>(list);
        valueFactory.setValue(OwnerType.CONCERN);
        ownerType.setValueFactory(valueFactory);

        DictionaryProvider dictionaryProvider = new DictionaryProvider();
//        dictionaryProvider.getEntry(DictionaryType.STREET_TYPE).getDictionary();//.findDictionaryByBusinessCode("000");
        dictionaryPositionList = FXCollections.observableArrayList(dictionaryProvider.getEntry(DictionaryType.STREET_TYPE).getDictionary());
        SpinnerValueFactory<Dictionary> farmStreetTypeValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<Dictionary>(dictionaryPositionList);
        SpinnerValueFactory<Dictionary> ownerStreetTypeValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<Dictionary>(dictionaryPositionList);
        farmStreetTypeValueFactory.setValue(dictionaryPositionList.get(0));
        ownerStreetTypeValueFactory.setValue(dictionaryPositionList.get(0));
        farmStreetType.setValueFactory(farmStreetTypeValueFactory);
        ownerStreetType.setValueFactory(ownerStreetTypeValueFactory);

        farmDto = new FarmDto();
        farmAddressDto = new AddressDto();
        ownerDto = new OwnerDto();
        ownerAddressDto = new AddressDto();
        ownerContactDto = new ContactDto();

        addressRequest = new AddressRequest();
        contactRequest = new ContactRequest();
        ownerRequest = new OwnerRequest();
        farmRequest = new FarmRequest();
        connectionRequest = new ConnectionRequest();

        authenticationRequest = new AuthenticationRequest();

        if (true) {
            resetButton.setVisible(Boolean.TRUE);
            resetButtonLabel.setVisible(Boolean.TRUE);
        } else {
            breadcrumb.setText("EDITING FIELD");
        }
        mapView.addMapInializedListener(this);
    }

    @Override
    public void mapInitialized() {
//        LatLong joeSmithLocation = new LatLong(52.402527, 16.951445);
//        LatLong joshAndersonLocation = new LatLong(47.6297, -122.3431);
//        LatLong bobUnderwoodLocation = new LatLong(47.6397, -122.3031);
//        LatLong tomChoiceLocation = new LatLong(47.6497, -122.3325);
//        LatLong fredWilkieLocation = new LatLong(47.6597, -122.3357);


        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();
        mapOptions.center(new LatLong(52.402527, 16.951445))
                .mapType(MapTypeIdEnum.HYBRID)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);

        map = mapView.createMap(mapOptions);

        //Add markers to the map
//        MarkerOptions markerOptions1 = new MarkerOptions();
//        markerOptions1.position(joeSmithLocation);
//        Marker joeSmithMarker = new Marker(markerOptions1);
//        map.addMarker(joeSmithMarker);

//        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
//        infoWindowOptions.content("<h2>Fred Wilkie</h2>"
//                + "Current Location: Safeway<br>"
//                + "ETA: 45 minutes");

//        InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
//        fredWilkeInfoWindow.open(map, fredWilkieMarker);

//        LatLong poly1 = new LatLong(52.39696594328029, 16.945290115829607);
//        LatLong poly2 = new LatLong(52.38229912807929, 16.930183914657732);
//        LatLong poly3 = new LatLong(52.37810771429377, 16.951126602646013);
//        LatLong poly4 = new LatLong(52.39759441217039, 16.959023025985857);

//        points.add(poly1);
//        points.add(poly2);
//        points.add(poly3);
//        points.add(poly4);

//        pmvc = new MVCArray(points.toArray());
//        polygOpts = new PolygonOptions()
//                .paths(pmvc)
//                .strokeColor("blue")
//                .strokeWeight(2)
//                .editable(false)
//                .fillColor("lightBlue")
//                .fillOpacity(0.5);
//
//        pg = new Polygon(polygOpts);

//        map.clearMarkers();//for a moment
//        points.clear();
        points = new ArrayList<>();

///========================
        map.addMouseEventHandler(UIEventType.click, (GMapMouseEvent event) -> {
////            //adding new marker
            latLong = event.getLatLong();
            System.out.println("Latitude: " + latLong.getLatitude());
            System.out.println("Longitude: " + latLong.getLongitude());
////
            map.clearMarkers();
////
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLong);
            Marker marker = new Marker(markerOptions);
            map.addMarker(marker);
//            //=====================
//
//            LatLong latLong = event.getLatLong();
//            points.add(latLong);
//
//            MVCArray pmvcTest = new MVCArray();
//            if (points.size() > 2) {
//                try {
//                    pmvc.clear();
//                    pmvc = new MVCArray(points.toArray());
//                } catch (Exception e) {
//                    System.out.println(e);
//                }
////
//                polygOpts = new PolygonOptions()
//                        .paths(pmvc)
//                        .strokeColor("blue")
//                        .strokeWeight(2)
//                        .editable(false)
//                        .fillColor("lightBlue")
//                        .fillOpacity(0.5);
////
//                pg = new Polygon(polygOpts);
////
////                map.addUIEventHandler(pg, UIEventType.click, (JSObject obj) -> {
////                    pg.setEditable(!pg.getEditable());
////                });
//                map.addMapShape(pg);
//            }
        });


//        map.addUIEventHandler(UIEventType.click, (JSObject obj) -> {
//            LatLong latLong = new LatLong((JSObject) obj.getMember("latLng"));
//            points.add(latLong);
//            System.out.println(latLong.getLatitude());
//            System.out.println(latLong.getLongitude());
//            MVCArray pmvcTest = new MVCArray();
//            if (points.size() > 2) {
//                try {
//                    pmvcTest = new MVCArray(points.toArray());
//                } catch (Exception e) {
//                    System.out.println(e);
//                }
//
//                polygOpts = new PolygonOptions()
//                        .paths(pmvcTest)
//                        .strokeColor("blue")
//                        .strokeWeight(2)
//                        .editable(false)
//                        .fillColor("lightBlue")
//                        .fillOpacity(0.5);
//
//                pg = new Polygon(polygOpts);
////                pg.setEditable(!pg.getEditable());
//                map.addMapShape(pg);
//
//            }
//        });

    }

//    private void collectPointToShape(LatLong newPoint) {
//        points.add(newPoint);
//        if (points.size() > 2) {
//            try {
//                MVCArray pmvc = new MVCArray(points.toArray());
//            } catch (Throwable e) {
//                e.fillInStackTrace();
//            }
//            polygOpts = new PolygonOptions()
//                    .paths(pmvc)
//                    .strokeColor("blue")
//                    .strokeWeight(2)
//                    .editable(false)
//                    .fillColor("lightBlue")
//                    .fillOpacity(0.5);
//
//            pg = new Polygon(polygOpts);
//            map.addUIEventHandler(pg, UIEventType.click, (JSObject obj) -> {
//                pg.setEditable(!pg.getEditable());
//            });
////            map.addMapShape(pg);
//            System.out.println("Rysuje nowy obszar");
//        }
//
//    }

    @FXML
    void onSave() {
        collectData();
        if (validate()) {
            saveData();
//            goToLoginScreen(MessageType.CONFIRMATION, "Your request has been sent successfully! Wait for mail and confirm your registration. Have a nice day!");
        }
    }

    private void fillOwnersDataFromCurrentUserData() {
        Person currentUser = contextHandler.getCurrentPerson();
        ownerName1.setText(currentUser.getFirstName());
        ownerName2.setText(currentUser.getSecondName());
        ownerName3.setText(currentUser.getSurname());
        ownerNip.setText(currentUser.getNip());

        ownerCountry.setText(currentUser.getAddress().getCountry());
        ownerCity.setText(currentUser.getAddress().getCity());
        ownerStreetType.getValueFactory().setValue(currentUser.getAddress().getStreetType());
        ownerStreet.setText(currentUser.getAddress().getStreet());
        ownerHouseNumber.setText(currentUser.getAddress().getHouseNumber());
        ownerLocalNumber.setText(currentUser.getAddress().getLocalNumber());
        ownerPostCode.setText(currentUser.getAddress().getPostCode());

        ownerCountryCodePhone.setText(currentUser.getContact().getCountryCode());
        ownerCodePhone.setText(currentUser.getContact().getCode());
        ownerPhone.setText(currentUser.getContact().getPhone());
        ownerFax.setText(currentUser.getContact().getFax());
        ownerEmail.setText(currentUser.getContact().getEmail());
        ownerCellphone.setText(currentUser.getContact().getCellphone());

        farmName1.setText(LangHelper.getLang("default_values.farm_name_1"));
        String name2 = currentUser.getFirstName().charAt(0)+".";
        if(!StringUtil.isEmpty(currentUser.getSecondName())){
            name2=name2+currentUser.getSecondName().charAt(0)+".";
        }
        name2=StringUtil.joinStringsWithDelimiter(" ", name2, currentUser.getSurname());
        farmName2.setText(name2);
        farmCountry.setText(currentUser.getAddress().getCountry());
        farmCity.setText(currentUser.getAddress().getCity());
        farmStreetType.getValueFactory().setValue(currentUser.getAddress().getStreetType());
        farmStreet.setText(currentUser.getAddress().getStreet());
        farmHouseNumber.setText(currentUser.getAddress().getHouseNumber());
        farmLocalNumber.setText(currentUser.getAddress().getLocalNumber());
        farmPostCode.setText(currentUser.getAddress().getPostCode());
    }

    private void collectData() {
        collectFarmData();
        collectFarmAddressData();
        collectOwnerData();
        collectOwnerAddressData();
        collectOwnerContactData();
    }

    private void collectFarmData() {
        farmDto.setName1(farmName1.getText());
        farmDto.setName2(farmName2.getText());
        farmDto.setFarmNumber(farmNumber.getText());
    }

    private void collectFarmAddressData() {
        farmAddressDto.setCountry(farmCountry.getText());
        farmAddressDto.setCity(farmCity.getText());
        farmAddressDto.setStreetType(farmStreetType.getValue());
        farmAddressDto.setStreet(farmStreet.getText());
        farmAddressDto.setHouseNumber(farmHouseNumber.getText());
        farmAddressDto.setLocalNumber(farmLocalNumber.getText());
        farmAddressDto.setPostCode(farmPostCode.getText());
    }

    private void collectOwnerData() {
        ownerDto.setOwnerType(ownerType.getValue());
        ownerDto.setName1(ownerName1.getText());
        ownerDto.setName2(ownerName2.getText());
        ownerDto.setName3(ownerName3.getText());
        ownerDto.setNip(ownerNip.getText());
    }

    private void collectOwnerAddressData() {
        ownerAddressDto.setCountry(ownerCountry.getText());
        ownerAddressDto.setCity(ownerCity.getText());
        ownerAddressDto.setStreetType(ownerStreetType.getValue());
        ownerAddressDto.setStreet(ownerStreet.getText());
        ownerAddressDto.setHouseNumber(ownerHouseNumber.getText());
        ownerAddressDto.setLocalNumber(ownerLocalNumber.getText());
        ownerAddressDto.setPostCode(ownerPostCode.getText());
    }

    private void collectOwnerContactData() {
        ownerContactDto.setCountryCode(ownerCountryCodePhone.getText());
        ownerContactDto.setCode(ownerCodePhone.getText());
        ownerContactDto.setPhone(ownerPhone.getText());
        ownerContactDto.setFax(ownerFax.getText());
        ownerContactDto.setEmail(ownerEmail.getText());
        ownerContactDto.setCellphone(ownerCellphone.getText());
    }

    private Boolean validate() {
        if (checkFarmData()) {
            if (checkFarmAddressData()) {
                if (checkOwnerData()) {
                    if (checkOwnerAddressData()) {
                        if (checkOwnerContactData()) {
                            return Boolean.TRUE;
                        }
                    }
                }
            }
        }

        return Boolean.FALSE;
    }

    private Boolean checkFarmData() {
        String message = "You have tto set a name1!";
        if (!StringUtil.isEmpty(farmDto.getName1())) {
            if (!StringUtil.isEmpty(farmDto.getFarmNumber())) {
                if (latLong != null) {
                    farmDto.setLatitude(new BigDecimal(latLong.getLatitude()));
                    farmDto.setLongitude(new BigDecimal(latLong.getLongitude()));
                    return Boolean.TRUE;
                } else {
                    message = "You have to set a headquarter location on map!";
                }
            } else {
                message = "You have to set a farm number!";
            }
        }
        callPopup(MessageType.ERROR, message);
        return Boolean.FALSE;
    }

    private Boolean checkFarmAddressData() {
        String message = "You have to enter a country!";
        if (!StringUtil.isEmpty(farmAddressDto.getCountry())) {
            if (!StringUtil.isEmpty(farmAddressDto.getCity())) {
                if (farmAddressDto.getStreetType() != null) {
                    if (farmAddressDto.getStreetType().getValue() != "bez ulicy") {
                        if (StringUtil.isEmpty(farmAddressDto.getStreet())) {
                            message = "You have to enter a street name";
                            callPopup(MessageType.ERROR, message);
                            return Boolean.FALSE;
                        }
                    }
                    if (!StringUtil.isEmpty(farmAddressDto.getHouseNumber())) {
                        if (!StringUtil.isEmpty(farmAddressDto.getPostCode())) {
                            return Boolean.TRUE;
                        } else {
                            message = "Tou have to enter a post code!";
                        }
                    } else {
                        message = "Tou have to enter a house number!";
                    }
                } else {
                    message = "Tou have to enter a street type!";
                }
            } else {
                message = "Tou have to enter a city!";
            }
        }
        callPopup(MessageType.ERROR, message);
        return Boolean.FALSE;
    }

    private Boolean checkOwnerData() {
        String message = "You have to set a owner type!";
        if (ownerDto.getOwnerType() != null) {
            if (!StringUtil.isEmpty(ownerDto.getName1())) {
                if (!StringUtil.isEmpty(ownerDto.getNip())) {
                    return Boolean.TRUE;
                } else {
                    message = "You have to set a nip";
                }
            } else {
                message = "You have to set a name1";
            }
        }
        callPopup(MessageType.ERROR, message);
        return Boolean.FALSE;
    }

    private Boolean checkOwnerAddressData() {
        String message = "You have to enter a country!";
        if (!StringUtil.isEmpty(ownerAddressDto.getCountry())) {
            if (!StringUtil.isEmpty(ownerAddressDto.getCity())) {
                if (ownerAddressDto.getStreetType() != null) {
                    if (ownerAddressDto.getStreetType().getValue() != "bez ulicy") {
                        if (StringUtil.isEmpty(ownerAddressDto.getStreet())) {
                            message = "You have to enter a street name";
                            callPopup(MessageType.ERROR, message);
                            return Boolean.FALSE;
                        }
                    }
                    if (!StringUtil.isEmpty(ownerAddressDto.getHouseNumber())) {
                        if (!StringUtil.isEmpty(ownerAddressDto.getPostCode())) {
                            return Boolean.TRUE;
                        } else {
                            message = "Tou have to enter a post code!";
                        }
                    } else {
                        message = "Tou have to enter a house number!";
                    }
                } else {
                    message = "Tou have to enter a street type!";
                }
            } else {
                message = "Tou have to enter a city!";
            }
        }
        callPopup(MessageType.ERROR, message);
        return Boolean.FALSE;
    }

    private Boolean checkOwnerContactData() {
        String message = "You have to enter one or more contact!";
        if (!StringUtils.isEmpty(ownerContactDto.getCellphone()) || !StringUtils.isEmpty(ownerContactDto.getPhone()) || !StringUtil.isEmpty(ownerContactDto.getFax())) {
            if (!StringUtil.isEmpty(ownerContactDto.getCode()) && !StringUtil.isEmpty(ownerContactDto.getCountryCode())) {
                return Boolean.TRUE;
            }
            message = "Tou have to enter a country code or code!";
        }
        callPopup(MessageType.ERROR, message);
        return Boolean.FALSE;
    }

    private void saveData() {
        Contact ownerContact = contactRequest.save(ownerContactDto.toEntity());
        Address ownerAddress = addressRequest.save(ownerAddressDto.toEntity());
        ownerDto.setContact(ownerContact);
        ownerDto.setAddress(ownerAddress);
        Owner owner = ownerRequest.save(ownerDto.toEntity());
        Address farmAddress = addressRequest.save(farmAddressDto.toEntity());
        farmDto.setAddress(farmAddress);
        farmDto.setOwner(owner);
        Farm farm = farmRequest.save(farmDto.toEntity());
        ConnectionDto connectionDto = new ConnectionDto();
        connectionDto.setAccount(contextHandler.getCurrentAccount());
        connectionDto.setFarm(farm);
        connectionDto.setConnectionType(ConnectionType.MANAGER);
        connectionRequest.save(connectionDto.toEntity());
    }

    @FXML
    void onReset(ActionEvent event) {

    }

    @FXML
    void onResign() {
        Boolean answer = callPopup(MessageType.WARNING, RESIGN_WARNING_QUESTION);
        if (answer == Boolean.TRUE) {
            goToLoginScreenWithMessage(MessageType.CONFIRMATION, "You are successfully logged out!");
        }

    }

    private void goToLoginScreenWithMessage(MessageType messageType, String message) {
        FXMLLoader innerLoader = new FXMLLoader();
        innerLoader.setLocation(this.getClass().getResource("/views/general/LoginScreen.fxml"));

        ResourceBundle bundle = ResourceBundle.getBundle("lang");
        innerLoader.setResources(bundle);
        try {

            Parent innerRoot = innerLoader.load();
            LoginScreenController loginScreenController = innerLoader.getController();

            scene.setRoot(innerRoot);
            stage.setScene(scene);
            stage.setMaximized(true);
            loginScreenController.setScene(scene);
            loginScreenController.setStage(stage);
            stage.show();
            if (messageType != null && message != null) {
                loginScreenController.callPopup(messageType, message);
            }

        } catch (IOException e) {
            e.printStackTrace();
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
        newWindow.initOwner(resetButton.getScene().getWindow());
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
}