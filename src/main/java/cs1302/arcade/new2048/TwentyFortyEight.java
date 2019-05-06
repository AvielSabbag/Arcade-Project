package cs1302.arcade.new2048;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.event.*;
import javafx.animation.*;
import javafx.util.*;

/**
 * Represents an instance of the game 2048
 */
public class TwentyFortyEight extends Group{
	MenuBar menuBar;
	PaneComponent root;
  Timeline timeline;
  Text scoret;
	boolean quit;
	/**
	 * Adds all of the necessary components for the game to work
	 */
	public TwentyFortyEight(Timeline t) {
    super();
    timeline = t;
		VBox pane = new VBox();
		root = new PaneComponent();
		HBox textAboveGame = new HBox();
		this.getChildren().add(pane);
		Text text = new Text("2048");
		text.setFont(Font.font("Arial", 50));
		text.setFill(Color.BLUE);
     scoret = new Text("Score: 0");  
     scoret.setFont(Font.font("Arial", 25));
    scoret.setFill(Color.BLUE);
		textAboveGame.getChildren().addAll(text, scoret);
		menuStuff();
		pane.getChildren().add(menuBar);
		pane.getChildren().add(textAboveGame);
		pane.getChildren().add(root);
		this.setOnKeyPressed(root::handleKey);
   updateScore();
   quit = false;
	}

	/**
	 * Helper method to create the menu at the top
	 */
	private void menuStuff() {
		menuBar = new MenuBar();
		Menu file = new Menu("File");
		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction(e -> setQuit());
		file.getItems().add(exit);
		menuBar.getMenus().add(file);
	}
 public boolean getQuit(){
   return this.quit;
 }
 public void setQuit(){
   this.quit = true;
 }
 public void resetStats(){
   root.setScore(0);
   this.quit = false;
 }
 public void updateScore() {
   EventHandler<ActionEvent> handler = event -> {
     scoret.setText("Score: " + root.getScore());
     };
     KeyFrame keyFrame = new KeyFrame(Duration.millis(1000/60), handler);
     timeline.setCycleCount(Timeline.INDEFINITE);
     timeline.getKeyFrames().add(keyFrame);
     timeline.play();
     }
}
