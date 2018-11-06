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
import mapObjects.Armor;
import mapObjects.Consumable;
import mapObjects.Equipable;
import mapObjects.Loot;
import mapObjects.MapObject;
import mapObjects.Wepon;
import src.Character;

public class InventoryView {
	VBox playerInventoryView;
	Character player;
	ListView<HBox> inventory;
	ObservableList<HBox> inventroyViewList;
	VBox equipment;
	EquipedView equipedView;
	
	
	public InventoryView(Character player, double prefWidth) {
		this.player = player;
		
		playerInventoryView = new VBox();
		playerInventoryView.setSpacing(20);
		playerInventoryView.setPadding(new Insets(30,30,30,30));
		playerInventoryView.setAlignment(Pos.CENTER_RIGHT);
		playerInventoryView.setPrefWidth(prefWidth);
		playerInventoryView.setFillWidth(true);
		
		///Equipment///
		equipedView = new EquipedView();
		equipment = equipedView.getMainView();
		//equipment.getChildren().add(new Text("Equiptment Gose Here"));
		
		
		
		
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
		HBox inventoryView = new HBox();
		inventoryView.setAlignment(Pos.CENTER);
		inventoryView.getChildren().add(inventory);
		
			
		
		this.updateInventory();
		
		playerInventoryView.getChildren().add(equipment);
		playerInventoryView.getChildren().add(inventoryView);
		this.setUnEquipHandl();
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
			            useEquip(itemView.item);
			         }
				}		
			});
			
		}
	}
	public void updateEquipedView() {
		InventoryItem item;
		Armor armor = player.getEquipedArmor();
		Wepon weapon = player.getEquipedWepon();
		if(weapon != null) {
			item = new InventoryItem(player.getEquipedWepon());
			this.equipedView.getWeponView().getChildren().add(item.getEquipedView());
		}
		else if(weapon == null && hasWeaponImage()) {//remove image if no weapon is equiped and there is an image to remove
			this.equipedView.getWeponView().getChildren().remove(1);
		}
		if(armor != null) {
			item = new InventoryItem(player.getEquipedArmor());
			this.equipedView.getArmorView().getChildren().add(item.getEquipedView());
		}
		else if(armor == null && hasArmorImage()) {//Remove image if no armor is equiped and there is an image to remove
			this.equipedView.getArmorView().getChildren().remove(1);
		}
			
	}
	private boolean hasWeaponImage(){
		if(this.equipedView.getWeponView().getChildren().size() >= 2)
			return true;
		return false;
	}
	private boolean hasArmorImage() {
		if(this.equipedView.getArmorView().getChildren().size() >=2)
			return true;
		return false;
	}
	public void setUnEquipHandl() {
		this.equipedView.getArmorView().setOnMouseClicked(new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent mouseEvent) {
			        if(mouseEvent.getClickCount() == 2){		 
			            removeArmorView();
			         }
				}		
			});
		this.equipedView.getWeponView().setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent mouseEvent) {
		        if(mouseEvent.getClickCount() == 2){		 
		            removeWeaponView();
		         }
			}		
		});
	}
	private void removeArmorView() {
		player.unEquipArmor();
	}
	private void removeWeaponView() {
		player.unEquipWeapon();
	}
	private void useEquip(MapObject item) {
		if(item instanceof Consumable) {
			player.useItem((Consumable)item);
		}
		if(item instanceof Equipable)
			((Equipable) item).equip(player);
	}
	
	public VBox getView() {
		return playerInventoryView;
	}

}
