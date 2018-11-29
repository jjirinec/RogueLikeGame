package src;

import mapObjects.Coordinate;

public class EnemyTanky extends Enemy {
    public EnemyTanky(Coordinate location, int imageSize, int chalengeRating) {
        super(imageSize,chalengeRating,"EnemyTanky.png",location);
        this.lvl = chalengeRating;
        this.lvlUp();
        this.hp = maxHp;
        for(int i = 0 ; i < availableStatPoint ; i++) {
            double rnd = Math.random();
            if (rnd > .50) {
                incrementDefence(1);
            }
            else if (rnd > .3) {
                incrementCon(1);
            }
            else {
                incrementStr(1);
            }
        }
    }
}
