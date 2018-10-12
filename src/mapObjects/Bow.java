package mapObjects;

public class Bow extends Wepon{

	private static String baseName = "Bow";
	private static String imageFile = "images/wepons/bow.png";
	private static int imageSize = 75;
	private static int baseDmg = 2;
	private static int baseValue = 5;
	private static double weponSpeed = 1;
	
	public Bow(int extraDmg,int bonusValue) {
		super(baseName, imageFile, baseValue + bonusValue, (baseDmg + extraDmg),"mele",weponSpeed,imageSize);
		if(extraDmg > 0)
			super.setName("+"+extraDmg+" "+ baseName);
	}
	public Bow(int extraDmg, int bonusValue, Coordinate location) {
		super(baseName, imageFile, location, baseValue + bonusValue, (baseDmg + extraDmg),"mele",weponSpeed,imageSize);
		if(extraDmg > 0)
			super.setName("+"+extraDmg+" "+ baseName);

	}
	
	public String description()
	{
		return super.description() + super.toString() + "(" + super.getType() + ") \n\tSpeed: Fast \n\tDmg: " + super.getDmg();
	}
}