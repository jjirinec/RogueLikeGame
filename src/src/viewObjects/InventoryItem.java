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
import mapObjects.Consumable;
import mapObjects.Equipable;
import mapObjects.Loot;

public class InventoryItem {
	
	Loot item;
	HBox itemView;
	Text name;
	ImageView image;
	String toUseEquip = "\n\nDoubleClick";
	
	public InventoryItem(Loot item) {
		this.item = item;
		if(item instanceof Consumable)
			toUseEquip = toUseEquip + " to Use";
		if(item instanceof Equipable)
			toUseEquip = toUseEquip + " to Equip";
		
		BackgroundFill backGroundFill = new BackgroundFill(Color.DARKSLATEGREY, new CornerRadii(25), new Insets(0,0,0,0) );
		Background backGround = new Background(backGroundFill);
		this.itemView = new HBox();
		
		itemView.setAlignment(Pos.CENTER_LEFT);
		itemView.setSpacing(30);
		itemView.setPadding(new Insets(5,5,5,5));
		itemView.setBackground(backGround);
		
		
		this.name = new Text(item.getObjectName());
		this.name.setFont(Font.font("Viner Hand ITC", FontWeight.BOLD, 20));
		this.name.setTextAlignment(TextAlignment.LEFT);
		HBox textPane = new HBox();
		textPane.setAlignment(Pos.CENTER_LEFT);
		textPane.getChildren().add(this.name);
		
		this.image = item.getImageView();
		image.minHeight(200);
		image.scaleXProperty();
		
		
		itemView.getChildren().add(image);
		itemView.getChildren().add(textPane);
		
		Tooltip.install(itemView, new Tooltip(item.description() + toUseEquip));
		
//		itemView.setOnMouseClicked(new EventHandler<MouseEvent>(){
//			@Override
//			public void handle(MouseEvent mouseEvent) {
//		        if(mouseEvent.getClickCount() == 2){
//		        	//TODO Add consume/equip logic here
//		        	System.out.println(item.description());
//		            System.out.println("Double clicked");
//		         }
//				
//			}		
//		});
//		
	}
//	
//	private void use(Consumable potion) {
//		potion.consume(player);
//	}
	public HBox getItemView() {
		return itemView;
	}

}