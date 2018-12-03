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
	private AnimationTimer animateArrowTimer;
	StackPane mapStack;
	int mapSize;
	int gridSize;
	String arrowFile = "images/Projectil/Arrow.png";
	Coordinate start;
	Coordinate end;
	
	int projectileSpeed = 10;
	
	ImageView arrow;
	int diffX;
	int diffY;
	double xMove;
	double yMove;
	double xMoved;
	double yMoved;
	double angle;
	
	public AnimationLayer(int mapSize,int tileSize,StackPane mapStack) {
		this.mapSize = mapSize;
		this.gridSize = tileSize;
		this.mapStack = mapStack;
		animationLayer = new Pane();
		animationLayer.setPrefSize(mapSize, mapSize);
		
		animateArrowTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				animateArrow();
				
			}
			
		};
	}
	
	public Pane getAnimationLayer() {
		return animationLayer;
	}
	public void startArrowAnimation(Coordinate start, Coordinate end) {
		this.start = start;
		this.end = end;
		diffX = Math.abs(distanceX(start, end)) * this.gridSize;
		diffY = Math.abs(distanceY(start,end)) * this.gridSize;
		xMoved = 0;
		yMoved = 0;
		arrow = placeArrow(start,end);
		
		angle = calculateAngle(start, end);
		double radians = Math.toRadians(angle);
		xMove = projectileSpeed * Math.cos(radians);
		yMove = projectileSpeed * Math.sin(radians);
		System.out.println(xMove + " , " + yMove);
		this.animateArrowTimer.start();
	}
	public void animateArrow() {
		System.out.println("Animation");
		xMoved += Math.abs(xMove);
		yMoved += Math.abs(yMove);
		System.out.println("xMove " + xMoved +  "   yMoved " + yMoved);
		arrow.setTranslateX(arrow.getTranslateX() + (xMove));
		arrow.setTranslateY(arrow.getTranslateY() + yMove);
		System.out.println("Angle " + angle);
		if(angle != 0 && angle != 90 && angle != 180 && angle != 270) {
			if(xMoved >= diffX || yMoved >= diffY) {
				this.animateArrowTimer.stop();
				System.out.println("Stoped by one");
				this.mapStack.getChildren().remove(animationLayer);
			}
		}
		else if(((angle == 90 || angle == 270) && yMoved >= diffY) || ((angle == 0 || angle == 180) && xMoved >= diffX)) {
			this.animateArrowTimer.stop();
			System.out.println("Stoped by 2");
			this.mapStack.getChildren().remove(animationLayer);
		}
		
		
		
		
	}
	
	private ImageView placeArrow(Coordinate startLocation, Coordinate targetLocation) {
		ImageView arrowImage = new ImageView(new Image(arrowFile));
		arrowImage.setTranslateX(startLocation.getX()*gridSize);
		arrowImage.setTranslateY(startLocation.getY()*gridSize);
		arrowImage.setRotate(calculateAngle(startLocation,targetLocation));
		arrowImage.setFitHeight(gridSize);
		arrowImage.setFitWidth(gridSize);
		if(animationLayer.getChildren().size() > 0) {
			animationLayer.getChildren().remove(0);
		}
		animationLayer.getChildren().add(arrowImage);
		if(mapStack.getChildren().size() > 1)
			mapStack.getChildren().remove(1);
		mapStack.getChildren().add(1, animationLayer);
		return arrowImage;
	}
	
	private double calculateAngle(Coordinate start, Coordinate end) {
		int diffY = distanceY(start,end);
		int diffX  = distanceX(start,end);
		
		double hyp = hypotinuse(diffX, diffY);
		System.out.println("xDiff: " + diffX + "  yDiff: " + diffY + "   Hypotinuse: " + hyp);
		double radians;
		if(diffY >= 0) {
			System.out.println("Case 1");
			radians =  Math.acos(diffX/hyp);
		}
		else {
			System.out.println("Case 2");
			radians = Math.acos(-diffX/hyp) +Math.PI;
		}
		System.out.println("Radians: " +radians);
		double degrees = radiansToDegrees(radians);
		//degrees += getAditionalDegrees(diffX, diffY);
		System.out.println(degrees);
		return  degrees;
		
		
	}
	private int getAditionalDegrees(int diffX, int diffY) {
		int degrees = 0;
		if(diffX < 0 && diffY >= 0)
			degrees = 90;
		if(diffX <= 0 && diffY < 0)
			degrees = 180;
		if(diffX > 0 && diffY <= 0)
			degrees = 270;
		return degrees;
	}
	private double radiansToDegrees(double radians) {
		return radians * (180/(Math.PI));
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
