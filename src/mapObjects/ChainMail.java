package mapObjects;

public class ChainMail extends Armor{

	private static String name = "ChainMail";
	private static String imageFile = "images/armor/LeatharArmor.png";
	private static int baseDefence = 4;
	private static Armor.Type type = Armor.Type.MEDIUM;
	private static int baseValue = 30;
	private static int imageSize = 50;
	
	public ChainMail(int bonusDefence, Coordinate location, int bonusValue,int imageSize) {
		super(name, (baseDefence + bonusDefence), type, imageFile, location, (baseValue+bonusValue), imageSize);
		if(imageSize > this.imageSize)
			this.setImageSize(this.imageSize);
		
		// TODO Auto-generated constructor stub
	}
	public ChainMail(int bonusDefence, int bonusValue,int imageSize) {
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
