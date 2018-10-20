package src;

import javafx.scene.layout.StackPane;
import mapObjects.Armor;
import mapObjects.Coordinate;
import mapObjects.MapObject;
import mapObjects.Obstacle;
import mapObjects.Wepon;

public abstract class Entity extends MapObject{
    //boolean myTurn;
	int hp;
    int maxHp;
    private double curentActions;
    private double moveCost = 1;		//Action cost to move (effected by armor speed penalty)
    private double attackCost = 1;		//Action cost to attack (effected by weapon speed)
    
    int str;
    int dex;
    int con;
    int mgk;
    int defence;
    int accuracy;
    int speed;
    
    //Equiped
    Armor armor;
    Wepon wepon;

     Entity(String objectName, String imageFile, Coordinate location, boolean isPasable, int imageSize){
        super(objectName,imageFile,location,isPasable,imageSize);
        
    }
//    public double getMoveCost() {return moveCost;}
//    public double getAttackCost() {return attackCost;}
    public void setObserver(View view)
    {
    	addObserver(view);
    }
    boolean move(int deltaX, int deltaY,Map map){
    	System.out.println("\n\n"+this.getObjectName() + " is moving!!!!!");
    	this.setChanged();
    	this.notifyObservers(this.getObjectName() + " moving");
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
            	this.setChanged();
            	this.notifyObservers(this.getObjectName() + " Moved from: (" + x + ","+ y + ")");
                location.removeEntity();
                currentLocationPane.getChildren().remove(this.getImageView());
                destinationPane.getChildren().add(this.getImageView());
                destination.setEntity(this);
                this.setLocation(x+deltaX,y+deltaY);
                this.setChanged();
            	this.notifyObservers(this.getObjectName() + " Moved to: (" + this.getLocation().getX() + ","+ this.getLocation().getY() + ")");
                this.spendActions(this.moveCost);
                return true;
            }
            else {
            	System.out.println(destination.isPasable());
            	System.out.println(destination.getObstacle());
            	System.out.println(destination.getEntity());
                return false;
            }
        }

    public void attack(MapObject target,Map map) {
    	//TODO Add Attack Logic here
    	if(target instanceof Entity)
    	{
    		target = (Entity)target;
    	}
    	if(target instanceof Obstacle) {
    		target = (Obstacle)target;
    		((Obstacle) target).damage(5, map);
    	}
    	
    	spendActions(attackCost);
    }
    public void heal(int healthPoints) {
        if (hp + healthPoints >= maxHp) {
            hp = maxHp;
        } else {
            hp += healthPoints;
        }
    }

    public void newTurn(){//myTurn set to true and reset curentActions
    	curentActions += (1 + speed/5);
    	//myTurn = true;
    }
    public boolean canAct() {
    	//System.out.println("Actions: " + curentActions + "\nmoveCost: " + moveCost + "\nAttackCost: " + attackCost);
    	if(hasMovement() || hasAttacks())
    		return true;
    	return false;
    }
    public boolean hasMovement()
    {
    	if(curentActions >= moveCost)
    		return true;
    	return false;
    		
    }
    public boolean hasAttacks() {
    	if(curentActions >= attackCost)
    		return true;
    	return false;
    }
    private void spendActions(double actionsCost){
    	curentActions -= actionsCost;
    	if(!canAct()){
    		this.setChanged();
    		this.notifyObservers(this.getObjectName() + " turn over.");
    	}
//    		myTurn = false;
    }
    
    
}//End Class

