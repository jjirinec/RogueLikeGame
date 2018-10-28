package src;


import mapObjects.*;

public class Character extends Entity {
    public Character(Coordinate location,int maxHP, int speed) {
        super("Character", "TempChar.png", location, false, 60);
        this.maxHp = maxHP;
        this.hp = maxHP;
        this.speed = speed;
    }

    public Character(int imageSize){
        super("Character","TempChar.png",new Coordinate(0,0),false,imageSize );
        this.maxHp = 10;
        this.hp = 10;
        this.speed = 2;
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
}

