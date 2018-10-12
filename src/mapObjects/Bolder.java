package mapObjects;

public class Bolder extends Obstacle{
	private static String imageFiles[] = {"bolder00.png","bolder01.png","bolder02.png","bolder03.png"};
	private static String name = "Bolder";
	private static int imageSize = 60;
	private static int baseHp = 50;
	
	public Bolder(int extraHp, Coordinate location)
	{
		super(name,(baseHp + extraHp),location,("images/Obstacles/" + imageFiles[3]),imageSize);
	}



	@Override
	public void updateImage(int imageNumber) {
		// TODO Auto-generated method stub
		this.setImage("images/Obstacles/" + imageFiles[imageNumber]);
		
	}
}