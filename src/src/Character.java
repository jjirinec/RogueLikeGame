package src;


import java.util.ArrayList;

import mapObjects.*;

public class Character extends Entity {
	ArrayList<Loot> inventory;
	Wepon equipedWepon = null;
	Armor equipedArmor = null;
	
    public Character(Coordinate location,int maxHP, int speed) {
        super("Character", "TempChar.png", location, false, 60);
        this.maxHp = maxHP;
        this.hp = maxHP;
        this.setSpeed(speed);
        inventory = new ArrayList<Loot>();
        inventory.add(new Dagger(0,0,this.getImageSize()));
        inventory.add(new Axe(1,0,this.getImageSize()));
        inventory.add(new SpeedPotion(1,0,this.getImageSize()));
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
                //hit here
            }
            return moveResult;
        }
        public ArrayList<Loot> getInventory(){
        	return inventory;
        }
        
        public void grabLoot(Loot item) {
        	this.inventory.add(item);
        	this.setChanged();
        	this.notifyObservers("NewLoot");
        	for(int i = 0; i < inventory.size(); i++)
        		System.out.println(inventory.get(i).toString());
        }
}

