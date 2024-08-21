package hello.world.demo.view;

import hello.world.demo.model.Flight;
import hello.world.demo.WebsiteApplication;
import hello.world.demo.controller.FlightController;
import hello.world.demo.model.MyFlightSortingOption;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class MyFlightScene extends Scene {
    private final WebsiteApplication application;
    private final FlightController flightController;
    private final TableView<Flight> table;
    private final ObservableList<Flight> flightList;

    public MyFlightScene(FlightController flightController, WebsiteApplication application) {
        super(new VBox(), 800, 500);
        this.application = application;
        this.flightController = flightController;
        this.flightList = FXCollections.observableArrayList();
        this.table = SceneSetups.createTableView(flightList, application);
        addButtonToTable();
        //addServiceButtonToTable();

        var borderPane = new BorderPane();
        borderPane.setCenter(table);
        borderPane.setTop(createFlightOptionBox());
        borderPane.setBottom(createButtonBox());

        borderPane.setBackground(new Background(new BackgroundFill(Color.WHITE,
                CornerRadii.EMPTY, Insets.EMPTY)));
        setRoot(borderPane);
        this.addFlight(new Flight("dlo456w",LocalDateTime.parse("2016-12-04T11:00:30"),LocalDateTime.parse("2016-12-04T12:45:30"),"Lufthansa", 89, 100,null, null, "Paris", "Barcelona"));
        flightController.getAllFlights(MyFlightSortingOption.UPCOMING_FLIGHTS, this::setFlight);
    }

    /*
     creates a new TableColumns feedback, which is a button cell
     */
    private void addButtonToTable() {
        TableColumn<Flight, Void> colBtn = new TableColumn("Feedback");

        Callback<TableColumn<Flight, Void>, TableCell<Flight, Void>> cellFactory = new Callback<TableColumn<Flight, Void>, TableCell<Flight, Void>>() {
            @Override
            public TableCell<Flight, Void> call(final TableColumn<Flight, Void> param) {
                final TableCell<Flight, Void> cell = new TableCell<>() {

                    private final Button btn = new Button("Feedback");
                    {
                        //btn.setStyle("-fx-background-color: tranparent");
                        btn.setOnAction(event -> {
                            Flight flight = getTableView().getItems().get(getIndex());
                            //when the arrival Date of the flight is before now, an alert opens informing that the feedback has to be taken after the flight
                            if (flight.getArrivalDate().isAfter(LocalDateTime.now())) {
                                Alert a = new Alert(Alert.AlertType.INFORMATION, "The feedback will be enabled after the flight", ButtonType.OK);
                                a.getDialogPane().setBackground(new Background(new BackgroundFill(Color.WHITE,
                                        CornerRadii.EMPTY, Insets.EMPTY)));
                                a.setTitle("Feedback");
                                a.setHeaderText("Please wait with your Feedback until after the flight.");
                                Optional<ButtonType> result = a.showAndWait();
                                if(result.isPresent() && result.get() == ButtonType.OK) {
                                    a.close();
                                }
                            }  else {
                                //opens Feedback scene

                                application.showFeedback(flight);
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        colBtn.setMinWidth(15);
        table.getColumns().add(colBtn);
    }

    /*
       creates a new TableColumns request service, which is a button cell
       */
//    private void addServiceButtonToTable() {
//        TableColumn<Flight, Void> serBtn = new TableColumn("Request\n service");
//
//        Callback<TableColumn<Flight, Void>, TableCell<Flight, Void>> cellFactory = new Callback<TableColumn<Flight, Void>, TableCell<Flight, Void>>() {
//            @Override
//            public TableCell<Flight, Void> call(final TableColumn<Flight, Void> param) {
//                final TableCell<Flight, Void> cell = new TableCell<>() {
//
//                    private final Button serbtn = new Button("Request");
//                    {
//                        serbtn.setOnAction(event -> {
//                            Flight flight = getTableView().getItems().get(getIndex());
//                            //when the arrival Date of the flight is before now, the button requests service
//                            if (flight.getArrivalDate().isAfter(LocalDateTime.now()) && flight.getDepartureDate().isBefore(LocalDateTime.now())) {
//                                Alert a = new Alert(Alert.AlertType.INFORMATION, "Request service", ButtonType.OK);
//                                a.setTitle("Notify stuff");
//                                a.setHeaderText("The staff has been notified.");
//                                Optional<ButtonType> result = a.showAndWait();
//                                if(result.isPresent() && result.get() == ButtonType.OK) {
//                                    a.close();
//                                }
//                            } else {
//                                //disable button
//                                serbtn.setDisable(true);
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void updateItem(Void item, boolean empty) {
//                        super.updateItem(item, empty);
//                        if (empty) {
//                            setGraphic(null);
//                        } else {
//                            setGraphic(serbtn);
//                        }
//                    }
//                };
//                return cell;
//            }
//        };
//        serBtn.setCellFactory(cellFactory);
//        serBtn.setMinWidth(25);
//        table.getColumns().add(serBtn);
//    }

    private HBox createFlightOptionBox() {
        var all = SceneSetups.buttonSetup("All Flights");
        all.setOnAction(event ->   {
            flightController.getAllFlights(MyFlightSortingOption.ALL_FLIGHTS, this::setFlight); });
        var upcoming = SceneSetups.buttonSetup("Upcoming Flights");
        upcoming.setOnAction(event -> flightController.getAllFlights(MyFlightSortingOption.UPCOMING_FLIGHTS, this::setFlight));
        var past = SceneSetups.buttonSetup("Past flights");
        past.setOnAction(event -> flightController.getAllFlights(MyFlightSortingOption.PAST_FLIGHTS, this::setFlight));
        var hBox = SceneSetups.createHBox();
        hBox.getChildren().addAll(all, upcoming, past);
        hBox.setPadding(new Insets(20,0,20,0));
        return hBox;
    }


    //creates the home button to go the home page
    private HBox createButtonBox() {
        var allFlights = SceneSetups.buttonSetup("Available Flights");
        allFlights.setOnAction(event -> application.showAllFlightScene());
        var hBox = SceneSetups.basicButtonBox(application);
        hBox.getChildren().addAll(allFlights);
        return hBox;
    }


    public void addFlight(Flight flight) {
        var newFlight = flight;
        flightController.addFlight(newFlight, this::setFlight);
    }

    public ObservableList<Flight> getFlightList() {
        return flightList;
    }

    public void setFlight(List<Flight> flight) {
        Platform.runLater(() -> flightList.setAll(flight));
    }
}
