package src.viewObjects;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import mapObjects.Container;
import mapObjects.Loot;
import src.Character;
public class ContainerView {
	
	ObservableList<HBox> containerList;
	ListView<HBox> lootList;
	Background background;
	VBox cratePane;
	Container container;
	Character player;
	
	
	public ContainerView(Container container,Character player) {
		this.container = container;
		this.player = player;
		background = new Background(new BackgroundFill(Color.BROWN, new CornerRadii(0), new Insets(0,0,0,0)));
		containerList = FXCollections.observableArrayList();
		
		lootList = new ListView<HBox>();
		lootList.setBackground(background);
		lootList.setItems(containerList);
		lootList.setFixedCellSize(75);
		fillLootList();
		
		
		cratePane = new VBox();
		cratePane.getChildren().add(lootList);
		cratePane.setBackground(background);
		
	}
	
	private void fillLootList() {
		ArrayList<Loot> containerContents = container.getContents();
		for(int i = 0; i < containerContents.size(); i++) {
			Loot item = containerContents.get(i);
			InventoryItem itemView = new InventoryItem(item);
			containerList.add(itemView.getItemView());
			itemView.getItemView().setOnMouseClicked(new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent mouseEvent) {
			        if(mouseEvent.getClickCount() == 2){		 
			            player.grabLoot(item);
			            container.removeItem(item);
			            updateLootList();
			         }
				}		
			});
		}
	}
	private void updateLootList() {
		containerList.removeAll(containerList);
		fillLootList();
	}
	
	public VBox getContainerView() {
		return cratePane;
	}
}
