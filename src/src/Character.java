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
            } else if (input == 'H') {
        	    MapLocation ml = map.getMapLocation()[map.getCursor().getLocation().getX()][map.getCursor().getLocation().getY()];
        	    Entity e = ml.getEntity();
        	    if(e != null){
                    attack(e,map);
                    }
                }

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
}



