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
    private double curentActions;		//Actions curentrly available
    private double moveCost = 1;		//Action cost to move (effected by armor speed penalty)
    private double attackCost = 1;		//Action cost to attack (effected by weapon speed)
    
    //Stats
    private int str = 1;
    private int dex = 1;
    private int con = 1;
    private int mgk = 1;
    private int defence = 1;
    private int accuracy = 1;
    private int speed = 1;
    
    //Equiped
	Wepon equipedWepon = null;
	Armor equipedArmor = null;


     Entity(String objectName, String imageFile, Coordinate location, boolean isPasable, int imageSize){
        super(objectName,imageFile,location,isPasable,imageSize);
    }
     
     public int getHp() {
    	 return hp;
     }
     public int getMaxHp() {
    	 return maxHp;
     }
     public double getHpPresentage() {
    	 return 1.0*hp/maxHp;
     }
     public int getStr() {
		return str;
	}
	public void setStr(int str) {
		this.str = str;
	}
	public int getDex() {
		return dex;
	}
	public void setDex(int dex) {
		this.dex = dex;
	}
	public int getCon() {
		return con;
	}
	public void setCon(int con) {
		this.con = con;
	}
	public int getMgk() {
		return mgk;
	}
	public void setMgk(int mgk) {
		this.mgk = mgk;
	}
	public int getDefence() {
		return defence;
	}
	public void setDefence(int defence) {
		this.defence = defence;
	}
	public int getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public double getCurentActions() {
		return curentActions;
	}
	public void setMovecost(double moveCost) {
		this.moveCost = moveCost;
		this.setChanged();
		this.notifyObservers("ActionUpdate");
	}
	public double getMoveCost() {
		return moveCost;
	}
	public void setAttackCost(double attackCost) {
		this.attackCost = attackCost;
		this.setChanged();
		this.notifyObservers("ActionUpdate");
	}
	public double getAttackCost() {
		return attackCost;
	}
	//	public void setCurentActions(double curentActions) {
//		this.curentActions = curentActions;
//	}
	//    public double getMoveCost() {return moveCost;}

    public void setObserver(View view)
    {
    	addObserver(view);
    }

    boolean move(int deltaX, int deltaY,Map map){
    	System.out.println("\n\n"+this.getObjectName() + " is moving!!!!!");
        Coordinate xy = super.getLocation();
        int x = xy.getX();
        int y = xy.getY();
        int targetX = x + deltaX;
        int targetY = y + deltaY;
        if(targetX > -1 && targetX < map.getMapLocation()[0].length && targetY > -1 && targetY < map.getMapLocation().length) {
	        StackPane[][] panes = map.getStackPane();
	        MapLocation[][] mapTiles = map.getMapLocation();
	
	        MapLocation location = mapTiles[x][y];
	        StackPane currentLocationPane = panes[x][y];
	        MapLocation destination = mapTiles[x + deltaX][y + deltaY];
	        StackPane destinationPane = panes[x + deltaX][y + deltaY];
	            if (destination.isPasable()){
	            	this.setChanged();
	            	this.notifyObservers(this.getObjectName() + " Moved from: (" + x + ","+ y + ") (this msg comming from Entity move())");
	            	 	System.out.println("("+x+","+y+") entity = "+ location.getEntity());
	            	location.removeEntity();
	                	System.out.println("("+x+","+y+") entity = "+ location.getEntity());
	                currentLocationPane.getChildren().remove(this.getImageView());				//Remove Image from old location 
	                //map.getStackPane()[x][y].getChildren().remove(this.getImageView());
	                destinationPane.getChildren().add(this.getImageView());						//Add Image to new location
	                //map.getStackPane()[x+deltaX][y+deltaY].getChildren().add(this.getImageView());
	                //map.getStackPane()[this.getLocation().getX()][this.getLocation().getY()].getChildren().remove(this.getImageView());
	                this.setLocation(x+deltaX,y+deltaY);										//Update entety location
	                //map.getStackPane()[this.getLocation().getX()][this.getLocation().getY()].getChildren().add(this.getImageView());
	                destination.setEntity(this);												//Add entity to location

	                this.setChanged();
	            	this.notifyObservers(this.getObjectName() + " Moved to: (" + this.getLocation().getX() + ","+ this.getLocation().getY() + ") (this msg comming from Entity move())");
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
        System.out.println("Move location off map");
        return false;
    }



    public void attack(MapObject target, Map map) {
    	if(target instanceof Entity){
    		Entity ent = (Entity)target;
			if(equipedWepon != null) {
				ent.damag((this.getStr() * equipedWepon.getDmg()) / ent.getDefence()); // CHANGE DAMAGE HERE
				setChanged();
				notifyObservers(this + " hit " + target + " for " + (this.getStr() * equipedWepon.getDmg()) / ent.getDefence() + "damage" );
			}
			else{
				ent.damag(this.getStr() * (this.getAccuracy() / ent.getDefence())); // CHANGE DAMAGE HERE
				setChanged();
				notifyObservers(this + " hit " + target + " for " + (this.getStr() * (this.getAccuracy() / ent.getDefence())) + "damage" );
			}
    	}
    	if(target instanceof Obstacle) {
			Obstacle t = (Obstacle)target;
    		t.damage(this.str, map);
    		Obstacle oTarget = (Obstacle)target;
			if(equipedWepon != null) {
				oTarget.damage((this.getStr() * equipedWepon.getDmg()),map); // CHANGE DAMAGE HERE
			}
			else{
				oTarget.damage(this.getStr() * (this.getAccuracy()),map); // CHANGE DAMAGE HERE

			}

    	}
    	spendActions(attackCost);
    }

    public void heal(int healthPoints) {
        if (hp + healthPoints >= maxHp) {
            hp = maxHp;
        } else {
            hp += healthPoints;
        }
		if(this instanceof Character) {
			this.setChanged();
			this.notifyObservers("Character health increased by" + healthPoints);
		}
		if(this instanceof Enemy){
			this.setChanged();
			this.notifyObservers("ENEMY Healed FOR " + healthPoints);
		}
     }

    public void damag(int dmg) {
    	hp -= dmg;
    	if(hp<0){
    	    hp=0;
        }
    	if(this instanceof Character) {
    		this.setChanged();
    		this.notifyObservers("Character health decreased by" + dmg);
    	}
    	if(this instanceof Enemy){
    		this.setChanged();
    		this.notifyObservers("ENEMY HIT FOR " + dmg);
		}
    }

    public boolean checkDead(){
        if(this.hp <= 0){
        	return true;
		}
		else{
        	return false;
		}
    }

    public void newTurn(){//myTurn set to true and reset curentActions
//    	double newActions = (1 + speed/5.0);
//    	curentActions = Math.round(curentActions * 10) / 10.0;

    	curentActions += (1 + speed/5.0);

    	if(this instanceof Character) {
    		this.setChanged();
    		this.notifyObservers("ActionUpdate");
    	}
    }
    public boolean canAct() {
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
    public void spendActions(double actionsCost) {
            curentActions -= actionsCost;
            if (this instanceof Character) {
                this.setChanged();
                this.notifyObservers("ActionUpdate");
            }
            if (!canAct()) {//TODO Remove this latter ????
                this.setChanged();
                this.notifyObservers(this.getObjectName() + " turn over. (this msg comming rom Entity spendActions())");
            }
        }

    
    public void giveActions(double actionPoints) {
    	this.curentActions += actionPoints;
    	this.setChanged();
    	this.notifyObservers("ActionUpdate");
    }
    
    public boolean isAdjacent(Coordinate location) {
    	int x = this.getLocation().getX();
    	int y = this.getLocation().getY();
    	int xDiff = x - location.getX();
    	int yDiff = y - location.getY();
    	if(xDiff == 1 || xDiff == -1 || yDiff == 1 || yDiff == -1)
    		return true;
    	return false;
    }
    
}