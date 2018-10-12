package mapObjects;

public class Loot extends MapObject{
	private int value;
	
	
	public Loot(String name, String imageFile, int value,int imageSize)
	{
		super(name, imageFile, true,imageSize);
		this.value = value;
	}
	public Loot (String name, String imageFile,Coordinate location, int value,int imageSize)
	{
		super(name, imageFile,location, true,imageSize);
		this.value = value;
	}

	
	public int getValue()
	{
		return value;
	}
	
}
