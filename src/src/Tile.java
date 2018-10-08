package src;
import javafx.scene.image.ImageView;


public abstract class Tile {
    protected boolean isMovable;
    protected ImageView background;

    Tile(){
        this.isMovable = false;
        this.background = null;
    }

    public abstract ImageView getImage();


}
