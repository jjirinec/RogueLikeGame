package src;

import javafx.concurrent.Task;
import javafx.scene.layout.StackPane;
import mapObjects.Armor;
import mapObjects.Coordinate;
import mapObjects.MapObject;
import mapObjects.Obstacle;
import mapObjects.Wepon;

public abstract class Entity extends MapObject{
    //boolean myTurn;
	int hp;		//Curretn Hp
    int maxHp;	//Maximum Hp based on lvl and constitution.
    private double curentActions;		//Actions curentrly available
    private double moveCost = 1;		//Action cost to move (effected by armor speed penalty)
    private double attackCost = 1;		//Action cost to attack (effected by weapon speed)
    
    protected int lvl = 1;		//Default to one
    int availableStatPoint;		
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

	Entity(String objectName, String imageFile, boolean isPasable, int imageSize){
		super(objectName,imageFile,isPasable,imageSize);
	}
	public void statPointIncrement(int vale) {
		this.availableStatPoint  += vale;
		System.out.println("Aval stat points" + this.availableStatPoint);
	}
     public int getAvailableStatPts() {
    	 return this.availableStatPoint;
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
	public void incrementStr(int val) {
		if(val > 0)
			str++;
		else 
			str--;
		notify("StatUpdate");
	}
	public int getDex() {
		return dex;
	}
	public void incrementDex(int val) {
		if(val > 0)
			dex++;
		else
			dex--;
		notify("StatUpdate");
	}
	public int getCon() {
		return con;
	}
	public void incrementCon(int val) {
		if(val > 0)	
			con++;
		else
			con--;
		calcMaxHp();
		notify("StatUpdate");
	}
	public int getMgk() {
		return mgk;
	}
	public void incrementMgk(int val) {
		if(val > 0)
			mgk++;
		else
			mgk--;
		notify("StatUpdate");
	}
	public int getDefence() {
		return defence;
	}
	public void incrementDefence(int val) {
		if(val > 0)
			defence++;
		else 
			defence--;
		notify("StatUpdate");
	}
	public int getAccuracy() {
		return accuracy;
	}
	public void incrementAccuracy(int val) {
		if(val > 0)
			accuracy++;
		else
			accuracy--;
		notify("StatUpdate");
	}
	public int getSpeed() {
		return speed;
	}
	public void incrementSpeed(int val) {
		if(val > 0)
			speed++;
		else 
			speed--;
		notify("StatUpdate");
	}
	public double getCurentActions() {
		return curentActions;
	}
	public int getLevel() {
		return this.lvl;
	}
	public void notify(String msg) {
		if(this instanceof Character) {
			this.setChanged();
			this.notifyObservers(msg);
		}
	}
	
	public abstract void lvlUp();
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


    public void setObserver(View view)
    {
    	addObserver(view);
    }

   synchronized boolean move(int deltaX, int deltaY,Map map){
    	System.out.println(this.getObjectName() + " is moving!!!!!");
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
	            	location.removeEntity();
	                this.updateGridImgage(currentLocationPane, destinationPane);	
	                this.setLocation(x+deltaX,y+deltaY);										//Update entety location      
	                destination.setEntity(this);												//Add entity to location
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


   private void updateGridImgage(StackPane currentLocationPane, StackPane destinationPane) {
	   javafx.application.Platform.runLater( () ->currentLocationPane.getChildren().remove(this.getImageView()));				//Remove Image from old location
	   javafx.application.Platform.runLater( () ->destinationPane.getChildren().add(this.getImageView()));						//Add Image to new location
   }
   

    public int attack(MapObject target, Map map) {
    	int dmg = 0;
    	if(target instanceof Entity){
    		Entity ent = (Entity)target;
    		double baseD = calcBaseDmg();
    		double hitChance = calcHitChance(ent.getDefence());
            System.out.println(baseD);
            System.out.println(hitChance);

            dmg = Math.round((float)(calcBaseDmg() * calcHitChance(ent.getDefence())));
				ent.damag(dmg); // CHANGE DAMAGE HERE
                spendActions(attackCost);
    	}
    	else if(target instanceof Obstacle) {
			Obstacle t = (Obstacle)target;
            dmg = Math.round((float)calcBaseDmg());
    		t.damage(dmg, map);
    		spendActions(attackCost);
    	}
    	setChanged();
		notifyObservers(this + " hit " + target + " for " + dmg + " damage" );
    	return dmg;
        
    }

	public void calcMaxHp() {
    	int curentMax = maxHp;
    	this.maxHp = 10 * lvl + 10 * lvl * getCon()/5;
    	this.hp += (maxHp - curentMax);
    	notify("Hp Change");
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
			this.setChanged();
    		this.notifyObservers("Hp Change");
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
    		this.setChanged();
    		this.notifyObservers("Hp Change");
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
    	
    	curentActions += (1 + speed/5.0);
    	if(this instanceof Character) {
    		System.out.println("Plaer NewTurn");
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
    		System.out.println(actionsCost + " Actions spent \nStarting with " + curentActions);
            curentActions -= actionsCost;
            System.out.println("Ending with " + curentActions);
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

    public double calcBaseDmg(){
	    if(this instanceof Enemy){
	        Enemy e = (Enemy)this;
            return(e.baseDmg + e.baseDmg * str/5.0);
        }
        if(equipedWepon != null) {
            return(equipedWepon.getDmg() + equipedWepon.getDmg() * str/5.0);
        }
        else{
            return(1 + this.str/5.0);
        }
    }

    public double calcHitChance(int def) {
	    double chance = (4.0*this.accuracy)/(3.0*def);
	    if (chance>1)
	        chance = 1;
	    return chance;
        }
    }
    
