package mapObjects;

public abstract class Consumable extends Loot{
	
	public Consumable(String name, String imageFile, int value, int imageSize)
	{
		super(name,imageFile,value,imageSize);
	}
	public Consumable(String name, String imageFile,Coordinate location, int value, int imageSize)
	{
		super(name,imageFile,location,value,imageSize);
	}
	
	public abstract void consume(src.Character character);

}
