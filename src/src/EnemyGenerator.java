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
	
	public Enemy generate(int mapRating, Coordinate location) {
		double rnd = Math.random();
		if (rnd > .66) {
			Enemy enemy = new EnemySpeedy(location, gridSize, mapRating);
			return enemy;
		}
		else if(rnd > .33) {
			Enemy enemy = new EnemyStrong(location, gridSize, mapRating);
			return enemy;
		}
		else
		{
			Enemy enemy = new EnemyTanky(location, gridSize, mapRating);
			return enemy;
		}
	}
}
