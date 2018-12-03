package src;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.scene.control.TextField;
import mapObjects.*;
import mapObjects.Container;
import src.viewObjects.AnimationLayer;
import src.viewObjects.ContainerView;
import src.viewObjects.HealthGlobe;
import src.viewObjects.InventoryView;
import src.viewObjects.LevelUpScene;
import src.viewObjects.PlayerInfoView;
import src.viewObjects.ScoreView;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import src.Character;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class View extends Application implements Observer{
	
	Dimension screenSize;
	int mapColums = 10;
	int mapRows = 10;
	int gridSize = 60;
	int mapWidth = mapColums * gridSize;
	int mapHight = mapRows * gridSize;
	
	AnimationLayer animationLayer;
	
	Controler ctr;
	StackPane forANDback;
	BorderPane layout;
	VBox rightSideView;
	Map map;
	StackPane mapStack;
	LevelUpScene levelUpView;
	///Character Info Variables
	HealthGlobe healthGlobe;
	PlayerInfoView playerInfoView;
	InventoryView playerInventoryView;
//	private Text actionsValue;
//	private Text moveCost;
//	private Text attackCost;
	VBox hudBox;
	Text hudMsg;
	ScrollPane hud;
	Stage mainStage;
	
	public static void main(String[]args){	launch(args);	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		mainStage = new Stage();
		screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		
		ctr = new Controler(this);
		levelUpView = new LevelUpScene(ctr.player,gridSize*mapColums, gridSize*mapRows,this);
		forANDback = new StackPane();
		map = new Map();
		//map = new Map(mapRows,mapColums,gridSize,ctr.player);
		mapStack = new StackPane();
		animationLayer = new AnimationLayer(gridSize*mapColums,gridSize,mapStack);
		
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
		mainStage.setScene(root);
		mainStage.show();
		mainStage.sizeToScene();
		mainStage.setFullScreen(true);
		
		//ctr.startPlay();

	}
	public Controler getControler() {
		return ctr;
	}
	public void showLevleUpScene() {
		//this.layout.setCenter(this.levelUpView.levelUp());
		levelUpView = new LevelUpScene(ctr.player,gridSize*mapColums, gridSize*mapRows,this);
		VBox center = (VBox)layout.getCenter();
		center.getChildren().remove(0);
		center.getChildren().add(0, levelUpView.levelUp());
	}
	
	private BorderPane setUpForFront()
	{
		BorderPane layout = new BorderPane();
		VBox center = new VBox();
		
		center.getChildren().add(levelUpView.levelUp());
		center.getChildren().add(setUpBottom());
		layout.setCenter(center);
		
		this.playerInventoryView = new InventoryView(ctr.player,(screenSize.getWidth() - mapWidth)/2,map);
		layout.setLeft(playerInventoryView.getView());
		
		playerInfoView = new PlayerInfoView(ctr.player,(screenSize.getWidth() - mapWidth)/2);
		
		layout.setRight(playerInfoView.getView());
		//layout.setRight(setUpRightSide());
//		layout.setBottom(setUpBottom());
		
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
		BackgroundFill backGroundFill = new BackgroundFill(Color.BLACK, new CornerRadii(25), new Insets(20,20,20,20) );
		Background backGround = new Background(backGroundFill);
		Pane hudPane = new Pane();
		
		hudPane.getChildren().add(hud);
		hudPane.setBackground(backGround);
		bottom.getChildren().add(hudPane);
		bottom.setAlignment(Pos.TOP_CENTER);
		bottom.setPrefHeight(screenSize.getHeight()- mapHight-100);
		bottom.setPrefWidth(mapWidth);
		return bottom;
	}

	private void setUpHud()
	{
		hud = new ScrollPane();
		hud.setFitToHeight(true);
		hud.setPrefViewportWidth(mapWidth);
//		hud.setOpacity(.6);
		hudBox = new VBox();
		hudBox.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(10),new Insets(0,0,0,0))));
		hudBox.setPadding(new Insets(10,10,10,10));
		//hudBox.getChildren().add(hudMsg);
		//hud.setContent(hudMsg);
		hud.setContent(hudBox);
		hud.setPrefHeight(screenSize.getHeight() - mapHight - 100);
		hudBox.setPrefHeight(screenSize.getHeight() - mapHight - 100);
		hudBox.setMinWidth(mapWidth);
	}
	public void setScoreScene() {
		ScoreView scoreView = new ScoreView(ctr.player, map, this, this.gridSize*this.mapRows);
		BorderPane scorePane = scoreView.getScoreView();

		BorderPane layout = (BorderPane)this.forANDback.getChildren().get(1);
//		//layout.getChildren().remove(3);
		VBox center = (VBox)layout.getCenter();
		center.getChildren().remove(0);
		center.getChildren().add(0, scorePane);
	}
	public void nextMap(){
		map = new Map(mapRows,mapColums,gridSize,ctr.player,this);
		if(mapStack.getChildren().size() == 1) {
			mapStack.getChildren().remove(0);
		}
		mapStack.getChildren().add(0,map.getMap());
		BorderPane layout = (BorderPane)this.forANDback.getChildren().get(1);
		VBox center = (VBox)layout.getCenter();
		center.getChildren().remove(0);
		center.getChildren().add(0, mapStack);
	}

	public void endScreen(){
		BorderPane endScreen = new BorderPane();
		endScreen.setCenter(new VBox(new TextField("INFINITIUM"),new TextField(String.valueOf(map.getDamageDealt())),new TextField(String.valueOf(map.getTotalActions()))));
		BorderPane layout = (BorderPane)this.forANDback.getChildren().get(1);
		VBox center = (VBox)layout.getCenter();
		//center.getChildren().remove(0,2);
		//center.getChildren().add(0, endScreen);

		ScoreView scoreView = new ScoreView(ctr.player, map, this, this.gridSize*this.mapRows);
		VBox stats = scoreView.scoreTextBlock();
		endScreen.setBottom(stats);
		center.getChildren().remove(0);
		center.getChildren().add(0, endScreen);
	}

	public void setNewStage()
	{
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("Msg Receved: " + arg1.toString());
		// TODO Auto-generated method stub
		if(arg1 instanceof String) {
			String observedMsg = (String)arg1;
			if(observedMsg.equals("Dead")){
				endScreen();
			}
			else if(observedMsg.equals("ActionUpdate") || observedMsg.equals("StatUpdate")) {
				//this.updatePlayerInfo();
				this.playerInfoView.updatStatActionBlocks();
			}
			else if(observedMsg.equals("LootChange")) {
				this.playerInventoryView.updateInventory();
				if(!ctr.player.canAct())
					ctr.enemyTurns();
			}
			else if(observedMsg.equals("Hp Change")) {
				this.playerInfoView.updateHealthGlobe(ctr.player.getHpPresentage());
			}
			else if(observedMsg.equals("EquipmentChange")) {
				this.playerInventoryView.updateInventory();
				this.playerInventoryView.updateEquipedView();
				this.playerInfoView.updateAttackDeffInfo();
			}
			else if(observedMsg.equals("Attack Type Change")) {
				System.out.println("Attack change Msg Receved");
				this.playerInfoView.updateAttackDeffInfo();
			}
			else if(observedMsg.equals("Exit")) {
				setScoreScene();
			}
		}
		else {
			if(arg1 instanceof Text) {
				Text msg = (Text)arg1;
				hudBox.getChildren().add(0,msg);
			}
		}
	}
	
	public void containerView(Container container,Character player,Map map) {
		ContainerView cView = new ContainerView(container,player,map);

		Scene conatinerView = new Scene(cView.getContainerView());
		
		Stage containerStage = new Stage();
		containerStage.setOnCloseRequest(new EventHandler<WindowEvent>(){
			@Override
			public void handle(WindowEvent arg0) {
				System.out.println("ContainerView Closed");
				map.getMap().requestFocus();
			}
		});
		containerStage.initOwner(mainStage);
		containerStage.setTitle(container.toString());
		containerStage.setScene(conatinerView);
		containerStage.show();
		
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
