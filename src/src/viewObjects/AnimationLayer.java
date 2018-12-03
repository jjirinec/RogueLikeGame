package src.viewObjects;


import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import mapObjects.Coordinate;

public class AnimationLayer {
	
	private Pane animationLayer;
	private AnimationTimer animation;
	StackPane mapStack;
	int mapSize;
	int gridSize;
	String arrowFile = "images/Projectil/Arrow.png";
	
	
	public AnimationLayer(int mapSize,int tileSize,StackPane mapStack) {
		this.mapSize = mapSize;
		this.gridSize = tileSize;
		this.mapStack = mapStack;
		animationLayer = new Pane();
		animationLayer.setPrefSize(mapSize, mapSize);
	}
	
	public Pane getAnimationLayer() {
		return animationLayer;
	}
	
	public void animateArrow(Coordinate startLocation,Coordinate targetLocation) {
		ImageView arrowImage = new ImageView(new Image(arrowFile));
		arrowImage.setTranslateX(startLocation.getX()*gridSize);
		arrowImage.setTranslateY(startLocation.getY()*gridSize);
		arrowImage.setRotate(calculateAngle(startLocation,targetLocation));
		
		arrowImage.setFitHeight(gridSize);
		arrowImage.setFitWidth(gridSize);
		animationLayer.getChildren().add(arrowImage);
		mapStack.getChildren().add(1, animationLayer);
		
		
	}
	
	private double calculateAngle(Coordinate start, Coordinate end) {
		int diffY = distanceY(start,end);
		int diffX  = distanceX(start,end);
		double hyp = hypotinuse(diffX, diffY);
		
		
		System.out.println("X: " +diffX);
		System.out.println("Y: "+diffY);
//		int angle = (int) Math.atan(diffY/diffX);
		double radians =  Math.acos(diffX/hyp);
		double degrees = radiansToDegrees(radians);
		System.out.println("Hypot: " + hyp + " Angle: " + degrees );
		degrees += getAditionalDegrees(diffX, diffY);
		return  degrees;
		
		
	}
	private int getAditionalDegrees(int diffX, int diffY) {
		int degrees = 0;
		if(diffX < 0 && diffY > 0)
			degrees = 90;
		if(diffX < 0 && diffY < 0)
			degrees = 180;
		if(diffX > 0 && diffY < 0)
			degrees = 270;
		return degrees;
	}
	private double radiansToDegrees(double radians) {
		return radians * (180/(2*Math.PI));
	}
	private int distanceX(Coordinate start, Coordinate end) {
		return end.getX() - start.getX();
	}
	private int distanceY(Coordinate start, Coordinate end) {
		return end.getY() - start.getY();
	}
	private double hypotinuse(int diffX, int diffY) {
		return Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
	}

}
