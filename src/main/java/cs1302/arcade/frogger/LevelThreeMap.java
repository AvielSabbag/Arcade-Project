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

public class LevelThreeMap extends Group {
    private ImageView grass;
    private Rectangle road1;
    private Rectangle road2;
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
    private Bird bibi;
    
    public LevelThreeMap() {
	super();
	timeline = new Timeline();
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
	bibi.runBird();
	checkCollisions();
	this.getChildren().add(fullLayer);
    }//LevelOneMap
    public void setImages() {
	grass = new ImageView(new Image("frogger/grass.png"));
	road1 = new Rectangle(640, 80, Color.BLACK);
	road2 = new Rectangle(640, 80, Color.BLACK);
	frogLayer = new Pane();
	carLayer = new Pane();
	pepe = new Frog();
	bibi = new Bird(timeline);
	frogLayer.getChildren().add(pepe);
	pepe.setX(300);
	pepe.setY(450);
	topCar = new Car(125.0, timeline);
	bottomCar = new Car(355.0, timeline);
	carLayer.getChildren().addAll(topCar, bottomCar, bibi);
    }
    
    public void setScoreBar() {
	scoreLayer = new HBox();
	scoreLayer.setPrefWidth(75);
	scoreNum = 0;
	livesNum = 3;
	score = new Text("Score: " + scoreNum);
	level1 = new Text("LEVEL THREE");
	lives = new Text("Lives: " + livesNum);
	scoreLayer.getChildren().addAll(score, level1, lives);
	score.setTextAlignment(TextAlignment.LEFT);
	lives.setTextAlignment(TextAlignment.RIGHT);
	level1.setTextAlignment(TextAlignment.CENTER);
	scoreLayer.setSpacing(200.0);
    }
    
    public void setRoads() {
	roadLayer = new VBox(150);
	roadLayer.setPadding(new Insets(100, 0, 100, 0));
	roadLayer.getChildren().addAll(road1, road2);
    }

    public Frog getFrog() {
	return pepe;
    }

    public void spawnCarBottom() {
	bottomCar.runCar(8);
    }

    public void spawnCarTop() {
	topCar.runCar(8);
    }

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
	    if(pepe.getBoundsInParent().intersects(bibi.getBoundsInParent())) {
		    livesNum--;
		    lives.setText("Lives: " + livesNum);
		}
	};
	KeyFrame keyFrame = new KeyFrame(Duration.millis(1000/60), handler);
	timeline.setCycleCount(Timeline.INDEFINITE);
	timeline.getKeyFrames().add(keyFrame);
	timeline.play();
    }

    public int getScore() {
	return scoreNum;
    }

    public int getLives() {
	return livesNum;
    }

    public boolean getWin() {
	return win;
    }
    public void setStats(LevelTwoMap lvl1) {
	scoreNum = lvl1.getScore();
	score.setText("Score: " + scoreNum);
	livesNum = lvl1.getLives();
	lives.setText("Lives: " + livesNum);
    }
    public void resetStats() {
	scoreNum = 0;
	score.setText("Score: " + scoreNum);
	livesNum = 3;
	lives.setText("Lives: " + livesNum);
	pepe.setX(300);
	pepe.setY(450);
	win = false;
    }

    public Timeline getTimeline() {
	return timeline;
    }
}
