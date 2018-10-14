package mapObjects;

public class LeatherArmor extends Armor{

	private static String name = "Leather Armor";
	private static String imageFile = "images/armor/LeatharArmor.png";
	private static int baseDefence = 2;
	private static String type = "Light";
	private static int baseValue = 20;
	private static int imageSize = 50;
	
	public LeatherArmor(int bonusDefence, Coordinate location, int bonusValue) {
		super(name, (baseDefence + bonusDefence), type, imageFile, location, (baseValue+bonusValue), imageSize);
		
		// TODO Auto-generated constructor stub
	}
	public LeatherArmor(int bonusDefence, int bonusValue) {
		super(name, (baseDefence + bonusDefence), type, imageFile, (baseValue+bonusValue), imageSize);
		if(bonusDefence > 0)
			super.setName("+" + bonusDefence + " " +name);
		// TODO Auto-generated constructor stub
	}
	
	public String description()
	{
		return super.description() + super.toString() + 
				"\n\tType: " + type + 
				"\n\tDevenciv Value: " + super.getDefence() + 
				"\n\tSpeed Penalty: -" + super.getSpeedPenalty() + " actions" +  
				"\n\tValue: " + super.getValue();
				
	}

}