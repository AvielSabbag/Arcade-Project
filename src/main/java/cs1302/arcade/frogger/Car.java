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
    private Timeline timeline;
    
    public Car(Double y, Timeline t) {
	super();
	setImages();
	this.setImage(allCars[ran.nextInt(3)]);
	this.setY(y);
	this.setX(650);
	timeline = t;
    }

    public void setImages() {
	allCars = new Image[3];
	allCars[0] = new Image("frogger/blueCar.png", 100, 100, true, true);
	allCars[1] = new Image("frogger/greenCar.png", 100, 100, true, true);
	allCars[2] = new Image("frogger/redCar.png", 100, 100, true, true);
    }//setImages

    public void runCar(int speed) {
	EventHandler<ActionEvent> handler = event -> {
	    if(this.getX() < 0) {
		this.setX(650);
	    } else {
		this.setX(this.getX() - speed);
	    }
	};

    
	KeyFrame keyFrame = new KeyFrame(Duration.millis(1000/60), handler);
	timeline.setCycleCount(Timeline.INDEFINITE);
	timeline.getKeyFrames().add(keyFrame);
	timeline.play();
	
    }//runCar
}
