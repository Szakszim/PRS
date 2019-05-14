package controllers.manager.plants;

//import com.google.gson.Gson;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.shapes.Polygon;
import com.lynden.gmapsfx.shapes.PolygonOptions;
import dtos.other.BasicFieldDataDto;
import entities.FieldCoordinate;
import helpers.LangHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import lombok.Getter;
import lombok.Setter;
import netscape.javascript.JSObject;
import requests.FieldCoordinateRequest;
import requests.FieldRequest;

//import com.lynden.gmapsfx.javascript.object.MapType;


public class FieldScreenController implements Initializable, MapComponentInitializedListener {

    @FXML
    private Button button;

    @FXML
    private GoogleMapView mapView;

    @FXML
    private TextField name;

    @FXML
    private TextField registrationNumber;

    @FXML
    private TextField area;

    @FXML
    private TextField measurementUnit;

    @FXML
    private TextField possessionState;

    @FXML
    private TextArea description;

    private GoogleMap map;

    @Getter
    @Setter
    ArrayList<LatLong> firstField = new ArrayList<>();
    @Getter
    @Setter
    ArrayList<LatLong> secondField = new ArrayList<>();
    @Getter
    @Setter
    MVCArray pmvcA;
    @Getter
    @Setter
    MVCArray pmvcB;
    PolygonOptions polygOptsA;
    Polygon pgA;
    PolygonOptions polygOptsB;
    Polygon pgB;


    @Getter
    @Setter
    private List<List<LatLong>> fieldCoordinates = new ArrayList<>();
    @Getter
    @Setter
    private List<MVCArray> pmvcs = new ArrayList<>();
    @Getter
    @Setter
    private List<PolygonOptions> polygonOptionsList = new ArrayList<>();
    @Getter
    @Setter
    private List<Polygon> polygons = new ArrayList<>();
    private FieldCoordinateRequest fieldCoordinateRequest = new FieldCoordinateRequest();
    private FieldRequest fieldRequest = new FieldRequest();


    @Setter
    private OwnerPlantsModuleMainScreenController ownerPlantsModuleMainScreenController;
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
//        LatLong firstFieldA = new LatLong(52.43833092928994, 16.86939086181644);
//        LatLong firstFieldB = new LatLong(52.43707514322278, 16.902006523437535);
//        LatLong firstFieldC = new LatLong(52.42807429571549, 16.90234984619144);
//        LatLong firstFieldD = new LatLong(52.42660886748387, 16.881063835449254);
//
//        firstField.add(firstFieldA);
//        firstField.add(firstFieldB);
//        firstField.add(firstFieldC);
//        firstField.add(firstFieldD);
//
//        LatLong secondFieldA = new LatLong(52.388490631530004, 16.973417656250035);
//        LatLong secondFieldB = new LatLong(52.386395268256756, 17.01598967773441);
//        LatLong secondFieldC = new LatLong(52.3744497983284, 17.016333000488316);
//        LatLong secondFieldD = new LatLong(52.37340179591227, 16.976164238281285);
//
//        secondField.add(secondFieldA);
//        secondField.add(secondFieldB);
//        secondField.add(secondFieldC);
//        secondField.add(secondFieldD);

        List<List<FieldCoordinate>> list = fieldCoordinateRequest.findAllCoordinatesByFarmId(ownerPlantsModuleMainScreenController.getContextHandler().getCurrentFarm().getId());
        for (int i = 0; i < list.size(); i++) {
            fieldCoordinates.add(new ArrayList<>());
            for (int j = 0; j < list.get(i).size(); j++) {
                fieldCoordinates.get(i).add(new LatLong(list.get(i).get(j).getLatitude().doubleValue(), list.get(i).get(j).getLongitude().doubleValue()));
            }
        }

        for (int k = 0; k < list.size(); k++) {
            pmvcs.add(new MVCArray(fieldCoordinates.get(k).toArray()));
        }

        for (int l = 0; l < list.size(); l++) {
            polygonOptionsList.add(new PolygonOptions()
                    .paths(pmvcs.get(l))
                    .strokeColor("green")
                    .strokeWeight(2)
                    .editable(false)
                    .fillColor("lightGreen")
                    .fillOpacity(0.5));
        }

        for (int m = 0; m < list.size(); m++) {
            polygons.add(new Polygon(polygonOptionsList.get(m)));
        }

        //====================

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

        for (int n = 0; n < list.size(); n++) {
            map.addMapShape(polygons.get(n));
        }

//        pmvcA = new MVCArray(firstField.toArray());
//        polygOptsA = new PolygonOptions()
//                .paths(pmvcA)
//                .strokeColor("blue")
//                .strokeWeight(2)
//                .editable(false)
//                .fillColor("lightBlue")
//                .fillOpacity(0.5);
//
//        pgA = new Polygon(polygOptsA);
//
//        pmvcB = new MVCArray(secondField.toArray());
//        polygOptsB = new PolygonOptions()
//                .paths(pmvcB)
//                .strokeColor("red")
//                .strokeWeight(2)
//                .editable(false)
//                .fillColor("lightRed")
//                .fillOpacity(0.5);
//
//        pgB = new Polygon(polygOptsB);
//
//        map.addMapShape(pgA);
//        map.addMapShape(pgB);

//        map.addMouseEventHandler(UIEventType.click, (GMapMouseEvent event) -> {

//        map.addUIEventHandler(pgA, UIEventType.click, (JSObject obj) -> {
//            pgA.setEditable(!pgA.getEditable());
//        });
//
//        map.addUIEventHandler(pgB, UIEventType.click, (JSObject obj) -> {
//            pgB.setEditable(!pgB.getEditable());
//        });

        for (int o = 0; o < list.size(); o++) {
            Polygon test = polygons.get(o);
            map.addUIEventHandler(test, UIEventType.click, (JSObject obj) -> {
//                for(int z =0;z<list.size();z++){
//                    polygons.get(z).setEditable(false);
//                }
//                test.setEditable(!test.getEditable());
//                for(int z =0;z<list.size();z++){
//                    if(polygons.get(z).getEditable()){
//                        testowyField.setText("lol" + z);
//                    }
//                }

                BasicFieldDataDto basicFieldDataDto = fieldRequest.getBasicFieldDataByFieldId(list.get(polygons.indexOf(test)).get(0).getField().getId());

                name.setText(basicFieldDataDto.getName());
                registrationNumber.setText(basicFieldDataDto.getRegistrationNumber());
                area.setText(basicFieldDataDto.getArea().toString());
                measurementUnit.setText(LangHelper.getLang(basicFieldDataDto.getMeasurementUnit().toString()));
                possessionState.setText(LangHelper.getLang(basicFieldDataDto.getPossessionState().toString()));
                description.setText(basicFieldDataDto.getDescription());


            });
        }
//        });

    }

    @FXML
    void onAdd(ActionEvent event) {
        FXMLLoader innerLoader = new FXMLLoader();
        innerLoader.setLocation(this.getClass().getResource("/views/manager/plants/FieldAddEditScreen.fxml"));
//        FXMLLoader innerLoader = new FXMLLoader(getClass().getResource("O:\\Adrian\\Projekty\\ASS\\ASSApp\\src\\main\\resources\\views\\TemplateScreen.fxml"));
//        ResourceBundle bundle = ResourceBundle.getBundle("gui.resources.lang");
//        loader.setResources(bundle);

        GridPane gridPane = null;
        try {
            gridPane = innerLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FieldAddEditScreenController controller = innerLoader.getController();
        controller.setOwnerPlantsModuleMainScreenController(this.ownerPlantsModuleMainScreenController);
//        set objects here

//        controller.setMainScreenController(this);
        ownerPlantsModuleMainScreenController.setView(gridPane);
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