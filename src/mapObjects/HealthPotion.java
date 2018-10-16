package mapObjects;

public class HealthPotion extends Consumable{

	private static String imageFile = "images/potions/HealthPotion.png";
	private static int imageSize = 30;
	private int healthValue = 10;
	private static String name = "Health Potion";
	
	
	public HealthPotion(int bounusHealth, Coordinate location, int value,int imageSize) {
		super(name, imageFile, location, value,imageSize);
		healthValue += bounusHealth;
		if(imageSize > this.imageSize)
			this.setImageSize(this.imageSize);
	}
	public HealthPotion(int bounusValue, int value,int imageSize) {
		super(name, imageFile, value,imageSize);
		healthValue += bounusValue;
		if(imageSize > this.imageSize)
			this.setImageSize(this.imageSize);
	}

	
	@Override
	public void consume(src.Character character) {
		// TODO Auto-generated method stub
		character.heal(healthValue);
	}
	
	public String description()
	{
		return super.description() + super.toString() + "\n\tHealing value: +" + healthValue + " Hp"; 
	}

}
