package src;

import mapObjects.Coordinate;

public class EnemySpeedy extends Enemy {
	String discription = "Looks fast and agile.";
    public EnemySpeedy(Coordinate location, int imageSize, int chalengeRating) {
        super("Speedster",imageSize,chalengeRating,"EnemySpeedy.png",location);
        this.lvl = chalengeRating+1;
        this.lvlUp();
        this.hp = maxHp;
        for(int i = 0 ; i < availableStatPoint ; i++) {
            double rnd = Math.random();
            if (rnd > .50) {
                incrementSpeed(1);
            }
            else if (rnd > .3) {
                incrementStr(1);
            }
            else {
                incrementAccuracy(1);
            }
        }
        this.availableStatPoint=0;
    }
    
    public String description() {
    	return this + " (" + discription + ")\n\t" + super.description();
    }
}//End of Class

