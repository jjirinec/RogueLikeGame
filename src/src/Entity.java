package src;

import javafx.concurrent.Task;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import mapObjects.Armor;
import mapObjects.Coordinate;
import mapObjects.MapObject;
import mapObjects.Obstacle;
import mapObjects.Wepon;

import java.util.ArrayList;

import static src.Entity.AttackType.MELLE;
import static src.Entity.AttackType.RANGED;

public abstract class Entity extends MapObject{
    //boolean myTurn;
	int hp;		//Curretn Hp
    int maxHp;	//Maximum Hp based on lvl and constitution.
    private double curentActions;		//Actions curentrly available
    private double moveCost = 1;		//Action cost to move (effected by armor speed penalty)
    private double attackCost = 1;		//Action cost to attack (effected by weapon speed)
    protected String status = "Uninjured";
    
    protected int lvl = 1;		//Default to one
    int availableStatPoint;	
    protected boolean myTurn = false;
    //Stats 
    private int str = 1;
    private int dex = 1;
    private int con = 1;
    private int mgk = 1;
    private int defence = 1;
    private int accuracy = 1;
    private int speed = 1;
    
    private AttackType attackType;
    //Equiped
	Wepon equipedWepon = null;
	Armor equipedArmor = null;

	public static enum AttackType{MELLE,RANGED,MAGIC}

     Entity(String objectName, String imageFile, Coordinate location, boolean isPasable, int imageSize){
        super(objectName,imageFile,location,isPasable,imageSize);
		this.attackType = MELLE;
    }

	Entity(String objectName, String imageFile, boolean isPasable, int imageSize){
		super(objectName,imageFile,isPasable,imageSize);
		this.attackType = MELLE;
	}
	public void statPointIncrement(int vale) {
		this.availableStatPoint  += vale;
//		System.out.println("Aval stat points" + this.availableStatPoint);
	}
     public int getAvailableStatPts() {
    	 return this.availableStatPoint;
     }
     public void cycleAttackType() {
    	 if(this.attackType.equals(MELLE))
    		 this.attackType = AttackType.RANGED;
    	 else if(this.attackType.equals(AttackType.RANGED))
    		 this.attackType = AttackType.MAGIC;
    	 else if(this.attackType.equals(AttackType.MAGIC))
    		 this.attackType = MELLE;
    	 this.setChanged();
		 this.notifyObservers("Attack Type Change");
     }
     public AttackType getAttackType() {
    	 return attackType;
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
	public int getDefence() {//This retruns the stat value of Defence
		return defence;
	}
	public double getTotalDefence() {//This includes Armor and Dextarity
		Armor.Type armorType = null;
		int armorDefenceiveValue = 0;
		if(this.equipedArmor != null) {
			armorDefenceiveValue = equipedArmor.getDefence();
			armorType = equipedArmor.getType();
		}
		double dexBonus = this.dex / 10.0;
		double maxDexBonus = maxDexBonus(armorType);
		if(maxDexBonus > 0 && dexBonus > maxDexBonus)
			dexBonus = maxDexBonus;
		double totalDefence = defence + armorDefenceiveValue + dexBonus;
		System.out.println("TotalDevence: " + totalDefence);
		return totalDefence;
	}
	private double maxDexBonus(Armor.Type armorType) {
		double bonus = 0;
		if(armorType == null)
			bonus = -1;//Flag for no maxDexBonus
		else if(armorType.equals(Armor.Type.LIGHT))
			bonus = 1;
		else if(armorType.equals(Armor.Type.MEDIUM))
			bonus = .75;
		else if(armorType.equals(Armor.Type.HEAVY))
			bonus = .25;
		return bonus;
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
        Coordinate xy = super.getLocation();
        int x = xy.getX();
        int y = xy.getY();
        int targetX = x + deltaX;
        int targetY = y + deltaY;
	   	MapLocation[][] mapTiles = map.getMapLocation();
        if(targetX > -1 && targetX < map.getMapLocation()[0].length && targetY > -1 && targetY < map.getMapLocation().length) {
			StackPane[][] panes = map.getStackPane();
			MapLocation location = mapTiles[x][y];
			StackPane currentLocationPane = panes[x][y];
			MapLocation destination = mapTiles[x + deltaX][y + deltaY];
			StackPane destinationPane = panes[x + deltaX][y + deltaY];
			if (destination.isPasable()) {
				location.removeEntity();
				this.updateGridImgage(currentLocationPane, destinationPane);
				this.setLocation(x + deltaX, y + deltaY);
				destination.setEntity(this);
				this.spendActions(this.moveCost);
				return true;
			}
		}
        return false;
    }


   private void updateGridImgage(StackPane currentLocationPane, StackPane destinationPane) {
	   javafx.application.Platform.runLater( () ->currentLocationPane.getChildren().remove(this.getImageView()));				//Remove Image from old location
	   javafx.application.Platform.runLater( () ->destinationPane.getChildren().add(this.getImageView()));						//Add Image to new location
   }
   

    public int attack(MapObject target, View view) {
		if(this.attackType == MELLE){
			return(melleAttack(target, view.map));
		}
		else if(this.attackType == RANGED){
			view.animationLayer.startArrowAnimation(this.getLocation(), target.getLocation());
			return(rangedAttack(target, view.map));
		}
		else{
			view.animationLayer.startFireAnimation(this.getLocation(), target.getLocation());
			return(magicAttack(target.getLocation(), view.map));
			
		}
    }

	public int melleAttack(MapObject target, Map map) {
		int dmg = 0;
		if(target instanceof Entity){
			Entity ent = (Entity)target;
			dmg = Math.round((float)(calcBaseDmg() * calcHitChance(ent.getTotalDefence())));
			if(dmg < 1)
				dmg = 1;
			ent.damag(dmg);
			spendActions(attackCost);
		}
		else if(target instanceof Obstacle) {
			Obstacle t = (Obstacle)target;
			dmg = Math.round((float)calcBaseDmg());
			t.damage(dmg, map);
			spendActions(attackCost);
		}
		String dmgDeltMsg = this + ": hit " + target.getObjectName() + " for " + dmg + " damage" ;
		sendHudMsg(dmgDeltMsg);
		return dmg;
	}

    public int rangedAttack(MapObject target, Map map) {
    	int dmg = 0;
    	if(target instanceof Entity){
    		Entity ent = (Entity)target;
            dmg = Math.round((float)(calcBaseDmg() * calcHitChance(ent.getTotalDefence())));
            float distanceCalc = (float)(4.0/calculateD(this.getLocation().getX(),this.getLocation().getY(),target.getLocation().getX(),target.getLocation().getY()));
            if(distanceCalc > 1){
            	distanceCalc = 1;
			}
            dmg = Math.round(dmg * distanceCalc);
			if(dmg < 1)
				dmg = 1;
			ent.damag(dmg);
            spendActions(attackCost);
    	}
    	else if(target instanceof Obstacle) {
			Obstacle t = (Obstacle)target;
            dmg = Math.round((float)calcBaseDmg());
    		t.damage(dmg, map);
    		spendActions(attackCost);
    	}
    	String dmgDeltMsg = this + ": Shot " + target.getObjectName() + " for " + dmg + " damage" ;
    	sendHudMsg(dmgDeltMsg);
    	return dmg;
    }

    public int magicAttack(Coordinate loc, Map map) {
    	int dmg = 0;
    	MapObject target = map.getMapLocation()[loc.getX()][loc.getY()].getObstacle();
    	MapObject targetE = map.getMapLocation()[loc.getX()][loc.getY()].getEntity();
    	if(target == null && targetE == null){
			dmg = Math.round((float)(calcBaseDmg()));
			if(dmg < 1)
				dmg = 1;
			ArrayList<MapObject> splash = map.getSurrounding(loc);
			for(MapObject mo : splash){
				if(mo instanceof Entity){
					((Entity) mo).damag(dmg/2);
					sendHudMsg(this + ": Blasted " + mo.getObjectName() + " for " + dmg/2 + " splash damage");
                    if (((Entity)mo).checkDead()) {
                        map.getEnemys().remove(mo);
                        map.removeEntity((Entity)mo);
                    }
				}
				else if(mo instanceof Obstacle){
					((Obstacle) mo).damage(dmg/2,map);
				}
			}
			spendActions(attackCost);
		}
    	else if(targetE instanceof Entity){
    		Entity ent = (Entity)targetE;
            dmg = Math.round((float)(calcBaseDmg()));
            if(dmg < 1)
            	dmg = 1;
			ent.damag(dmg); // CHANGE DAMAGE HERE
			ArrayList<MapObject> splash = map.getSurrounding(targetE.getLocation());
			for(MapObject mo : splash){
				if(mo instanceof Entity){
					((Entity) mo).damag(dmg/2);
					sendHudMsg(this + ": Blasted " + mo.getObjectName() + " for " + dmg/2 + " splash damage");
                    if (((Entity)mo).checkDead()) {
                        map.getEnemys().remove(mo);
                        map.removeEntity((Entity)mo);
                    }
				}
				else if(mo instanceof Obstacle){
					((Obstacle) mo).damage(dmg/2,map);
				}
			}
            spendActions(attackCost);
			String dmgDeltMsg = this + ": Blasted " + targetE.getObjectName() + " for " + dmg + " damage" ;
			sendHudMsg(dmgDeltMsg);
			return dmg;
    	}
    	else if(target instanceof Obstacle) {
			Obstacle t = (Obstacle)target;
            dmg = Math.round((float)calcBaseDmg());
    		t.damage(dmg, map);
    		ArrayList<MapObject> splash = map.getSurrounding(target.getLocation());
			for(MapObject mo : splash){
				if(mo instanceof Entity){
					((Entity) mo).damag(dmg/2);
					sendHudMsg(this + ": Blasted " + mo.getObjectName() + " for " + dmg/2 + " splash damage");
                    if (((Entity)mo).checkDead()) {
                        map.getEnemys().remove(mo);
                        map.removeEntity((Entity)mo);
                    }
				}
				else if(mo instanceof Obstacle){
					((Obstacle) mo).damage(dmg/2,map);
				}
			}
    		spendActions(attackCost);
			String dmgDeltMsg = this + ": Blasted " + target.getObjectName() + " for " + dmg + " damage" ;
			sendHudMsg(dmgDeltMsg);
			return dmg;
    	}
		//String dmgDeltMsg = this + ": Blasted " + target.getObjectName() + " for " + dmg + " damage" ;
		//sendHudMsg(dmgDeltMsg);
		return dmg;
    }


    private void sendHudMsg(String msg) {
    	Text msgText = new Text(msg);
    	msgText.setFill(Color.GREEN);
    	if(this instanceof Enemy) 
    		msgText.setFill(Color.RED);
    	javafx.application.Platform.runLater( () ->setChanged());
    	javafx.application.Platform.runLater( () ->notifyObservers(msgText));

		
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
			sendHudMsg(this +" health increased by" + healthPoints);
			this.setChanged();
    		this.notifyObservers("Hp Change");
		}
		if(this instanceof Enemy){
			this.checkForStatusUpdate();
		}
     }


    public void damag(int dmg) {
    	hp -= dmg;
    	if(hp<0){
    	    hp=0;
        }
    	if(this instanceof Character) {
//    		this.setChanged();
//    		this.notifyObservers("Character health decreased by" + dmg);
			if(this.checkDead()){
				setChanged();
				System.out.println("PLAYER IS DEAD FROM ENEMY");
				notifyObservers("Dead");
			}
    		this.setChanged();
    		this.notifyObservers("Hp Change");
    	}
    	if(this instanceof Enemy){
    		checkForStatusUpdate();
		}
    }
    private void checkForStatusUpdate() {
    	if(hp <= ((maxHp - 2*maxHp/3)))// 2/3 hp lost
    		status = "Mangled";
    	else if(hp <= (maxHp - maxHp/3))//1/3 hp lost
    		status = "Bloodied";
    	else if(hp < maxHp)
			status = "Merely a flesh wound";
    	else
    		status = "Uninjured";
    	
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
    	myTurn  = true;
    	curentActions += (1 + speed/5.0);
    	if(this instanceof Character) {
//    		System.out.println("Plaer NewTurn");
    		this.setChanged();
    		this.notifyObservers("ActionUpdate");
    		Text yourTurnMsg = new Text("Its Your Turn");
    		yourTurnMsg.setFill(Color.ORANGE);
    		this.setChanged();
    		this.notifyObservers(yourTurnMsg);
    	}
    }
    public boolean canAct() {
    	if(getCurentActions() > 0 &&(hasMovement() || hasAttacks()))
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
    	System.out.println("CurrentActions: " + curentActions + " AttackCost: " + attackCost);
    	if(curentActions >= attackCost)
    		return true;
    	return false;
    }
    public void spendActions(double actionsCost) {
            curentActions -= actionsCost;
            curentActions = removeExtraDecimal(curentActions);
            if (this instanceof Character) {
                this.setChanged();
                this.notifyObservers("ActionUpdate");
            }
        }
    private double removeExtraDecimal(double num) {
    	num *= 100;
    	num = Math.round(num);
    	num /= 100.0;
    	return num;
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
    	if((xDiff == 1 || xDiff == -1 || xDiff == 0) && (yDiff == 0 || yDiff == 1 || yDiff == -1))
    		return true;
    	return false;
    }

    public double calcBaseDmg(){
    	double baseDmg = 0;
	    if(this instanceof Enemy){
	        Enemy e = (Enemy)this;
            baseDmg = (e.baseDmg + e.baseDmg * str/5.0);
        }
	    else {
	    	if(this.attackType.equals(MELLE))
	    		baseDmg = melleBaseDmg();
	    	else if(this.attackType.equals(AttackType.RANGED))
	    		baseDmg = rangedBaseDmg();
	    	else if(this.attackType.equals(AttackType.MAGIC))
	    		baseDmg = magicBaseDmg();
	    }
	    return baseDmg;
    }
    private double melleBaseDmg() {
    	double baseDmg = 1 + str/5.0;
    	if(equipedWepon != null) 
            baseDmg = (equipedWepon.getDmg() + equipedWepon.getDmg() * str/5.0);
        return baseDmg;
    }
    private double rangedBaseDmg() {
    	double baseDmg = 0;
    	if(equipedWepon != null && equipedWepon.getType().equals(Wepon.Type.RANGED))
    		baseDmg = equipedWepon.getDmg() + equipedWepon.getDmg() * dex/5.0;
    	return baseDmg;
    }
    private double magicBaseDmg() {
    	return 1 + mgk/3;
    }
    
    public double calcHitChance(double def) {
	    double chance = (4.0*this.accuracy)/(3.0*def);
	    if (chance>1)
	        chance = 1;
	    return chance;
        }

	double calculateD(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
	}
    }
    
