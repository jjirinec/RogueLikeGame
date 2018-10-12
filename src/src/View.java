package src;

import mapObjects.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class View extends Application{
	Controler ctr;
	BorderPane layout;
	Map map;
	
	public static void main(String[]args){	launch(args);	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		ctr = new Controler(this);
		layout = new BorderPane();
		map = new Map();
		
		layout.setCenter(map.getMap());
		
		
		Scene root = new Scene(layout);
		
		primaryStage.setScene(root);
		primaryStage.show();
		
		
		ctr.gameLoop();
		
		
		
	}
	
	public void setNewStage()
	{
		
	}
	
}
