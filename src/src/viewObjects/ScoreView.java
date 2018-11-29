package src.viewObjects;

import src.View;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import src.Character;
import src.Map;

public class ScoreView {
	View view;
	Character player;
	Map map;
	int prefSize;
	BorderPane mainView;
	
	public ScoreView(Character player, Map map, View view, int size) {
		this.view = view;
		this.map = map;
		this.prefSize = size;
	}
	
	public BorderPane getScoreView() {
		setUpScoreView();
		updateScore();
		return mainView;
	}
	
	private void setUpScoreView() {
		mainView = new BorderPane();
		mainView.setBackground(background(Color.BLACK,25,0));
		mainView.setMinSize(prefSize, prefSize);
		mainView.setTop(header("Room # " + Map.ROOM_NUMBER + " Compleat",35));
		mainView.setCenter(splitView());
		mainView.setBottom(continueButton());
		
	}
	private HBox splitView() {
		HBox splitView = new HBox();
		splitView.getChildren().add(scorePane());
		splitView.getChildren().add(devider());
		splitView.getChildren().add(expPane());
		return splitView;
	}
	private VBox scorePane() {
		VBox scorePane = new VBox();
		scorePane.setPadding(insets(5));
		scorePane.setSpacing(10);
		scorePane.setPrefWidth(prefSize/2-5/2);
		scorePane.getChildren().add(header("Room Stats",20));
		scorePane.getChildren().add(scoreTextBlock());
		
		return scorePane;
	}
	private VBox expPane() {
		VBox expPane = new VBox();
		expPane.setPadding(insets(5));
		expPane.setSpacing(10);
		expPane.setPrefWidth(prefSize/2-5/2);
		expPane.getChildren().add(header("Experiance", 20));
		expPane.getChildren().add(expTextBlock());
		return expPane;
	}
	private VBox expTextBlock() {
		VBox expBlock = new VBox();
		//expBlock.setMinWidth(prefSize/2 - 5/2 - 20);
		expBlock.setPadding(insets(10));
		expBlock.setBackground(background(Color.GRAY,20,0));
		HBox roomRating = textBlock("Room Rating", ""+map.getRoomRating());
		HBox enemysKilled = textBlock("Enemys Killed", "Still need");
		HBox lootColected = textBlock("Loot Colected", "Stille need");
		
		expBlock.getChildren().addAll(roomRating,enemysKilled,lootColected);
		return expBlock;
	}
	private VBox scoreTextBlock() {
		VBox scoreBlock = new VBox();
		//scoreBlock.setMinWidth(prefSize/2 - 5/2 - 20);
		scoreBlock.setPadding(insets(10));
		scoreBlock.setBackground(background(Color.GRAY,20,0));
		HBox roomRating = textBlock("Room Rating", ""+map.getRoomRating());
		HBox totalEnemys = textBlock("Total Enemys", ""+map.getStartEnemyCount());
		HBox enemysKilled = textBlock("Enemys Killed", ""+map.getEnemyKillCount());
		HBox totalLoot = textBlock("Total Loot", ""+map.getStartLootCount());
		HBox lootColected = textBlock("Loot Colected", ""+map.getLootColected());
		
		scoreBlock.getChildren().addAll(roomRating,totalEnemys,enemysKilled,totalLoot,lootColected);
		return scoreBlock;
	}
	private HBox textBlock(String primaryTxt, String valueTxt){
		int fontSize = 15;
		HBox textBlock = new HBox();
		textBlock.setPadding(new Insets(15,5,5,5));
		HBox primaryTxtBox = new HBox();
		primaryTxtBox.setPrefWidth(150);
		Text primaryText = new Text(primaryTxt + ": ");
		primaryText.setFont(font(fontSize));
		primaryTxtBox.getChildren().add(primaryText);
		
		Text value = new Text(valueTxt);
		value.setFont(font(fontSize));
		
		textBlock.getChildren().addAll(primaryTxtBox,value);
		
		
		return textBlock;
	}
	private Rectangle devider() {
		Rectangle devider = new Rectangle();
		devider.setHeight(prefSize-20);
		devider.setWidth(5);
		devider.setFill(Color.DARKGOLDENROD);
		return devider;
	}
	private HBox header(String headerTXT, int fontSize) {
		HBox header = new HBox();
		Text headerTxt = new Text(headerTXT);
		headerTxt.setTextAlignment(TextAlignment.CENTER);
		headerTxt.setFont(font(fontSize));
		header.getChildren().add(headerTxt);
		header.setBackground(background(Color.GRAY,20,5));
		header.setAlignment(Pos.CENTER);
		return header;
	}
	private HBox continueButton() {
		HBox buttonBox = new HBox();
		Button button = new Button();
		button.setAlignment(Pos.CENTER_RIGHT);
		button.setText("Continue");
		button.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent mouseEvent) {
		        view.nextMap();
			}		
		});
		buttonBox.getChildren().add(button);
		buttonBox.setAlignment(Pos.CENTER_RIGHT);
		return buttonBox;
	}
	private void updateScore() {
		
	}
	
	private Background background(Color color, int radious, int inset) {
		BackgroundFill fill = new BackgroundFill(color, new CornerRadii(radious), new Insets(inset,inset,inset,inset));
		Background background = new Background(fill);
		return background;
	}
	private Font font(int textSize) {
		return Font.font("Viner Hand ITC", FontWeight.BOLD, textSize);
	}
	private Insets insets(int insetsSize) {
		return new Insets(insetsSize, insetsSize, insetsSize, insetsSize);
	}
}//endclass
