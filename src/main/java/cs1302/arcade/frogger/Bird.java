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

public class Bird extends ImageView {
    private Image birdImg;
    private Timeline timeline;

    public Bird(Double x, Timeline t) {
	super();
	setImage();
	this.setImage(birdImg);
	this.setY(500);
	this.setX(x);
	timeline = t;
    }

    public void setImage() {
	birdImg = new Image("frogger/bird.png", 65, 65, true, true);
    }

    public void runBird() {
	EventHandler<ActionEvent> handler = event -> {
	    if(this.getY() <= 0) {
		this.setY(500);
	    } else {
		this.setY(this.getY() - 5);
	    }
	};
	KeyFrame keyFrame = new KeyFrame(Duration.millis(1000/60), handler);
	timeline.setCycleCount(Timeline.INDEFINITE);
	timeline.getKeyFrames().add(keyFrame);
	timeline.play();
    }
}
