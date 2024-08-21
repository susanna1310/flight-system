package hello.world.demo.view;


import hello.world.demo.WebsiteApplication;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class SafetyInstructionScene extends Scene {
    private final WebsiteApplication application;
    private final GridPane gridPane;

    public SafetyInstructionScene(WebsiteApplication application) throws FileNotFoundException {
        super(new VBox(), 800, 500);
        this.application = application;
        this.gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setBackground(new Background(new BackgroundFill(Color.WHITE,
                CornerRadii.EMPTY, Insets.EMPTY)));
        this.gridPane.setPadding(new Insets(5));
        var title = new Label("Safety Instructions");
        title.setFont(new Font("Arial", 25));
        title.setTextFill(Color.WHITE);
        var titleBox = new HBox(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setBackground(new Background(new BackgroundFill(Color.DARKBLUE.desaturate(),
                CornerRadii.EMPTY, Insets.EMPTY)));
        titleBox.setPadding(new Insets(10,0,10,0));
        var spaceHolder = new Text();
        spaceHolder.setFont(new Font("Arial", 24));
        gridPane.add(spaceHolder, 0,1);
        createInstructionAndPicture();
        ColumnConstraints cConstraints = new ColumnConstraints();
        cConstraints.setHalignment(HPos.RIGHT);
        cConstraints.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().addAll(cConstraints, cConstraints);
        ScrollPane pane = new ScrollPane();
        pane.setBackground(new Background(new BackgroundFill(Color.WHITE,
                CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setContent(this.gridPane);
        pane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        pane.setFitToWidth(true);
        pane.prefViewportWidthProperty().bind(this.application.getStage().widthProperty());
        var borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setTop(titleBox);
        borderPane.setBottom(SceneSetups.basicButtonBox(application));
        setRoot(borderPane);
    }

    //create label with text and picture and adds it to the Gridpane
    private void createInstructionAndPicture() throws FileNotFoundException {
        var label1 = new Label("SAFETY REQUEST");
        label1.setFont(new Font("Arial", 20));
        var b1 = new VBox(10, new Label(), new Label(), label1, setupText("• After boarding, store your baggage securely, either in an overhead compartment or under the seat in front of you."),
                setupText("• Whenever the seatbelt sign is on, please make sure your seatbelt is fastened."),
                setupText("• Return your seat and table to their original position during  takeoff and landing."),
                setupText("• Smoking is strictly prohibited!"),
                setupText("• The emergency exists are in the front, the middle and the back of the plane."));
        Image image1 = new Image(new FileInputStream("src/client/java/hello/world/demo/view/res/seatbelt.jpg"));
        ImageView view1 = setupView(image1);
        gridPane.add(b1, 0,2);
        GridPane.setHalignment(b1, HPos.RIGHT);
        var hBox1= new HBox(view1);
        hBox1.setAlignment(Pos.CENTER_RIGHT);
        gridPane.add(hBox1, 1, 2);
        var label2 = new Label("OXYGEN EMERGENCY");
        label2.setFont(new Font("Arial", 20));
        var b2 = new VBox(10, new Label(), new Label(), label2,
                setupText("• If there is a loss of cabin pressure, the panels above your seat will open, and oxygen masks will drop down."),
                setupText("• If this happens place the mask over your nose and mouth, and adjust it as necessary."),
                setupText("• Be sure to adjust your own mask before helping others."));
        Image image2 = new Image(new FileInputStream("src/client/java/hello/world/demo/view/res/oxygen.jpg"));
        ImageView view2 = setupView(image2);
        gridPane.add(b2, 0, 3);
        GridPane.setValignment(b2, VPos.CENTER);
        var hBox2= new HBox(view2);
        hBox2.setAlignment(Pos.CENTER_RIGHT);
        gridPane.add(hBox2, 1, 3);
        var label3 = new Label("LIVE VEST");
        label3.setFont(new Font("Arial", 20));
        var b3 = new VBox(10, new Label(), new Label(), label3,
                 setupText("• In the event of a water landing, life vests are under your seat."),
                setupText("• Pull the life vest over your head."), setupText("• Take the straps and put them through the buckle on the front side of the vest."),
                 setupText("• Tighten the straps by pulling them away from you."), setupText("• Lastly blow up you life vest by blowing into the the red straw at the top of the vest"));
        Image image3 = new Image(new FileInputStream("src/client/java/hello/world/demo/view/res/vest.jpg"));
        ImageView view3 = setupView(image3);
        gridPane.add(b3, 0, 4);
        GridPane.setValignment(b3, VPos.CENTER);
        var hBox3= new HBox(view3);
        hBox3.setAlignment(Pos.CENTER_RIGHT);
        gridPane.add(hBox3, 1, 4);
        var label4 = new Label("EMERGENCY SLIDE");
        label4.setFont(new Font("Arial", 20));
        var b4 = new VBox(10, new Label(), new Label(), label4, setupText("• In the event of an unexpected landing, either on water or the ground, lean over and wrap your arms around your legs or put your arms on the back of the seat in front of you."),
                 setupText("• Baggage could cause an obstruction in the event of an evacuation or damage the slide and render it unusable. For this reason, passengers must leave all baggage behind."),
                setupText("• High heels could damage or deflate the slide and must therefore be removed."));
        Image image4 = new Image(new FileInputStream("src/client/java/hello/world/demo/view/res/slide.jpg"));
        ImageView view4 = setupView(image4);
        gridPane.add(b4, 0, 5);
        GridPane.setValignment(b4, VPos.CENTER);
        var hBox4 = new HBox(view4);
        hBox4.setAlignment(Pos.CENTER_RIGHT);
        gridPane.add(hBox4, 1, 5);

    }

    //creates ImageView with given Image and adjusts the size
    private ImageView setupView(Image image) {
        ImageView view = new ImageView(image);
        view.setFitHeight(400);
        view.setFitWidth(400);
        return view;
    }

    //creates Text with given String and adjusts spacing and Font
    private Text setupText(String content) {
        Text text = new Text(content);
        text.setLineSpacing(2.0);
        text.setFont(new Font("Arial", 18));
        text.setWrappingWidth(390);
        return text;
    }

}