package mapObjects;

public class Dagger extends Wepon{
	
	private static String baseName = "Dagger";
	private static String imageFile = "images/wepons/dagger.png";
	private static int imageSize = 80;
	private static int baseDmg = 1;
	private static int baseValue = 5;
	private static double weponSpeed = .75;
	
	public Dagger(int extraDmg,int bonusValue) {
		super(baseName, imageFile, baseValue + bonusValue, (baseDmg + extraDmg),"mele",weponSpeed,imageSize);
		if(extraDmg > 0)
			super.setName("+"+extraDmg+" "+ baseName);
	}
	public Dagger(int extraDmg, int bonusValue, Coordinate location) {
		super(baseName, imageFile, location, baseValue + bonusValue, (baseDmg + extraDmg),"mele",weponSpeed,imageSize);
		if(extraDmg > 0)
			super.setName("+"+extraDmg+" "+ baseName);

	}
	
	public String description()
	{
		return super.description() + super.toString() + "(" + super.getType() + ") \n\tSpeed: Fast \n\tDmg: " + super.getDmg();
	}
}
