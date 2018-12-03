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
	private AnimationTimer animateFireTimer;
	
	StackPane mapStack;
	int mapSize;
	int gridSize;
	String arrowFile = "images/Projectil/Arrow.png";
	String fireBallFile = "images/Projectil/FireBall.png";
	String smokeFile = "images/Projectil/Smoke.png";
	Coordinate start;
	Coordinate end;
	
	int projectileSpeed = 10;
	double explodeRate = .3;
	
	ImageView arrow;
	ImageView fireBall;
	ImageView imageView;
	StackPane image;
	
	boolean atDestination;
	boolean fullExplotion;
	boolean smokeExpanded;
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
		//animationLayer.setMinSize(mapSize, mapSize);
		initilizeAnimationTimers();
		
	}
	private void initilizeAnimationTimers() {
		animateArrowTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				animateArrow();
			}
		};
		animateFireTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				animateFire();
			}
		};
	}
	
	public Pane getAnimationLayer() {
		return animationLayer;
	}
	public void startArrowAnimation(Coordinate start, Coordinate end) {
		calcAnimationVariables(start, end);
		//Add Image to view
		imageView = this.setUpImage(start, end, arrowFile, 1);
		prepAnimationLayer(imageView);
		
		this.animateArrowTimer.start();
	}
	public void animateArrow() {
		if(atDestination == false)
			atDestination = moveProjectile(0,true);
		else
			this.animationEnd(animateArrowTimer);
//		xMoved += Math.abs(xMove);
//		yMoved += Math.abs(yMove);
//		image.setTranslateX(image.getTranslateX() + (xMove));
//		image.setTranslateY(image.getTranslateY() + yMove);
//		if(angle != 0 && angle != 90 && angle != 180 && angle != 270) {
//			if(xMoved >= diffX || yMoved >= diffY) {
//				animationEnd(this.animateArrowTimer);
//			}
//		}
//		else if(((angle == 90 || angle == 270) && yMoved >= diffY) || ((angle == 0 || angle == 180) && xMoved >= diffX)) {
//			animationEnd(this.animateArrowTimer);
//		}	
	}
	private boolean moveProjectile(int projectileIndex,boolean incrementMoved) {
		boolean atDestination = false;
		if(incrementMoved) {
			xMoved += Math.abs(xMove);
			yMoved += Math.abs(yMove);
		}
		image.getChildren().get(projectileIndex).setTranslateX(image.getChildren().get(projectileIndex).getTranslateX() + (xMove));
		image.getChildren().get(projectileIndex).setTranslateY(image.getChildren().get(projectileIndex).getTranslateY() + yMove);
		if(angle != 0 && angle != 90 && angle != 180 && angle != 270) {
			if(xMoved >= diffX || yMoved >= diffY) {
				atDestination = true;
			}
		}
		else if(((angle == 90 || angle == 270) && yMoved >= diffY) || ((angle == 0 || angle == 180) && xMoved >= diffX)) {
			atDestination = true;
		}
		System.out.println("AtDestination: " + atDestination);
		return atDestination;
		
	}
	
	public void startFireAnimation(Coordinate start, Coordinate end) {
		calcAnimationVariables(start, end);
		//Add Image to view
		fullExplotion = false;
		imageView = this.setUpImage(start, end, this.fireBallFile, .3);
		
		prepAnimationLayer(imageView);
		ImageView smoke = setUpImage(start,end,this.smokeFile,0);
		image.getChildren().add(smoke);
		this.animateFireTimer.start();
	}
	private void animateFire() {
		
		if(atDestination == false) {
			atDestination = this.moveProjectile(0,true);
			moveProjectile(1,false);
		}
		else {
			if(smokeExpanded == false) {
				if(fullExplotion == false) {
					fullExplotion = fireExpand(0,explodeRate,.05);
				}
				smokeExpanded = fireExpand(1,explodeRate-.1,.05);
			}
			else
				this.animationEnd(animateFireTimer);
		}
		
	}
	private boolean fireExpand(int projectileIndex,double explodeRate,double fadeRate) {
		image.setOpacity(image.getOpacity() - fadeRate);
		image.getChildren().get(projectileIndex).setScaleX(image.getChildren().get(projectileIndex).getScaleX() + explodeRate);
		image.getChildren().get(projectileIndex).setScaleY(image.getChildren().get(projectileIndex).getScaleY() + explodeRate);
		if(image.getChildren().get(projectileIndex).getScaleX() >= 3)
			return true;
		return false;
	}
	private void prepAnimationLayer(ImageView imageView) {
		image =  new StackPane();
		image.getChildren().add(imageView);
		image.setPrefSize(gridSize, gridSize);
		animationLayer.getChildren().add(image);
		mapStack.getChildren().add(1, animationLayer);
	}
	private void animationEnd(AnimationTimer animation) {
		animation.stop();
		this.mapStack.getChildren().remove(animationLayer);
		while(image.getChildren().size() > 0)
			image.getChildren().remove(0);
		animationLayer.getChildren().remove(image);
	}
	private void calcAnimationVariables(Coordinate start, Coordinate end) {
		atDestination = false;
		smokeExpanded = false;
		//Calculate distance in X and Y  to target
		diffX = Math.abs(distanceX(start, end)) * this.gridSize;
		diffY = Math.abs(distanceY(start,end)) * this.gridSize;
		//Reset x and y moved to 0
		xMoved = 0;
		yMoved = 0;
		//Calculating prijectile angle
		angle = calculateAngle(start, end);
		double radians = Math.toRadians(angle);
		//Calculating X and Y move speeds
		xMove = projectileSpeed * Math.cos(radians);
		yMove = projectileSpeed * Math.sin(radians);
	}
//	private ImageView placeArrow(Coordinate startLocation, Coordinate targetLocation) {
//		ImageView arrowImage = new ImageView(new Image(arrowFile));
//		arrowImage.setTranslateX(startLocation.getX()*gridSize);
//		arrowImage.setTranslateY(startLocation.getY()*gridSize);
//		arrowImage.setRotate(calculateAngle(startLocation,targetLocation));
//		arrowImage.setFitHeight(gridSize);
//		arrowImage.setFitWidth(gridSize);
////		if(animationLayer.getChildren().size() > 0) {
////			animationLayer.getChildren().remove(0);
////		}
//		animationLayer.getChildren().add(arrowImage);
////		if(mapStack.getChildren().size() > 1)
////			mapStack.getChildren().remove(1);
//		mapStack.getChildren().add(1, animationLayer);
//		return arrowImage;
//	}
	private ImageView setUpImage(Coordinate startLocation, Coordinate targetLocation,String imageFile,double scale) {
		ImageView image = new ImageView(new Image(imageFile));
		image.setTranslateX(startLocation.getX()*gridSize);
		image.setTranslateY(startLocation.getY()*gridSize);
		image.setRotate(calculateAngle(startLocation,targetLocation));
		image.setFitHeight(gridSize);
		image.setFitWidth(gridSize);
		image.setScaleX(scale);
		image.setScaleY(scale);
//		animationLayer.getChildren().add(image);
		return image;
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
