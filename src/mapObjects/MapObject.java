package mapObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MapObject {
	
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
		this.objectName = objectName;
		this.image = new Image(imageFile);
		this.imageView = new ImageView(image);
		this.isPasable = isPasable;
		setImageSize(imageSize);
	}
	public MapObject(String objectName, String imageFile,Coordinate location, boolean isPasable, int imageSize)
	{
		this.objectName = objectName;
		this.imageSize = imageSize;
		this.image = new Image(imageFile);
		this.imageView = new ImageView(image);
		
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
		this.objectName = name;
	}
	
		
	public void setImageSize(int imageSize)
	{
		if(imageSize > 80)
			imageSize = 80;		
		imageView.setFitHeight(imageSize);
		imageView.setFitWidth(imageSize);
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
		return objectName;
	}
	public String description()
	{
		return "Description:\n\t";
	}
}
