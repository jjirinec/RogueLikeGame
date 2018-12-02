package mapObjects;

public class Sword extends Wepon{
	private static String baseName = "Sword";
	private static String imageFile = "images/wepons/sword.png";
	private static int imageSize = 65;
	private static int baseDmg = 3;
	private static int baseValue = 5;
	private static double weponSpeed = 1;
	private static Wepon.Type type = Wepon.Type.MELLE;
	
	public Sword(int extraDmg,int bonusValue,int imageSize) {
		super(baseName, imageFile, baseValue + bonusValue, (baseDmg + extraDmg),type,weponSpeed,imageSize);
		if(extraDmg > 0)
			super.setName("+"+extraDmg+" "+ baseName);
	}
	public Sword(int extraDmg, int bonusValue, Coordinate location,int imageSize) {
		super(baseName, imageFile, location, baseValue + bonusValue, (baseDmg + extraDmg),type,weponSpeed,imageSize);
		if(extraDmg > 0)
			super.setName("+"+extraDmg+" "+ baseName);

	}
	
	public String description()
	{
		return super.description() + super.toString() + "(" + super.getType() + ") \n\tSpeed: Normal \n\tDmg: " + super.getDmg();
	}
//	public String toString()
//	{
//		return super.toString() + "(" + super.getType() + ") Speed: " + weaponSpeed + " Dmg: " + super.getDmg(); 
//	}
	
	
	

}
