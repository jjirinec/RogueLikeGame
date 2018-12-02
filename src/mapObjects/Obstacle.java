package mapObjects;
import src.Map;

public abstract class Obstacle extends MapObject{
	
	int hp;
	int maxHp;
	String status = "Undamaged";
	String[] imageFiles;
	
//	public Obstacle(String objectName,int hp, int imageSize, String startImageFile)
//	{
//		super(objectName, startImageFile, false, imageSize);
//		this.hp = hp;
//		this.maxHp = hp;
//	}
	
	public Obstacle(String objectName, int hp, Coordinate location, String[] imageFiles, int imageSize)
	{
		super(objectName,"images/Obstacles/" + imageFiles[0],location,false,imageSize);
		this.imageFiles = imageFiles;
		this.hp = hp;
		this.maxHp = hp;
	}
	
	
	public void updateImage(int imageNumber)
	{
		this.setImage("images/Obstacles/" + imageFiles[imageNumber]);
	}
	
	
	public void damage(int dmg, Map map)
	{
		this.hp -= dmg;
		checkForImageUpdate(map);
		
	}
	private void checkForImageUpdate(Map map)
	{
		map.getStackPane()[this.getLocation().getX()][this.getLocation().getY()].getChildren().remove(this.getImageView());
		if(hp < maxHp)
			status = "Minor Damage";
		if(hp <= (maxHp - maxHp/3))//1/3 hp lost
		{
			
			updateImage(1);
			status = "Damaged";
			
		}
		if(hp <= ((maxHp - 2*maxHp/3)))// 2/3 hp lost
		{
			System.out.println(" Image");
			updateImage(2);
			status = "Very Damaged";
		}
		if(hp <= 0)					//Hp less than 0
		{
			System.out.println("Broken Image");
			updateImage(3);
			this.isPasable = true;
			status = "Broken";
		}
		map.getStackPane()[this.getLocation().getX()][this.getLocation().getY()].getChildren().add(this.getImageView());
	}
	
	public String description()
	{
		return "Status: (" + status + ")";
	}

}
