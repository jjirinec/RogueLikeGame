package src.viewObjects;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import src.Character;

public class StatBlock {
	private GridPane stats;
	private StackPane statBlock;
	private Character player;
	
	public StatBlock(Character player) {
		stats = new GridPane();
		statBlock = new StackPane();
		this.player = player;
		
		statBlock.getChildren().add(stats);
		//statBlock.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY )));
	}
	public StackPane getStackBlock() {
		updateStatBlock();
		stats.setAlignment(Pos.BASELINE_CENTER);
		statBlock.getChildren().remove(0);
		statBlock.getChildren().add(stats);
		return statBlock;
	}
	private void updateStatBlock() {
		stats = new GridPane();
		stats.setVgap(10);
		stats.setHgap(10);
		stats.setPadding(new Insets(20,10,20,10));
		Text str = statText("Strength: ");
		Text strValue = statText("" + player.getStr());
		stats.add(str, 0, 0);
		stats.add(strValue, 1, 0);
		Text dex = statText("Dexterity: ");
		Text dexValue = statText("" + player.getDex());
		stats.add(dex, 0, 1);
		stats.add(dexValue, 1, 1);
		Text con = statText("Constatution:");
		Text conValue = statText("" + player.getCon());
		stats.add(con, 0, 2);
		stats.add(conValue, 1, 2);
		Text mgk = statText("Magic:");
		Text mgkValue = statText("" + player.getMgk());
		stats.add(mgk, 0, 3);
		stats.add(mgkValue, 1, 3);
		Text defence = statText("Defense:");
		Text defenceValue = statText("" + player.getDefence());
		stats.add(defence, 0, 4);
		stats.add(defenceValue, 1, 4);
		Text accuracy = statText("Accuracy:");
		Text accuracyValue = statText("" + player.getAccuracy());
		stats.add(accuracy, 0, 5);
		stats.add(accuracyValue, 1, 5);
		Text speed = statText("Speed:");
		Text speedValue = statText("" + player.getSpeed());
		stats.add(speed, 0, 6);
		stats.add(speedValue, 1, 6);
		//stats.setGridLinesVisible(true);
		
//		Border border = new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.FULL));
//		stats.setBorder(border);	
		BackgroundFill backGroundFill = new BackgroundFill(Color.DARKGREY, new CornerRadii(25), new Insets(0,0,0,0) );
		Background backGround = new Background(backGroundFill);
	
		stats.setBackground(backGround);
	}
	private Text statText(String txt) {
		Text text = new Text(txt);
		text.setFont(Font.font("Viner Hand ITC", FontWeight.BOLD, 20));
		return text;	
	}

}
