package hello.world.demo.view;


import hello.world.demo.WebsiteApplication;
import hello.world.demo.model.Flight;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public class WebsiteUI extends Scene {
    public final WebsiteApplication application;

    public WebsiteUI(WebsiteApplication application) throws FileNotFoundException {
        super(new VBox(), 800, 500);
        this.application = application;
        this.setFill(Color.WHITE);
        var vBox = new VBox(20, createHomePage("My FlightList", "src/client/java/hello/world/demo/view/res/IMG-2868.jpg", 1),
                createHomePage("Find a destination", "src/client/java/hello/world/demo/view/res/IMG-1015.jpg", 2),
                createHomePage("Safety Instructions", "src/client/java/hello/world/demo/view/res/safetyLogo.jpg", 3));
        vBox.setAlignment(Pos.CENTER);
        vBox.setBackground(new Background(new BackgroundFill(Color.WHITE,
                CornerRadii.EMPTY, Insets.EMPTY)));

        var pane = new ScrollPane();
        pane.setContent(vBox);
        pane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        pane.setBackground(new Background(new BackgroundFill(Color.WHITE,
                CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setMinViewportHeight(0);
        pane.setFitToWidth(true);
        pane.setFitToHeight(true);
        var borderPane = new BorderPane();
        borderPane.setTop(createButtonBox());
        borderPane.setBottom(createRequestBox());
        borderPane.setCenter(pane);
        setRoot(borderPane);
    }

    private HBox createRequestBox() {
        var requestButton = SceneSetups.buttonSetup("Request Service");
        requestButton.setOnAction(event -> {
            List<Flight> flights = application.getMyFlights();
            Flight flight = flights.stream().filter(f -> f.getArrivalDate().isAfter(LocalDateTime.now()) && f.getDepartureDate().isBefore(LocalDateTime.now())).findFirst().orElse(null);
            Alert a = new Alert(Alert.AlertType.INFORMATION, "", ButtonType.OK);
            a.getDialogPane().setBackground(new Background(new BackgroundFill(Color.WHITE,
                    CornerRadii.EMPTY, Insets.EMPTY)));
            if (flight == null) {
                a.setTitle("RequestService");
                a.setHeaderText("This button can be used to notify the service staff during your flight.");
            } else {
                a.setTitle("Notify stuff");
                a.setHeaderText("The staff has been notified.");
            }
            Optional<ButtonType> result = a.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                a.close();
            }
        });
        var hBox = SceneSetups.createHBox();
        hBox.getChildren().add(requestButton);
        return hBox;
    }

    private HBox createButtonBox() {
        var myFlightButton = SceneSetups.buttonSetup("FlightList");
        myFlightButton.setOnAction(event -> application.showMyFlightScene());
        var flightButton = SceneSetups.buttonSetup("Available Flights");
        flightButton.setOnAction(event -> application.showAllFlightScene());
        ;
        var safetyInstructionButton = SceneSetups.buttonSetup("Safety Instruction");
        safetyInstructionButton.setOnAction(event -> application.showSafetyInstructionScene());
        var hBox = SceneSetups.createHBox();
        hBox.setPadding(new Insets(20, 0, 20, 0));
        hBox.getChildren().addAll(myFlightButton, flightButton, safetyInstructionButton);
        return hBox;
    }

    public VBox createHomePage(String l, String image, int des) throws FileNotFoundException {
        var stackPane = new StackPane();
        Image image1 = new Image(new FileInputStream(image));
        ImageView view = new ImageView(image1);
        view.setFitHeight(200);
        view.setFitWidth(600);
        var button = new Button();
        button.setGraphic(view);
        if (des == 1) {
            button.setOnAction(event -> application.showMyFlightScene());
        } else if (des == 2) {
            button.setOnAction(event -> application.showAllFlightScene());
        } else {
            button.setOnAction(event -> application.showSafetyInstructionScene());
        }

        var label = new Label(l);
        label.setMaxSize(600, 40);
        label.setBackground(new Background(new BackgroundFill(Color.WHITE,
                CornerRadii.EMPTY, Insets.EMPTY)));
        label.setTextFill(Color.web("#0076a3"));
        label.setFont(new Font("Arial", 25));
        label.setAlignment(Pos.CENTER);
        stackPane.getChildren().add(button);
        stackPane.getChildren().add(label);
        return new VBox(stackPane);

    }
}
