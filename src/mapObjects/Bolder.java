package mapObjects;

public class Bolder extends Obstacle{
	private static String imageFiles[] = {"bolder00.png","bolder01.png","bolder02.png","bolder03.png"};
	private static String name = "Bolder";
	private static int imageSize = 60;
	private static int baseHp = 30;
	private String condition = "Week";
	
	public Bolder(int extraHp, Coordinate location,int imageSize)
	{
		super(name,(baseHp + extraHp),location,imageFiles,imageSize);
		setCondition();
		if(imageSize > this.imageSize)
			this.setImageSize(this.imageSize);
	}
	
	private void setCondition()
	{
		if(this.hp >= baseHp*1.5)
			condition = "Moderatly Sturdy";
		if(this.hp >= baseHp*2)
			condition = "Surdy";
		if(this.hp >= baseHp * 2.5)
			condition = "Very Sturdy";
		if(this.hp >= baseHp * 3)
			condition = "Dence as Shit!";		
	}
	
	public String description()
	{
		return super.toString() + "-- This Rock Looks " + condition + "\t\t"  + super.description();
	}
	
	


	
}