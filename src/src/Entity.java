package src;

import javafx.scene.layout.StackPane;
import mapObjects.Coordinate;
import mapObjects.MapObject;

public abstract class Entity extends MapObject {
    int hp;
    int maxHp;
    int speed;

    Entity(String objectName, String imageFile, Coordinate location, boolean isPasable, int imageSize){
        super(objectName,imageFile,location,isPasable,imageSize);
    }

    boolean move(int deltaX, int deltaY,Map map){
        Coordinate xy = super.getLocation();
        int x = xy.getX();
        int y = xy.getY();
        StackPane[][] panes = map.getStackPane();
        MapLocation[][] mapTiles = map.getMapLocation();

        MapLocation location = mapTiles[x][y];
        StackPane currentLocationPane = panes[x][y];
        MapLocation destination = mapTiles[x + deltaX][y + deltaY];
        StackPane destinationPane = panes[x + deltaX][y + deltaY];
            if (destination.isPasable()){
                location.removeEntity();
                currentLocationPane.getChildren().remove(this.getImageView());
                destinationPane.getChildren().add(this.getImageView());
                destination.setEntity(this);
                this.setLocation(x+deltaX,y+deltaY);
                return true;
            }
            else {
                return false;
            }
        }

    public void heal(int healthPoints) {
        if (hp + healthPoints >= maxHp) {
            hp = maxHp;
        } else {
            hp += healthPoints;
        }
    }

}

