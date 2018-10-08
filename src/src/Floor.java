package src;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Floor extends Tile {

    public ImageView getImage()  {
    Image floorG = new Image("myFloor.jpg");
    ImageView floorView = new ImageView();
    floorView.setImage(floorG);
    return floorView;
    }

    public Floor(){
        this.background = getImage();
        this.isMovable = true;

    }


}
