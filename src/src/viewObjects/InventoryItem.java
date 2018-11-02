package src.viewObjects;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class InventoryItem {
	
	HBox itemView;
	Text name;
	ImageView image;
	
	public InventoryItem(String name, ImageView image) {
		BackgroundFill backGroundFill = new BackgroundFill(Color.DARKSLATEGREY, new CornerRadii(25), new Insets(0,0,0,0) );
		Background backGround = new Background(backGroundFill);
		this.itemView = new HBox();
		
		itemView.setAlignment(Pos.CENTER_LEFT);
		itemView.setSpacing(50);
		itemView.setPadding(new Insets(5,5,5,5));
		itemView.setBackground(backGround);
		this.name = new Text(name);
		this.name.setFont(Font.font("Viner Hand ITC", FontWeight.BOLD, 20));
		this.image = image;
		image.minHeight(200);
		image.scaleXProperty();
		itemView.getChildren().add(image);
		itemView.getChildren().add(this.name);
		
	}
	
	public HBox getItemView() {
		return itemView;
	}

}
