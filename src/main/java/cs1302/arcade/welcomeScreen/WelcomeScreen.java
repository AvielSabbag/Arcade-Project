package cs1302.arcade.welcomeScreen;

import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.image.*;

public class WelcomeScreen extends Scene  {
    
    private StackPane stack;
    private Rectangle rect;
    private ImageView TitleScreen;
    private VBox textLayer;
    private HBox gameOneLayer;
    private HBox gameTwoLayer;

    public WelcomeScreen() {
	setRect();
	setStack();
	super(stack, 300, 300);
	Color scCol = Color.LIGHTBLUE;
	this.setFill(scCol);
    }
    public void setRect() {
	Color rCol = Color.DARKSLATEBLUE;
	rect = new Rectangle(50, 200, rCol);
	Color sCol = Color.INDIANRED;
	rect.setStroke(sCol);
	rect.setStrokeWidth(5);
    }//setRect
    public void setStack() {
	stack = new StackPane();
	stack.getChildren().add(rect);
    }//setStack

}
