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
/**
 *This class represents the frog that will be controlled in each level of Frogger
 */
public class Frog extends ImageView {
    private Image pepe;
    private Double xCor;
    private Double yCor;
    private boolean quit; /**The boolean that holds whether or not a player has quit the game*/
    /**
     *The constructor for each frog instance. Sets the quit {@code boolean} to false and 
     *sets initial xCor and yCor
     */
    public Frog() {
	super();
	quit = false;
	pepe = new Image("frogger/pixel_Pepe.jpg", 75, 75, true, true);
	xCor = 40.0;
	yCor = 60.0;
	this.setImage(pepe);
	this.setX(xCor);
	this.setY(yCor);
    }
    /**
     *Creates a Key Handler in order for the frog to move and quit game
     *@return EventHandler<? super KeyEvent> what the frog will do with certain button presses
     */
    public EventHandler<? super KeyEvent> createKeyHandler() {
	this.setFocusTraversable(true);
	return event -> {
	    Thread t = new Thread(() -> {
		    if(event.getCode() == KeyCode.LEFT && this.getX() > 10)
			Platform.runLater(() -> {this.setX(this.getX() - 15.0);});
		    if(event.getCode() == KeyCode.RIGHT && this.getX() < 550)
			Platform.runLater(() -> {this.setX(this.getX() + 15.0);});
		    if(event.getCode() == KeyCode.UP && this.getY() > 10)
			Platform.runLater(() -> {this.setY(this.getY() - 15.0);});
		    if(event.getCode() == KeyCode.DOWN && this.getY() < 450)
			Platform.runLater(() -> {this.setY(this.getY() + 15.0);});
		    if(event.getCode() == KeyCode.Q) quit = true;
	    });
	    t.setDaemon(true);
	    t.start();
	};
    }//createKeyHandler
    /**
     *Returns the quit {@code boolean}
     *@return boolean whether or not the player has quit the game
     */
    public boolean getQuit() {
	return quit;
    }//getQuit
    /**
     *Used to reset the quit {@code boolean} after a level has been quit
     */
    public void setQuit(boolean q) {
	quit = q;
    }
}
