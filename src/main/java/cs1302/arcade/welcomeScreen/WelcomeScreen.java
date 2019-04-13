package cs1302.arcade.welcomeScreen;

import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.image.*;

public class WelcomeScreen extends BorderPane  {
    
    private StackPane stack;
    private Rectangle rect;
    private ImageView TitleScreen;
    private VBox textLayer;
    private HBox gameOneLayer;
    private HBox gameTwoLayer;

    public WelcomeScreen() {
	super();
	setRect();
	setStack();
	this.setCenter(stack);
    }
    public void setRect() {
	Color rCol = Color.DARKSLATEBLUE;
	rect = new Rectangle(400, 360, rCol);
	Color sCol = Color.INDIANRED;
	rect.setStroke(sCol);
	rect.setStrokeWidth(5);

    }//setRect
    public StackPane setStack() {
	stack = new StackPane();
	stack.setAlignment(rect, Pos.CENTER);
	stack.getChildren().add(rect);
	return stack;
    }//setStack

}
