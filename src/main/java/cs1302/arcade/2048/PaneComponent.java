import java.util.ArrayList;
import java.util.Random;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class PaneComponent extends Pane{
	int[] xLocs = new int[4];
	int[] yLocs = new int[4];
	ArrayList<Location> locations = new ArrayList<Location>();
	Random rand = new Random();
	ArrayList<Tile> tiles = new ArrayList<Tile>();
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
	
	private int twoFour(){
		int twoFour = rand.nextInt(2);
		if(twoFour == 1){
			return 2;
		}
		else{
			return 4;
		}
	}
	
	public void update(){
		boolean c = true;
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
	
	public void handleKey(KeyEvent event) {
		switch(event.getCode()) {
			case UP: up(); break;
			case DOWN: down(); break;
			case LEFT: left(); break;
			case RIGHT: right(); break;
			default: break;
		}
		update();
	}
		
	
	private void up() {
		Tile tile;
		for(int x: xLocs) {
			for(int y: yLocs) {
				if(findLoc(x,y).getTile() != null && y > 0 && findLoc(x,y - 120).getTile() == null) {
					tile = findLoc(x,y).getTile();
					for(int i = 0; i < yLocs.length; i++) {
						if(!findLoc(x, yLocs[i]).isTaken()) {
							findLoc(x,y).getTile().setY(yLocs[i]);
							findLoc(x,y).removeTile();
							findLoc(x,yLocs[i]).setTile(tile);
							break;
						}
					}
					
				}
			}
		}
	}
	private void down() {
		Tile tile;
		for(int x: xLocs) {
			for(int y = yLocs.length -1; y >= 0; y--) {
				if(findLoc(x,yLocs[y]).getTile() != null && yLocs[y] < 360 && findLoc(x,yLocs[y] + 120).getTile() == null) {
					tile = findLoc(x,yLocs[y]).getTile();
					for(int i = yLocs.length -1; i >= 0; i--) {
						if(!findLoc(x, yLocs[i]).isTaken()) {
							findLoc(x,yLocs[y]).getTile().setY(yLocs[i]);
							findLoc(x,yLocs[y]).removeTile();
							findLoc(x,yLocs[i]).setTile(tile);
							break;
						}
					}
					
				}
			}
		}
	}
	private void left() {
		Tile tile;
		for(int y: yLocs) {
			for(int x: xLocs) {
				if(findLoc(x,y).getTile() != null && x > 0 && findLoc(x- 120,y).getTile() == null) {
					tile = findLoc(x,y).getTile();
					for(int i = 0; i < xLocs.length; i++) {
						if(!findLoc(xLocs[i], y).isTaken()) {
							findLoc(x,y).getTile().setX(xLocs[i]);
							findLoc(x,y).removeTile();
							findLoc(xLocs[i],y).setTile(tile);
							break;
						}
					}
					
				}
			}
		}
	}
	private void right() {
		Tile tile;
		for(int y: yLocs) {
			for(int x = xLocs.length -1; x >= 0; x--) {
				if(findLoc(xLocs[x],y).getTile() != null && xLocs[x] < 360 && findLoc(xLocs[x] + 120,y).getTile() == null) {
					tile = findLoc(xLocs[x],y).getTile();
					for(int i = xLocs.length -1; i >= 0; i--) {
						if(!findLoc(xLocs[i], y).isTaken()) {
							findLoc(xLocs[x],y).getTile().setX(xLocs[i]);
							findLoc(xLocs[x],y).removeTile();
							findLoc(xLocs[i],y).setTile(tile);
							break;
						}
					}
					
				}
			}
		}
	}
	private Location findLoc(int x, int y) {
		for(Location loc: locations) {
			if(loc.getX() == x && loc.getY() == y) {
				return loc;
			}
		}
		return null;
	}
}
