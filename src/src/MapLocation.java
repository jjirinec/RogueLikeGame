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
	private Entity entity;

	public MapLocation(Tile tile)
	{
		this.tile = tile;
		loot = new ArrayList<Loot>();
	}
	
	
	public Tile getTile()
	{
		return tile;
	}
	public Entity getEntity()
	{
		return entity;
	}
	
	public void setEntity(Entity e)
	{
		this.entity = e;
	}

	public Entity removeEntity()
	{
		Entity temp = this.entity;
		this.entity = null;
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
		if(obstacle == null && entity == null && tile.isMovable)
			result = true;
		return result;
	}
	
	
}
