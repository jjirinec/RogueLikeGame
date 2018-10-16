package mapObjects;

public class PlateArmor extends Armor{

	private static String name = "Plate Armor";
	private static String imageFile = "images/armor/LeatharArmor.png";
	private static int baseDefence = 6;
	private static String type = "Heavy";
	private static int baseValue = 40;
	private static int imageSize = 50;
	
	public PlateArmor(int bonusDefence, Coordinate location, int bonusValue,int imageSize) {
		super(name, (baseDefence + bonusDefence), type, imageFile, location, (baseValue+bonusValue), imageSize);
		if(imageSize > this.imageSize)
			this.setImageSize(this.imageSize);
		
		// TODO Auto-generated constructor stub
	}
	public PlateArmor(int bonusDefence, int bonusValue,int imageSize) {
		super(name, (baseDefence + bonusDefence), type, imageFile, (baseValue+bonusValue), imageSize);
		if(bonusDefence > 0)
			super.setName("+" + bonusDefence + " " +name);
		if(imageSize > this.imageSize)
			this.setImageSize(this.imageSize);
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
