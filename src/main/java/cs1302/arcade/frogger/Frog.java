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

public class Frog extends ImageView {
    private Image pepe;
    private Double xCor;
    private Double yCor;

    public Frog() {
	super();
	pepe = new Image("frogger/pixel_Pepe.jpg", 75, 75, true, true);
	xCor = 40.0;
	yCor = 60.0;
	this.setImage(pepe);
	this.setX(xCor);
	this.setY(yCor);
    }
    
    public EventHandler<? super KeyEvent> createKeyHandler() {
	this.setFocusTraversable(true);
	return event -> {
	    Thread t = new Thread(() -> {
		    if(event.getCode() == KeyCode.LEFT && this.getX() > 10) Platform.runLater(() -> {this.setX(this.getX() - 15.0);});
		    if(event.getCode() == KeyCode.RIGHT && this.getX() < 550) Platform.runLater(() -> {this.setX(this.getX() + 15.0);});
		    if(event.getCode() == KeyCode.UP && this.getY() > 10) Platform.runLater(() -> {this.setY(this.getY() - 15.0);});
		    if(event.getCode() == KeyCode.DOWN && this.getY() < 450) Platform.runLater(() -> {this.setY(this.getY() + 15.0);});
	    });
	    t.setDaemon(true);
	    t.start();
	};
    }
}
