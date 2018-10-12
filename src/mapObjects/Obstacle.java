package mapObjects;

public abstract class Obstacle extends MapObject{
	
	int hp;
	int maxHp;
	
	public Obstacle(String objectName,int hp, int imageSize, String startImageFile)
	{
		super(objectName, startImageFile, false, imageSize);
		this.hp = hp;
		this.maxHp = hp;
	}
	
	public Obstacle(String objectName, int hp, Coordinate location, String startImageFile, int imageSize)
	{
		super(objectName,startImageFile,location,false,imageSize);
		this.hp = hp;
		this.maxHp = hp;
	}
	
	
	public abstract void updateImage(int imageNumber);
	
	public void damage(int dmg)
	{
		this.hp -= dmg;
		checkForImageUpdate();
		
	}
	private void checkForImageUpdate()
	{
		if(hp <= (maxHp - maxHp/3))//1/3 hp lost
			updateImage(1);
		else if(hp <= ((maxHp - 2*maxHp/3)))// 2/3 hp lost
			updateImage(2);
		else if(hp <= 0)					//Hp less than 0
		{
			updateImage(3);
			this.isPasable = true;
		}
	}

}
