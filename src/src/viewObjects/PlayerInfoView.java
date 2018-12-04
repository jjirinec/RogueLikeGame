package src.viewObjects;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import src.Character;

public class PlayerInfoView {
	private VBox playerInfoView;
	private HealthGlobe healthGlobe;
	private StackPane healthGlobeStack;
	private StatBlock statBlock;
	private VBox attackDeffBlock;
	private ActionBlock actionBlock;
	private Character player;
	private int playerCurentHp;
	private int playerMaxHp;
	private double playerHpPercentage;
	
	public PlayerInfoView(Character player,double prefferdWidth) {
		this.player = player;
		this.playerCurentHp = player.getHp();
		this.playerMaxHp = player.getMaxHp();
		this.playerHpPercentage = 100*player.getHpPresentage();
		playerInfoView = new VBox();
		playerInfoView.setFillWidth(true);
		playerInfoView.setPrefWidth(prefferdWidth);
		GridPane viewGrid = setUpGrid();
		healthGlobe = new HealthGlobe(175);//TODO change size value to scale proporly
		healthGlobeStack = healthGlobe.getHealthGlob();
		statBlock = new StatBlock(player);
		actionBlock = new ActionBlock(player);
		attackDeffBlock = new VBox();
		this.updateAttackDeffInfo();
		viewGrid.add(healthGlobeStack, 3, 2);
		viewGrid.add(attackDeffBlock, 5, 2);
		viewGrid.add(statBlock.getStackBlock(),3,6);
		viewGrid.add(actionBlock.getActionBlock(), 3, 7);
		
		
		playerInfoView.getChildren().add(viewGrid);
		
		Tooltip.install(healthGlobeStack, new Tooltip("%" + this.playerHpPercentage + "\n==========\nCurrentHp: " + this.playerCurentHp + "\nMaxHp: " + this.playerMaxHp ));
		
		
	}
	public VBox getView() {
		return playerInfoView;
	}
	public void updatStatActionBlocks() {
		GridPane grid = (GridPane) playerInfoView.getChildren().get(0);
		grid.getChildren().remove(2);
		grid.getChildren().remove(2);
		grid.add(statBlock.getStackBlock(), 3, 4);
		grid.add(actionBlock.getActionBlock(), 3, 7);
	}
	
	public void updateHealthGlobe(double scale) {
		healthGlobe.updateHealthGlobe(scale);
		this.playerCurentHp = player.getHp();
		this.playerMaxHp = player.getMaxHp();
		this.playerHpPercentage = (Math.round(1000*player.getHpPresentage())/10);
		Tooltip.install(healthGlobeStack, new Tooltip("%" + this.playerHpPercentage + "\n==========\nCurrentHp: " + this.playerCurentHp + "\nMaxHp: " + this.playerMaxHp ));
		
	}
	public HealthGlobe getGlob() {return healthGlobe;}
	private GridPane setUpGrid() {
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0,10,0,10));
		
		return grid;
	}
	
	public VBox updateAttackDeffInfo() {
		
		HBox attackType = getText("Attack Type:\t" + player.getAttackType());
		HBox baseDmg = getText("Base Damage:\t" + player.calcBaseDmg());
		HBox totalDeff = getText("Total Defence:\t" + player.getTotalDefence());
		attackDeffToolTips(attackType,baseDmg,totalDeff);
		attackDeffBlock.setBackground(new Background(new BackgroundFill(Color.GREY,new CornerRadii(25),new Insets(0,0,0,0))));
		if(!attackDeffBlock.getChildren().isEmpty())
			attackDeffBlock.getChildren().remove(0, 3);
		attackDeffBlock.getChildren().addAll(attackType,baseDmg,totalDeff);
		attackDeffBlock.setPadding(new Insets(10));
		attackDeffBlock.setMaxHeight(65);
		return attackDeffBlock;
	}
	private void attackDeffToolTips(HBox attackType,HBox baseDmg,HBox totalDeff) {
		Tooltip att = new Tooltip("Tab: to cycle Attack Type");
		Tooltip.install(attackType, att);
		Tooltip dmg = new Tooltip("Base Dmg is based on relevent weapon and stat\nActual dmg uses this value as well as Accuracy to Defence ratio");
		Tooltip.install(baseDmg, dmg);
		Tooltip deff = new Tooltip("Total Defence is based on armor dex and deffence");
		Tooltip.install(totalDeff, deff);
	}
	private HBox getText(String textMsg) {
		Text text = new Text(textMsg);
		text.setFont(Font.font("Viner Hand ITC", FontWeight.BOLD, 15));
		HBox textBox = new HBox(text);
		textBox.setAlignment(Pos.CENTER_LEFT);
		return textBox;
	}

}
