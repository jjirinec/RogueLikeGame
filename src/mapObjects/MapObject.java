package mapObjects;

import java.util.Observable;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import mapObjects.Coordinate;


public class MapObject extends Observable{
	
	private String objectName;
	private int imageSize;
	private Image image;
	private ImageView imageView;
	private Coordinate location;
	protected boolean isPasable;
	
	
//////////////////////////////////////////////
//            Constructors                  //
//////////////////////////////////////////////
	public MapObject() {};
	public MapObject(String objectName, String imageFile, boolean isPasable,int imageSize)
	{
		this.setObjectName(objectName);
		this.image = new Image(imageFile);
		this.imageView = new ImageView(image);	
		this.isPasable = isPasable;
		setImageSize(imageSize);
	}
	public MapObject(String objectName, String imageFile,Coordinate location, boolean isPasable, int imageSize)
	{
		this.setObjectName(objectName);
		this.imageSize = imageSize;
		this.image = new Image(imageFile);

		this.imageView = new ImageView(image);
		//imageView.localToParent(localBounds);
		
//		imageView.prefWidth(3);
		this.location = location;
		this.isPasable = isPasable;
		setImageSize(imageSize);
	}
	
/////////////////////////////////////////////
//          Setter/Getter                  //
/////////////////////////////////////////////
	public void setLocation(int x, int y)
	{
		if(this.location == null)
			this.location = new Coordinate(x,y);
		else
		{
			this.location.setX(x);
			this.location.setY(y);
		}
	}
	public Coordinate getLocation()
	{
		return location;
	}
	public void setName(String name)
	{
		this.setObjectName(name);
	}
	public int getImageSize() {
		return imageSize;
	}
		
	public void setImageSize(int imageSize)
	{
		if(imageSize > 80)
			imageSize = 80;		
		imageView.setFitHeight(imageSize);
		imageView.setFitWidth(imageSize);
		imageView.autosize();
	}
	protected void setImage(String imageFile)
	{
		image = new Image(imageFile);
		imageView = new ImageView(image);
		setImageSize(imageSize);
	}
	public ImageView getImageView()
	{
		return imageView;
	}
	public boolean isPasable()
	{
		return isPasable;
	}
	
	public String toString()
	{
		return getObjectName();
	}
	public String description()
	{
		return "Description:\n\t";
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
}
