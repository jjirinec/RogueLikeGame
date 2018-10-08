package mapObjects;

public class Loot extends MapObject{
	private int value;
	
	
	public Loot(String name, String imageFile, int value)
	{
		super(name, imageFile, true);
		this.value = value;
	}
	public Loot (String name, String imageFile,Coordinate location, int value)
	{
		super(name, imageFile,location, true);
		this.value = value;
	}

	
	public int getValue()
	{
		return value;
	}
	
}
