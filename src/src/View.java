package src;

import mapObjects.*;
import src.viewObjects.HealthGlobe;
import src.viewObjects.PlayerInfoView;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
	VBox rightSideView;
	Map map;
	
	///Character Info Variables
	HealthGlobe healthGlobe;
	PlayerInfoView playerInvoeView;
//	private Text actionsValue;
//	private Text moveCost;
//	private Text attackCost;
	
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
		ImageView backGround = new ImageView(new Image("images/Character/background.jpg"));
		
		//background.setPrefSize(screenSize.getWidth(),screenSize.getHeight());
		//background.autosize();
		background.getChildren().add(backGround);
//		layout.setLeft(background);
		
		forANDback.getChildren().addAll(background,layout);
		forANDback.setMaxSize(500, 500);
		Scene root = new Scene(forANDback);
		root.setOnKeyPressed(ctr);
		primaryStage.setScene(root);
		primaryStage.show();
		//primaryStage.setFullScreen(true);
		
		ctr.startPlay();
		
		
		
		
	}
	
	private BorderPane setUpForFront()
	{
		BorderPane layout = new BorderPane();
		layout.setCenter(map.getMap());
		
		
		layout.setLeft(setUpLeftSide());
		
		playerInvoeView = new PlayerInfoView(ctr.player,(screenSize.getWidth() - mapWidth)/2);
		
		layout.setRight(playerInvoeView.getView());
		//layout.setRight(setUpRightSide());
		layout.setBottom(setUpBottom());
		return layout;
	}
	private VBox setUpLeftSide()
	{
		VBox leftSideView = new VBox();
		leftSideView.setPrefWidth((screenSize.getWidth() - mapWidth)/2);
		return leftSideView;
	}


	private ImageView getImageView(String imageFile,double size) {
		Image image = new Image(imageFile);
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(size);
		imageView.setFitWidth(size);
		return imageView;
	}
	private HBox setUpBottom() {
		HBox bottom = new HBox();
		setUpHud();
		bottom.getChildren().add(hud);
		bottom.setAlignment(Pos.TOP_CENTER);
		bottom.setPrefHeight(screenSize.getHeight()- mapHight-100);
		bottom.setPrefWidth(mapWidth);
		return bottom;
	}

	private void setUpHud()
	{
		hud = new ScrollPane();
		hud.setPrefHeight(screenSize.getHeight() - mapHight);
		
		hud.setPrefViewportWidth(mapWidth);

//		Background backgroud = new Background(new BackgroundFill(Paint.valueOf(Color.AQUA),new CornerRadii(20),new Insets(5.5)));
//		hud.setBackground(backgroud);
		hudMsg = new Text("HUD\nTest me by pressing wasd, arrow, space, tab, or g");
		hudMsg.setFill(Color.GREEN);
		
		hud.setContent(hudMsg);
		
		BackgroundFill[] bfill = {new BackgroundFill(Color.BLACK,new CornerRadii(20),new Insets(5))};
		Background hudBackground = new Background(bfill);
		
		//hudBackground.setFill(Color.BLACK);
		hud.setBackground(hudBackground);
		hud.setPrefHeight(screenSize.getHeight() - mapHight - 100);
	}
	
	public void setNewStage()
	{
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		String observedMsg = (String)arg1;
		if(observedMsg.equals("ActionUpdate")) {
			//this.updatePlayerInfo();
			this.playerInvoeView.updatStatActionBlocks();
		}
		System.out.println("observed "+observedMsg);
		hudMsg.setText(observedMsg += "\n" +hudMsg.getText());
		//hud.setContent(new Text(observedMsg));
		
//		hudMsg. +="\nobservedMsg";
	}
	/*
	 * Because I hate typing System.out.println()
	 */
	public void print(String x){
		System.out.println(x);
	}
	public void print(int x){
		System.out.println(x);
	}
}
