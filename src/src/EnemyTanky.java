package src;

import mapObjects.Coordinate;

public class EnemyTanky extends Enemy {
    public EnemyTanky(Coordinate location, int imageSize, int chalengeRating) {
        super("The Big One",imageSize,chalengeRating,"EnemyTanky.png",location);
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
}
