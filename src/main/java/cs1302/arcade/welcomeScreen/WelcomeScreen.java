package cs1302.arcade.welcomeScreen;

import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafs.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
public class WelcomeScreen extends Scene  {
    
    private StackPane stack;
    private Rectangle rect;
    private ImageView TitleScreen;
    private VBox textLayer;
    private HBox gameOneLayer;
    private HBox gameTwoLayer;
    public static WelcomeScreen() {
	super(stack);
	Color scCol = Color.LIGHTBLUE;
	this.setFill(scCol);
    }
    public static void setRect() {
	Color rCol = Color.DARKSLATEBLUE;
	rect = new Rectangle(50, 200, rCol);
	Color sCol = Color.INDIANRED;
	rect.setStroke(sCol);
	rect.setStrokeWidth(5);
    }//setRect
    public static void setStack() {
	stack = new StackPane();
	stackPane.getChildren().add(rect);
    }
