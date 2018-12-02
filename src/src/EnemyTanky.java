package src;

import mapObjects.Coordinate;

public class EnemyTanky extends Enemy {
	String description = "Looks like it can take a beeting";
    public EnemyTanky(Coordinate location, int imageSize, int chalengeRating) {
        super("Big Bady",imageSize,chalengeRating,"EnemyTanky.png",location);
        this.lvl = chalengeRating+1;
        this.lvlUp();
        this.hp = maxHp;
        for(int i = 0 ; i < availableStatPoint ; i++) {
            double rnd = Math.random();
            if (rnd > .40) {
                incrementDefence(1);
            }
            else if (rnd > .1) {
                incrementCon(1);
            }
            else {
                incrementStr(1);
            }
        }
        this.availableStatPoint=0;
    }
    public String description() {
    	return this + " (" + description + ")\n\t" + super.description();
    }
}
