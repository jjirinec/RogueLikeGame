package src;
import mapObjects.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.Random;

public class Map extends Application{

    static int MAX_SIZE = 800; // size of window
    static int NUM_GRIDS = 10; // number of grid boxes
    GridPane gridPane;

    public static void main(String[] args) {
        launch(args);
    }

//    public Map()
//    {
//    	gridPane = new GridPane();
//    	StackPane[][] stkPnes = new StackPane[NUM_GRIDS][NUM_GRIDS];
//        Button[][] optionButtons = new Button[NUM_GRIDS][NUM_GRIDS];
//
//        // 2D array of stkPnes with value of 5,5
//
//        //Column is a vertical line and row is a horizontal line
//        //Two FOR loops used for creating 2D array of StackPanes with values i,j
//
//        int randEnemyHeight = (int)(Math.random() * (NUM_GRIDS - 3) + 2);
//        int randEnemyWidth = (int) (Math.random() * (NUM_GRIDS - 5) + 4);
//
//        int randDoorLeftHeight = (int)(Math.random() * (NUM_GRIDS - 2) + 1);
//        int randDoorRightHeight = (int) (Math.random() * (NUM_GRIDS - 2) + 1);
//
//
//
//        for(int i=0; i<stkPnes.length; i++){
//            for(int j=0; j<stkPnes.length;j++){
//                //Initializing StackPanes with values i,j
//                stkPnes[i][j] = new StackPane();
//
//                optionButtons[i][j] = new Button();
//                optionButtons[i][j].setStyle("-fx-focus-color : red; -fx-faint-focus-color: red;");
//                optionButtons[i][j].setMaxHeight(78);
//                optionButtons[i][j].setMaxWidth(79);
//                //Add test Floor to all tiles
//
//                Floor testFloor = new Floor();
//
//                stkPnes[i][j].getChildren().add(optionButtons[i][j]);
//                stkPnes[i][j].getChildren().add(testFloor.getImage());
//
//                stkPnes[i][j].setPrefSize(85, 85);
//                gridPane.add(stkPnes[i][j], i, j);
//            }
//        }
//        for(int i=0; i<stkPnes.length; i++) {
//            for (int j = 0; j < stkPnes.length; j++) {
//
//            Wall testWall = new Wall();
//
//            if(i==0 && j != randDoorLeftHeight || i == NUM_GRIDS - 1 && j != randDoorRightHeight){
//                stkPnes[i][j].getChildren().add(testWall.getImage());
//            }
//            if(j==0 || j == NUM_GRIDS - 1 ){ // puts walls on the bottom and top row
//                stkPnes[i][j].getChildren().add(testWall.getImage());
//            }
//            }
//        }
//    }
//    
//    public GridPane getMap()
//    {
//    	return gridPane;
//    }
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Our Map Title");

        gridPane = new GridPane();


        StackPane[][] stkPnes = new StackPane[NUM_GRIDS][NUM_GRIDS];
        Button[][] optionButtons = new Button[NUM_GRIDS][NUM_GRIDS];

        // 2D array of stkPnes with value of 5,5

        //Column is a vertical line and row is a horizontal line
        //Two FOR loops used for creating 2D array of StackPanes with values i,j

        int randEnemyHeight = (int)(Math.random() * (NUM_GRIDS - 3) + 2);
        int randEnemyWidth = (int) (Math.random() * (NUM_GRIDS - 5) + 4);

        int randDoorLeftHeight = (int)(Math.random() * (NUM_GRIDS - 2) + 1);
        int randDoorRightHeight = (int) (Math.random() * (NUM_GRIDS - 2) + 1);



        for(int i=0; i<stkPnes.length; i++){
            for(int j=0; j<stkPnes.length;j++){
                //Initializing StackPanes with values i,j
                stkPnes[i][j] = new StackPane();

                optionButtons[i][j] = new Button();
                optionButtons[i][j].setStyle("-fx-focus-color : red; -fx-faint-focus-color: red;");
                optionButtons[i][j].setMaxHeight(78);
                optionButtons[i][j].setMaxWidth(79);
                //Add test Floor to all tiles

                Floor testFloor = new Floor();

                stkPnes[i][j].getChildren().add(optionButtons[i][j]);
                stkPnes[i][j].getChildren().add(testFloor.getImage());

                stkPnes[i][j].setPrefSize(85, 85);
                gridPane.add(stkPnes[i][j], i, j);
            }
        }
        for(int i=0; i<stkPnes.length; i++) {
            for (int j = 0; j < stkPnes.length; j++) {

            Wall testWall = new Wall();

            if(i==0 && j != randDoorLeftHeight || i == NUM_GRIDS - 1 && j != randDoorRightHeight){
                stkPnes[i][j].getChildren().add(testWall.getImage());
            }
            if(j==0 || j == NUM_GRIDS - 1 ){ // puts walls on the bottom and top row
                stkPnes[i][j].getChildren().add(testWall.getImage());
            }
            }
        }
///////////////////////////////////////////
///			MapObject Test Aria			///
///////////////////////////////////////////
        Loot[] loot = new Loot[6];
        Obstacle[] obs = new Obstacle[4];
        
        obs[0] = new Bolder(5, new Coordinate(3,2));
       
        loot[0] = new Sword(1,0,new Coordinate(4,8));
        loot[1] = new Bow(0,0,new Coordinate(4,4));
        loot[2] = new HealthPotion(10,new Coordinate(3,5),10);
        loot[3] = new SpeedPotion(2,new Coordinate(2,5),10);
        
        loot[4] = new Axe(1,0,new Coordinate(5,8));
        loot[5] = new Dagger(1,0,new Coordinate(3,2
        		));
        
        
        for(int i = 0; i < obs.length; i++)
        {
        	if(obs[i] != null)
        		stkPnes[obs[i].getLocation().getX()][obs[i].getLocation().getY()].getChildren().add(obs[i].getImageView());
        }
        for(int i = 0; i < loot.length; i++)
        {
        	System.out.println(loot[i].toString());
        	System.out.println(loot[i].description());
        	//System.out.println("Worth" +loot[i].getValue());
        	stkPnes[loot[i].getLocation().getX()][loot[i].getLocation().getY()].getChildren().add(loot[i].getImageView());
        }
//        System.out.println(x);(stkPnes[3][6].getChildren().toString());
/////////////////////////////////////////////////////////////////////////////////////////////////////////////        
        Character testChar = new Character(randDoorLeftHeight);
        ImageView charImage = testChar.getImage();
        stkPnes[testChar.xMapVal][testChar.yMapVal].getChildren().add(charImage);


        Enemy testEnemy = new Enemy(randEnemyWidth,randEnemyHeight);
        ImageView enemyImage = testEnemy.getImage();
        stkPnes[testEnemy.xMapVal][testEnemy.yMapVal].getChildren().add(enemyImage);


        primaryStage.setWidth(MAX_SIZE);
        primaryStage.setHeight(MAX_SIZE);
        //Adding GridPane to the scene
        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();
///////////////////////////////////////////////////////////////////////////////

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.D) {
                if(testChar.moveChar(1,0)){
                    stkPnes[testChar.xMapVal-1][testChar.yMapVal].getChildren().remove(charImage);
                    stkPnes[testChar.xMapVal][testChar.yMapVal].getChildren().add(charImage);

                    stkPnes[testEnemy.xMapVal][testEnemy.yMapVal].getChildren().remove(enemyImage);
                    testEnemy.readInput(testEnemy.smartDirectionEnemy(testChar.xMapVal,testChar.yMapVal,9,randDoorRightHeight));
                    stkPnes[testEnemy.xMapVal][testEnemy.yMapVal].getChildren().add(enemyImage);
                }
            }
            else if (e.getCode() == KeyCode.A) {
                if (testChar.moveChar(-1, 0)) {
                    stkPnes[testChar.xMapVal + 1][testChar.yMapVal].getChildren().remove(charImage);
                    stkPnes[testChar.xMapVal][testChar.yMapVal].getChildren().add(charImage);

                    stkPnes[testEnemy.xMapVal][testEnemy.yMapVal].getChildren().remove(enemyImage);
                    testEnemy.readInput(testEnemy.smartDirectionEnemy(testChar.xMapVal, testChar.yMapVal, 9, randDoorRightHeight));
                    stkPnes[testEnemy.xMapVal][testEnemy.yMapVal].getChildren().add(enemyImage);
                }
            }
            else if (e.getCode() == KeyCode.S) {
                if(testChar.moveChar(0,1)){
                    stkPnes[testChar.xMapVal][testChar.yMapVal-1].getChildren().remove(charImage);
                    stkPnes[testChar.xMapVal][testChar.yMapVal].getChildren().add(charImage);

                    stkPnes[testEnemy.xMapVal][testEnemy.yMapVal].getChildren().remove(enemyImage);
                    testEnemy.readInput(testEnemy.smartDirectionEnemy(testChar.xMapVal,testChar.yMapVal,9,randDoorRightHeight));
                    stkPnes[testEnemy.xMapVal][testEnemy.yMapVal].getChildren().add(enemyImage);
                }
            }
            else if (e.getCode() == KeyCode.W) {
                if (testChar.moveChar(0, -1)) {
                    stkPnes[testChar.xMapVal][testChar.yMapVal + 1].getChildren().remove(charImage);
                    stkPnes[testChar.xMapVal][testChar.yMapVal].getChildren().add(charImage);

                    stkPnes[testEnemy.xMapVal][testEnemy.yMapVal].getChildren().remove(enemyImage);
                    testEnemy.readInput(testEnemy.smartDirectionEnemy(testChar.xMapVal,testChar.yMapVal,9,randDoorRightHeight));
                    stkPnes[testEnemy.xMapVal][testEnemy.yMapVal].getChildren().add(enemyImage);
                }
            }
            else if(e.getCode() == KeyCode.SPACE) {
                // attack

            }
        });


    }

}
