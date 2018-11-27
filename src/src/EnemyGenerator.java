package src;

import java.util.Random;

import mapObjects.Coordinate;

public class EnemyGenerator {
	private int gridSize;
	private Random rand;
	public EnemyGenerator(int gridSize)
	{
		this.gridSize = gridSize;
		rand = new Random();
	}
	
	public Enemy generate(int mapRating, Coordinate location)
	{
		//TODO Add logic to generate enemy with challenge rating according to mapRating
		Enemy enemy  = new Enemy(location,gridSize,2);
		return enemy;
	}

}
