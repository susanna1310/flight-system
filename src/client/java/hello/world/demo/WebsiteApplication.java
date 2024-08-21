package hello.world.demo;

import hello.world.demo.controller.FlightController;
import hello.world.demo.model.Cities;
import hello.world.demo.model.Flight;
import hello.world.demo.model.Website;
import hello.world.demo.view.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebsiteApplication extends Application {
    private Stage stage;
    private List<Scene> scenes;
    private final FlightController flightController = new FlightController();
    private List<Flight> myFlights;
    private Map<String, FeedbackScene> feedbackScenes;
    private Website website;
    private Cities cities;


    @Override
    public void start(Stage primaryStage) throws Exception {
        this. stage = primaryStage;
        this.cities = new Cities();
        this.website = new Website(cities);
        this.scenes = createScenes();
        this.feedbackScenes = createFeedbackScene();
        this.myFlights = new ArrayList<>();
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        primaryStage.setScene(scenes.get(0));
        primaryStage.sizeToScene();
        primaryStage.getIcons().add(new Image(new FileInputStream("src/client/java/hello/world/demo/view/res/icon.jpg")));
        primaryStage.show();
        primaryStage.setMinWidth(primaryStage.getWidth());
        primaryStage.setMinHeight(primaryStage.getHeight());

    }

    private List<Scene> createScenes() throws FileNotFoundException {
        List<Scene> list = new ArrayList<>();
        list.add(new WebsiteUI(this));
        list.add(new MyFlightScene(flightController,this));
        list.add(new AllFlightScene(this.website, this));
        list.add(new SafetyInstructionScene(this));
        return list;
    }

    private  Map<String, FeedbackScene> createFeedbackScene() {
        Map<String, FeedbackScene> feedbackMap = new HashMap<>();
        for (Flight f : website.getFlights()) {
            feedbackMap.put(f.getFlight(), new FeedbackScene(this));
        }
        //for the feedback demonstration
        feedbackMap.put("dlo456w", new FeedbackScene(this));
        return feedbackMap;
    }

    public void showWebsiteUI() {
        stage.setScene(scenes.get(0));
    }

    public void showMyFlightScene() {
        stage.setScene(scenes.get(1));
    }

    public void showFeedback(Flight flight) {
        feedbackScenes.get(flight.getFlight()).setFlight(flight);
        stage.setScene(feedbackScenes.get(flight.getFlight()));
    }

    public void showAllFlightScene() {
        stage.setScene(scenes.get(2));
    }

    public void showSafetyInstructionScene() {
        stage.setScene(scenes.get(3));
    }

    public void showMapScene(Flight flight) {
       stage.setScene(new MapScene(this, flight));
    }

    public void showFlightDetailsScene(Flight flight) {
        stage.setScene(new FlightDetailsScene(this, flight));
    }

    public Stage getStage() {
        return stage;
    }

    public List<Scene> getScenes() {
        return scenes;
    }

    public List<Flight> getMyFlights() {
        return myFlights;
    }

    public Map<String, FeedbackScene> getFeedbackScenes() {
        return feedbackScenes;
    }

    public Cities getCities() {
        return cities;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
