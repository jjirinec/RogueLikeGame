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

import java.util.ArrayList;
import java.util.Random;

public class TestMap extends Application{

    static int MAX_SIZE = 800; // size of window
    static int NUM_GRIDS = 10; // number of grid boxesc
    static int gridSize = 80;
    GridPane gridPane;
    StackPane[][] stkPnes;

    public static void main(String[] args) {
        launch(args);
    }

//    public Map()
//    {
//    	gridPane = new GridPane();
//    	stkPnes = new StackPane[NUM_GRIDS][NUM_GRIDS];
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
    public GridPane getMap()
    {
    	return gridPane;
    }
    public StackPane[][] getStackPane()
    {
    	return stkPnes;
    }
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Our Map Title");

        gridPane = new GridPane();


        stkPnes = new StackPane[NUM_GRIDS][NUM_GRIDS];
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

                Floor testFloor = new Floor(80);

                stkPnes[i][j].getChildren().add(optionButtons[i][j]);
                stkPnes[i][j].getChildren().add(testFloor.getImage());

//                stkPnes[i][j].setPrefSize(85, 85);
                gridPane.add(stkPnes[i][j], i, j);
            }
        }
        for(int i=0; i<stkPnes.length; i++) {
            for (int j = 0; j < stkPnes.length; j++) {

            Wall testWall = new Wall(80);

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
        LootGenerator lootGen = new LootGenerator();
        ObstacleGenerrator obsGen = new ObstacleGenerrator();
        ArrayList<Loot> loot = new ArrayList<Loot>();
        Obstacle[] obs = new Obstacle[10];
        

        Random rand = new Random();
        for(int i = 0; i < 10; i++)/// Fills Loot Array
        {
        	int x = 1 + rand.nextInt(NUM_GRIDS-2);
        	int y = 1 + rand.nextInt(NUM_GRIDS-2);
        	Coordinate location = new Coordinate(x,y);
        	Loot item = lootGen.generate(3);
        	item.setLocation(x, y);
        	loot.add(item);
        }
        for(int i = 0; i < obs.length; i++)///Fills Obstical Array
        {
        	int x = 1 + rand.nextInt(NUM_GRIDS-2);
    		int y = 1 + rand.nextInt(NUM_GRIDS-2);
    		Coordinate location = new Coordinate(x,y);
    		obs[i] = obsGen.generate(0,location);
        }
        
        for(int i = 0; i < obs.length; i++)///Adss Obsicals to map
        {
        	if(obs[i] != null)
        	{
        		stkPnes[obs[i].getLocation().getX()][obs[i].getLocation().getY()].getChildren().add(obs[i].getImageView());
        		System.out.println(obs[i].description());
        		if(obs[i].toString().equals("Crate"))
        		{
        			Crate c = (Crate)obs[i];
        			c.printContents();
        			
        		}
        	}
        	
        }
        for(int i = 0; i < loot.size(); i++)///Adds Loot to map
        {
        	if(loot.get(i) != null && loot.get(i).getLocation() != null)
        	{
        		//System.out.println(loot.get(i).toString());
        		System.out.println(loot.get(i).description());
        	//System.out.println("Worth" +loot[i].getValue());
        		stkPnes[loot.get(i).getLocation().getX()][loot.get(i).getLocation().getY()].getChildren().add(loot.get(i).getImageView());
        	}
        }
//        System.out.println(x);(stkPnes[3][6].getChildren().toString());
/////////////////////////////////////////////////////////////////////////////////////////////////////////////        
        Character testChar = new Character(75);
        testChar.setLocation(0, randDoorLeftHeight);
        ImageView charImage = testChar.getImage();
        stkPnes[testChar.xMapVal][testChar.yMapVal].getChildren().add(charImage);


        Enemy testEnemy = new Enemy(randEnemyWidth,randEnemyHeight,75);
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
            	System.out.println("W hit");
                if (testChar.moveChar(0, -1)) {
                    stkPnes[testChar.xMapVal][testChar.yMapVal + 1].getChildren().remove(charImage);
                    stkPnes[testChar.xMapVal][testChar.yMapVal].getChildren().add(charImage);

                    stkPnes[testEnemy.xMapVal][testEnemy.yMapVal].getChildren().remove(enemyImage);
                    testEnemy.readInput(testEnemy.smartDirectionEnemy(testChar.xMapVal,testChar.yMapVal,9,randDoorRightHeight));
                    stkPnes[testEnemy.xMapVal][testEnemy.yMapVal].getChildren().add(enemyImage);
                }
            }
            else if(e.getCode() == KeyCode.SPACE ) {
                // attack
            	System.out.println("Attack");

            }
            
            ////////////////////////////////////////////////////////
            ///		Use this to test Obstical Damaging System 	///
            ///////////////////////////////////////////////////////
            else if(e.getCode() == KeyCode.X  ) {
                // attack

            	
            	obs[0].damage(5,this);
            	obs[1].damage(5, this);

            	System.out.println(obs[0].description());
            	System.out.println(obs[1].description());

            }
          
        });


    }

}
