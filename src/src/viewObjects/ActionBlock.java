package src.viewObjects;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import src.Character;

public class ActionBlock {
	private GridPane actionBlock;
	private Character player;
	
	public ActionBlock(Character player) {
		actionBlock = new GridPane();
		this.player = player;
	}
	
	public GridPane getActionBlock() {
		updateActionBlock();
		return actionBlock;
	}
	
	private void updateActionBlock() {
		actionBlock = new GridPane();
		actionBlock.setHgap(10);
		actionBlock.setVgap(10);
		actionBlock.setPadding(new Insets(0,10,0,10));
		Text actions = statText("Action Points:");
		Text actionValue = statText("" + player.getCurentActions());
		actionBlock.add(actions, 0, 0);
		actionBlock.add(actionValue, 1, 0);
		Text moveCost = statText("Movment Cost:");
		Text moveCostValue = statText("" + player.getMoveCost());
		actionBlock.add(moveCost, 0, 1);
		actionBlock.add(moveCostValue, 1, 1);
		Text attackCost = statText("Attack Cost:");
		Text attackCostValue = statText("" + player.getAttackCost());
		actionBlock.add(attackCost, 0, 2);
		actionBlock.add(attackCostValue, 1, 2);	
	}
	
	private Text statText(String txt) {
		Text text = new Text(txt);
		text.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		return text;	
	}
}
