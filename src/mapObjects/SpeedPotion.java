package mapObjects;

public class SpeedPotion extends Consumable{
	
	private static String imageFile = "images/potions/SpeedPotion.png";
	private static int imageSize = 30;
	private double speedBonus = 2;
	private static String name = "Speed Potion";
	
	
	public SpeedPotion(double bounusSpeed, Coordinate location, int value,int imageSize) {
		super(name, imageFile, location, value,imageSize);
		speedBonus += bounusSpeed;
		if(imageSize > this.imageSize)
			this.setImageSize(this.imageSize);
	}
	public SpeedPotion(double bounusEffect, int value,int imageSize) {
		super(name, imageFile, value,imageSize);
		speedBonus += bounusEffect;
		if(imageSize > this.imageSize)
			this.setImageSize(this.imageSize);
	}

	
	@Override
	public void consume(src.Character character) {
		// TODO Auto-generated method stub
		//character.heal(speedBonus);//TODO Change to increas actions once character is implemented
	}
	
	public String description()
	{
		return super.description() + super.toString() + "\n\tSpeed value: +" + speedBonus + " actions"; 
	}

}
