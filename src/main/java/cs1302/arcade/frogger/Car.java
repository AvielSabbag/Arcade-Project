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
    private Timeline timeline2;
    
    public Car(Double y) {
	super();
	setImages();
	this.setImage(allCars[ran.nextInt(3)]);
	this.setY(y);
	this.setX(650);
	timeline2 = new Timeline();
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
		    if(this.getX() < 0) {
			this.setX(650);
		    } else {
			Platform.runLater(() -> this.setX(this.getX() - 6));
		    }
	    });
	    t.setDaemon(true);
	    t.start();
	    
	};
	
	KeyFrame keyFrame = new KeyFrame(Duration.millis(1000/60), handler);
	timeline2.setCycleCount(Timeline.INDEFINITE);
	timeline2.getKeyFrames().add(keyFrame);
	timeline2.play();
	
    }//runCar
}
