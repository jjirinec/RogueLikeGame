package src.viewObjects;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import mapObjects.Loot;
import src.Character;

public class InventoryView {
	VBox inventoryView;
	Character player;
	ListView<HBox> inventory;
	ObservableList<HBox> inventroyViewList;
	HBox equipment;
	
	
	public InventoryView(Character player, double prefWidth) {
		inventoryView = new VBox();
		inventoryView.setPadding(new Insets(0,30,30,30));
		inventoryView.setAlignment(Pos.CENTER_RIGHT);
		inventoryView.setPrefWidth(prefWidth);
		inventoryView.setFillWidth(true);
		equipment = new HBox();
		this.player = player;
		inventoryView.getChildren().add(equipment);
		
		inventory = new ListView<HBox>();
		inventory.setPadding(new Insets(10,10,10,10));
		inventory.setMaxSize(300, 300);
		BackgroundFill backGroundFill = new BackgroundFill(Color.DARKGRAY, new CornerRadii(25), new Insets(0,0,0,0) );
		Background backGround = new Background(backGroundFill);
		inventory.setBackground(backGround);
		
		
		inventroyViewList =  FXCollections.observableArrayList();
		this.updateInventory();
		inventoryView.getChildren().add(inventory);
		
		inventory.setItems(inventroyViewList);
		inventory.setFocusTraversable(false);
		inventory.setFixedCellSize(75);
//		inventory.setOpacity(.7);
		
		
		this.player = player;
	}
	
	public void updateInventory() {
		ArrayList<Loot> inventoryItems = player.getInventory();
		for(int i = 0; i < inventoryItems.size(); i++) {
			Loot item = inventoryItems.get(i);
			InventoryItem itemView = new InventoryItem(item.getObjectName(),item.getImageView());
			inventroyViewList.add(itemView.getItemView());
		}
	}
	
	public VBox getView() {
		return inventoryView;
	}

}
