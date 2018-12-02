package mapObjects;
import src.Character;
public class Wepon extends Loot implements Equipable{
	private int dmg;
	private Type type;
	private double weponSpeed;
	
	
	public static enum Type {MELLE,RANGED}
	public Wepon(String name, String imageFile, int value, int dmg, Type type, double speed, int imageSize) {
		super(name, imageFile, value,imageSize);
		this.dmg = dmg;
		this.type = type;
		this.weponSpeed = speed;
		// TODO Auto-generated constructor stub
	}
	public Wepon(String name, String imageFile, Coordinate location, int value, int dmg, Type type,double speed, int imageSize) {
		super(name, imageFile, location, value, imageSize);
		this.dmg = dmg;
		this.type = type;
		this.weponSpeed = speed;
		// TODO Auto-generated constructor stub
	}
	public double getWepopSpeed()
	{
		return weponSpeed;
	}
	public int getDmg()
	{
		return dmg;
	}
	public Type getType()
	{
		return type;
	}
	@Override
	public void equip(Character player) {
		player.equipWepon(this);
		
	}

	

}
