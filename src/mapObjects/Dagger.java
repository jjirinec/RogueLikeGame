package mapObjects;

public class Dagger extends Wepon{
	
	private static String baseName = "Dagger";
	private static String imageFile = "images/wepons/dagger.png";
	private static int imageSize = 70;
	private static int baseDmg = 2;
	private static int baseValue = 5;
	private static double weponSpeed = .75;
	
	public Dagger(int extraDmg,int bonusValue,int imageSize) {
		super(baseName, imageFile, baseValue + bonusValue, (baseDmg + extraDmg),"mele",weponSpeed,imageSize);
		if(extraDmg > 0)
			super.setName("+"+extraDmg+" "+ baseName);
		if(imageSize > this.imageSize)
			this.setImageSize(this.imageSize);
	}
	public Dagger(int extraDmg, int bonusValue, Coordinate location,int imageSizen) {
		super(baseName, imageFile, location, baseValue + bonusValue, (baseDmg + extraDmg),"mele",weponSpeed,imageSize);
		if(extraDmg > 0)
			super.setName("+"+extraDmg+" "+ baseName);
		if(imageSize > this.imageSize)
			this.setImageSize(this.imageSize);

	}
	
	public String description()
	{
		return super.description() + super.toString() + "(" + super.getType() + ") \n\tSpeed: Fast \n\tDmg: " + super.getDmg();
	}
}
