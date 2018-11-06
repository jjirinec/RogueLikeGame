package src.viewObjects;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import mapObjects.Armor;
import mapObjects.Wepon;

public class EquipedView {
	VBox mainView;
	HBox equipmentView;
	BackgroundFill backGroundFill = new BackgroundFill(Color.BLACK, new CornerRadii(25), new Insets(0,0,0,0) );
//	Wepon equipedWeapon;
//	Armor equipedArmor;
	StackPane weaponView;
	StackPane armorView;
	
	String weaponBaseLayer = "images/PlayerView/weaponEmpty.png";
	String armorBaseLayer = "images/PlayerView/armorEmpty.png";
	
	public EquipedView()
	{
		mainView = new VBox();
//		mainView.setSpacing(10);
		mainView.setPadding(new Insets(15,15,15,15));
		mainView.setBackground(new Background(backGroundFill));
		
		equipmentView = new HBox();
//		equipmentView.setSpacing();
		equipmentView.setAlignment(Pos.CENTER);
		
		weaponView = new StackPane();
		armorView = new StackPane();
		ImageView weaponViewBaseLayer = new ImageView(new Image(weaponBaseLayer));
		ImageView armorViewBaseLayer = new ImageView(new Image(armorBaseLayer));
		weaponView.getChildren().add(weaponViewBaseLayer);
		armorView.getChildren().add(armorViewBaseLayer);
		weaponView.setScaleX(.6);
		weaponView.setScaleY(.6);
		armorView.setScaleX(.6);
		armorView.setScaleY(.6);
		
		equipmentView.getChildren().addAll(weaponView,armorView);
		
		//Set up Header
		Text header = new Text("Equiped Gear");
		header.setFont(Font.font("Viner Hand ITC", FontWeight.BOLD, 35));
		HBox headerPane = new HBox();
		headerPane.setBackground(new Background(new BackgroundFill(Color.GREY, new CornerRadii(25), new Insets(0,0,0,0) )));
		headerPane.getChildren().add(header);
		headerPane.setAlignment(Pos.CENTER);
		
		
		
		mainView.getChildren().addAll(headerPane,equipmentView);
	}
	
	public VBox getMainView() {
		return mainView;
	}
	
	public StackPane getWeponView() {
		return this.weaponView;
	}
	public StackPane getArmorView() {
		return this.armorView;
	}

}
