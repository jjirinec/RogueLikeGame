package mapObjects;

/**
 * @author ThinkPad41
 *
 */
public class SpeedPotion extends Consumable{
	
	private static String imageFile = "images/potions/SpeedPotion.png";
	private static int imageSize = 30;
	private double speedBonus = 2;
	private static String name = "Speed Potion";
	
	
	public SpeedPotion(double bounusSpeed, Coordinate location, int value,int imageSize) {
		super(name, imageFile, location, value,imageSize);
		speedBonus += bounusSpeed;
		if(imageSize > this.imageSize)
			this.setImageSize(this.imageSize);
	}
	public SpeedPotion(double bounusEffect, int value,int imageSize) {
		super(name, imageFile, value,imageSize);
		speedBonus += bounusEffect;
		if(imageSize > this.imageSize)
			this.setImageSize(this.imageSize);
	}

	
	@Override
	public void consume(src.Character player) {
		player.giveActions(speedBonus);
	}
	
	public String description()
	{
		return super.description() + super.toString() + "\n\tSpeed value: +" + speedBonus + " actions"; 
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpeedPotion other = (SpeedPotion) obj;
		if (Double.doubleToLongBits(speedBonus) != Double.doubleToLongBits(other.speedBonus))
			return false;
		return true;
	}

}
