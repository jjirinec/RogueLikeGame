package mapObjects;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import src.Map;

public class TargetingCursor extends MapObject{
	private static String imageFile = "images/TargetingCursor.png";
	private static String name = "Cursor";
	public TargetingCursor(int gridSize,Coordinate location)
	{
		super(name,imageFile,location,true,gridSize);
	}
	
	public void moveTo(Coordinate location, GridPane map) {
		map.getChildren().remove(getImageView());
		this.setLocation(location.getY(), location.getX());
		map.add(getImageView(), location.getY(), location.getX());
	}
	public void move(KeyCode keyCode,Map map)
	{
		int x = this.getLocation().getX();
		int y = this.getLocation().getY();
		switch(keyCode)
		{
			case UP:
				if(y-1 >= 0) {	
					this.setLocation(x, y-1);
					map.getStackPane()[x][y].getChildren().remove(getImageView());
					map.getStackPane()[x][y-1].getChildren().add(getImageView());
					System.out.println(this.getLocation());
				}
				break;
			case DOWN:
				if(y+1 < map.getStackPane().length) {
					this.setLocation(x, y+1);
					map.getStackPane()[x][y].getChildren().remove(getImageView());
					map.getStackPane()[x][y+1].getChildren().add(getImageView());
					System.out.println(this.getLocation());
				}
				break;
			case LEFT:
				if(x-1 >= 0) {
					this.setLocation(x-1, y);
					map.getStackPane()[x][y].getChildren().remove(getImageView());
					map.getStackPane()[x-1][y].getChildren().add(getImageView());
					System.out.println(this.getLocation());
				}
				break;
			case RIGHT:
				if(x+1 < map.getStackPane()[0].length) {
					this.setLocation(x+1, y);
					map.getStackPane()[x][y].getChildren().remove(getImageView());
					map.getStackPane()[x+1][y].getChildren().add(getImageView());
					System.out.println(this.getLocation());
				}
				break;
		}
	}

}
