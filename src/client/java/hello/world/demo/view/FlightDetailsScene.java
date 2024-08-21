package hello.world.demo.view;

import hello.world.demo.WebsiteApplication;
import hello.world.demo.model.Flight;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class FlightDetailsScene extends Scene {
    private final WebsiteApplication application;
    private final Flight flight;
    private final WeatherCorner weatherCorner;

    public FlightDetailsScene(WebsiteApplication application, Flight flight) {
        super(new VBox(), 800, 500);
        this.application = application;
        this.flight = flight;
        this.weatherCorner = new WeatherCorner(flight.getCityDes());
        var title = new Label("Flight Details");
        title.setFont(new Font("Arial", 25));
        title.setTextFill(Color.WHITE);
        var titleBox = new HBox(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setBackground(new Background(new BackgroundFill(Color.DARKBLUE.desaturate(),
                CornerRadii.EMPTY, Insets.EMPTY)));
        titleBox.setPadding(new Insets(10,0,10,0));
        var borderPane = new BorderPane();
        borderPane.setBottom(createButtonBox());
        borderPane.setTop(titleBox);
        borderPane.setCenter(detailsBox());
        borderPane.setBackground(new Background(new BackgroundFill(Color.WHITE,
                CornerRadii.EMPTY, Insets.EMPTY)));
        setRoot(borderPane);
    }

    private HBox createButtonBox() {
        var hBox = SceneSetups.basicButtonBox(application);
        var flightList = SceneSetups.buttonSetup("FlightList");
        flightList.setOnAction(event -> application.showMyFlightScene());
        var allFlights = SceneSetups.buttonSetup("Available Flights");
        allFlights.setOnAction(event -> application.showAllFlightScene());
        hBox.getChildren().addAll(flightList, allFlights);
        return hBox;
    }

    private HBox detailsBox() {
        var gridPane = new GridPane();
        gridPane.add(createLable("Flight:"), 0,0);
        gridPane.add(new Label("   "),1,0);
        gridPane.add(createLable(flight.getFlight()),2,0);
        gridPane.add(createLable("Airline:"), 0,1);
        gridPane.add(new Label("   "),1,1);
        gridPane.add(createLable(flight.getAirline()),2,1);
        gridPane.add(createLable("Economy Price:"), 0,2);
        gridPane.add(new Label("   "),1,2);
        gridPane.add(createLable(flight.getEconomyPrice() + " \u20ac"),2,2);
        gridPane.add(createLable("Business Price:"), 0,3);
        gridPane.add(new Label("   "),1,3);
        gridPane.add(createLable(flight.getBusinessPrice() + " \u20ac"),2,3);
        gridPane.add(createLable("Departure:"), 0,4);
        gridPane.add(new Label("   "),1,4);
        gridPane.add(createLable(flight.getCityDep()),2,4);
        gridPane.add(createLable("Destination:"), 0,5);
        gridPane.add(new Label("   "),1,5);
        gridPane.add(createLable(flight.getCityDes()),2,5);
        gridPane.add(createLable("Departure Time:"), 0,6);
        gridPane.add(new Label("   "),1,6);
        gridPane.add(createLable(flight.getDeparture()),2,6);
        gridPane.add(createLable("Arrival Time:"), 0,7);
        gridPane.add(new Label("   "),1,7);
        gridPane.add(createLable(flight.getArrival()),2,7);
        gridPane.add(createLable("Weather in " + flight.getCityDes() + ": "), 0,8);
        gridPane.add(new Label("   "),1,8);
        gridPane.add(createLable(weatherCorner.getTemperature(flight.getCityDes())),2,8);
        gridPane.setHgap(10); //horizontal gap in pixels
        gridPane.setVgap(10); //vertical gap in pixels
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        var des = new Button();
        Image logo = null;
        try {
            logo = new Image(new FileInputStream("src/client/java/hello/world/demo/view/res/maplogo.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView view = new ImageView(logo);
        view.setFitWidth(200);
        view.setFitHeight(200);
        des.setGraphic(view);
        des.setOnAction(event -> {
                    application.showMapScene(flight);
                });
        var mapLabel = new Label("Map of " + flight.getCityDes());
        mapLabel.setFont(new Font("Ariel", 20));
        var labelBox = new HBox(mapLabel);
        labelBox.setAlignment(Pos.CENTER);
        des.setStyle("-fx-background-color: transparent ");

        var pane = new BorderPane();
        pane.setLeft(gridPane);
        //vBox with the Label and the Map button
        var mapBox = new VBox(labelBox, des);
        pane.setRight(mapBox);
        pane.setCenter(new Label("                       "));
        BorderPane.setMargin(gridPane, new Insets(10));
        BorderPane.setMargin(mapBox, new Insets(10));
        pane.setBackground(new Background(new BackgroundFill(Color.WHITE,
                CornerRadii.EMPTY, Insets.EMPTY)));
        return new HBox(10, pane);
    }

    private Label createLable(String string) {
        var label = new Label(string);
        label.setFont(new Font("Arial", 20));
        return label;
    }
}
