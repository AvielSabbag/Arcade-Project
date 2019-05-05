package cs1302.arcade;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

/**
 * Represents the board that holds all of the tiles
 */
public class PaneComponent extends Pane{
	int[] xLocs = new int[4];
	int[] yLocs = new int[4];
	ArrayList<Location> locations = new ArrayList<Location>();
	Random rand = new Random();
	ArrayList<Tile> tiles = new ArrayList<Tile>();

	/**
	 * creates the board, fills it with blank tiles, and adds a random 2 or 4 tile
	 */
	public PaneComponent() {
		super();
		setXYLocs();
		for(int i = 0; i < 4; i++) {
			for(int x = 0; x < 4; x++) {
				this.getChildren().add(new Tile(xLocs[i], yLocs[x], 0));
			}
		}
		int twoFour = rand.nextInt(2);
		int x = rand.nextInt(4);
		int y = rand.nextInt(4);
		if(twoFour == 1) {
			tiles.add(new Tile(xLocs[x], yLocs[y], 2));
			this.getChildren().add(tiles.get(0));
			findLoc(xLocs[x], yLocs[y]).setTile(tiles.get(0));
		}
		else {
			tiles.add(new Tile(xLocs[x], yLocs[y], 4));
			this.getChildren().add(tiles.get(0));
			findLoc(xLocs[x], yLocs[y]).setTile(tiles.get(0));
		}

	}

	/**
	 * sets the locations to spots on the board
	 */
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
		for(int i = 0; i < 4; i++) {
			for(int x = 0; x < 4; x++) {
				locations.add(new Location(xLocs[i], yLocs[x]));
			}

		}
	}

	/**
	 * helper method that returns 2 or 4
	 * @return 2 or 4
	 */
	private int twoFour(){
		int twoFour = rand.nextInt(2);
		if(twoFour == 1){
			return 2;
		}
		else{
			return 4;
		}
	}

	/**
	 * adds a random 2 or 4 in a random open spot on the board
	 */
	public void update(){
		System.out.println("test");
		boolean c = true;
		//repeats the loop until it generates a location that is empty
		while(c){
			int newXLoc = rand.nextInt(4);
			int newYLoc = rand.nextInt(4);
			if(!findLoc(xLocs[newXLoc], yLocs[newYLoc]).isTaken()) {
				tiles.add(new Tile(xLocs[newXLoc], yLocs[newYLoc], twoFour()));
				this.getChildren().add(tiles.get(tiles.size() - 1));
				findLoc(tiles.get(tiles.size()-1).getXCoord(), tiles.get(tiles.size()-1).
						getYCoord()).setTile(tiles.get(tiles.size()-1));
				c = false;
			}
		}


	}
	private int k = 0;
	/**
	 * handles the keyboard input of the user
	 * @param event the key that is pressed
	 */
	public void handleKey(KeyEvent event) {
		switch(event.getCode()) {
		case UP: up(); k = 0; break;
		case DOWN: down(); k = 0; break;
		case LEFT: left(); k = 0; break;
		case RIGHT: right(); k = 0; break;
		default: break;
		}
		
	}

	/**
	 * moves the tiles up if they have room
	 */
	private void up() {
		Tile tile = null;
		boolean isMoved = false;
		for(int y: xLocs) {
			for(int x: yLocs) {
				if(findLoc(x,y).getTile() != null && y > 0 && findLoc(x,y - 120).getTile() == null) {
					tile = findLoc(x,y).getTile();
					for(int i = 0; i < yLocs.length; i++) {
						if(!findLoc(x, yLocs[i]).isTaken()) {
							isMoved = true;
							findLoc(x, y).getTile().setY(yLocs[i]);
							findLoc(x, y).removeTile();
							findLoc(x, yLocs[i]).setTile(tile);
							break;
						}	

					}
				}
			}
		}
		if(combineUp() || isMoved && k ==0) {
			update();
		}
	}
	/**
	 * Combines tiles that are the same if possible when moving up
	 * @return true if tiles combine
	 */
	private boolean combineUp() {
		Tile tile = null;
		boolean tf = false;
		if(k ==0) {
			for(int y: yLocs) {
				for(int x: xLocs) {
					if(findLoc(x,y).getTile() != null && y > 0 && findLoc(x,y-120).getTile() != null) {
						if(findLoc(x, y -120).getTile().getValue() == findLoc(x,y).getTile().getValue()) {
							findLoc(x,y-120).getTile().setValue(findLoc(x,y-120).getTile().getValue() *2);
							this.getChildren().remove(findLoc(x,y).getTile());
							findLoc(x,y).removeTile();
							tf = true;
						}
					}
				}
			}
		}

		if(tf && k == 0) {
			k = 1;
			up();
		}
		return tf;
	}
	/**
	 * moves the tiles down if they have room
	 */
	private void down() {
		boolean isMoved = false;
		Tile tile;
		for(int y = yLocs.length -1; y >= 0; y--) {
			for(int x = yLocs.length -1; x >= 0; x--) {
				if(findLoc(xLocs[x],yLocs[y]).getTile() != null && yLocs[y] < 360 && findLoc(xLocs[x],yLocs[y] + 120).getTile() == null) {
					tile = findLoc(xLocs[x],yLocs[y]).getTile();
					for(int i = yLocs.length -1; i >= 0; i--) {
						if(!findLoc(xLocs[x], yLocs[i]).isTaken()) {
							isMoved = true;
							findLoc(xLocs[x],yLocs[y]).getTile().setY(yLocs[i]);
							findLoc(xLocs[x],yLocs[y]).removeTile();
							findLoc(xLocs[x],yLocs[i]).setTile(tile);
							break;
						}
					}

				}
			}
		}
		if(combineDown() || isMoved && k == 0){
			update();
		}
	}
	/**
	 * Combines tiles that are the same if possible when moving down
	 * @return true if tiles combine
	 */
	private boolean combineDown() {
		Tile tile = null;
		boolean tf = false;
		if(k ==0) {
			for(int y = yLocs.length -1; y >= 0; y--) {
				for(int x = yLocs.length -1; x >= 0; x--) {
					if(findLoc(xLocs[x],yLocs[y]).getTile() != null && yLocs[y] < 360 && findLoc(xLocs[x],yLocs[y]+120).getTile() != null) {
						if(findLoc(xLocs[x], yLocs[y] +120).getTile().getValue() == findLoc(xLocs[x],yLocs[y]).getTile().getValue()) {
							findLoc(xLocs[x],yLocs[y]+120).getTile().setValue(findLoc(xLocs[x],yLocs[y]+120).getTile().getValue() *2);
							this.getChildren().remove(findLoc(xLocs[x],yLocs[y]).getTile());
							findLoc(xLocs[x],yLocs[y]).removeTile();
							tf = true;
						}
					}
				}
			}
		}

		if(tf && k == 0) {
			k = 1;
			down();
		}
		return tf;
	}
	/**
	 * moves the tiles to the left if they have room
	 */
	private void left() {
		boolean isMoved = false;
		Tile tile;
		for(int x: yLocs) {
			for(int y: xLocs) {
				if(findLoc(x,y).getTile() != null && x > 0 && findLoc(x- 120,y).getTile() == null) {
					tile = findLoc(x,y).getTile();
					for(int i = 0; i < xLocs.length; i++) {
						if(!findLoc(xLocs[i], y).isTaken()) {
							isMoved = true;
							findLoc(x,y).getTile().setX(xLocs[i]);
							findLoc(x,y).removeTile();
							findLoc(xLocs[i],y).setTile(tile);
							break;
						}
					}

				}
			}
		}
		if(combineLeft() || isMoved && k == 0){
			update();
		}
	}
	/**
	 * Combines tiles that are the same if possible when moving left
	 * @return true if tiles combine
	 */
	private boolean combineLeft() {
		Tile tile = null;
		boolean tf = false;
		if(k ==0) {
			for(int x: yLocs) {
				for(int y: xLocs) {
					if(findLoc(x,y).getTile() != null && x > 0 && findLoc(x-120,y).getTile() != null) {
						if(findLoc(x-120, y).getTile().getValue() == findLoc(x,y).getTile().getValue()) {
							findLoc(x-120,y).getTile().setValue(findLoc(x-120,y).getTile().getValue() *2);
							this.getChildren().remove(findLoc(x,y).getTile());
							findLoc(x,y).removeTile();
							tf = true;
						}
					}
				}
			}
		}

		if(tf && k == 0) {
			k = 1;
			left();
		}
		return tf;
	}
	/**
	 * moves the tiles right if they have room
	 */
	private void right() {
		boolean isMoved = false;
		Tile tile;
		for(int x = xLocs.length-1; x >=0; x--) {
			for(int y = xLocs.length -1; y >= 0; y--) {
				if(findLoc(xLocs[x],yLocs[y]).getTile() != null && xLocs[x] < 360 && findLoc(xLocs[x] + 120,yLocs[y]).getTile() == null) {
					tile = findLoc(xLocs[x],yLocs[y]).getTile();
					for(int i = xLocs.length -1; i >= 0; i--) {
						if(!findLoc(xLocs[i], yLocs[y]).isTaken()) {
							isMoved = true;
							findLoc(xLocs[x],yLocs[y]).getTile().setX(xLocs[i]);
							findLoc(xLocs[x],yLocs[y]).removeTile();
							findLoc(xLocs[i],yLocs[y]).setTile(tile);
							break;
						}
					}

				}
			}
		}
		if(combineRight() || isMoved && k == 0){
			update();
		}
	}
	/**
	 * Combines tiles that are the same if possible when moving right
	 * @return true if tiles combine
	 */
	private boolean combineRight() {
		Tile tile = null;
		boolean tf = false;
		if(k ==0) {
			for(int y = yLocs.length -1; y >= 0; y--) {
				for(int x = yLocs.length -1; x >= 0; x--) {
					if(findLoc(xLocs[x],yLocs[y]).getTile() != null && xLocs[x] < 360 && findLoc(xLocs[x]+120,yLocs[y]).getTile() != null) {
						if(findLoc(xLocs[x]+120, yLocs[y] ).getTile().getValue() == findLoc(xLocs[x],yLocs[y]).getTile().getValue()) {
							findLoc(xLocs[x]+120,yLocs[y]).getTile().setValue(findLoc(xLocs[x]+120,yLocs[y]).getTile().getValue() *2);
							this.getChildren().remove(findLoc(xLocs[x],yLocs[y]).getTile());
							findLoc(xLocs[x],yLocs[y]).removeTile();
							tf = true;
						}
					}
				}
			}
		}

		if(tf && k == 0) {
			k = 1;
			down();
		}
		return tf;
	}
	/**
	 * returns a location that has the same x and y coords as the parameters
	 * @param x the x location
	 * @param y the y  location
	 * @return a location that has the same x and y coords as the parameters
	 */
	private Location findLoc(int x, int y) {
		for(Location loc: locations) {
			if(loc.getX() == x && loc.getY() == y) {
				return loc;
			}
		}
		return null;
	}

}
