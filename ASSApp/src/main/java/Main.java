//import entities.Address;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
//
//public class Main {
//    public static void main(String[] args) {
//
//        RestTemplate restTemplate = new RestTemplate();
//        Address test = restTemplate.getForObject("http://localhost:8080/test", Address.class);
//
//        Address testowy = new Address();
//        testowy.setCity("Poznań");
//        testowy.setStreet("Królowej Jadwigi");
//        testowy.setHouseNumber("25");
//        testowy.setLocalNumber("5");
//        testowy.setPostCode("60-811");
//
//        restTemplate.postForObject("http://localhost:8080/testb",testowy,Address.class);
//    }
//}

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("views/login/LoginScreen.fxml"));
        GridPane gridPane = loader.load();
        Scene scene = new Scene(gridPane);
        primaryStage.setResizable(false);
        primaryStage.setTitle("PRS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void Main(String[] args) {
        launch(args);
    }



}

