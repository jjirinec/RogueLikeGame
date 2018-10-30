package src;

import mapObjects.*;
import src.viewObjects.HealthGlobe;

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
import javafx.scene.layout.BorderPane;
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
		//primaryStage.setFullScreen(true);
		
		ctr.startPlay();
		
		
		
		
	}
	
	private BorderPane setUpForFront()
	{
		BorderPane layout = new BorderPane();
		layout.setCenter(map.getMap());
		layout.setLeft(setUpLeftSide());
		layout.setRight(setUpRightSide());
		layout.setBottom(setUpBottom());
		return layout;
	}
	private VBox setUpLeftSide()
	{
		VBox leftSideView = new VBox();
		leftSideView.setPrefWidth((screenSize.getWidth() - mapWidth)/2);
		return leftSideView;
	}
	private VBox setUpRightSide() {
		rightSideView = new VBox();
		rightSideView.setPrefWidth((screenSize.getWidth() - mapWidth)/2);
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0,10,0,10));
		
		//StackPane healthGlobe = getHealthGlobe();
		healthGlobe = new HealthGlobe(175);
		//StackPane healthGlobe = 
		healthGlobe.getHealthGlob().setAlignment(Pos.TOP_CENTER);
		grid.add(healthGlobe.getHealthGlob(),3,2);
		
//		Text str = new Text("Str: " + ctr.player.getStr());
//		grid.add(str, 4, 6);
		grid.add(getCharStats(), 3, 4);getCharStats();
		grid.add(getActionGrid(),3, 7);
		rightSideView.getChildren().add(grid);
		//grid.add(this.getCharStats(), 3, 4);
		return rightSideView;
	}
	private GridPane getCharStats() {
		GridPane statGrid = new GridPane();
		statGrid.setVgap(10);
		statGrid.setHgap(10);
		statGrid.setPadding(new Insets(0,10,0,10));
		Text str = statText("Strength: ");
		Text strValue = statText("" + ctr.player.getStr());
		statGrid.add(str, 0, 7);
		statGrid.add(strValue, 1, 7);
		Text dex = statText("Dexterity: ");
		Text dexValue = statText("" + ctr.player.getDex());
		statGrid.add(dex, 0, 8);
		statGrid.add(dexValue, 1, 8);
		Text con = statText("Constatution:");
		Text conValue = statText(""+ ctr.player.getCon());
		statGrid.add(con, 0, 9);
		statGrid.add(conValue, 1, 9);
		Text mgk = statText("Magic:");
		Text mgkValue = statText(""+ctr.player.getMgk());
		statGrid.add(mgk, 0, 10);
		statGrid.add(mgkValue, 1, 10);
		Text defence = statText("Defense:");
		Text defenceValue = statText(""+ctr.player.getDefence());
		statGrid.add(defence, 0, 11);
		statGrid.add(defenceValue, 1, 11);
		Text speed = statText("Speed:");
		Text speedValue = statText(""+ctr.player.getSpeed());
		statGrid.add(speed, 0, 12);
		statGrid.add(speedValue, 1, 12);

		return statGrid;
	}
	private GridPane getActionGrid() {
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0,10,0,10));
		Text actions = statText("Action Points:");
		Text actionValue = statText(""+ctr.player.getCurentActions());
		grid.add(actions, 0, 0);
		grid.add(actionValue, 1, 0);
		Text moveCost = statText("Movment Cost:");
		Text moveCostValue = statText(""+ctr.player.getMoveCost());
		grid.add(moveCost, 0, 1);
		grid.add(moveCostValue, 1, 1);
		Text attackCost = statText("Attack Cost:");
		Text attackCostValue = statText(""+ctr.player.getAttackCost());
		grid.add(attackCost, 0, 2);
		grid.add(attackCostValue, 1, 2);	
		return grid;
	}
//	private GridPane set
	private Text statText(String txt) {
		Text text = new Text(txt);
		text.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		return text;	
	}
//	public void updateStatGrid() {
//		GridPane grid = (GridPane) this.rightSideView.getChildren().get(0);
//		grid.getChildren().remove(1);
//		grid.add(this.getCharStats(), 3, 4);
//		
//		
//	}
//	public void updateActionGrid() {
//		GridPane grid = (GridPane) this.rightSideView.getChildren().get(0);
//		grid.getChildren().remove(2);
//		grid.add(this.getActionGrid(), 3, 7);
//	}
	
	public void updatePlayerInfo() {
		GridPane grid = (GridPane) this.rightSideView.getChildren().get(0);
		grid.getChildren().remove(1);
		grid.getChildren().remove(1);
		grid.add(this.getCharStats(), 3, 4);
		grid.add(this.getActionGrid(), 3, 7);
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
			this.updatePlayerInfo();
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
