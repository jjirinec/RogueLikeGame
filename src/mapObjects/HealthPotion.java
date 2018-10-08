package mapObjects;

public class HealthPotion extends Loot implements Consumable{

	private static String imageFile = "images/potions/HealthPotion.png";
	private int healthValue = 10;
	private static String name = "Health Potion";
	
	
	public HealthPotion(int bounusValue, Coordinate location, int value) {
		super(name, imageFile, location, value);
		healthValue += bounusValue;
	}
	public HealthPotion(int bounusValue, int value) {
		super(name, imageFile, value);
		healthValue += bounusValue;
	}

	
	@Override
	public void consume(src.Character character) {
		// TODO Auto-generated method stub
		character.heal(healthValue);
	}
	
	public String description()
	{
		return super.description() + "Healing value: +" + healthValue + "Hp"; 
	}

}
