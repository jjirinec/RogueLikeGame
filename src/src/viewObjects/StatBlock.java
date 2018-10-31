package src.viewObjects;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import src.Character;

public class StatBlock {
	private GridPane statBlock;
	private Character player;
	
	public StatBlock(Character player) {
		statBlock = new GridPane();
		this.player = player;
	}
	public GridPane getStackBlock() {
		updateStatBlock();
		return statBlock;
	}
	private void updateStatBlock() {
		statBlock = new GridPane();
		statBlock.setVgap(10);
		statBlock.setHgap(10);
		statBlock.setPadding(new Insets(0,10,0,10));
		Text str = statText("Strength: ");
		Text strValue = statText("" + player.getStr());
		statBlock.add(str, 0, 7);
		statBlock.add(strValue, 1, 7);
		Text dex = statText("Dexterity: ");
		Text dexValue = statText("" + player.getDex());
		statBlock.add(dex, 0, 8);
		statBlock.add(dexValue, 1, 8);
		Text con = statText("Constatution:");
		Text conValue = statText("" + player.getCon());
		statBlock.add(con, 0, 9);
		statBlock.add(conValue, 1, 9);
		Text mgk = statText("Magic:");
		Text mgkValue = statText("" + player.getMgk());
		statBlock.add(mgk, 0, 10);
		statBlock.add(mgkValue, 1, 10);
		Text defence = statText("Defense:");
		Text defenceValue = statText("" + player.getDefence());
		statBlock.add(defence, 0, 11);
		statBlock.add(defenceValue, 1, 11);
		Text speed = statText("Speed:");
		Text speedValue = statText("" + player.getSpeed());
		statBlock.add(speed, 0, 12);
		statBlock.add(speedValue, 1, 12);
	}
	private Text statText(String txt) {
		Text text = new Text(txt);
		text.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		return text;	
	}

}
