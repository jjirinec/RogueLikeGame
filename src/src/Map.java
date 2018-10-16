package src;
import mapObjects.*;

import java.util.Random;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class Map {
	private GridPane map;
	private StackPane[][] stacks;
	private MapLocation[][] location;
	
	private Character player;
	
	private Loot[] loot;
	private Obstacle[] obstacles;
	
	private int mapHight;
	private int mapWidth;
	private int gridSize;
	private int mapRating;
	
	private Coordinate entrance;
	private Coordinate exit;
	private Random rand = new Random();
	
	public Map(int hight, int width, int gridSize, int mapRating,Character player)
	{
		this.mapHight = hight;
		this.mapWidth = width;
		this.gridSize = gridSize;
		this.mapRating = mapRating;
		this.player = player;
		setDoorLocations();
		initializeGrid();
	}
	public MapLocation[][] getMapLocation()
	{
		return location;
	}
	private void initializeGrid()
	{
		map = new GridPane();
		stacks = new StackPane[mapHight][mapWidth];
		location = new MapLocation[mapHight][mapWidth];
		for(int row = 0; row < mapHight; row++)
		{
			for(int colum = 0; colum < mapWidth; colum++)
			{
				stacks[row][colum] = new StackPane();
				Tile tile = newTile(row, colum);
				stacks[row][colum].getChildren().add(tile.getImage());
				stacks[row][colum].setPickOnBounds(true);
				stacks[row][colum].setPrefSize(gridSize, gridSize);
				location[row][colum] = new MapLocation(tile);
				map.add(stacks[row][colum],row,colum);
			}
		}
		stacks[entrance.getY()][entrance.getX()].getChildren().add(player.getImage());
	}
	
	private void populateMap()
	{
		for(int row  = 0;row < location[].length();row++)
	}
	
	public GridPane getMap()
	{
		return map;
	}
	public StackPane[][] getStackPane()
	{
		return stacks;
	}
	private Tile newTile(int row, int colum)
	{
		Tile tile = null;
		Coordinate location = new Coordinate(colum,row);
		if(row > 0 && row < mapHight-1 && colum > 0  && colum < mapWidth-1)
			tile = new Floor(this.gridSize);
		else if(location.equals(entrance) || location.equals(exit))
			tile = new Floor(this.gridSize);
		else
			tile = new Wall(this.gridSize);
		return tile;
	}
	
	private void setDoorLocations()
	{
		entrance = setDoor();
		while(exit == null || exit.equals(entrance))
			exit = setDoor();
		
	}
	private Coordinate setDoor()
	{
		Coordinate door = null;
		int side = rand.nextInt(4) + 1;//Left(1),Right(2),Top(3),Bottom(4)
		int x = 0;
		int y = 0;
		switch(side)
		{
			case 1://Left
				x = 0;
				y = rand.nextInt(mapHight-2)+1;
				break;
			case 2://Right
				x = mapWidth-1;
				y = rand.nextInt(mapHight-2)+1;
				break;
			case 3://Top
				y = 0;
				x = rand.nextInt(mapWidth-2)+1;
				break;
			case 4://Bottom
				y = mapHight - 1;
				x = rand.nextInt(mapWidth-2)+1;
				break;
		}
		door = new Coordinate(x,y);
		return door;
		
	}
}
