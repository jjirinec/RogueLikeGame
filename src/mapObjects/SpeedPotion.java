package mapObjects;

public class SpeedPotion extends Loot implements Consumable{
	
	private static String imageFile = "images/potions/SpeedPotion.png";
	private static int imageSize = 30;
	private int baseSpeedBonus = 1;
	private static String name = "Speed Potion";
	
	
	public SpeedPotion(int bounusValue, Coordinate location, int value) {
		super(name, imageFile, location, value,imageSize);
		baseSpeedBonus += bounusValue;
	}
	public SpeedPotion(int bounusValue, int value) {
		super(name, imageFile, value,imageSize);
		baseSpeedBonus += bounusValue;
	}

	
	@Override
	public void consume(src.Character character) {
		// TODO Auto-generated method stub
		character.heal(baseSpeedBonus);//TODO Change to increas actions once character is implemented
	}
	
	public String description()
	{
		return super.description() + "Speed value: +" + baseSpeedBonus + " actions"; 
	}

}
