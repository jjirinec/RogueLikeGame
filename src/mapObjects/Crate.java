package mapObjects;

import java.util.ArrayList;

public class Crate extends Obstacle implements Container{

	private static String imageFiles[] = {"Crate00.png","Crate01.png","Crate02.png","Crate03.png"};
	private static String name = "Crate";
	private static int imageSize = 60;
	private static int baseHp = 30;
	private ArrayList<Loot> items = null;
	
	
	public Crate(int numberOfItems, Coordinate location, int itemRating,int imageSize) {
		super(name, baseHp, location, imageFiles, imageSize);
		this.imageSize = imageSize;
		items = new ArrayList<Loot>();
		if(numberOfItems > 0) {
			generateContents(itemRating,numberOfItems);
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
	public ArrayList<Loot> getContents() {
		return items;
	}
	
	public void printContents() {
		System.out.println("This container holds: ");
		if(items != null)
		for(int i = 0; i < items.size(); i++)
			System.out.println("\t" + items.get(i).toString());
		else
			System.out.println("Nothing");
		System.out.println("\n");
	}

	@Override
	public void generateContents(int itemRating,int numberOfItems) {
		LootGenerator lootGen = new LootGenerator(imageSize);
		for(int item = 0; item < numberOfItems; item++)
			items.add(lootGen.generate(itemRating));
		
	}

	@Override
	public void removeItem(Loot item) {
		this.items.remove(item);
		
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
