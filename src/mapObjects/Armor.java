package mapObjects;
import src.Character;

public class Armor extends Loot implements Equipable{
	
	private int defence;
	private Type type;
	private double speedPenalty;

	public static enum Type {LIGHT,MEDIUM,HEAVY}
	public Armor(String name,int defence,Type type, String imageFile, Coordinate location, int value, int imageSize) {
		super(name, imageFile, location, value, imageSize);
		this.type = type;
		this.defence = defence;
		setSpdPenatly();		
		// TODO Auto-generated constructor stub
	}
	public Armor(String name,int defence, Type type, String imageFile, int value, int imageSize) {
		super(name, imageFile, value, imageSize);
		this.type = type;
		this.defence = defence;
		setSpdPenatly();
		// TODO Auto-generated constructor stub
	}
	
	private void setSpdPenatly()
	{
		if(type.equals(Armor.Type.LIGHT))
			this.speedPenalty = .25;
		else if(type.equals(Armor.Type.MEDIUM))
			this.speedPenalty = .5;
		else if(type.equals(Armor.Type.HEAVY))
			this.speedPenalty = .75;
	}
	public double getSpdPenalty() {
		return speedPenalty;
	}
	
	public int getDefence()
	{
		return this.defence;
	}
	public Armor.Type getType(){
		return type;
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
