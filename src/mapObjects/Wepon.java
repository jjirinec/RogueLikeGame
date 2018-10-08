package mapObjects;

public class Wepon extends Loot implements Equipable{
	private int dmg;
	private String type;
	private double weponSpeed;

	public Wepon(String name, String imageFile, int value, int dmg, String type, double speed) {
		super(name, imageFile, value);
		this.dmg = dmg;
		this.type = type;
		this.weponSpeed = speed;
		// TODO Auto-generated constructor stub
	}
	public Wepon(String name, String imageFile, Coordinate location, int value, int dmg, String type,double speed) {
		super(name, imageFile, location, value);
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
	public String getType()
	{
		return type;
	}

	@Override
	public void equip(Character character) {
		// TODO Auto-generated method stub
		
	}

}
