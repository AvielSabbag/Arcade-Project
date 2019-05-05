
/**
 * Represents a location in the grid
 */
public class Location {
    private boolean taken = false;
    private int x;
    private int y;
    private Tile tile;

    /**
     * Creates a representation of a location in the grid
     * @param x the x location
     * @param y the y location
     */
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * returns true if the location has a tile in it
     * @return true if the location has a tile in it
     */
    public boolean isTaken() {
        return taken;
    }

    /**
     * sets a tile to this location
     * @param t the tile to set to the location
     */
    public void setTile(Tile t) {
        this.tile = t;
        this.taken = true;
    }

    /**
     * sets the tile variable to null and sets the taken variable to false
     */
    public void removeTile() {
        this.tile = null;
        this.taken = false;
    }

    /**
     * returns the tile that is in this location
     * @return the tile that is in this location
     */
    public Tile getTile() {
        return this.tile;
    }

    /**
     * returns the x location
     * @return the x location
     */
    public int getX() {
        return this.x;
    }

    /**
     * retuns the y location
     * @return the y location
     */
    public int getY() {
        return this.y;
    }
}
