package hello.world.demo.view;

import hello.world.demo.WebsiteApplication;
import hello.world.demo.model.Flight;
import hello.world.demo.model.Website;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AllFlightScene extends Scene {
    private final WebsiteApplication application;
    private final TableView<Flight> table;
    private final ObservableList<Flight> flightList;
    private final Website web;
    private final List<Flight> websiteFlights;

    public AllFlightScene( Website web, WebsiteApplication application) {
        super(new VBox(), 800, 500);
        this.application = application;
        this.web = web;
        this.websiteFlights = web.getFlights();
        this.flightList = FXCollections.observableArrayList(websiteFlights);
        this.table = SceneSetups.createTableView(flightList, application);

        addButtonToTable();
        //so that the table adapts to the screen size
        var borderPane = new BorderPane();
        borderPane.setCenter(table);
        borderPane.setTop(createSearchOptionBox());
        borderPane.setBottom(createButtonBox());
        setRoot(borderPane);
    }

    private void addButtonToTable() {
        TableColumn<Flight, Void> colBtn = new TableColumn("Purchase\n  ticket");

        Callback<TableColumn<Flight, Void>, TableCell<Flight, Void>> cellFactory = new Callback<TableColumn<Flight, Void>, TableCell<Flight, Void>>() {
            @Override
            public TableCell<Flight, Void> call(final TableColumn<Flight, Void> param) {
                final TableCell<Flight, Void> cell = new TableCell<>() {

                    private final Button btn = new Button("Purchase");

                    {
                        btn.setOnAction(event -> {
                            Flight flight = getTableView().getItems().get(getIndex());
                            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to book this flight?", ButtonType.YES, ButtonType.NO);
                            a.getDialogPane().setBackground(new Background(new BackgroundFill(Color.WHITE,
                                    CornerRadii.EMPTY, Insets.EMPTY)));
                            a.setTitle("Purchase");
                            a.setHeaderText("After confirming the flight will be added to 'FlightList'.");
                            Optional<ButtonType> result = a.showAndWait();
                            if (result.isPresent() && result.get() == ButtonType.YES) {
                                web.getFlights().removeIf(f -> f.equals(flight));
                                for (Scene s : application.getScenes()) {
                                    if (s instanceof MyFlightScene scene) {
                                        scene.addFlight(flight);
                                        break;
                                    }
                                    application.getMyFlights().add(flight);
                                }
                                btn.setText("Purchased");
                                btn.setDisable(true);
                            }
                            a.close();

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

    //creates the home button to go the home page
    private HBox createButtonBox() {
        var myFlight = SceneSetups.buttonSetup("FlightList");
        myFlight.setOnAction(event -> application.showMyFlightScene());
        var hBox = SceneSetups.basicButtonBox(application);
        hBox.getChildren().addAll(myFlight);
        return hBox;
    }

    private HBox createSearchOptionBox() {
        var t1 = new Text("From:");
        t1.setFill(Color.WHITE);
        t1.setFocusTraversable(false);
        var t2 = new Text("To:");
        t2.setFill(Color.WHITE);
        var t3 = new Text("Date:");
        t3.setFill(Color.WHITE);
        var from = new TextField("From");
        var to = new TextField("To");
        var date = new DatePicker();
       date.getEditor().setDisable(true);
       date.getEditor().setOpacity(1);
        var v1 = new VBox(t1, from);
        var v2 = new VBox(t2, to);
        var v3 = new VBox(t3, date);
        var search = SceneSetups.buttonSetup("Search");
        var clear = SceneSetups.buttonSetup("Clear");
        clear.setOnAction( event -> {
            date.setValue(null);
            from.clear();
            to.clear();
            this.flightList.addAll(websiteFlights);
        });
        search.setOnAction(event -> {
            List<Flight> flights = websiteFlights;
            boolean f = !from.getText().trim().isEmpty() && !from.getText().equals("From");
            boolean t = !to.getText().trim().isEmpty() && !to.getText().equals("To");
            boolean d = date.getValue() != null && !date.getValue().toString().trim().isEmpty();

            if (f && t) {
                flights = flights.stream().filter(flight -> flight.getCityDep().equals(from.getText())).filter(flight -> flight.getCityDes().equals(to.getText())).collect(Collectors.toList());
            } else if (f) {
                flights = flights.stream().filter(flight -> flight.getCityDep().equals(from.getText())).collect(Collectors.toList());
            } else if (t) {
                flights = flights.stream().filter(flight -> flight.getCityDes().equals(to.getText())).collect(Collectors.toList());
            }
            if (d) {
                flights = flights.stream().filter(dates -> dates.getDepartureDate().toLocalDate().equals(date.getValue())).collect(Collectors.toList());
            }
            this.flightList.clear();
            this.flightList.addAll(flights);
        });
        var hBox = SceneSetups.createHBox();
        hBox.getChildren().addAll(v1,v2,v3,search,clear);
        return hBox;
    }
}

