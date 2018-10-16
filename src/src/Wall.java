package src;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Wall extends Tile {

    public ImageView getImage() {
        Image wallG = new Image("myWall.jpg");
        ImageView wallView = new ImageView();
        wallView.setImage(wallG);
        wallView.setFitHeight(this.tileSize);
        wallView.setFitWidth(this.tileSize);
        return wallView;
    }

    public Wall(int tileSize) {
    	super(tileSize);
        this.background = getImage();
        this.isMovable = false;

    }
    public Wall(Wall wall)
    {
    	super(wall);
    }
}
