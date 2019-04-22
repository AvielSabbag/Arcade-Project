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
	    if(event.getCode() == KeyCode.LEFT) this.setX(this.getX() - 15.0);
	    if(event.getCode() == KeyCode.RIGHT) this.setX(this.getX() + 15.0);
	    if(event.getCode() == KeyCode.UP) this.setY(this.getY() - 15.0);
	    if(event.getCode() == KeyCode.DOWN) this.setY(this.getY() + 15.0);
	};
    }
}
