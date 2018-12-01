package src;

import mapObjects.Coordinate;

public class EnemyStrong extends Enemy {
    public EnemyStrong(Coordinate location, int imageSize, int chalengeRating) {
        super(imageSize,chalengeRating,"EnemyStrong.png",location);
        this.lvl = chalengeRating+1;
        this.lvlUp();
        this.hp = maxHp;
        for(int i = 0 ; i < availableStatPoint ; i++) {
            double rnd = Math.random();
            if (rnd > .50) {
                incrementStr(1);
            }
            else if (rnd > .3) {
                incrementSpeed(1);
            }
            else {
                incrementCon(1);
            }
        }
        this.availableStatPoint=0;
    }
}
