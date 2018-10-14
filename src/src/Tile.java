package src;
import javafx.scene.image.ImageView;


public abstract class Tile {
    protected boolean isMovable;
    protected ImageView background;
    protected int tileSize;

    Tile(int tileSize){
        this.isMovable = false;
        this.background = null;
        this.tileSize = tileSize;
    }
    public Tile(Tile tile)
    {
    	this.isMovable = tile.isMovable;
    	this.background = tile.background;
    }

    public abstract ImageView getImage();


}
