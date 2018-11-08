package src;


import java.util.ArrayList;

import mapObjects.*;

public class Character extends Entity {
	ArrayList<Loot> inventory;
	
    public Character(Coordinate location,int maxHP, int speed) {
        super("Character", "TempChar.png", location, false, 60);
        this.maxHp = maxHP;
        this.hp = maxHP;
        this.setSpeed(speed);
        inventory = new ArrayList<Loot>();
        inventory.add(new Dagger(0,0,this.getImageSize()));
        inventory.add(new Axe(1,0,this.getImageSize()));
        inventory.add(new SpeedPotion(1,0,this.getImageSize()));
        inventory.add(new LeatherArmor(0,0,this.getImageSize()));
    }

    public Character(int imageSize){
        super("Character","TempChar.png",new Coordinate(0,0),false,imageSize );
        this.maxHp = 10;
        this.hp = 10;
        this.setSpeed(2);
        inventory = new ArrayList<Loot>();
        inventory.add(new Dagger(0,0,this.getImageSize()));
        inventory.add(new Axe(1,0,this.getImageSize()));
        inventory.add(new SpeedPotion(1,0,this.getImageSize()));
        inventory.add(new LeatherArmor(0,0,this.getImageSize()));
    }

        /**
         * reads char input built for smartDirection enemy
         * @param input W,S,A,D to move, other to return false
         * @return truth if moves, false if doesnt move
         */
        boolean readInput(char input,Map map){
           boolean moveResult = false;
        	if(input == 'W'){
                moveResult = move(0,-1,map);
                //return true;
            }
            else if(input == 'S'){
               moveResult =  move(0,1,map);
                //return true;
            }
            else if(input == 'A'){
                moveResult = move(-1,0,map);
                //return true;
            }
            else if(input == 'D'){
                moveResult = move(1,0,map);
                //return true;
<<<<<<< HEAD
            } else if (input == 'H') {
        	    MapLocation ml = map.getMapLocation()[map.getCursor().getLocation().getX()][map.getCursor().getLocation().getY()];
        	    Entity e = ml.getEntity();
        	    if(e != null){
                    attack(e,map);
                    }
                }

=======
//            } else if (input == 'H') {
//        	    MapLocation ml = map.getMapLocation()[map.getCursor().getLocation().getX()][map.getCursor().getLocation().getY()];
//        	    Entity e = ml.getEntity();
//        	    if(e != null){
//                    if(wepon != null) {
//                        e.damag((this.getStr() * wepon.getDmg()) / e.getDefence()); // CHANGE DAMAGE HERE
//                    }
//                    else{
//                        e.damag(this.getStr() * (this.getAccuracy() / e.getDefence())); // CHANGE DAMAGE HERE
//
//                    }
//                }
                spendActions(this.getAttackCost());
            }
>>>>>>> 7a345dc089f1f7c63cb895bf72cc6705ce0d1a8c
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
//        private void removeItemFromInventory(Object item) {
//        	for(int i = 0; i < inventory.size(); i++) {
//        		if(inventory.get(i).equals(item)) {
//        			System.out.println("ItemFound");
//        			inventory.remove(i);
//        		}
//        	}
//        }
        public boolean hasExited(Coordinate exit1,char direction) {
        	boolean result = false;
        	Coordinate exit = new Coordinate(exit1.getY(),exit1.getX());
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



