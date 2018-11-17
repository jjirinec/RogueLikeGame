package src;

import java.util.Random;

import javafx.application.Platform;
import javafx.concurrent.Task;
import mapObjects.*;


public class Enemy extends Entity {
    public Enemy(Coordinate location, int maxHP, int speed, int imageSize) {
        super("Enemy", "TempChar.png", location, false, imageSize);
        this.maxHp = maxHP;
        this.hp = maxHP;
        this.setSpeed(speed);
    }

    public Enemy(int imageSize) {
        super("Enemy", "TempChar.png", new Coordinate(0, 0), false, imageSize);
        this.maxHp = 10;
        this.hp = 10;
        this.setSpeed(2);
    }

    char smartDirectionEnemy(int Xp, int Yp, int Xd, int Yd) {
    	//System.out.println("SmartMove");
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
    	//System.out.println("readInput");
        boolean result = false;
        if (input == 'W') {
            result = move(0, -1, map);
            if(!result) {
            randomMove(map);
            }
        } else if (input == 'S') {
            result = move(0, 1, map);
            if(!result) {
                randomMove(map);
            }
        } else if (input == 'A') {
            result = move(-1, 0, map);
            if(!result) {
                randomMove(map);
            }
        } else if (input == 'D') {
            result = move(1, 0, map);
            if(!result) {
                randomMove(map);
            }
        } else if (input == 'H') {
            //hit here
            result = true;
            if(wepon != null) {
                map.getPlayer().damag((this.getStr() * wepon.getDmg()) / map.getPlayer().getDefence()); // CHANGE DAMAGE HERE
            }
            else{
                map.getPlayer().damag(this.getStr() * (this.getAccuracy() / map.getPlayer().getDefence())); // CHANGE DAMAGE HERE

            }
            spendActions(this.getAttackCost());
            if(map.getPlayer().checkDead()){
                map.removeEntity(map.getPlayer());
                setChanged();
                notifyObservers("PLAYER IS DEAD");
                // END GAME HERE
            }
            }
     //   timeStop(800);
        return result;
    }

    public synchronized void turn(Character player, Map map, Coordinate doorLoc) {
        this.newTurn();
		while (this.canAct()) {
    		timeStop(500);
    		readInput(smartDirectionEnemy(player.getLocation().getX(), player.getLocation().getY(), doorLoc.getX(), doorLoc.getY()), map);
		}
    }///End turn


    public void randomMove(Map map) {
        char[] direction = {'W', 'S', 'A', 'D'};
        Random rand = new Random();
        boolean temp = false;
        while (!temp) {
            int randomDirection = rand.nextInt(3);
            temp = readInput(direction[randomDirection], map);
        }
    }
    /*
     * Pause the thread
     * The purpose of this method is to wait a short time each time an enamy moves so that it dose not just jump from place to place on the map
     */

    private synchronized void timeStop(long time)
    {
        try {	///Waits a short time before acting again
        	Thread.sleep(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }


}
