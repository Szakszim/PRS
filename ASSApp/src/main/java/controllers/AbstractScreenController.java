package controllers;

import context.ContextHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ResourceBundle;

@Setter
@Getter
public class AbstractScreenController implements Initializable {


    private ContextHandler contextHandler;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initData(Integer objectId) {

    }


//    public void navigateTo(String viewPath) {
//        FXMLLoader innerLoader = new FXMLLoader();
//        innerLoader.setLocation(this.getClass().getResource(viewPath));
////        ResourceBundle bundle = ResourceBundle.getBundle("gui.resources.lang");
////        loader.setResources(bundle);
//
//        GridPane gridPane = null;
//        try {
//            gridPane = innerLoader.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
////        controller?
//
//        mainScreenController.setView(gridPane);
//    }
}


