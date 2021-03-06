package src;

import mapObjects.Coordinate;

public class EnemyStrong extends Enemy {
	String description = "Looks like it can dish out some Damage";
    public EnemyStrong(Coordinate location, int imageSize, int chalengeRating) {
        super("Mr. Big Arms",imageSize,chalengeRating,"EnemyStrong.png",location);
        this.lvl = chalengeRating+1;
        this.lvlUp();
        this.hp = maxHp;
        for(int i = 0 ; i < availableStatPoint ; i++) {
            double rnd = Math.random();
            if (rnd > .40) {
                incrementStr(1);
            }
            else if (rnd > .2) {
                incrementSpeed(1);
            }
            else {
                incrementCon(1);
            }
        }
        this.availableStatPoint=0;
    }
    
    public String description() {
    	return this + " (" + description + ")\n\t" + super.description();
    }
}
