package src.viewObjects;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import mapObjects.Armor;
import mapObjects.Consumable;
import mapObjects.Equipable;
import mapObjects.Loot;
import mapObjects.Wepon;

public class InventoryItem {
	
	Loot item;
	HBox itemView;
	Text name;
	ImageView inventoryImage;
	ImageView equipedImage;
	String toUseEquip = "\n\nDoubleClick";
	
	Color weaponColor = Color.DARKSLATEGREY;
	Color armorColor = Color.BROWN;
	Color consumableColor = Color.DARKGOLDENROD;
	Background backGround;
	BackgroundFill backGroundFill;
	HBox equipedView;
	
	public InventoryItem(Loot item) {
		this.item = item;
		if(item instanceof Consumable)
			toUseEquip = toUseEquip + " to Use";
		if(item instanceof Equipable)
			toUseEquip = toUseEquip + " to Equip";

		backGroundFill = setBackgroundColor();
		backGround = new Background(backGroundFill);
		this.itemView = new HBox();
		
		
		
		itemView.setAlignment(Pos.CENTER_LEFT);
		itemView.setSpacing(20);
		itemView.setPadding(new Insets(5,5,5,5));
		itemView.setBackground(backGround);
		
		
		this.name = new Text(item.getObjectName());
		this.name.setFont(Font.font("Viner Hand ITC", FontWeight.BOLD, 20));
		this.name.setTextAlignment(TextAlignment.LEFT);
		HBox textPane = new HBox();
		textPane.setAlignment(Pos.CENTER_LEFT);
		textPane.getChildren().add(this.name);
		
		this.inventoryImage = item.getImageView();
//		inventoryImage.setScaleX(.2);
//		INVENTORYIMAGE.SETSCALEY(.2);
		//inventoryImage.scaleXProperty();
		
		
		itemView.getChildren().add(inventoryImage);
		itemView.getChildren().add(textPane);
//		itemView.setScaleX(.8);
//		itemView.setScaleY(.8);
		
		Tooltip.install(itemView, new Tooltip(item.description() + toUseEquip));
		
		setUpEquipedView();
		
	}
//	
//	private void use(Consumable potion) {
//		potion.consume(player);
//	}
	public HBox getItemView() {
		return itemView;
	}
	private BackgroundFill setBackgroundColor() {
		BackgroundFill fill;
		if(item instanceof Armor)
			fill = new BackgroundFill(armorColor, new CornerRadii(25), new Insets(0,0,0,0) );
		else if(item instanceof Wepon)
			fill = new BackgroundFill(weaponColor, new CornerRadii(25), new Insets(0,0,0,0) );
		else
			fill = new BackgroundFill(consumableColor, new CornerRadii(25), new Insets(0,0,0,0) );
		return fill;
	}
	private void setUpEquipedView() {
		ImageView image = new ImageView(item.getImageView().getImage());
//		image.setScaleX(3.5);
//		image.setScaleY(3.5);
		this.equipedView = new HBox();
		equipedView.setAlignment(Pos.CENTER);
		equipedView.getChildren().add(image);
		equipedView.setBackground(backGround);
		equipedView.setScaleX(.85);
		equipedView.setScaleY(.85);
		Tooltip.install(equipedView, new Tooltip(item.description() + "\nDoubleClick to unEquip"));
		
		
	}
	public Pane getEquipedView() {
		return equipedView;
	}
}
