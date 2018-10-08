package src;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Character extends Tile {
    int xMapVal;
    int yMapVal;
    int hp;

    public ImageView getImage() {
        Image wallG = new Image("myCharacterTrans.png");
        ImageView wallView = new ImageView();
        wallView.setImage(wallG);
        return wallView;
    }

    public Character(int yVal) {
        this.background = getImage();
        this.isMovable = true;
        this.xMapVal =0;
        this.yMapVal = yVal;
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

}

