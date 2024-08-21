package hello.world.demo.view;
import com.sothawo.mapjfx.*;


import com.sothawo.mapjfx.app.TestApp;
import com.sothawo.mapjfx.event.MapViewEvent;
import hello.world.demo.WebsiteApplication;

import hello.world.demo.model.Flight;

import javafx.scene.Scene;

import javafx.scene.layout.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapScene extends Scene {
    private final WebsiteApplication application;
    private MapView mapView;
    private Flight flight;
    private Coordinate coordCity;


    private static final WMSParam wmsParam;

    static {
        wmsParam = new WMSParam()
                .setUrl("http://ows.terrestris.de/osm/service")
                .addParam("layers", "OSM-WMS");

    }

    public MapScene(WebsiteApplication application, Flight flight) {
        super(new VBox(), 800, 500);
        this.application = application;
        this.flight = flight;
        coordCity = new Coordinate(application.getCities().getLatitude(flight.getCityDes()), application.getCities().getLongitude(flight.getCityDes()));
        final BorderPane borderPane = new BorderPane();

        // MapView in the center with an initial coordinate (optional)
        // the MapView is created first as the other elements reference it
        mapView = new MapView();
        // animate pan and zoom with 500ms
        mapView.setAnimationDuration(500);
        borderPane.setCenter(mapView);
        borderPane.setBottom(createButtonBox());
        // add WMSParam
        mapView.setWMSParam(wmsParam);


        setRoot(borderPane);

        mapView.setMapType(MapType.WMS);



        mapView.initializedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // a map is only displayed when an initial coordinate is set
                mapView.setCenter(coordCity);

            }

        });




        mapView.initialize(Configuration.builder()
                .build());
    }

    private HBox createButtonBox() {
        var backButton = SceneSetups.buttonSetup("Back");
        backButton.setOnAction(event -> application.showFlightDetailsScene(flight));
        var hBox = SceneSetups.basicButtonBox(application);
        hBox.getChildren().add(backButton);
        return hBox;
    }
}
