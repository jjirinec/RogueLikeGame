package src;

import java.util.Random;

import javafx.application.Platform;
import javafx.concurrent.Task;
import mapObjects.*;



public class Enemy extends Entity {

    public final int STAT_PTS_PER_LVL = 3;
    public int baseDmg;

    public Enemy(Coordinate location, int imageSize, int chalengeRating) {
        super("Enemy", "EnemySpeedy.png", location, false, imageSize);
        this.baseDmg = chalengeRating + 1;
        //this.hp = maxHp;
        // this.setSpeed(speed);
    }

    public Enemy(int imageSize) {
        super("Enemy", "EnemySpeedy.png", new Coordinate(0, 0), false, imageSize);
        this.maxHp = 10;
        this.hp = 10;
        // this.setSpeed(2);
    }


    Enemy(String name,int imageSize, int chalengeRating,String image,Coordinate loc) {
        super(name, image, false, imageSize);
        this.lvl = chalengeRating;
        this.lvlUp();
        this.setLocation(loc.getX(),loc.getY());
        this.baseDmg = chalengeRating + 1;
    }



    char smartDirectionEnemy(int Xp, int Yp, int Xd, int Yd,Map map) {


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
                return(randomChar(map));
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
        if(isSurrounded(map)){
            spendActions(10);
            System.out.println("IS SURROUNDED CHUNK");
            return true;
        }
        if (input == 'W') {
            result = move(0, -1, map);
            if(!result) {
                result = randomMove(map);
            }
        } else if (input == 'S') {
            result = move(0, 1, map);
            if(!result) {
                result = randomMove(map);
            }
        } else if (input == 'A') {
            result = move(-1, 0, map);
            if(!result) {
                result = randomMove(map);
            }
        } else if (input == 'D') {
            result = move(1, 0, map);
            if(!result) {
                result = randomMove(map);
            }
        } else if (input == 'H') {
            result = true;
            attack(map.getPlayer(),map);
            if(map.getPlayer().checkDead()){
                map.removeEntity(map.getPlayer());
                setChanged();
                notifyObservers("PLAYER IS DEAD");
                // END GAME HERE
            }
            }
        return result;
    }


    public synchronized void turn(Character player, Map map, Coordinate doorLoc) {
        this.newTurn();
		while (this.canAct() && !isSurrounded(map) && this.getCurentActions() > 0) {
    		timeStop(500);
    		readInput(smartDirectionEnemy(player.getLocation().getX(), player.getLocation().getY(), doorLoc.getX(), doorLoc.getY(),map), map);
		}
    }///End turn


    public boolean isSurrounded(Map map) {
        Coordinate Loc = getLocation();
        int x = Loc.getX();
        int y = Loc.getY();
        if (Loc == map.getExit()){
            return true;
        }
        return(!(map.getMapLocation()[x+1][y].isPasable()) && !(map.getMapLocation()[x-1][y].isPasable())
        && !(map.getMapLocation()[x][y+1].isPasable()) && !(map.getMapLocation()[x][y-1].isPasable())); /// BUGGED IF ENEMY IS IN DOORWAY
    }



    public boolean randomMove(Map map) {
        char[] direction = {'W', 'S', 'A', 'D'};
        Random rand = new Random();
        boolean temp = false;
        int counter = 0;
        while (!temp && counter < 100) {
            int randomDirection = rand.nextInt(4);
            temp = readInput(direction[randomDirection], map);
            counter++;
        }
        return temp;
    }

    public char randomChar(Map map) {
        char[] direction = {'W', 'S', 'A', 'D'};
        Random rand = new Random();
            int randomDirection = rand.nextInt(4);
            char temp = direction[randomDirection];
            return temp;
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

	@Override
	public void lvlUp() {
		this.availableStatPoint = this.STAT_PTS_PER_LVL * this.lvl;
		this.calcMaxHp();
	}

	public String description() {
		return this.getObjectName() + "\n\tHp: " + this.hp + "\n\tDmg: " + this.calcBaseDmg() + "\n\tStats: Str-" + 
	this.getStr() + " Con-" +this.getCon() + " Acc-" + this.getAccuracy() + " Def-" + this.getDefence()+ " Spd-" +this.getSpeed();
	}
}
