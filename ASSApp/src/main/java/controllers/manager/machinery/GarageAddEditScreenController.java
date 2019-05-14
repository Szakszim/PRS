package controllers.manager.machinery;

//import com.google.gson.Gson;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.GMapMouseEvent;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.shapes.Polygon;
import com.lynden.gmapsfx.shapes.PolygonOptions;
import controllers.general.NewPopupScreenController;
import controllers.manager.animals.HallScreenController;
import controllers.manager.animals.OwnerAnimalsModuleMainScreenController;
import enums.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

//import com.lynden.gmapsfx.javascript.object.MapType;


public class GarageAddEditScreenController implements Initializable, MapComponentInitializedListener {

    public static final String RESIGN_WARNING_QUESTION = "Unsaved changes will be lost. Are you sure you want to resign?";

    @FXML
    private Label breadcrumb;

    @FXML
    private Button resetButton;

    @FXML
    private Button resetButtonLabel;

    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;

    @Getter
    @Setter
    ArrayList<LatLong> points = new ArrayList<>();
    @Getter
    @Setter
    MVCArray pmvc = new MVCArray();
    PolygonOptions polygOpts;
    Polygon pg;


    @Setter
    private OwnerMachineryModuleMainScreenController ownerMachineryModuleMainScreenController;

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
        if(true){
            resetButton.setVisible(Boolean.TRUE);
            resetButtonLabel.setVisible(Boolean.TRUE);
        }else{
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
            LatLong latLong = event.getLatLong();
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
    void onSave(ActionEvent event) {

    }

    @FXML
    void onReset(ActionEvent event) {

    }

    @FXML
    void onResign(ActionEvent event) {

        if(callPopup(MessageType.WARNING, RESIGN_WARNING_QUESTION)){
            backToFieldScreen();
        }


    }

    private void backToFieldScreen(){
        FXMLLoader innerLoader = new FXMLLoader();
        innerLoader.setLocation(this.getClass().getResource("/views/manager/animals/GarageScreen.fxml"));
//        FXMLLoader innerLoader = new FXMLLoader(getClass().getResource("O:\\Adrian\\Projekty\\ASS\\ASSApp\\src\\main\\resources\\views\\TemplateScreen.fxml"));
//        ResourceBundle bundle = ResourceBundle.getBundle("gui.resources.lang");
//        loader.setResources(bundle);

        GridPane gridPane = null;
        try {
            gridPane = innerLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GarageScreenController controller = innerLoader.getController();
        controller.setOwnerMachineryModuleMainScreenController(this.ownerMachineryModuleMainScreenController);
//        set objects here

//        controller.setMainScreenController(this);
        ownerMachineryModuleMainScreenController.setView(gridPane);
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

        switch (messageType){
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