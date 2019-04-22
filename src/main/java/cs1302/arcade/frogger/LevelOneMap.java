package cs1302.arcade.frogger;

import javafx.application.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.image.*;
import javafx.scene.text.*;
import javafx.scene.effect.*;
import javafx.scene.input.*;
import javafx.animation.*;
import javafx.util.*;

public class LevelOneMap extends StackPane {
    private ImageView grass;
    private Rectangle road1;
    private Rectangle road2;
    private VBox roadLayer;
    private Pane frogLayer;
    private Pane carLayer;
    private Frog pepe;
    
    public LevelOneMap() {
	super();
	setImages();
	setVbox();
	this.getChildren().addAll(grass, roadLayer, frogLayer, carLayer);
	spawnCarBottom();
	spawnCarTop();
    }//LevelOneMap
    public void setImages() {
	grass = new ImageView(new Image("frogger/grass.png"));
	road1 = new Rectangle(640, 80, Color.BLACK);
	road2 = new Rectangle(640, 80, Color.BLACK);
	frogLayer = new Pane();
	carLayer = new Pane();
	pepe = new Frog();
	frogLayer.getChildren().add(pepe);
	pepe.setX(300);
	pepe.setY(400);
    }

    public void setVbox() {
	roadLayer = new VBox(150);
	roadLayer.setPadding(new Insets(100, 0, 100, 0));
	roadLayer.getChildren().addAll(road1, road2);
    }

    public Frog getFrog() {
	return pepe;
    }

    public void spawnCarBottom() {
	EventHandler<ActionEvent> handler = event -> {
	    Car c1 = new Car(340.0);
	    carLayer.getChildren().add(c1);
	    c1.runCar();
	};
	KeyFrame keyFrame = new KeyFrame(Duration.seconds(2.5), handler);
	Timeline timeline = new Timeline();
	timeline.setCycleCount(Timeline.INDEFINITE);
	timeline.getKeyFrames().add(keyFrame);
	timeline.play();
    }

    public void spawnCarTop() {
	EventHandler<ActionEvent> handler = event -> {
	    Car c1 = new Car(110.0);
	    carLayer.getChildren().add(c1);
	    c1.runCar();
	};
	KeyFrame keyFrame = new KeyFrame(Duration.seconds(2.5), handler);
	Timeline timeline = new Timeline();
	timeline.setCycleCount(Timeline.INDEFINITE);
	timeline.getKeyFrames().add(keyFrame);
	timeline.play();
    }
}
    
	
