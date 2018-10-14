package mapObjects;

import java.util.Random;

public class ObstacleGenerrator {
	
	private Random rand;
	public ObstacleGenerrator()
	{
		rand = new Random();
	}
	
	public Obstacle generate(int obsRating, Coordinate location)
	{
		Obstacle obstacle = null;
		int obsNumber = rand.nextInt(2);
		int obsBonus = obsRating;
		if(obsRating > 1)
			obsBonus = rand.nextInt(obsRating); 
		switch(obsNumber)
		{
			case 0:
				int extraHp = rand.nextInt(20)*rand.nextInt(2 + obsBonus);
				obstacle = new Bolder(extraHp,location);
				break;
			case 1:
				int numberOfItems = rand.nextInt(4+obsBonus);
				obstacle = new Crate(numberOfItems,location,obsRating);
				break;
		}
		
		return obstacle;
	}

}
