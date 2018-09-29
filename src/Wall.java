import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Wall extends Tile {

    public ImageView getImage() {
        Image wallG = new Image("myWall.jpg");
        ImageView wallView = new ImageView();
        wallView.setImage(wallG);
        return wallView;
    }

    public Wall() {
        this.background = getImage();
        this.isMovable = true;

    }
}
