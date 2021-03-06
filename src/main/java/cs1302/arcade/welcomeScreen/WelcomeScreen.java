package cs1302.arcade.welcomeScreen;

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

public class WelcomeScreen extends BorderPane  {
    /** class that builds the functioning welcome screen with list of games */
    private StackPane stack;
    private Rectangle rect;
    private VBox titleLayer;
    private VBox gamesLayer;
    private Text gameOne;
    private Text gameTwo;
    private Text title;
    private Text names;
    private Text exit;
    private boolean gameOneSelect;
    private boolean gameTwoSelect;
    /** Constructor for welcome screen */
    public WelcomeScreen() {
	super();
	setRect();
	setTitleLayer();
	setGamesLayer();
	setStack();
	gameOneSelect = false;
	gameTwoSelect = false;
	this.setCenter(stack);
    }
    /** sets and aligns background rectangle */
    public void setRect() {
	Color rCol = Color.DARKSLATEBLUE;
	rect = new Rectangle(400, 360, rCol);
	Color sCol = Color.INDIANRED;
	rect.setStroke(sCol);
	rect.setStrokeWidth(5);
    }//setRect
    /** sets and aligns all nodes into a stackpane */
    public StackPane setStack() {
	stack = new StackPane();
	stack.setMaxSize(400, 360);
	stack.setAlignment(rect, Pos.CENTER);
	stack.setAlignment(titleLayer, Pos.TOP_CENTER);
	stack.setAlignment(gamesLayer, Pos.CENTER);
	stack.setAlignment(exit, Pos.BOTTOM_RIGHT);
	stack.setMargin(titleLayer, new Insets(20, 20, 10, 10));
	stack.setMargin(exit, new Insets(10, 10, 10, 10));
	stack.getChildren().addAll(rect, titleLayer, gamesLayer, exit);
	return stack;
    }//setStack
    /** Sets font of title */
    public void setTitle() {
	DropShadow ds = new DropShadow();
	ds.setOffsetY(3.0f);
	ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
	
	title = new Text();
	title.setEffect(ds);
	title.setCache(true);
	title.setX(10.0f);
	title.setY(270.0f);
	title.setFill(Color.INDIANRED);
	title.setText("CS1302 ARCADE");
	title.setFont(Font.font(null, FontWeight.BOLD, 32));
	
    }//setTitle
    /** sets font of authors */
    public void setNames() {
	names = new Text();
	names.setCache(true);
	names.setX(10.0f);
	names.setY(100.0f);
	names.setFill(Color.INDIANRED);
	names.setText("BY: " + "\n"+ "AVIEL SABBAG & CAM LAHEY");
	names.setFont(Font.font(null, FontWeight.BOLD, 18));
    }
    /** sets all text into a VBox */
    public void setTitleLayer() {
	titleLayer = new VBox(10);
	titleLayer.setAlignment(Pos.TOP_CENTER);
	setTitle();
	setNames();
	titleLayer.getChildren().addAll(title, names);
    }
    /** sets font of game lists and exit */
    public void setGames() {
	gameOne = new Text();
	gameOne.setCache(true);
	gameOne.setX(10.0f);
	gameOne.setY(100.0f);
	gameOne.setFill(Color.INDIANRED);
	gameOne.setText("@- FROGGER");
	gameOne.setFont(Font.font(null, FontWeight.BOLD, 18));
	gameOne.setUnderline(true);
	
	gameTwo = new Text();
	gameTwo.setCache(true);
	gameTwo.setX(10.0f);
	gameTwo.setY(100.0f);
	gameTwo.setFill(Color.INDIANRED);
	gameTwo.setText("@- 2048");
	gameTwo.setFont(Font.font(null, FontWeight.BOLD, 18));

	exit = new Text();
	exit.setCache(true);
	exit.setX(10.0f);
	exit.setY(100.0f);
	exit.setFill(Color.INDIANRED);
	exit.setText("EXIT");
	exit.setFont(Font.font(null, FontWeight.BOLD, 18));

	
    }
    /** sets game list into a VBox */
    public void setGamesLayer() {
	gamesLayer = new VBox(10);
	gamesLayer.setAlignment(Pos.CENTER_LEFT);
	setGames();
	gamesLayer.getChildren().addAll(gameOne, gameTwo);
	gamesLayer.setMargin(gameOne, new Insets(10, 100, 10, 100));
	gamesLayer.setMargin(gameTwo, new Insets(10, 100, 10, 100));
    }
    /** underlines one of the games in the list */
    public void turnBold() {
	if(gameOne.isUnderline()) {
	    gameOne.setUnderline(false);
	    gameTwo.setUnderline(true);
	} else if(gameTwo.isUnderline()) {
	    gameTwo.setUnderline(false);
	    gameOne.setUnderline(true);
	}
    }
    /** underlines the exit option for easier selection */
    public void turnExitBold() {
	if(gameOne.isUnderline() || gameTwo.isUnderline()) {
	    gameOne.setUnderline(false);
	    gameTwo.setUnderline(false);
	    exit.setUnderline(true);
	} else {
	    gameOne.setUnderline(true);
	    exit.setUnderline(false);
	}
    }
    /** allows for selection of game one, game two, or exit */
    public void performEnter() {
	if(exit.isUnderline()) {
	    Platform.exit();
	}
	if(gameOne.isUnderline()) {
	    gameOneSelect = true;
	}
	if(gameTwo.isUnderline()) {
	    gameTwoSelect = true;
	}
	    
    }
    /** creates KeyEvent for navigating the list of games and selecting one
     *@return EventHandler 
     */
    public EventHandler<? super KeyEvent> createWelcomeKeyHandler() {
	return event -> {
	    if(event.getCode() == KeyCode.UP) turnBold();
	    if(event.getCode() == KeyCode.DOWN) turnBold();
	    if(event.getCode() == KeyCode.RIGHT) turnExitBold();
	    if(event.getCode() == KeyCode.LEFT) turnExitBold();
	    if(event.getCode() == KeyCode.ENTER) performEnter();
	};
    }
    /** Returns which game has been selected 
     *@param i the number of the game to be checked for selection
     *@return boolean true if game correpsonding with parameter has been selected; false otherwise
     */
    public boolean getSelect(int i) {
	if(i == 1) return gameOneSelect;
	if(i == 2) return gameTwoSelect;
	else return false;
    }
    /** resets selection of game for reuse of welcome screen */
    public void resetSelect() {
	gameOneSelect = false;
	gameTwoSelect = false;
    }
    
}
