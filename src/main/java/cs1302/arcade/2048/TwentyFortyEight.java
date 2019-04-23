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

public class TwentyFortyEight extends Application{
	MenuBar menuBar;
	PaneComponent root;
	@Override
	public void start(Stage stage) throws Exception {
		VBox pane = new VBox();
		root = new PaneComponent();
		HBox textAboveGame = new HBox();
		Scene scene = new Scene(pane);
		Text text = new Text("2048");
		text.setFont(Font.font("Arial", 50));
		text.setFill(Color.BLUE);
		textAboveGame.getChildren().add(text);
		menuStuff();
		pane.getChildren().add(menuBar);
		pane.getChildren().add(textAboveGame);
		pane.getChildren().add(root);
		stage.setWidth(500);
		stage.setHeight(650);
		stage.setResizable(false);
		stage.setTitle("2048");
		stage.setScene(scene);
		stage.show();
		

	}
	private void menuStuff() {
		menuBar = new MenuBar();
		Menu file = new Menu("File");
		MenuItem exit = new MenuItem("Exit");
		file.getItems().add(exit);
		menuBar.getMenus().add(file);
	}
}
