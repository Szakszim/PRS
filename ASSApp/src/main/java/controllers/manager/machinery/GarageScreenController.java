package controllers.manager.machinery;

//import com.google.gson.Gson;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import controllers.manager.animals.HallAddEditScreenController;
import controllers.manager.animals.OwnerAnimalsModuleMainScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import lombok.Setter;
import netscape.javascript.JSObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//import com.lynden.gmapsfx.javascript.object.MapType;


public class GarageScreenController implements Initializable, MapComponentInitializedListener {

    @FXML
    private Button button;

    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;

//    @Getter
//    @Setter
//    ArrayList<LatLong> firstField = new ArrayList<>();
//    @Getter
//    @Setter
//    ArrayList<LatLong> secondField = new ArrayList<>();
//    @Getter
//    @Setter
//    MVCArray pmvcA;
//    @Getter
//    @Setter
//    MVCArray pmvcB;
//    PolygonOptions polygOptsA;
//    Polygon pgA;
//    PolygonOptions polygOptsB;
//    Polygon pgB;

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
        mapView.addMapInializedListener(this);
    }

    @Override
    public void mapInitialized() {
        LatLong hallA = new LatLong(52.43833092928994, 16.86939086181644);
        LatLong hallB = new LatLong(52.43707514322278, 16.902006523437535);
        LatLong hallC = new LatLong(52.42807429571549, 16.90234984619144);
        LatLong hallD = new LatLong(52.42660886748387, 16.881063835449254);

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

        MarkerOptions markerOptionsA = new MarkerOptions();
        markerOptionsA.position(hallA);

        MarkerOptions markerOptionsB = new MarkerOptions();
        markerOptionsB.position(hallB);

        MarkerOptions markerOptionsC = new MarkerOptions();
        markerOptionsC.position(hallC);

        MarkerOptions markerOptionsD = new MarkerOptions();
        markerOptionsD.position(hallD);

        Marker markerA = new Marker(markerOptionsA);
        Marker markerB = new Marker(markerOptionsB);
        Marker markerC = new Marker(markerOptionsC);
        Marker markerD= new Marker(markerOptionsD);

        map.addMarker( markerA );
        map.addMarker( markerB );
        map.addMarker( markerC );
        map.addMarker( markerD );


//        map.addMouseEventHandler(UIEventType.click, (GMapMouseEvent event) -> {

            map.addUIEventHandler(markerA, UIEventType.click, (JSObject obj) -> {
//                markerA.setEditable(!pgA.getEditable());
                InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                infoWindowOptions.content("<h2>Fred Wilkie</h2>"
                        + "Current Location: Safeway<br>"
                        + "ETA: 45 minutes" );

                InfoWindow markerAWindow = new InfoWindow(infoWindowOptions);
                markerAWindow.open(map, markerA);

            });
//
//            map.addUIEventHandler(pgB, UIEventType.click, (JSObject obj) -> {
//                pgB.setEditable(!pgB.getEditable());
//            });

//        });

    }

    @FXML
    void onAdd(ActionEvent event) {
        FXMLLoader innerLoader = new FXMLLoader();
        innerLoader.setLocation(this.getClass().getResource("/views/manager/machinery/GarageAddEditScreen.fxml"));
//        FXMLLoader innerLoader = new FXMLLoader(getClass().getResource("O:\\Adrian\\Projekty\\ASS\\ASSApp\\src\\main\\resources\\views\\TemplateScreen.fxml"));
//        ResourceBundle bundle = ResourceBundle.getBundle("gui.resources.lang");
//        loader.setResources(bundle);

        GridPane gridPane = null;
        try {
            gridPane = innerLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GarageAddEditScreenController controller = innerLoader.getController();
        controller.setOwnerMachineryModuleMainScreenController(this.ownerMachineryModuleMainScreenController);
//        set objects here

//        controller.setMainScreenController(this);
        ownerMachineryModuleMainScreenController.setView(gridPane);
//        serviceRequestScreenController.setMainScreenController(this);
//        setView(gridPane);
    }

    @FXML
    void onEdit(ActionEvent event) {

    }

    @FXML
    void onDelete(ActionEvent event) {

    }
}