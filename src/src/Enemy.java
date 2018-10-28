package src;

import java.util.Random;

import mapObjects.*;


public class Enemy extends Entity {
    public Enemy(Coordinate location, int maxHP, int speed, int imageSize) {
        super("Enemy", "TempChar.png", location, false, imageSize);
        this.maxHp = maxHP;
        this.hp = maxHP;
        this.speed = speed;
    }

    public Enemy(int imageSize) {
        super("Enemy", "TempChar.png", new Coordinate(0, 0), false, imageSize);
        this.maxHp = 10;
        this.hp = 10;
        this.speed = 2;
    }

    char smartDirectionEnemy(int Xp, int Yp, int Xd, int Yd) {
    	Coordinate pos = getLocation();
        double totalD_P = calculateD(pos.getX(), pos.getY(), Xp, Yp);
        double totalDW = calculateD(pos.getX(), pos.getY() - 1, Xd, Yd) + calculateD(pos.getX(), pos.getY() - 1, Xp, Yp);
        double totalDS = calculateD(pos.getX(), pos.getY() + 1, Xd, Yd) + calculateD(pos.getX(), pos.getY() + 1, Xp, Yp);
        double totalDA = calculateD(pos.getX() - 1, pos.getY(), Xd, Yd) + calculateD(pos.getX() - 1, pos.getY(), Xp, Yp);
        double totalDD = calculateD(pos.getX() + 1, pos.getY(), Xd, Yd) + calculateD(pos.getX() + 1, pos.getY(), Xp, Yp);
        if (totalD_P <= 1) {
            System.out.println("ENEMY HIT");
            return 'H';
        } else if (totalDW < totalDS && totalDW < totalDA && totalDW < totalDD) {
        	return ('W');
        } else if (totalDD < totalDS && totalDD < totalDA && totalDD < totalDW) {
            return ('D');
        } else if (totalDS < totalDW && totalDS < totalDA && totalDS < totalDD) {
            return ('S');
        } else if (totalDA < totalDS && totalDA < totalDW && totalDA < totalDD) {
            return ('A');
        } else {
            return ('A');
        }
    }

    double calculateD(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    
    /**
     * reads char input built for smartDirection enemy
     *
     * @param input W,S,A,D to move, other to return false
     * @return truth if moves, false if doesnt move
     */
    boolean readInput(char input, Map map) {
    	boolean result = false;
        if (input == 'W') {
            move(0, -1, map);
            result =  true;
        } else if (input == 'S') {
            move(0, 1, map);
            result =  true;
        } else if (input == 'A') {
            move(-1, 0, map);
            result =  true;
        } else if (input == 'D') {
            move(1, 0, map);
            result =  true;
        } else if (input == 'H') {
        	
            //hit here
        }
        //timeStop(800);
        return result;
    }
    public void turn(Character player, Map map)
    {
    	this.newTurn();
    	

    	
    	
    //TODO This is just here for testing purposes
    	char[] direction = {'W','S','A','D'};
    	Random rand = new Random();
    	
    	int randomDerection;
    	while(this.canAct())
    	{
    		///TODO Enemy turn logic goes here replace the folowing
    	randomDerection = rand.nextInt(3);
    	readInput(direction[randomDerection],map);
		
		
    	
    	}
    }
    
    /*
     * Pause the thread 
     * The purpose of this method is to wait a short time each time an enamy moves so that it dose not just jump from place to place on the map
     */
    private void timeStop(long time)
    {
    	//Thread current = Thread.currentThread();
        try {									///Waits a short time before acting again
			//Thread.currentThread();;
        	System.out.println("waiting");
        	Thread.sleep(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    /// For enemy action
    /// CALL readInput(smartEnemyDirection(player.getX(),player.getY(),exit.getX(),exit.getY() ///
}
