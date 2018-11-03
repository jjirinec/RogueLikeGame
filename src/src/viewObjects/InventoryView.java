package src.viewObjects;
import java.util.ArrayList;
//import javafx.event.EventHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import mapObjects.Consumable;
import mapObjects.Loot;
import mapObjects.MapObject;
import src.Character;

public class InventoryView {
	VBox inventoryView;
	Character player;
	ListView<HBox> inventory;
	ObservableList<HBox> inventroyViewList;
	HBox equipment;
	
	
	public InventoryView(Character player, double prefWidth) {
		this.player = player;
		
		inventoryView = new VBox();
		inventoryView.setPadding(new Insets(0,30,30,30));
		inventoryView.setAlignment(Pos.CENTER_RIGHT);
		inventoryView.setPrefWidth(prefWidth);
		inventoryView.setFillWidth(true);
		
		///Equipment///
		equipment = new HBox();
		equipment.getChildren().add(new Text("Equiptment Gose Here"));
		
		
		
		
		///Inventrory list///
		inventroyViewList =  FXCollections.observableArrayList();
		inventory = new ListView<HBox>();
		inventory.setPadding(new Insets(20,20,20,20));
		inventory.setMaxSize(300, 400);
		BackgroundFill backGroundFill = new BackgroundFill(Color.BLACK, new CornerRadii(25), new Insets(0,0,0,0) );
		Background backGround = new Background(backGroundFill);
		inventory.setBackground(backGround);
		inventory.setItems(inventroyViewList);
		inventory.setFocusTraversable(false);
		inventory.setFixedCellSize(75);
		
		
			
		
		this.updateInventory();
		
		inventoryView.getChildren().add(equipment);
		inventoryView.getChildren().add(inventory);
		
		
//		inventory.setOpacity(.7);
		
		
//		this.player = player;
	}
	
	public void updateInventory() {
		inventroyViewList.removeAll(inventroyViewList);
		ArrayList<Loot> inventoryItems = player.getInventory(); 
		for(int i = 0; i < inventoryItems.size(); i++) {
			Loot item = inventoryItems.get(i);
			InventoryItem itemView = new InventoryItem(item);
			inventroyViewList.add(itemView.getItemView());
			itemView.getItemView().setOnMouseClicked(new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent mouseEvent) {
			        if(mouseEvent.getClickCount() == 2){
			        	//TODO Add consume/equip logic here
			        	System.out.println(item.description());
			            System.out.println("Double clicked");
			 
			            useEquip(itemView.item);
			         }
					
				}		
			});
			
		}
	}
	
	private void useEquip(MapObject item) {
		if(item instanceof Consumable) {
			player.useItem((Consumable)item);
			System.out.println("Consumeing Potion");
		}
	}
	
	public VBox getView() {
		return inventoryView;
	}

}
