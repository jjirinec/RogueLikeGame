package src.viewObjects;
import javafx.geometry.Insets;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import src.Character;

public class ActionBlock {
	private GridPane actionBlock;
	private Pane actionPane;
	private Character player;
	
	
	public ActionBlock(Character player) {
		actionBlock = new GridPane();
		actionBlock.setMinWidth(300);
		actionPane = new Pane();
		//actionPane.setMinWidth(250);
		actionPane.getChildren().add(actionBlock);
		this.player = player;
	}
	
	public Pane getActionBlock() {
		updateActionBlock();
		actionPane.getChildren().remove(0);
		actionPane.getChildren().add(actionBlock);
		return actionPane;
	}
	
	private void updateActionBlock() {
		actionBlock = new GridPane();
		actionBlock.setHgap(10);
		actionBlock.setVgap(10);
		actionBlock.setPadding(new Insets(20,10,20,10));
		Text actions = statText("Action Points:");
		Text actionValue = statText("" + Math.round(player.getCurentActions()*100)/100.0);
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
		
		BackgroundFill backGroundFill = new BackgroundFill(Color.DARKGREY, new CornerRadii(25), new Insets(0,0,0,0) );
		Background backGround = new Background(backGroundFill);
		actionBlock.setBackground(backGround);
		Tooltip.install(actions, new Tooltip("1 + speed/5 actions per round"));
		Tooltip.install(moveCost, new Tooltip("Equiped armor effects movment cost"));
		Tooltip.install(attackCost, new Tooltip("Equiped weapon effects attack cost"));
	}
	
	private Text statText(String txt) {
		Text text = new Text(txt);
		text.setFont(Font.font("Viner Hand ITC", FontWeight.BOLD, 20));
		return text;	
	}
}
