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
		int stats = mapRating * 3;
		int health = mapRating * 2 + 10;
		int speed = mapRating * 2 + 10;
		Enemy enemy  = new Enemy(location,health,speed,gridSize);
		for(int i = 0 ; i < stats ; i++) {
			double rnd = Math.random();
			if (rnd > .50) {
				enemy.setDefence(enemy.getDefence() + 1);
			} else {
				enemy.setStr(enemy.getStr() + 1);
			}
		}
		return enemy;
	}

}
