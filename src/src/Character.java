package src;


import java.util.ArrayList;

import mapObjects.*;

public class Character extends Entity {

	ArrayList<Loot> inventory;
	Wepon equipedWepon = null;
	Armor equipedArmor = null;
	static final int statingStats = 5;
	static final int statPtsPerLvl = 3;
	
	private int exp = 0;
//	int availableStatPoint;

	
    public Character(Coordinate location,int maxHP, int speed) {

        super("Character", "TempChar.png", location, false, 60);
//        this.maxHp = maxHP;
        calcMaxHp();
        this.hp = maxHP;
        this.availableStatPoint = this.statingStats;
       // this.setSpeed(speed);
        inventory = new ArrayList<Loot>();
        inventory.add(new Dagger(0, 0, this.getImageSize()));
        inventory.add(new Axe(1, 0, this.getImageSize()));
        inventory.add(new SpeedPotion(1, 0, this.getImageSize()));
        inventory.add(new LeatherArmor(0, 0, this.getImageSize()));
    }


    public Character(int imageSize){
        super("Character","TempChar.png",new Coordinate(0,0),false,imageSize );
       // this.maxHp = 10;
        calcMaxHp();
        this.hp = maxHp;
        this.availableStatPoint = this.statingStats;
       // this.setSpeed(2);

        inventory = new ArrayList<Loot>();
        inventory.add(new Dagger(0, 0, this.getImageSize()));
        inventory.add(new Axe(1, 0, this.getImageSize()));
        inventory.add(new SpeedPotion(1, 0, this.getImageSize()));
        inventory.add(new LeatherArmor(0, 0, this.getImageSize()));
    }

	public int getStatPtsPerLvl() {
    	return statPtsPerLvl;
    }
    public void useStatPts(int ptsUsed) {
    	this.availableStatPoint -= ptsUsed;
    }
    /**
     * reads char input built for smartDirection enemy
     *
     * @param input W,S,A,D to move, other to return false
     * @return truth if moves, false if doesnt move
     */
    boolean readInput(char input, Map map) {
        boolean moveResult = false;
        if (input == 'W') {
            moveResult = move(0, -1, map);
            //return true;
        } else if (input == 'S') {
            moveResult = move(0, 1, map);
            //return true;
        } else if (input == 'A') {
            moveResult = move(-1, 0, map);
            //return true;
        } else if (input == 'D') {
            moveResult = move(1, 0, map);
            //return true;
        } /*else if (input == 'H') {
            MapLocation ml = map.getMapLocation()[map.getCursor().getLocation().getX()][map.getCursor().getLocation().getY()];
            Entity e = ml.getEntity();
            if (e != null) {
                attack(e, map);

            }
        }
        spendActions(this.getAttackCost());*/
        return moveResult;
}

        public ArrayList<Loot> getInventory(){
        	return inventory;
        }
        public Wepon getEquipedWepon() {
        	return this.equipedWepon;
        }
        public Armor getEquipedArmor() {
        	return this.equipedArmor;
        }
        public void grabLoot(Loot item) {
        	this.inventory.add(item);
        	this.spendActions(getMoveCost());
        	this.setChanged();
        	this.notifyObservers("LootChange");
        	for(int i = 0; i < inventory.size(); i++)
        		System.out.println(inventory.get(i).toString());
        }
        public void useItem(Consumable item) {
        	item.consume(this);
        	inventory.remove(item);
        	this.setChanged();
        	this.notifyObservers("LootChange");
        	
//        	removeItemFromInventory(item);
        }
        public void unEquipArmor() {
        	if(equipedArmor != null) {
        		inventory.add(equipedArmor);
        		equipedArmor = null;
        		this.setMovecost(1);
        		this.setChanged();
        		this.notifyObservers("EquipmentChange");
        	}
        }
        public void unEquipWeapon() {
        	if(equipedWepon != null) {
        		inventory.add(equipedWepon);
        		equipedWepon = null;
        		this.setAttackCost(1);
        		this.setChanged();
        		this.notifyObservers("EquipmentChange");
        	}
        }
        public void equipArmor(Equipable item) {
        	unEquipArmor();
        	this.equipedArmor = (Armor) item;
        	this.inventory.remove(item);
        	this.setMovecost(this.getMoveCost() + equipedArmor.getSpdPenalty());
        	this.setChanged();
    		this.notifyObservers("EquipmentChange");        	
        }
        public void equipWepon(Equipable item) {
        	unEquipWeapon();
        	this.equipedWepon = (Wepon) item;
        	this.inventory.remove(item);
        	this.setAttackCost(equipedWepon.getWepopSpeed());
        	this.setChanged();
    		this.notifyObservers("EquipmentChange"); 
        }



        public void lvlUp() {
        	this.lvl++;
        	this.availableStatPoint += this.statPtsPerLvl;
        	calcMaxHp();
        }
        
        /*
         * Calculates the total experiance points needed to advance to the next levle
         * @param - currentLvl = the level of the player
         * returns experience needed
         */
        public int calcNextLvl(int currentLvl) {
        	int experianceNeeded = 50;	//The expericance needed to advance to next level defaults to half of desired experiance needed for lvl 1
        	for(int lvl = 0; lvl < currentLvl; lvl++) {
        		experianceNeeded += experianceNeeded;
        	}
        	return experianceNeeded;
        }
        
        public boolean hasExited(Coordinate exit,char direction) {

        	boolean result = false;
        	//Coordinate exit = new Coordinate(exit1.getY(),exit1.getX());
        	System.out.println("Checking exit");
        	if(this.getLocation().equals(exit)) {
        		System.out.println("this location: " + this.getLocation().toString() + "\texit Location: " + exit.toString());
	        	switch(direction) {
	        	
	        		case 'A':
	        			if(exit.getX() - 1 < 0)
	        				result = true;
	        			break;
	        		case 'D':
	        			if(this.getLocation().getX() + 1 > exit.getX())
	        				result = true;
	        			break;
	        		case 'W':
	        			if(exit.getY() - 1 < 0)
	        				result = true;
	        			break;
	        		case 'S':
	        			if(this.getLocation().getY() + 1 > exit.getY())
	        				result = true;
	        			break;
	        	}
        	}
        	System.out.println(result);
        	if(result) {
        		this.setChanged();
        		this.notifyObservers("Exit");
        	}
        	return result;
        }
}



