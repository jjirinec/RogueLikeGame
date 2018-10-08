package mapObjects;

public class Axe extends Wepon{
	
	private static String baseName = "Axe";
	private static String imageFile = "images/wepons/axe.png";
	private static int baseDmg = 5;
	private static int baseValue = 5;
	private static double weponSpeed = 1.5;
	
	public Axe(int extraDmg,int bonusValue) {
		super(baseName, imageFile, baseValue + bonusValue, (baseDmg + extraDmg),"mele",weponSpeed);
		if(extraDmg > 0)
			super.setName("+"+extraDmg+" "+ baseName);
	}
	public Axe(int extraDmg, int bonusValue, Coordinate location) {
		super(baseName, imageFile, location, baseValue + bonusValue, (baseDmg + extraDmg),"mele",weponSpeed);
		if(extraDmg > 0)
			super.setName("+"+extraDmg+" "+ baseName);

	}
	
	public String description()
	{
		return super.description() + super.toString() + "(" + super.getType() + ") \n\tSpeed: Slow \n\tDmg: " + super.getDmg();
	}
}