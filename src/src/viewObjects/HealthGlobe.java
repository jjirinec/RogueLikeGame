package src.viewObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class HealthGlobe {
	private int size;
	private final double centerScale = .78;
	private StackPane healthGlobe;
	
	public HealthGlobe(int size) {
		this.size = size;
		healthGlobe = new StackPane();
		healthGlobe.getChildren().add(getImageView("images/Character/HealthGlobe_Background.png",size));
		ImageView healthImage = getImageView("images/Character/HealthGlobe_Center.png",size*centerScale);
		healthImage.setTranslateY(2);//(size - size*centerScale)/2 + 2);
		healthGlobe.getChildren().add(healthImage);
		healthGlobe.getChildren().add(getImageView("images/Character/HealthGlobe_Top.png",size));
		healthGlobe.setMaxSize(size, size);
	}
	private ImageView getImageView(String imageFile,double size) {
		Image image = new Image(imageFile);
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(size);
		imageView.setFitWidth(size);
		return imageView;
	}
	
	public StackPane getHealthGlob(){
		return healthGlobe;
	}
	public void updateHealthGlobe(double scale) {
		ImageView image = (ImageView)healthGlobe.getChildren().get(1);//175;
		double imageSize = image.getFitHeight();
		double imageTransform = (2);//size - size*centerScale)/2 + 2;
		double newSize = imageSize*scale;
		double move = (imageSize - newSize)/2;
		double fullMove = move + imageTransform;
		System.out.println("newSize: " + newSize); 
		image.setTranslateY(fullMove);
		image.setScaleY(scale);
	}
}
