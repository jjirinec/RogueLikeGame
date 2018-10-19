package src;

import mapObjects.*;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.plaf.basic.BasicBorders.RolloverButtonBorder;
import com.sun.javafx.tk.Toolkit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class View extends Application implements Observer{
	
	Dimension screenSize;
	int mapColums = 10;
	int mapRows = 10;
	int gridSize = 60;
	int mapWidth = mapColums * gridSize;
	int mapHight = mapRows * gridSize;
	
	Controler ctr;
	StackPane forANDback;
	BorderPane layout;
	Map map;
	Character player;
	Text hudMsg;
	ScrollPane hud;
	
	public static void main(String[]args){	launch(args);	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		
		ctr = new Controler(this);
		forANDback = new StackPane();
		map = new Map(mapRows,mapColums,gridSize,2,ctr.player,2);
		
		
		
		///ForFront///
		layout = setUpForFront();
		
		///Background///
		Rectangle bgImage = new Rectangle(screenSize.getWidth(),screenSize.getHeight());
		bgImage.setFill(Color.DARKCYAN);
		Pane background = new Pane();

		
		//background.setPrefSize(screenSize.getWidth(),screenSize.getHeight());
		//background.autosize();
		background.getChildren().add(bgImage);
//		layout.setLeft(background);
		
		forANDback.getChildren().addAll(background,layout);
		forANDback.setMaxSize(500, 500);
		Scene root = new Scene(forANDback);
		root.setOnKeyPressed(ctr);
		primaryStage.setScene(root);
		primaryStage.show();
		primaryStage.setFullScreen(true);
		
		
//		ctr.gameLoop();
		
		
		
	}
	
	private BorderPane setUpForFront()
	{
		BorderPane layout = new BorderPane();
		layout.setCenter(map.getMap());
		VBox leftSideView = new VBox();
		VBox rightSideView = new VBox();
		HBox bottom = new HBox();
		setUpHud();
		bottom.getChildren().add(hud);
		bottom.setAlignment(Pos.CENTER);
		bottom.setPrefHeight(screenSize.getHeight()- mapHight-100);
		bottom.setPrefWidth(mapWidth);
		leftSideView.setPrefWidth((screenSize.getWidth() - mapWidth)/2);
		rightSideView.setPrefWidth((screenSize.getWidth() - mapWidth)/2);
		layout.setLeft(leftSideView);
		layout.setRight(rightSideView);
		layout.setBottom(bottom);
		return layout;
	}
	private void setUpHud()
	{
		hud = new ScrollPane();
		hud.setPrefHeight(screenSize.getHeight() - mapHight);
		
		hud.setPrefViewportWidth(mapWidth);
		hudMsg = new Text("HUD\nTest me by pressing wasd, arrow, space, tab, or g");
		hudMsg.setFill(Color.GREEN);
		
		hud.setContent(hudMsg);
		
		BackgroundFill[] bfill = {new BackgroundFill(Color.BLACK,new CornerRadii(20),new Insets(5))};
		Background hudBackground = new Background(bfill);
		
		//hudBackground.setFill(Color.BLACK);
		hud.setBackground(hudBackground);
		
	}
	
	public void setNewStage()
	{
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		String observedMsg = (String)arg1;
		System.out.println("observed "+observedMsg);
		hudMsg.setText(observedMsg += "\n" +hudMsg.getText());
		//hud.setContent(new Text(observedMsg));
		
//		hudMsg. +="\nobservedMsg";
	}
	
}
