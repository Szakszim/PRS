package controllers.manager.animals;

//import com.google.gson.Gson;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import dtos.other.BasicHallDataDto;
import entities.Dictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import lombok.Setter;
import netscape.javascript.JSObject;
import requests.AnimalHallRequest;
import utils.Coordinates;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

//import com.lynden.gmapsfx.javascript.object.MapType;


public class HallScreenController implements Initializable, MapComponentInitializedListener {

    @FXML
    private Button button;

    @FXML
    private TextField name;

    @FXML
    private TextField animalType;

    @FXML
    private TextField capacity;

    @FXML
    private TextField fullLevel;

    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;

    private List<BasicHallDataDto> list = new ArrayList<>();

    private List<MarkerOptions> markerOptionsList = new ArrayList<>();

    private List<Marker> markers = new ArrayList<>();

    private AnimalHallRequest animalHallRequest = new AnimalHallRequest();


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
    private OwnerAnimalsModuleMainScreenController ownerAnimalsModuleMainScreenController;
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
        list = animalHallRequest.findAllCoordinatesByFarmId(ownerAnimalsModuleMainScreenController.getContextHandler().getCurrentFarm().getId());

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

        for (int i = 0; i < list.size(); i++) {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLong(list.get(i).getLatitude().doubleValue(), list.get(i).getLongitude().doubleValue()));
            markerOptionsList.add(markerOptions);
        }

        for (int j = 0; j < markerOptionsList.size(); j++) {
            markers.add(new Marker(markerOptionsList.get(j)));
        }

        for (int k = 0; k < markers.size(); k++) {
            map.addMarker(markers.get(k));
        }


//        map.addMouseEventHandler(UIEventType.click, (GMapMouseEvent event) -> {
        for (int m = 0; m < markers.size(); m++) {
            Marker marker = markers.get(m);
            map.addUIEventHandler(marker, UIEventType.click, (JSObject obj) -> {
//                markerA.setEditable(!pgA.getEditable());
//                InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
//                infoWindowOptions.content("<h2>Fred Wilkie</h2>"
//                        + "Current Location: Safeway<br>"
//                        + "ETA: 45 minutes");
                BasicHallDataDto basicHallDataDto = list.get(markers.indexOf(marker));
                System.out.println(basicHallDataDto.getName());

                name.setText(basicHallDataDto.getName());
                animalType.setText(basicHallDataDto.getAnimalType().getValue());
                capacity.setText(basicHallDataDto.getCapacity().toString());
                fullLevel.setText(basicHallDataDto.getFullLevel().toString());
//                InfoWindow markerAWindow = new InfoWindow(infoWindowOptions);
//                markerAWindow.open(map, markerA);
            });
//
        }
//            map.addUIEventHandler(pgB, UIEventType.click, (JSObject obj) -> {
//                pgB.setEditable(!pgB.getEditable());
//            });

//        });

    }

    @FXML
    void onAdd(ActionEvent event) {
        FXMLLoader innerLoader = new FXMLLoader();
        innerLoader.setLocation(this.getClass().getResource("/views/manager/animals/HallAddEditScreen.fxml"));
//        FXMLLoader innerLoader = new FXMLLoader(getClass().getResource("O:\\Adrian\\Projekty\\ASS\\ASSApp\\src\\main\\resources\\views\\TemplateScreen.fxml"));
//        ResourceBundle bundle = ResourceBundle.getBundle("gui.resources.lang");
//        loader.setResources(bundle);

        GridPane gridPane = null;
        try {
            gridPane = innerLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HallAddEditScreenController controller = innerLoader.getController();
        controller.setOwnerAnimalsModuleMainScreenController(this.ownerAnimalsModuleMainScreenController);
//        set objects here

//        controller.setMainScreenController(this);
        ownerAnimalsModuleMainScreenController.setView(gridPane);
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