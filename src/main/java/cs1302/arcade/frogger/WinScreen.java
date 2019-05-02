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

public class WinScreen extends StackPane {

    private Rectangle rect;
    private VBox textLayer;
    private Text win;
    private Text backToMenu;
    private Boolean back;
    
    public WinScreen() {
	super();
	setRect();
	setVBox();
	back = false;
	this.setMaxSize(400, 360);
	this.setAlignment(rect,Pos.CENTER);
	this.setAlignment(textLayer, Pos.CENTER);
	this.getChildren().addAll(rect, textLayer);
    }
    public void setRect() {
	Color rCol = Color.DARKSLATEBLUE;
	rect = new Rectangle(400, 360, rCol);
	Color sCol = Color.INDIANRED;
	rect.setStroke(sCol);
	rect.setStrokeWidth(5);
    }
    
    public void setText() {
	win = new Text();
	win.setCache(true);
	win.setX(10.0f);
	win.setY(100.0f);
	win.setFill(Color.INDIANRED);
	win.setText("YOU WIN");
	win.setFont(Font.font(null, FontWeight.BOLD, 32));

	backToMenu = new Text();
	backToMenu.setCache(true);
	backToMenu.setX(10.0f);
	backToMenu.setY(100.0f);
	backToMenu.setFill(Color.INDIANRED);
	backToMenu.setText("Press ENTER to return " + "\n" + "to welcome screen");
	backToMenu.setFont(Font.font(null, FontWeight.BOLD, 18));
    }
    public void setVBox() {
	textLayer = new VBox(10);
	textLayer.setAlignment(Pos.CENTER);
	setText();
	textLayer.getChildren().addAll(win, backToMenu);
    }
    
    public EventHandler<? super KeyEvent> createWelcomeKeyHandler() {
	return event -> {
	    if(event.getCode() == KeyCode.ENTER) back = true;
	};
    }

    public boolean getBack() {
	return back;
    }

    public void resetSelect() {
	back = false;
    }
}
    
