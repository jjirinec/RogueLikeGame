package src;

import java.util.Deque;
import java.util.Stack;

import mapObjects.MapObject;

public class MapLocation {
	private Tile tile;
	private Stack<MapObject> mapObjects;
	private Character character;

	public MapLocation(Tile tile)
	{
		this.tile = tile;
		mapObjects = new Stack<MapObject>();
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
//	public Stack<MapObject> getObjects()
//	{
//		return mapObjects;
//	}
	public void addObject(MapObject object)
	{
		mapObjects.push(object);
	}
	
	public MapObject seeTopObject()
	{
		return mapObjects.peek();
	}
	
	public MapObject tackTopObject()
	{
		return mapObjects.pop();
	}
}
