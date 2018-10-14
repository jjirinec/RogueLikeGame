package mapObjects;

public interface Container {
	
	public abstract MapObject[] getContents();
	public abstract void generateContents(int itemRating);

}
