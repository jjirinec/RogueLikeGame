package src.viewObjects;
import java.util.ArrayList;
//import javafx.event.EventHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import mapObjects.Armor;
import mapObjects.Consumable;
import mapObjects.Equipable;
import mapObjects.Loot;
import mapObjects.MapObject;
import mapObjects.Wepon;
import src.Character;
import src.Map;

public class InventoryView {
	VBox playerInventoryView;
	Character player;
	ListView<HBox> inventory;
	ObservableList<HBox> inventroyViewList;
	VBox equipment;
	EquipedView equipedView;
	BackgroundFill backGroundFill = new BackgroundFill(Color.BLACK, new CornerRadii(25), new Insets(0,0,0,0) );
	Background backGround = new Background(backGroundFill);
	ComboBox filter;
	Map map;
	
	public InventoryView(Character player, double prefWidth, Map map) {
		this.player = player;
		this.map = map;
		
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
		
		inventory.setBackground(backGround);
		inventory.setItems(inventroyViewList);
		inventory.setFocusTraversable(false);
		inventory.setFixedCellSize(75);
				
		
		
		
		playerInventoryView.getChildren().add(equipment);
		playerInventoryView.getChildren().add(setUpInventoryView());
		this.updateInventory();
		this.setUnEquipHandl();
	}
	private VBox setUpInventoryView() {
		VBox inventoryView = new VBox();
		inventoryView.getChildren().add(setUpHeader("Inventory"));
		inventoryView.getChildren().add(setUpInventoryFilter());
		inventoryView.setAlignment(Pos.CENTER);
		inventoryView.getChildren().add(inventory);
		inventoryView.setBackground(backGround);
		inventoryView.setPadding(new Insets(15,15,15,15));
		return inventoryView;
	}
	private HBox setUpInventoryFilter() {
		HBox filterView = new HBox();
		filterView.setPadding(new Insets(5,5,5,5));
		
		filter = new ComboBox();
		
		filter.getItems().add("All");
		filter.getItems().add("Weapons");
		filter.getItems().add("Armor");
		filter.getItems().add("Consumbables");
		filter.setValue("All");
		filter.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				filterSwitch(filter.getValue());
				filter.setFocusTraversable(false);
			}
		});
		
		filterView.getChildren().add(filter);
		filter.setFocusTraversable(false);
		
		return filterView;
	}
	private void filterSwitch(Object filterValue) {
		String value = (String)filterValue;
		switch(value) {
			case "All":
				filterAll();
				break;
			case "Weapons":
				filterWeapons();
				break;
			case "Armor":
				filterArmor();
				break;
			case "Consumbables":
				filterConsumables();
				break;
		}
		System.out.println("ResetingFocus" + filter.isFocused());
		filter.setFocusTraversable(false);
		map.getMap().requestFocus();
		System.out.println("ResetingFocus" + filter.isFocused());
	}
	private HBox setUpHeader(String headerString) {
		Text header = new Text(headerString);
		header.setFont(Font.font("Viner Hand ITC", FontWeight.BOLD, 35));
		HBox headerPane = new HBox();
		headerPane.getChildren().add(header);
		headerPane.setAlignment(Pos.CENTER);
		headerPane.setBackground(new Background(new BackgroundFill(Color.GRAY,new CornerRadii(25),new Insets(0,0,0,0))));
		return headerPane;
	}
	private void filterAll() {
		inventroyViewList.removeAll(inventroyViewList);
		ArrayList<Loot> inventoryItems = player.getInventory(); 
		for(int i = 0; i < inventoryItems.size(); i++) {
			Loot item = inventoryItems.get(i);
			addItemToInventory(item);
		}
	}
	private void filterWeapons() {
		inventroyViewList.removeAll(inventroyViewList);
		ArrayList<Loot> inventoryItems = player.getInventory(); 
		for(int i = 0; i < inventoryItems.size(); i++) {
			Loot item = inventoryItems.get(i);
			if(item instanceof Wepon) {
				addItemToInventory(item);
			}
			
		}
	}
	private void filterArmor() {
		inventroyViewList.removeAll(inventroyViewList);
		ArrayList<Loot> inventoryItems = player.getInventory(); 
		for(int i = 0; i < inventoryItems.size(); i++) {
			Loot item = inventoryItems.get(i);
			if(item instanceof Armor) {
				addItemToInventory(item);
			}
			
		}
	}
	private void filterConsumables() {
		inventroyViewList.removeAll(inventroyViewList);
		ArrayList<Loot> inventoryItems = player.getInventory(); 
		for(int i = 0; i < inventoryItems.size(); i++) {
			Loot item = inventoryItems.get(i);
			if(item instanceof Consumable) {
				addItemToInventory(item);
			}
			
		}
	}
	private void addItemToInventory(Loot item) {
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
	public void updateInventory() {
		filterSwitch(filter.getValue());
	}
	public void updateEquipedView() {
		if(player.getEquipedWepon() == null && this.hasWeaponImage() || player.getEquipedWepon() != null && !this.hasWeaponImage())
			updateWeaponView();
		if(player.getEquipedArmor() == null && this.hasArmorImage() || player.getEquipedArmor() != null && !this.hasArmorImage())
			updateArmorView();	
	}
	
	private void updateWeaponView() {
		System.out.println("updatArmor:\n\tis any equiped: " + player.getEquipedWepon()+"\n\tisthere an image:" + hasWeaponImage());
		InventoryItem item;
		Wepon weapon = player.getEquipedWepon();
		if(weapon != null) {
			item = new InventoryItem(player.getEquipedWepon());
			this.equipedView.getWeponView().getChildren().add(item.getEquipedView());
		}
		else if(hasWeaponImage()) {//remove image if no weapon is equiped and there is an image to remove
			this.equipedView.getWeponView().getChildren().remove(1);
		}
	}
	private void updateArmorView() {
		InventoryItem item;
		System.out.println("updatArmor:\n\tis any equiped: " + player.getEquipedArmor()+"\n\tisthere an image:" + hasArmorImage());
		Armor armor = player.getEquipedArmor();
		if(armor != null) {
			item = new InventoryItem(player.getEquipedArmor());
			this.equipedView.getArmorView().getChildren().add(item.getEquipedView());
		}
		else if(hasArmorImage()) {//Remove image if no armor is equiped and there is an image to remove
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
