package mapObjects;

public class Crate extends Obstacle implements Container{

	private static String imageFiles[] = {"Crate00.png","Crate01.png","Crate02.png","Crate03.png"};
	private static String name = "Crate";
	private static int imageSize = 60;
	private static int baseHp = 30;
	private Loot[] items = null;
	
	
	public Crate(int numberOfItems, Coordinate location, int itemRating,int imageSize) {
		super(name, baseHp, location, imageFiles, imageSize);
		this.imageSize = imageSize;
		if(numberOfItems > 0)
		{
			this.items = new Loot[numberOfItems];
			generateContents(itemRating);
		}
		if(imageSize > this.imageSize)
			this.setImageSize(this.imageSize);
		// TODO Auto-generated constructor stub
	}
//	public Crate(String objectName, int hp,String startImageFile, int imageSize) {
//		super(objectName, hp, imageSize, startImageFile);
//		// TODO Auto-generated constructor stub
//	}

	@Override
	public Loot[] getContents() {
		return items;
	}
	
	public void printContents() {
		System.out.println("This container holds: ");
		if(items != null)
		for(int i = 0; i < items.length; i++)
			System.out.println("\t" + items[i].toString());
		else
			System.out.println("Nothing");
		System.out.println("\n");
	}

	@Override
	public void generateContents(int itemRating) {
		LootGenerator lootGen = new LootGenerator(imageSize);
		for(int item = 0; item < items.length; item++)
			items[item] = lootGen.generate(itemRating);
		
	}

//	@Override
//	public void updateImage(int imageNumber) {
//		// TODO Auto-generated method stub
//		
//	}
	
	
//	public String description()
//	{
//		return super.description() + "\n\tContains: " + "ADD Loot Contents Here";//TODO UpDate Loot Contents
//	}

}
