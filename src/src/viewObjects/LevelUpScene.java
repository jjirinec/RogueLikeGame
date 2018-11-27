package src.viewObjects;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import src.Character;
import src.View;
public class LevelUpScene {
	Character player;
	BorderPane mainPane;
	int statPoints;
	int buttonSize = 25;
	
	//ToolTipText
	String strTip = "Str";
	String dexTip = "dex";
	String conTip = "Con";
	String mgkTip = "Magic";
	String defTip = "Defence";
	String accTip = "Accuracy";
	String spdTip = "Speed";
	
	int availablePts;
	int totalPts;
	Label points;
	
	View view;
    
    
	public LevelUpScene(Character player,int width, int hight,View view) {
		this.player = player;
		this.view = view;
		this.totalPts = player.getAvailableStatPts();
		this.availablePts = this.totalPts;
		mainPane = new BorderPane();
		mainPane.setPrefSize(width, hight);
		mainPane.setCenter(setUpCenter());
		mainPane.setBottom(setUpCommitButton());
		mainPane.setBackground(background(Color.SLATEGREY,20,0));
		mainPane.setPadding(new Insets(20,20,20,20));
	}
	public enum Stat {
		Str,Dex,Con,Mgk,Def,Acc,Spd
	}
	/*
	 * Sets up the main view
	 */
	private VBox setUpCenter() {
		VBox center = new VBox();
		center.setPadding(new Insets(20,20,20,20));
//		center.setBackground(background(Color.SLATEGREY,20,0));
		
		//Header
		center.getChildren().add(header("Level Up"));
		
		//AvailableStatPoints
		center.getChildren().add(statPoints());
		
		//StatBlock
		center.getChildren().add(statBlock());
		
		//CommitButton
		//
		//center.getChildren().add(this.setUpCommitButton());
		return center;
	}
	
	/*
	 * Sets up the display for the available stat points
	 * returns HBox of this view
	 */
	private HBox statPoints() {
		HBox statPointsView = new HBox();
		Label stPoints = new Label("Stat Points Avalable:");
		stPoints.setFont(font(15));
		stPoints.setPrefWidth(175);
		points = new Label(this.availablePts + "");
		points.setFont(font(15));
		statPointsView.getChildren().addAll(stPoints,points);
		statPointsView.setAlignment(Pos.CENTER_RIGHT);
		Tooltip tip = new Tooltip( player.getStatPtsPerLvl() + " / level \nbeyond 1st");
		Tooltip.install(statPointsView, tip);
		return statPointsView;
	}
	
	/*
	 * SetUp Commit Button
	 * return commit button
	 */
	private HBox setUpCommitButton() {
		Button button  = new Button();
		button.setAlignment(Pos.CENTER_RIGHT);
		button.setText("Continue");
		button.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				int usedPts = totalPts - availablePts;
				System.out.println(usedPts);
				player.useStatPts(usedPts);
				view.nextMap();				
			}
			
		});
		Tooltip tip = new Tooltip("Commit Points and continue to next Level");
		Tooltip.install(button, tip);
		HBox buttonBox = new HBox(button);
		buttonBox.setAlignment(Pos.CENTER_RIGHT);
		return buttonBox;
	}
	
	private Font font(int textSize) {
		return Font.font("Viner Hand ITC", FontWeight.BOLD, textSize);
	}
	/*
	 * Builds the header
	 * @param - headerMsg = The text the header should have
	 * Return - HBox containing header
	 */
	private HBox header(String headerMsg) {
		HBox header = new HBox();
		header.setAlignment(Pos.TOP_CENTER);
		Label headerText = new Label(headerMsg);
		headerText.setFont(font(35));
		header.getChildren().add(headerText);
		
		return header;
	}
	
	/*
	 * Sets up the StatBlock
	 * returns and VBox containgin each stat and the ablility to add points to it
	 */
	private VBox statBlock() {
		VBox statBlock = new VBox();
		statBlock.setAlignment(Pos.CENTER);
		statBlock.setMaxWidth(500);
		HBox str = statBox(Stat.Str);
		HBox dex = statBox(Stat.Dex);
		HBox con = statBox(Stat.Con);
		HBox mgk = statBox(Stat.Mgk);
		HBox def = statBox(Stat.Def);
		HBox acc = statBox(Stat.Acc);
		HBox spd = statBox(Stat.Spd);

		statBlock.getChildren().addAll(str,dex,con,mgk,def,acc,spd);
		return statBlock;
	}
	
	/*
	 * Gets stat string
	 * @param - enum of stat 
	 * return stat string
	 */
	private String getStat(Stat stat) {
		String statString = "";
		switch(stat) {
			case Str:
				statString = "Strength:";
				break;
			case Dex:
				statString = "Dextatity:";
				break;
			case Con:
				statString = "Constitution:";
				break;
			case Mgk:
				statString = "Magic:";
				break;
			case Def:
				statString = "Defence:";
				break;
			case Acc:
				statString = "Accurace:";
				break;
			case Spd:
				statString = "Speed:";
				break;
		}
		return statString;
	}
	
	/*
	 * Gets stat ToolTip string
	 * @param enum of stat
	 * return tooltip string
	 */
	private String getStatTip(Stat stat) {
		switch(stat) {
			case Str:
				return strTip;
			case Dex:
				return dexTip;
			case Con:
				return conTip;
			case Mgk:
				return mgkTip;
			case Def:
				return defTip;
			case Acc:
				return accTip;
			case Spd:
				return spdTip;
			default:
				return "";
		}
	}
	
	/*
	 * Sets up individual stat box containgin the statstring
	 * ability to increments stat up and down 
	 * ToolTip instaled
	 * @param - stat = the stat to set up
	 * return HBox of desired stat
	 */
	private HBox statBox(Stat stat) {
		HBox statBox = new HBox();
		statBox.setMaxWidth(300);
//		statBox.setBackground(background(Color.BLUE,10,0));//TODO Remvoe
		statBox.getChildren().add(statText(getStat(stat)));
		statBox.getChildren().add(statModifier(stat));
		Tooltip tip = new Tooltip(getStatTip(stat));
		Tooltip.install(statBox, tip);
		return statBox;
	}
	
	/*
	 * 
	 */
	private HBox statModifier(Stat stat) {
		HBox statModifier = new HBox();
//		statModifier.setPrefWidth(100);
		Label numLabel = new Label("0");
		numLabel.setFont(font(25));
		numLabel.setAlignment(Pos.CENTER);
		HBox upButton = upButton(stat,numLabel);
		HBox num = new HBox();
		num.setMinWidth(30);
		num.setAlignment(Pos.CENTER);
		num.getChildren().add(numLabel);
		HBox downButton = downButton(stat,numLabel);
		statModifier.getChildren().addAll(downButton,num,upButton);
		return statModifier;
	}
	
	private HBox upButton(Stat stat,Label value) {
		HBox buttonPane = new HBox();
		buttonPane.setAlignment(Pos.CENTER_RIGHT);
		Button button = new Button(">");
		button.setPrefSize(buttonSize, statPoints);
		button.setAlignment(Pos.CENTER);
		button.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				if(availablePts > 0) {
					availablePts--;
					points.setText(availablePts + "");
					int lValue = Integer.parseInt(value.getText());
					lValue++;
					value.setText(lValue+"");
					setPlayerStat(stat,1);
				}
				
			}
			
		});
		buttonPane.getChildren().add(button);
		return buttonPane;
	}
	private void setPlayerStat(Stat stat,int val) {
		switch(stat) {
			case Str:
				player.incrementStr(val);
				break;
			case Dex:
				player.incrementDex(val);
				break;
			case Con:
				player.incrementCon(val);
				break;
			case Mgk:
				player.incrementMgk(val);
				break;
			case Def:
				player.incrementDefence(val);
				break;
			case Acc:
				player.incrementAccuracy(val);
				break;
			case Spd:
				player.incrementSpeed(val);
				break;
		}
	}
	
	private HBox downButton(Stat stat, Label value) {
		HBox buttonPane = new HBox();
		buttonPane.setAlignment(Pos.CENTER_LEFT);
		Button button  = new Button("<");
		button.setPrefSize(buttonSize, statPoints);
		button.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {

				int lValue = Integer.parseInt(value.getText());
				if(lValue > 0 && availablePts < totalPts) {
					availablePts++;
					points.setText("" + availablePts);
					lValue--;
					value.setText(lValue+"");
					setPlayerStat(stat,-1);
				}
				
			}
			
		});
		
		buttonPane.getChildren().add(button);
		return buttonPane;
	}
	private HBox statText(String stat) {
		HBox statTextBox = new HBox();
		statTextBox.setPrefWidth(200);
		statTextBox.setAlignment(Pos.CENTER_LEFT);
		Label statLabel = new Label(stat);
		statLabel.setFont(font(20));
		statTextBox.getChildren().add(statLabel);
		return statTextBox;
	}
	private Background background(Color color, int radious, int inset) {
		BackgroundFill fill = new BackgroundFill(color, new CornerRadii(radious), new Insets(inset,inset,inset,inset));
		Background background = new Background(fill);
		return background;
	}
	public BorderPane levelUp(int statPoints) {
		this.statPoints = statPoints;
		return mainPane;
	}

}
