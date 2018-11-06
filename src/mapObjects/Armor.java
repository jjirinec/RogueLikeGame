package mapObjects;
import src.Character;

public class Armor extends Loot implements Equipable{
	
	private int defence;
	private String type;
	private double speedPenalty;

	public Armor(String name,int defence,String type, String imageFile, Coordinate location, int value, int imageSize) {
		super(name, imageFile, location, value, imageSize);
		this.type = type;
		this.defence = defence;
		setSpdPenatly();		
		// TODO Auto-generated constructor stub
	}
	public Armor(String name,int defence, String type, String imageFile, int value, int imageSize) {
		super(name, imageFile, value, imageSize);
		this.type = type;
		this.defence = defence;
		setSpdPenatly();
		// TODO Auto-generated constructor stub
	}
	
	private void setSpdPenatly()
	{
		if(type.equals("Light"))
			this.speedPenalty = .25;
		else if(type.equals("Medium"))
			this.speedPenalty = .5;
		else if(type.equals("Heavy"))
			this.speedPenalty = .75;
	}
	public double getSpdPenalty() {
		return speedPenalty;
	}
	
	public int getDefence()
	{
		return this.defence;
	}
	public double getSpeedPenalty()
	{
		return this.speedPenalty;
	}
	@Override
	public void equip(Character player) {
		player.equipArmor(this);		
	}

}
