import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile extends ImageView{
	private int x;
	private int y;
	private int value;
	private boolean isMerged = false;
	public Tile(int x, int y, int value) {
		super();
		this.x = x;
		this.y = y;
		setValue(value);
		this.setYCoord(y);
		this.setXCoord(x);
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
		this.setX(x);
	}
	public void setYCoord(int y) {
		this.y = y;
		this.setY(y);
	}
	public boolean isMerged() {
		return this.isMerged;
	}
	public void setMerged(boolean isMerged) {
		this.isMerged = isMerged;
	}
}
