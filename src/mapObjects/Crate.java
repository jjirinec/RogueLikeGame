package mapObjects;

public class Crate extends Obstacle implements Container{

	private static String imageFiles[] = {"Crate00.png","Crate01.png","Crate02.png","Crate03.png"};
	private static String name = "Crate";
	private static int imageSize = 60;
	private static int baseHp = 30;
	private Loot[] items;
	
	
	public Crate(int containerSize, Coordinate location, int numberOfItems) {
		super(name, baseHp, location, imageFiles, imageSize);
		this.items = new Loot[numberOfItems];
		// TODO Auto-generated constructor stub
	}
//	public Crate(String objectName, int hp,String startImageFile, int imageSize) {
//		super(objectName, hp, imageSize, startImageFile);
//		// TODO Auto-generated constructor stub
//	}

	@Override
	public MapObject[] getContents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void generateContents() {
		LootGenerator lootGen = new LootGenerator();
		for(int item = 0; item < items.length; item++)
			items[item] = lootGen.generate(1);
		
	}

	@Override
//	public void updateImage(int imageNumber) {
//		// TODO Auto-generated method stub
//		
//	}
	
	
	public String description()
	{
		return super.description() + "\n\tContains: " + "ADD Loot Contents Here";//TODO UpDate Loot Contents
	}

}
