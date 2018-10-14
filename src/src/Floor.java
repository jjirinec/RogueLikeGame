package src;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Floor extends Tile {

    public ImageView getImage()  {
    Image floorG = new Image("myFloor.jpg");
    ImageView floorView = new ImageView();
    floorView.setImage(floorG);
    floorView.setFitHeight(this.tileSize);
    floorView.setFitWidth(this.tileSize);
    return floorView;
    }

    public Floor(int tileSize){
    	super(tileSize);
        this.background = getImage();
        this.isMovable = true;

    }
    public Floor(Floor floor)
    {
    	super(floor);
    }


}
