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
import java.util.*;
import javafx.util.*;

public class Car extends ImageView {
    private Image[] allCars;
    private Random ran = new Random();
    
    public Car(Double y) {
	super();
	setImages();
	this.setImage(allCars[ran.nextInt(3)]);
	this.setY(y);
	this.setX(650);
    }

    public void setImages() {
	allCars = new Image[3];
	allCars[0] = new Image("frogger/blueCar.png", 75, 75, true, true);
	allCars[1] = new Image("frogger/greenCar.png", 75, 75, true, true);
	allCars[2] = new Image("frogger/redCar.png", 75, 75, true, true);
    }//setImages

    public void runCar() {
	EventHandler<ActionEvent> handler = event -> {
	    Thread t = new Thread(() -> {
		    Platform.runLater(() -> this.setX(this.getX() - 2));
	    });
	    t.setDaemon(true);
	    t.start();
	};
	KeyFrame keyFrame = new KeyFrame(Duration.millis(1000/60), handler);
	Timeline timeline = new Timeline();
	timeline.setCycleCount(Timeline.INDEFINITE);
	timeline.getKeyFrames().add(keyFrame);
	timeline.play();
    }//runCar
}
