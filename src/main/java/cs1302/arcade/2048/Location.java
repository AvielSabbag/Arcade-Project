
public class Location {
	private boolean taken = false;
	private int x;
	private int y;
	private Tile tile;
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public boolean isTaken() {
		return taken;
	}
	public void setTile(Tile t) {
		this.tile = t;
		this.taken = true;
	}
	public void removeTile() {
		this.tile = null;
		this.taken = false;
	}
	public Tile getTile() {
		return this.tile;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
}
