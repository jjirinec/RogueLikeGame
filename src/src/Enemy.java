package src;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Enemy extends Tile {
    int xMapVal;
    int yMapVal;

    public ImageView getImage() {
        Image wallG = new Image("TempChar.png");
        ImageView wallView = new ImageView();
        wallView.setImage(wallG);
        wallView.setFitHeight(75);
        wallView.setFitWidth(75);
        return wallView;
    }

    public Enemy(int xVal, int yVal,int tileSize) {
    	super(tileSize);
        this.background = getImage();
        this.isMovable = true;
        this.xMapVal =xVal;
        this.yMapVal = yVal;
    }

    char smartDirectionEnemy(int Xp, int Yp, int Xd, int Yd){
        double totalD_P =calculateD(xMapVal,yMapVal,Xp,Yp);
        double totalDW = calculateD(xMapVal,yMapVal -1,Xd,Yd) + calculateD(xMapVal,yMapVal-1,Xp,Yp);
        double totalDS = calculateD(xMapVal,yMapVal +1,Xd,Yd) + calculateD(xMapVal,yMapVal+1,Xp,Yp);
        double totalDA = calculateD(xMapVal-1,yMapVal,Xd,Yd) + calculateD(xMapVal-1,yMapVal,Xp,Yp);
        double totalDD = calculateD(xMapVal+1,yMapVal,Xd,Yd) + calculateD(xMapVal+1,yMapVal,Xp,Yp);

        if (totalD_P == 1){
            System.out.println("ENEMY HIT");
            return 'H';
        }
        else if(totalDW < totalDS && totalDW < totalDA && totalDW < totalDD){
            return('W');
        }
        else if(totalDD < totalDS && totalDD < totalDA && totalDD < totalDW){
            return('D');
        }
        else if(totalDS < totalDW && totalDS < totalDA && totalDS < totalDD){
            return('S');
        }
        else if(totalDA < totalDS && totalDA < totalDW && totalDA < totalDD){
            return('A');
        }
        else{
            return('A');
        }
    }

    /**
     * reads char input built for smartDirection enemy
     * @param input W,S,A,D to move, other to return false
     * @return truth if moves, false if doesnt move
     */
    boolean readInput(char input){
        if(input == 'W'){
            moveEnemy(0,-1);
            return true;
        }
        else if(input == 'S'){
            moveEnemy(0,1);
            return true;
        }
        else if(input == 'A'){
            moveEnemy(-1,0);
            return true;
        }
        else if(input == 'D'){
            moveEnemy(1,0);
            return true;
        } else if (input == 'H') {
            //hit here
        }
        return false;
    }

    boolean moveEnemy(int x, int y) {
        if ((x + xMapVal < Map.NUM_GRIDS - 1 && x + xMapVal > 0) && (y + yMapVal < Map.NUM_GRIDS - 1 && y + yMapVal > 0)) {
            xMapVal += x;
            yMapVal += y;
            return true;
        } else {
            return false;
        }
    }

    double calculateD(int x1,int y1, int x2, int y2){
        return Math.sqrt(Math.pow((x1-x2),2)+Math.pow((y1-y2),2));
    }
}
