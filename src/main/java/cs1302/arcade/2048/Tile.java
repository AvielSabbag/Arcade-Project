import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile extends ImageView{
	int x;
	int y;
	int value;
	public Tile(int x, int y, int value) {
		super();
		this.x = x;
		this.y = y;
		setValue(value);
		this.relocate(this.x, this.y);
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
		this.setImage(new Image(value + ".png"));
	}
	public int getXCoord() {
		return this.x;
	}
	public int getYCoord() {
		return this.y;
	}
	public void setXCoord(int x) {
		this.x = x;
		this.setTranslateX(x);
	}
	public void setYCoord(int y) {
		this.y = y;
		this.setTranslateY(y);
	}
}
