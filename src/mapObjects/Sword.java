package mapObjects;

public class Sword extends Wepon{
	private static String baseName = "Sword";
	private static String imageFile = "images/wepons/sword.png";
	private static int baseDmg = 3;
	private static int baseValue = 5;
	private static double weponSpeed = 1;
	
	public Sword(int extraDmg,int bonusValue) {
		super(baseName, imageFile, baseValue + bonusValue, (baseDmg + extraDmg),"mele",weponSpeed);
		if(extraDmg > 0)
			super.setName("+"+extraDmg+" "+ baseName);
	}
	public Sword(int extraDmg, int bonusValue, Coordinate location) {
		super(baseName, imageFile, location, baseValue + bonusValue, (baseDmg + extraDmg),"mele",weponSpeed);
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
