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

/**
 * Represents an instance of the game 2048
 */
public class TwentyFortyEight extends Group{
	MenuBar menuBar;
	PaneComponent root;
	
	/**
	 * Adds all of the necessary components for the game to work
	 */
	public TwentyFortyEight() throws Exception {
    super();
		VBox pane = new VBox();
		root = new PaneComponent();
		HBox textAboveGame = new HBox();
		this.getChildren().add(pane);
		Text text = new Text("2048");
		text.setFont(Font.font("Arial", 50));
		text.setFill(Color.BLUE);
		textAboveGame.getChildren().add(text);
		menuStuff();
		pane.getChildren().add(menuBar);
		pane.getChildren().add(textAboveGame);
		pane.getChildren().add(root);
		this.setOnKeyPressed(root::handleKey);
	}

	/**
	 * Helper method to create the menu at the top
	 */
	private void menuStuff() {
		menuBar = new MenuBar();
		Menu file = new Menu("File");
		MenuItem exit = new MenuItem("Exit");
		file.getItems().add(exit);
		menuBar.getMenus().add(file);
	}
}
