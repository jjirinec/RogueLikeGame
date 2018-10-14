package src;

import java.util.Deque;
import java.util.Stack;

import mapObjects.MapObject;

public class MapLocation {
	private Tile tile;
	private Stack<MapObject> mapObjects;

	public MapLocation(Tile tile)
	{
		this.tile = tile;
	}
	
	
	public Tile getTile()
	{
		return tile;
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
