package hello.world.demo.view;

import hello.world.demo.WebsiteApplication;
import hello.world.demo.model.Flight;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Optional;



public class FeedbackScene extends Scene {
    private WebsiteApplication application;
    private Slider flightSlider;
    private Slider cateringSlider;
    private Slider entertainmentSlider;
    private Slider comfortSlider;
    private Slider serviceSlider;
    private Label feedback;
    private TextArea comment;
    private Flight flight;

    public FeedbackScene(WebsiteApplication application) {
        super(new VBox(), 800, 500);
        this.application = application;
        // adding title
        this.feedback = new Label();
        feedback.setText("Please leave us a feedback");
        feedback.setFont(new Font("Arial", 25));
        feedback.setTextFill(Color.WHITE);
        var feedbackBox = new VBox(new Label(), feedback);
        feedbackBox.setAlignment(Pos.CENTER);
        feedbackBox.setBackground(new Background(new BackgroundFill(Color.DARKBLUE.desaturate(),
                CornerRadii.EMPTY, Insets.EMPTY)));
        feedbackBox.setPadding(new Insets(10,0,10,0));
        ScrollPane pane = new ScrollPane();
        var box = new HBox(createSliderBox());
        box.setAlignment(Pos.CENTER);
        box.setBackground(new Background(new BackgroundFill(Color.WHITE,
                CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setContent(box);
        pane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        pane.setMinViewportHeight(0);
        pane.setFitToWidth(true);
        pane.setFitToHeight(true);
        pane.setBackground(new Background(new BackgroundFill(Color.WHITE,
                CornerRadii.EMPTY, Insets.EMPTY)));
        var borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setTop(feedbackBox);
        borderPane.setBottom(createButtonBox());
        borderPane.setBackground(new Background(new BackgroundFill(Color.WHITE,
                CornerRadii.EMPTY, Insets.EMPTY)));
        setRoot(borderPane);
    }

    private ColumnConstraints constraint(int width) {
        ColumnConstraints constraints = new ColumnConstraints();
        constraints.setMinWidth(width);
        constraints.setHalignment(HPos.RIGHT);
        constraints.setHgrow(Priority.ALWAYS);
        return constraints;
    }

    //creates one slider
    private Slider createSliders() {
        Slider s1 = new Slider(0, 5, 0);
        s1.setShowTickLabels(true);
        s1.setShowTickMarks(true);
        s1.setMajorTickUnit(1);
        s1.setMinorTickCount(0);
        s1.setSnapToTicks(true);
        s1.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
            }
        });
        var box = new HBox(s1);
        box.setAlignment(Pos.CENTER);
        box.setBackground(new Background(new BackgroundFill(Color.WHITE,
                CornerRadii.EMPTY, Insets.EMPTY)));
        return s1;
    }

    //puts together slider and label
    private VBox createSliderBox() {
        //creating the slider
        flightSlider = createSliders();
        flightSlider.setMaxWidth(350);
        cateringSlider = createSliders();
        cateringSlider.setMaxWidth(350);
        entertainmentSlider = createSliders();
        entertainmentSlider.setMaxWidth(350);
        serviceSlider = createSliders();
        serviceSlider.setMaxWidth(350);
        comfortSlider = createSliders();
        comfortSlider.setMaxWidth(350);

        //create comment section
        this.comment = new TextArea();
        comment.setPromptText("Enter your comment here");
        comment.setMinSize(400, 100);
        comment.setPrefColumnCount(10);
        comment.setWrapText(true);
        comment.setMaxWidth(400);
        comment.getText();
        comment.setBackground(new Background(new BackgroundFill(Color.WHITE,
                CornerRadii.EMPTY, Insets.EMPTY)));
        //adds label with new slider for each rating
        var sliderBox = new VBox(10, createLabel("Rate the service"), serviceSlider,
                createLabel("Rate the catering"), cateringSlider,
                createLabel("Rate the comfort"), comfortSlider,
                createLabel("Rate the entertainment"), entertainmentSlider,
                createLabel("Rate the flight"), flightSlider, comment);
        sliderBox.setAlignment(Pos.CENTER);
        sliderBox.setMaxHeight(200);
        sliderBox.setBackground(new Background(new BackgroundFill(Color.WHITE,
                CornerRadii.EMPTY, Insets.EMPTY)));
        return sliderBox;
    }

    private Label createLabel(String label) {
        var flightLabel = new Label();
        flightLabel.setText(label);
        flightLabel.setFont(new Font("Ariel", 20));
        return flightLabel;
    }


    //creates the Back and Done button and puts it in a HBox
    private HBox createButtonBox() {
        var backButton = SceneSetups.buttonSetup("Back");
        backButton.setOnAction(event -> application.showMyFlightScene());
        var saveFeedback = SceneSetups.buttonSetup("Done");
        saveFeedback.setOnAction(event -> {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION,
                    "After confirming changes cannot be made", ButtonType.YES, ButtonType.NO);
            a.setTitle("Save Feedback");
            a.getDialogPane().setBackground(new Background(new BackgroundFill(Color.WHITE,
                    CornerRadii.EMPTY, Insets.EMPTY)));
            a.setHeaderText("Do you want to save your feedback");
            Optional<ButtonType> b = a.showAndWait();
            if (b.isPresent() && b.get() == ButtonType.YES) {
                flightSlider.setDisable(true);
                entertainmentSlider.setDisable(true);
                comfortSlider.setDisable(true);
                serviceSlider.setDisable(true);
                cateringSlider.setDisable(true);
                application.showMyFlightScene();
                comment.setDisable(true);
                saveFeedback.setDisable(true);
                saveFeedback.setVisible(false);
                feedback.setText("Thank you for your Feedback");
                this.flight.getFeedback().updateRating(flightSlider.getValue(),
                        cateringSlider.getValue(), entertainmentSlider.getValue(), serviceSlider.getValue(), comfortSlider.getValue(),
                        comment.getText());
                Alert coupon = new Alert(Alert.AlertType.INFORMATION, "Your Coupon code is '3f63b2'");
                coupon.getDialogPane().setBackground(new Background(new BackgroundFill(Color.WHITE,
                        CornerRadii.EMPTY, Insets.EMPTY)));
                coupon.setTitle("Coupon");
                coupon.setHeaderText("You receive a 30% discount for your next purchase in the flight catering menu!");
                Optional<ButtonType> c = coupon.showAndWait();
                if (c.isPresent() && c.get() == ButtonType.OK) {
                    coupon.close();
                }
            } else {
                a.close();
            }

        });
        //goes back to the flight list on click
        var hBox = SceneSetups.basicButtonBox(application);
        hBox.getChildren().addAll(backButton, saveFeedback);
        return hBox;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }


}