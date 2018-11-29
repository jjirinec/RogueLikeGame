package src;

import mapObjects.Coordinate;

public class EnemySpeedy extends Enemy {
    public EnemySpeedy(Coordinate location, int imageSize, int chalengeRating) {
        super(imageSize,chalengeRating,"EnemySpeedy.png",location);
        this.lvl = chalengeRating;
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
                incrementCon(1);
            }
        }
    }
    }

