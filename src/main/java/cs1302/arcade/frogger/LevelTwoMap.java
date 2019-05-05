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
import javafx.util.*;
import java.util.*;
/**
 *This class builds the first level of frogger
 */
public class LevelTwoMap extends Group {
    private ImageView grass;
    private Rectangle road1;
    private Rectangle road2;
    private Rectangle finishLine;
    private VBox fullLayer;
    private HBox scoreLayer;
    private Text score;
    private Text level1;
    private Text lives;
    private int scoreNum;
    private int livesNum;
    private VBox roadLayer;
    private Pane frogLayer;
    private Pane carLayer;
    private Frog pepe;
    private Car topCar;
    private Car bottomCar;
    private Timeline timeline;
    private StackPane stack;
    private boolean win;
    /**
     *Constructor for first level
     *@param t {@code Timeline} that the cars of the level run on
     */
    public LevelTwoMap(Timeline t) {
	super();
	timeline = t;
	setImages();
	setRoads();
	setScoreBar();
	fullLayer = new VBox();
	stack = new StackPane();
	win = false;
	stack.getChildren().addAll(grass, roadLayer, frogLayer, carLayer);
	fullLayer.getChildren().addAll(scoreLayer, stack);
	spawnCarBottom();
	spawnCarTop();
	checkCollisions();
	this.getChildren().add(fullLayer);
    }//LevelOneMap
     /**
     *Sets all neccessary imageViews and shapes for the level
     *
     */
    public void setImages() {
	grass = new ImageView(new Image("frogger/grass.png"));
	road1 = new Rectangle(640, 80, Color.BLACK);
	road2 = new Rectangle(640, 80, Color.BLACK);
	finishLine = new Rectangle(640, 10, Color.RED);
	finishLine.setY(30);
	frogLayer = new Pane();
	carLayer = new Pane();
	pepe = new Frog();
	frogLayer.getChildren().add(pepe);
	pepe.setX(300);
	pepe.setY(450);
	topCar = new Car(125.0, timeline);
	bottomCar = new Car(355.0, timeline);
	carLayer.getChildren().addAll(topCar, bottomCar, finishLine);
    }
     /**
     *Aligns score bar into the scene graph
     */
    public void setScoreBar() {
	scoreLayer = new HBox();
	scoreLayer.setPrefWidth(75);
	scoreNum = 0;
	livesNum = 3;
	score = new Text("Score: " + scoreNum);
	level1 = new Text("LEVEL TWO");
	lives = new Text("Lives: " + livesNum);
	scoreLayer.getChildren().addAll(score, level1, lives);
	score.setTextAlignment(TextAlignment.LEFT);
	lives.setTextAlignment(TextAlignment.RIGHT);
	level1.setTextAlignment(TextAlignment.CENTER);
	scoreLayer.setSpacing(200.0);
    }
    /**
     *Aligns roads into the scene graph
     */
    public void setRoads() {
	roadLayer = new VBox(150);
	roadLayer.setPadding(new Insets(100, 0, 100, 0));
	roadLayer.getChildren().addAll(road1, road2);
    }
    /**
     *Returns the frog object of the level
     *@return Frog current frog of the level
     */
    public Frog getFrog() {
	return pepe;
    }
    /**
     *Begins the bottom Car animation loop with a speed of 6 pixels/frame
     */
    public void spawnCarBottom() {
	bottomCar.runCar(10);
    }
    /**
     *Begins the top Car animation loop with a speed of 6 pixels/frame
     */
    public void spawnCarTop() {
	topCar.runCar(10);
    }
    /**
     *Sets the loop for checking for the frog hitting cars, the finish line, or in the road 
     *collecting points
     */
    public void checkCollisions() {
	EventHandler<ActionEvent> handler = event -> {
	    if(pepe.getBoundsInParent().intersects(bottomCar.getBoundsInParent()) ||
	       pepe.getBoundsInParent().intersects(topCar.getBoundsInParent()) ) {
		pepe.setX(300);
		pepe.setY(450);
		livesNum--;
		lives.setText("Lives: " + livesNum);
	    }
	    if(pepe.getY() < 25) {
		win = true;//Change to win screen
	    }
	    
	    if(pepe.getBoundsInParent().intersects(road1.getBoundsInParent()) ||
	       pepe.getBoundsInParent().intersects(road2.getBoundsInParent())) {
		scoreNum += 2;
		score.setText("Score: " + scoreNum);
	    }
	};
	KeyFrame keyFrame = new KeyFrame(Duration.millis(1000/60), handler);
	timeline.setCycleCount(Timeline.INDEFINITE);
	timeline.getKeyFrames().add(keyFrame);
	timeline.play();
    }
    /**
     *Returns the score of the level
     *@return int score of the level
     */
    public int getScore() {
	return scoreNum;
    }
    /**
     *Returns the current lives of the level
     *@return int the current lives of the level
     */
    public int getLives() {
	return livesNum;
    }
    /**
     *Returns whether the player has reached the finish line 
     *@return boolean true if the player has reached the finish line, false otherwise
     */
    public boolean getWin() {
	return win;
    }
    /**
     *Sets the stats of the level based on the previous level
     */
    public void setStats(LevelOneMap lvl1) {
	scoreNum = lvl1.getScore();
	score.setText("Score: " + scoreNum);
	livesNum = lvl1.getLives();
	lives.setText("Lives: " + livesNum);
    }
     /**
     *Resets lives, win status, and score of the level
     */
    public void resetStats() {
	scoreNum = 0;
	score.setText("Score: " + scoreNum);
	livesNum = 3;
	lives.setText("Lives: " + livesNum);
	pepe.setX(300);
	pepe.setY(450);
	win = false;
    }
    /**
     *Returns timeline used in the level
     *@return Timeline the animation timeline used in the level
     */
    public Timeline getTimeline() {
	return timeline;
    }
}
    
