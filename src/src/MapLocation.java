package src;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Stack;

import mapObjects.Loot;
import mapObjects.MapObject;
import mapObjects.Obstacle;

public class MapLocation {
	private Tile tile;
	private ArrayList<Loot> loot;
	private Obstacle obstacle;
	private Character character;

	public MapLocation(Tile tile)
	{
		this.tile = tile;
		loot = new ArrayList<Loot>();
	}
	
	
	public Tile getTile()
	{
		return tile;
	}
	public Character getCharacter()
	{
		return character;
	}
	
	public void setCacharacter(Character x)
	{
		this.character = x;
	}
	public Character removeCharacter()
	{
		Character temp = this.character;
		this.character = null;
		return temp;
	}
	public Obstacle getObstacle()
	{
		return obstacle;
	}
	public void setObstacle(Obstacle obstacle)
	{
		this.obstacle = obstacle;
	}
//	public Stack<MapObject> getObjects()
//	{
//		return mapObjects;
//	}
	public void addObject(Loot object)
	{
		loot.add(object);
	}
	public void removeObstacle()
	{
		this.obstacle = null;
	}
	public Loot topLoot()
	{
		return loot.get(loot.size()-1);
	}
	public boolean isPasable()
	{
		boolean result = false;
		if(obstacle == null && character == null)
			result = true;
		return result;
	}
	
	
}
