package src;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mapObjects.Coordinate;

public class Character extends Tile {
    int xMapVal;
    int yMapVal;
    int hp;

    public ImageView getImage() {
        Image wallG = new Image("TempChar.png");
        ImageView wallView = new ImageView();
        wallView.setImage(wallG);
        wallView.setFitHeight(75);
        wallView.setFitWidth(75);
        wallView.setFitHeight(tileSize);
        wallView.setFitWidth(tileSize);
        return wallView;
    }

    public Character(int tileSize) {
    	super(tileSize);
        this.background = getImage();
        this.isMovable = true;
        
    }

    boolean moveChar(int x, int y){
        if ((x + xMapVal < Map.NUM_GRIDS-1 && x + xMapVal >0) && (y + yMapVal < Map.NUM_GRIDS-1 && y + yMapVal > 0)){
            xMapVal += x;
            yMapVal += y;
            return true;
        }
        else{
            return false;
        }

    }
    public void heal(int healthPoints)
    {
    	hp += healthPoints;
    }

    public void setLocation(int x, int y)
    {
    	this.xMapVal = x;
    	this.yMapVal = y;
    }
}

