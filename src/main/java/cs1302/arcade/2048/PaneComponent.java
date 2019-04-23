import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PaneComponent extends Pane{
	int[] xLocs = new int[4];
	int[] yLocs = new int[4];
	Random rand = new Random();
	public PaneComponent() {
		super();
		setXYLocs();
		for(int i = 0; i < 4; i++) {
			for(int x = 0; x < 4; x++) {
				this.getChildren().add(new Tile(xLocs[i], yLocs[x], 0));
			}
		}
		int twoFour = rand.nextInt(2);
		if(twoFour == 1) {
			this.getChildren().add(new Tile(xLocs[rand.nextInt(4)], yLocs[rand.nextInt(4)], 2));
		}
		else {
			this.getChildren().add(new Tile(xLocs[rand.nextInt(4)], yLocs[rand.nextInt(4)], 4));
		}
		
	}
	private void setXYLocs() {
		int loc = 0;
		for(int i = 0; i < xLocs.length; i++) {
			xLocs[i] = loc;
			loc += 120;
		}
		loc = 0;
		for(int i = 0; i < yLocs.length; i++) {
			yLocs[i] = loc;
			loc += 120;
		}
	}
}
