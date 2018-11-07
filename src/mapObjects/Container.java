package mapObjects;

import java.util.ArrayList;

public interface Container {
	
	public abstract ArrayList<Loot> getContents();
	public abstract void generateContents(int itemRating,int numberOfItems);
	public abstract void removeItem(Loot item);

}
