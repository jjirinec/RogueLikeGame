package src;
import mapObjects.*;

import java.util.ArrayList;
import java.util.Random;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

public class Map {
	private int roomRating;
	private GridPane map;
	private StackPane[][] stacks;
	private MapLocation[][] location;
	
	private Character player;
	private TargetingCursor cursor;
	private ArrayList<Enemy> enemys;
	
	private Loot[] loot;
	private Obstacle[] obstacles;
	
	private int mapHight;
	private int mapWidth;
	private int gridSize;
	private int mapRating;
	
	private Coordinate entrance;
	private Coordinate exit;
	private Random rand = new Random();
///////////////////////////
///		Constructor		///
///////////////////////////
	public Map(int hight, int width, int gridSize, int mapRating,Character player,int roomRating)
	{
		this.mapHight = hight;
		this.mapWidth = width;
		this.gridSize = gridSize;
		this.mapRating = mapRating;
		this.player = player;
		this.roomRating = roomRating;
		enemys = new ArrayList<Enemy>();
		setDoorLocations();
		initializeGrid();
		populateMap();
		//printLoot();
		//printObs();
	}


	public void printLoot()//TODO For Testing only Remove Latter
	{
		for( int row = 0; row < location.length; row++)
			for(int colum = 0; colum < location[row].length; colum++)
				//System.out.println(location[row][colum].topLoot().description());							//TODO Remove
				//System.out.println(location[row][colum].getObstacle().description());
				if(location[row][colum].topLoot() != null)
				System.out.println(location[row][colum].topLoot().description());							//TODO Remove
				//System.out.println(location[row][colum].getObstacle().description());						//TODO Remove
	}
	public void printObs()///TODO For Testing only Remove Latter
	{
		for( int row = 0; row < location.length; row++)
			for(int colum = 0; colum < location[row].length; colum++)
			{
				if(location[row][colum].getObstacle() != null)
				System.out.println(location[row][colum].getObstacle().description());
				if(location[row][colum].getObstacle() instanceof Crate)//TODO Remove
				{
					Crate temp = (Crate)location[row][colum].getObstacle();
					temp.printContents();
				}
			}
					
	}
///////////////////////	
///		Get/Set		///
///////////////////////
	public Coordinate getExit() {return new Coordinate(exit);}
	public ArrayList<Enemy> getEnemys()
	{
		return enemys;
	}
	public MapLocation[][] getMapLocation()
	{
		return location;
	}
	public TargetingCursor getCursor() {return cursor;}
	
	
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
				Tile tile = newTile(row,colum);
				stacks[row][colum].getChildren().add(tile.getImage());
				stacks[row][colum].setPickOnBounds(true);
				stacks[row][colum].setPrefSize(gridSize, gridSize);
				location[row][colum] = new MapLocation(tile);
				map.add(stacks[row][colum],row,colum);
				
				
				//map.setHgrow(stacks[row][colum], Priority.ALWAYS);
				//map.setVgrow(stacks[row][colum], Priority.ALWAYS);
//				stacks[row][colum]
				
			}
		}
	}
	private void moveTargetingCursor(int row, int colum) {
		
	}
	
	private void populateMap()
	{
		spawnPlayer(player);
		spawnMapObjects();
		spawnEnemys(1);
	}
	private void spawnEnemys(int numberOfEnemys) {
		EnemyGenerator enemyGen = new EnemyGenerator(gridSize);
		for(int enemyNum = 0; enemyNum < numberOfEnemys; enemyNum++) {
			Coordinate enemyLocation = findEmptyLocation();
			System.out.println("enemy spawn @ (" + enemyLocation.getX() + "," + enemyLocation.getY() + ")");
			Enemy enemy = enemyGen.generate(roomRating, enemyLocation);
			enemys.add(enemy);
			location[enemyLocation.getX()][enemyLocation.getY()].setEntity(enemy);
			stacks[enemyLocation.getX()][enemyLocation.getY()].getChildren().add(enemy.getImageView());
		}
	}
	private Coordinate findEmptyLocation()
	{
		Coordinate location = null;
		while(location == null)
		{
			int x = rand.nextInt(mapWidth);
			int y = rand.nextInt(mapHight);
			if(this.location[x][y].isPasable())
				location  = new Coordinate(x,y);
		}
		return location;
	}
	private void spawnMapObjects() {
		LootGenerator lootGen = new LootGenerator(gridSize);
		ObstacleGenerrator objGen = new ObstacleGenerrator(gridSize);
		for(int row  = 0;row < location.length; row++) {
			for (int colum = 0; colum < location[row].length; colum++) {
				int lNumber = rand.nextInt(100);
				int oNumber = rand.nextInt(100);
				if (lNumber <= 5 && location[row][colum].getTile().isMovable && !entrance.equals(new Coordinate(colum, row))) {
					location[row][colum].addObject(lootGen.generate(roomRating));
					stacks[row][colum].getChildren().add(location[row][colum].topLoot().getImageView());
		//			System.out.println(location[row][colum].topLoot().description());							//TODO Remove
				}
				if (oNumber <= 15 && location[row][colum].getTile().isMovable && !entrance.equals(new Coordinate(colum, row))) {
					location[row][colum].setObstacle(objGen.generate(roomRating, new Coordinate(row, colum)));
					stacks[row][colum].getChildren().add(location[row][colum].getObstacle().getImageView());
			//		System.out.println(location[row][colum].topLoot().description());							//TODO Remove
				//	System.out.println(location[row][colum].getObstacle().description());						//TODO Remove
				}
			}
		}
	}
	public void spawnPlayer(Character player){
		player.setLocation(entrance.getY(),entrance.getX());
		stacks[entrance.getY()][entrance.getX()].getChildren().add(player.getImageView());
		location[entrance.getY()][entrance.getX()].setEntity(player);
		cursor = new TargetingCursor(gridSize,new Coordinate(player.getLocation()));
		stacks[entrance.getY()][entrance.getX()].getChildren().add(cursor.getImageView());
	}


	public void removeEntity(Entity e){
		stacks[e.getLocation().getX()][e.getLocation().getY()].getChildren().remove(e.getImageView());
		location[e.getLocation().getX()][e.getLocation().getY()].removeEntity();
		if(e instanceof Enemy){
			enemys.remove(e);
		}
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

	public Character getPlayer(){return player;}
}
