package cs1302.arcade;

import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import cs1302.arcade.welcomeScreen.*;
import cs1302.arcade.frogger.*;
import cs1302.arcade.new2048.TwentyFortyEight;
import javafx.event.*;
import javafx.animation.*;
import javafx.util.*;
/** Application that runs arcade with 2048 and Frogger as the two playable games */

public class ArcadeApp extends Application {

    Group group = new Group();           // main container
    Random rng = new Random();           // random number generator
    Rectangle r = new Rectangle(20, 20); // some rectangle
    Scene s;
    WelcomeScreen newWelcome;
    LevelOneMap newLevel1;
    LevelTwoMap newLevel2;
    LevelThreeMap newLevel3;
    WinScreen newWin;
    Timeline timelineW = new Timeline();
    Timeline timeline1 = new Timeline();
    Timeline timeline2 = new Timeline();
    Timeline timeline3 = new Timeline();
    Timeline timeline2048 = new Timeline();
    TwentyFortyEight new2048;
    
    /** {@inheritdoc} */
    @Override
    public void start(Stage stage) {
	newWelcome = new WelcomeScreen();
	newWelcome.setOnKeyPressed(newWelcome.createWelcomeKeyHandler());
	newLevel1 = new LevelOneMap(timeline1);
	newLevel1.setOnKeyPressed(newLevel1.getFrog().createKeyHandler());
	newLevel2 = new LevelTwoMap(timeline2);
	newLevel2.setOnKeyPressed(newLevel2.getFrog().createKeyHandler());
	newLevel3 = new LevelThreeMap(timeline3);
	newLevel3.setOnKeyPressed(newLevel3.getFrog().createKeyHandler());
	newWin = new WinScreen();
	newWin.setOnKeyPressed(newWin.createWelcomeKeyHandler());
	new2048 = new TwentyFortyEight(timeline2048);
	s = new Scene(newWelcome, 640, 620);
	setTimelineW();
	setTimeline1();
	setTimeline2();
	setTimeline3();
	setTimeline2048();
	stage.setTitle("testing");
	stage.setScene(s);
	stage.sizeToScene();
	stage.show();
	newWelcome.requestFocus();
	newLevel1.requestFocus();
	newLevel2.requestFocus();
	newLevel3.requestFocus();
	newWin.requestFocus();
	checkForWelcomeScreen();
	new2048.requestFocus();
    } // start
    /** Begins timeline for welcome screen */
    public void checkForWelcomeScreen() {
	timelineW.play();
    }//checkForWelcomeScreen
    /** Begins timeline for level one of frogger */
    public void checkForWinsLosses1() {
	timeline1.play();
    }//checkForWins
    /** Begins timeline for level two of frogger */
    public void checkForWinsLosses2() {
	timeline2.play();
    }//checkForWins2
    /** Begins timeline for level three of frogger */
    public void checkForWinsLosses3() {
	timeline3.play();
    }
    /** Begins timeline for 2048 */
    public void checkForQuit2048() {
	timeline2048.play();
    }
    /** sets Action Event for welcome/win screen timeline */
    public void setTimelineW() {
	EventHandler<ActionEvent> handler = event -> {
	    //if player selects frogger: sets scene to level 1
	    if(newWelcome.getSelect(1)) {
		newLevel1.resetStats();
		newLevel2.resetStats();
		newLevel3.resetStats();
		s.setRoot(newLevel1);
		checkForWinsLosses1();
		timelineW.stop();
	    }
	    //if player selects 2048: sets scene to 2048
	    if(newWelcome.getSelect(2)) {
		new2048.resetStats();
		s.setRoot(new2048);
		new2048.requestFocus();
		checkForQuit2048();
		timelineW.stop();
	    }
	    //For win screen of frogger: brings user back to welcome screen
	    if(newWin.getBack()) {
		newLevel1.resetStats();
		newLevel2.resetStats();
		newLevel3.resetStats();
		newWelcome.resetSelect();
		s.setRoot(newWelcome);
		newWelcome.requestFocus();
		newWin.resetSelect();
	    }
	};
	
	KeyFrame keyFrame = new KeyFrame(Duration.millis(1000/60), handler);
	timelineW.setCycleCount(Timeline.INDEFINITE);
	timelineW.getKeyFrames().add(0, keyFrame);
    }//setTimelineW
    /** sets Action Event for timeline for level one of frogger */
    public void setTimeline1() {
	EventHandler<ActionEvent> handler = event -> {
	    //if player wins level one: sets scene to level 2
	    if(newLevel1.getWin()) {
		newLevel2.setStats(newLevel1);
		s.setRoot(newLevel2);
		checkForWinsLosses2();
		newLevel1.resetStats();
		timeline1.stop();
	    }
	    //if player loses all lives: sets scene to welcome screen
	    if(newLevel1.getLives() <= 0) {
		newWelcome.resetSelect();
		s.setRoot(newWelcome);
		newWelcome.requestFocus();
		checkForWelcomeScreen();
		timeline1.stop();
	    }
	    //if player quits: sets scene to welcome screen
	    if(newLevel1.getFrog().getQuit()) {
		newWelcome.resetSelect();
		s.setRoot(newWelcome);
		newWelcome.requestFocus();
		checkForWelcomeScreen();
		timeline1.stop();
	    }
	};
	
	KeyFrame keyFrame = new KeyFrame(Duration.millis(1000/60), handler);
	timeline1.setCycleCount(Timeline.INDEFINITE);
	timeline1.getKeyFrames().add(keyFrame);
    }//setTimeline1
    /** sets Action Event for timeline for level two of frogger */
    public void setTimeline2() {
	EventHandler<ActionEvent> handler = event -> {
	    //if player wins level two: sets scene to level three
	    if(newLevel2.getWin()) {
		newLevel3.setStats(newLevel2);
		s.setRoot(newLevel3);
		checkForWinsLosses3();
		newLevel2.resetStats();
		timeline2.stop();
	    }
	    //if player loses all lives: sets scene to welcome screen
	    if(newLevel2.getLives() <= 0) {
		newWelcome.resetSelect();
		s.setRoot(newWelcome);
		newWelcome.requestFocus();
		checkForWelcomeScreen();
		timeline2.stop();
	    }
	    //if player quits: sets scene to welcome screen
	    if(newLevel2.getFrog().getQuit()) {
		newWelcome.resetSelect();
		s.setRoot(newWelcome);
		newWelcome.requestFocus();
		checkForWelcomeScreen();
		timeline2.stop();
	    }
	};
	
	KeyFrame keyFrame = new KeyFrame(Duration.millis(1000/60), handler);
	timeline2.setCycleCount(Timeline.INDEFINITE);
	timeline2.getKeyFrames().add(keyFrame);
    }//setTimeline2
    /** sets Action Event for timeline for level three of frogger */
    public void setTimeline3() {
	EventHandler<ActionEvent> handler = event -> {
	    //if player wins level three: sets scene to win screen
	    if(newLevel3.getWin()) {
		newWelcome.resetSelect();
		newWin.resetSelect();
		s.setRoot(newWin);
		newWin.requestFocus();
		checkForWelcomeScreen();
		timeline3.stop();
	    }
	    //if player loses all lives: sets scene to welcome screen
	    if(newLevel3.getLives() <= 0) {
		newWelcome.resetSelect();
		s.setRoot(newWelcome);
		newWelcome.requestFocus();
		checkForWelcomeScreen();
		timeline3.stop();
	    }
	    //if player quits: sets scene to welcome screen
	    if(newLevel3.getFrog().getQuit()) {
		newWelcome.resetSelect();
		s.setRoot(newWelcome);
		newWelcome.requestFocus();
		checkForWelcomeScreen();
		timeline3.stop();
	    }
	};
	
	KeyFrame keyFrame = new KeyFrame(Duration.millis(1000/60), handler);
	timeline3.setCycleCount(Timeline.INDEFINITE);
	timeline3.getKeyFrames().add(keyFrame);
    }
    /** sets Action Event for timeline for 2048 */
    public void setTimeline2048() {
	EventHandler<ActionEvent> handler = event -> {
	    //if player quits: sets scene to welcome screen
	    if(new2048.getQuit()) {
		newWelcome.resetSelect();
		s.setRoot(newWelcome);
		newWelcome.requestFocus();
		checkForWelcomeScreen();
		timeline2048.stop();
	    }
	};
	KeyFrame keyFrame = new KeyFrame(Duration.millis(1000/60), handler);
	timeline2048.setCycleCount(Timeline.INDEFINITE);
	timeline2048.getKeyFrames().add(keyFrame);
    }
} // ArcadeApp
