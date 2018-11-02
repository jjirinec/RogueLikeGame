package src.viewObjects;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import src.Character;

public class PlayerInfoView {
	private VBox playerInfoView;
	private HealthGlobe healthGlobe;
	private StatBlock statBlock;
	private ActionBlock actionBlock;
	
	public PlayerInfoView(Character player,double prefferdWidth) {
		playerInfoView = new VBox();
		playerInfoView.setFillWidth(true);
		playerInfoView.setPrefWidth(prefferdWidth);
		GridPane viewGrid = setUpGrid();
		healthGlobe = new HealthGlobe(175);//TODO change size value to scale proporly
		statBlock = new StatBlock(player);
		actionBlock = new ActionBlock(player);
		viewGrid.add(healthGlobe.getHealthGlob(), 3, 2);
		viewGrid.add(statBlock.getStackBlock(),3,6);
		viewGrid.add(actionBlock.getActionBlock(), 3, 7);
		
		playerInfoView.getChildren().add(viewGrid);
		
		
	}
	public VBox getView() {
		return playerInfoView;
	}
	public void updatStatActionBlocks() {
		GridPane grid = (GridPane) playerInfoView.getChildren().get(0);
		grid.getChildren().remove(1);
		grid.getChildren().remove(1);
		grid.add(statBlock.getStackBlock(), 3, 4);
		grid.add(actionBlock.getActionBlock(), 3, 7);
	}
	
	public void updateHealthGlobe(double scale) {
		healthGlobe.updateHealthGlobe(scale);
	}
	public HealthGlobe getGlob() {return healthGlobe;}
	private GridPane setUpGrid() {
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0,10,0,10));
		
		return grid;
	}

}
