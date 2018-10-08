package mapObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MapObject {
	
	private String objectName;
	
	private Image image;
	private ImageView imageView;
	private Coordinate location;
	private boolean isPasable;
	
//////////////////////////////////////////////
//            Constructors                  //
//////////////////////////////////////////////
	public MapObject() {};
	public MapObject(String objectName, String imageFile, boolean isPasable)
	{
		this.objectName = objectName;
		this.image = new Image(imageFile);
		this.imageView = new ImageView(image);
		this.isPasable = isPasable;
	}
	public MapObject(String objectName, String imageFile,Coordinate location, boolean isPasable)
	{
		this.objectName = objectName;
		this.image = new Image(imageFile);
		this.imageView = new ImageView(image);
		
//		imageView.prefWidth(3);
		this.location = location;
		this.isPasable = isPasable;
	}
	
/////////////////////////////////////////////
//          Setter/Getter                  //
/////////////////////////////////////////////
	public void setLocation(int x, int y)
	{
		this.location.setX(x);
		this.location.setY(y);
	}
	public Coordinate getLocation()
	{
		return location;
	}
	public void setName(String name)
	{
		this.objectName = name;
	}
	public ImageView getImageView(int gridSize)
	{
		imageView.setFitHeight(gridSize);
		imageView.setFitWidth(gridSize);
		return imageView;
	}
	public boolean isPasable()
	{
		return isPasable;
	}
	
	public String toString()
	{
		return objectName;
	}
	public String description()
	{
		return "Description:\n\t";
	}
}
