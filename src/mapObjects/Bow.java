package mapObjects;

public class Bow extends Wepon{

	private static String baseName = "Bow";
	private static String imageFile = "images/wepons/bow.png";
	private static int imageSize = 75;
	private static int baseDmg = 2;
	private static int baseValue = 5;
	private static double weponSpeed = 1;
	private static Wepon.Type type = Wepon.Type.RANGED;
	
	public Bow(int extraDmg,int bonusValue,int imageSize) {
		super(baseName, imageFile, baseValue + bonusValue, (baseDmg + extraDmg),type,weponSpeed,imageSize);
		if(extraDmg > 0)
			super.setName("+"+extraDmg+" "+ baseName);
		if(imageSize > this.imageSize)
			this.setImageSize(this.imageSize);
	}
	public Bow(int extraDmg, int bonusValue, Coordinate location,int imageSize) {
		super(baseName, imageFile, location, baseValue + bonusValue, (baseDmg + extraDmg),type,weponSpeed,imageSize);
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