package hello.world.demo.view;


import hello.world.demo.WebsiteApplication;
import hello.world.demo.model.Flight;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SceneSetups {

    public SceneSetups() {
    }

    public static HBox basicButtonBox(WebsiteApplication application) {
        var homeButton = SceneSetups.buttonSetup("Home");
        homeButton.setOnAction(event -> application.showWebsiteUI());
        var hBox = SceneSetups.createHBox();
        hBox.getChildren().add(homeButton);
        return hBox;
    }


    public static Button buttonSetup(String label) {
        var button = new Button(label);
        button.setFont(new Font("Arial", 12));
        button.setTextFill(Color.WHITE);
        button.setBackground(new Background(new BackgroundFill(Color.DARKBLUE.desaturate(),
                CornerRadii.EMPTY, Insets.EMPTY)));
        return button;
    }

    public static HBox createHBox() {
        var hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.setBackground(new Background(new BackgroundFill(Color.DARKBLUE.desaturate(),
                CornerRadii.EMPTY, Insets.EMPTY)));
        hBox.setPadding(new Insets(5,0,5,0));
        return hBox;
    }

    public static TableView<Flight> createTableView(ObservableList<Flight> flightList, WebsiteApplication application) {
        var table = new TableView<>(flightList);
        table.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
                application.showFlightDetailsScene(table.getSelectionModel().getSelectedItem());
            }
        });

        //creates new TableColumn for the TableView and add where to take the values
        var flightNumber = new TableColumn<Flight, String>("Number");
        flightNumber.setCellValueFactory(new PropertyValueFactory<>("flight"));
        var cityOfDestination = new TableColumn<Flight, String>("To");
        cityOfDestination.setCellValueFactory(new PropertyValueFactory<>("cityDes"));
        var cityOfDeparture = new TableColumn<Flight, String>("From");
        cityOfDeparture.setCellValueFactory(new PropertyValueFactory<>("cityDep"));
        var airline = new TableColumn<Flight, String>("Airline");
        airline.setCellValueFactory(new PropertyValueFactory<>("airline"));
        var departureDate = new TableColumn<Flight, String>("Departure");
        departureDate.setCellValueFactory(new PropertyValueFactory<>("departure"));
        departureDate.setMinWidth(30);
        var arrivalDate = new TableColumn<Flight, String>("Arrival");
        arrivalDate.setCellValueFactory(new PropertyValueFactory<>("arrival"));
        arrivalDate.setMinWidth(30);
        var economyPrice = new TableColumn<Flight, Double>("Economy");
        economyPrice.setCellValueFactory(new PropertyValueFactory<>("economyPrice"));
        var businessPrice = new TableColumn<Flight, Double>("Business");
        businessPrice.setCellValueFactory(new PropertyValueFactory<>("businessPrice"));

        // add Columns to table
        table.getColumns().addAll(flightNumber, cityOfDeparture, cityOfDestination, departureDate, arrivalDate,
                airline, economyPrice, businessPrice);

        //so that the table adapts to the screen size
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return table;
    }


}
