
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a tile on the board
 */
public class Tile extends ImageView{
	private int x;
	private int y;
	private int value;
	private boolean isMerged = false;

	/**
	 * Creates a tile and sets the position to the given x and y coords
	 * @param x x location to be placed
	 * @param y y location to be placed
	 * @param value the number value that the tile holds
	 */
	public Tile(int x, int y, int value) {
		super();
		this.x = x;
		this.y = y;
		setValue(value);
		this.setYCoord(y);
		this.setXCoord(x);
	}

	/**
	 * Returns the value of the tile
	 * @return the value of the tile
	 */
	public int getValue() {
		return value;
	}

	/**
	 * sets the value of the tile
	 * @param value the value for the tile to be set to
	 */
	public void setValue(int value) {
		this.value = value;
		this.setImage(new Image(value + ".png"));
	}

	/**
	 * returns the x coordinate of the tile
	 * @return the x coordinate of the tile
	 */
	public int getXCoord() {
		return this.x;
	}

	/**
	 * returns the y coordinate of the tile
	 * @return the y coordinate of the tile
	 */
	public int getYCoord() {
		return this.y;
	}

	/**
	 * sets the x coordinate of the tile
	 * @param x the x coordinate to set the tile to
	 */
	public void setXCoord(int x) {
		this.x = x;
		this.setX(x);
	}

	/**
	 * sets the y coordinate of the tile
	 * @param y the y coordinate to set the tile to
	 */
	public void setYCoord(int y) {
		this.y = y;
		this.setY(y);
	}

	/**
	 * returns true if the tile has been merged
	 * @return the isMerged boolean
	 */
	public boolean isMerged() {
		return this.isMerged;
	}

	/**
	 * sets the isMerged boolean to the param isMerged
	 * @param isMerged the boolean to set the isMerged boolean
	 */
	public void setMerged(boolean isMerged) {
		this.isMerged = isMerged;
	}
}
