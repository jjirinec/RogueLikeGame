package src;

import java.util.Observable;
import java.util.Random;

import javafx.concurrent.Task;
import javafx.event.EventHandler;
//import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import mapObjects.Container;
import mapObjects.Coordinate;
import mapObjects.Loot;
import mapObjects.Obstacle;
import mapObjects.TargetingCursor;

public class Controler extends Observable implements EventHandler<KeyEvent>{
	
	View view;
	Character player;
	//TargetingCursor cursor;
	
	/*
	 * Constructor for controler
	 */
	public Controler(View view)
	{
		this.view = view;
		initializePlayer();
		//System.out.println(player.curentActions);
		addObserver(view);
		System.out.println("Controler Created");
		//this.player = new Player();
	}
	/*
	 * Creates a new instance of the player
	 */
	private void initializePlayer()
	{
		
		player = new Character(view.gridSize);
		//cursor = new TargetingCursor(view.gridSize,new Coordinate(player.getLocation()));
		player.setObserver(view);
		//player.setSpeed(6);
		//player.newTurn();
		System.out.println("Player Initialized");
		
		System.out.println(player.calcNextLvl(1));
		System.out.println(player.calcNextLvl(2));
		System.out.println(player.calcNextLvl(3));
		System.out.println(player.calcNextLvl(4));
		System.out.println(player.calcNextLvl(5));
	}
	private void playerMovement(KeyCode eCode)
	{
		
		boolean moveResult = false;
		switch(eCode)
		{
			case A:
				if(player.hasExited(view.map.getExit(), 'A'))
						System.out.println("Exit");
				moveResult = player.readInput('A',view.map);
				break;
			case D:
				if(player.hasExited(view.map.getExit(), 'D'))
						System.out.println("Exit");
				moveResult = player.readInput('D',view.map);				
				break;
			case W:
				if(player.hasExited(view.map.getExit(), 'W'))
						System.out.println("Exit");
				moveResult = player.readInput('W',view.map);
				break;
			case S:
				if(player.hasExited(view.map.getExit(), 'S'))
						System.out.println("Exit");
				moveResult = player.readInput('S',view.map);
				break;
		}
		if(!moveResult) {
			setChanged();
			notifyObservers("Something is in the way!");
		}
	}
	public void startPlay()
	{
		player.newTurn();
	}
	private void interact() {
		Coordinate cursorLocation = view.map.getCursor().getLocation();
		MapLocation mapLocation = view.map.getMapLocation()[cursorLocation.getX()][cursorLocation.getY()];
		if(mapLocation.getObstacle() != null) {
			Obstacle obstacle = mapLocation.getObstacle();
			if(obstacle instanceof Container) {
				Container container = (Container) obstacle;
				System.out.println(container.getContents().size());
				if(container.getContents().size() >= 0) {
					System.out.println(container.getContents());
					view.containerView(container,player,view.map);
				}
			}
		}
	}
	private void interactAttack()
	{
		Coordinate cursorLocation = view.map.getCursor().getLocation();
		MapLocation mapLocation = view.map.getMapLocation()[cursorLocation.getX()][cursorLocation.getY()];
		if(mapLocation.getEntity() != null && player.isAdjacent(cursorLocation)) {
			Entity target = mapLocation.getEntity();
			view.map.updateDamageDealt(player.attack(target,view.map));
			if(target.checkDead()) {
				view.map.getEnemys().remove(target);
                view.map.removeEntity(target);

            }
		}

		if(mapLocation.getObstacle() != null && player.isAdjacent(cursorLocation)) {
//
//			Obstacle obstacle = mapLocation.getObstacle();
////			obstacle.damage(5, view.map);

//			if(obstacle instanceof Container) {
//				Container container = (Container) obstacle;
//				System.out.println(container.getContents().size());
//				if(container.getContents().size() > 0) {
//					System.out.println(container.getContents());
//					view.containerView(container,player,view.map);
//				}
//			}


			Obstacle target = mapLocation.getObstacle();
			player.attack(target,view.map);
			setChanged();
			notifyObservers("Player Attacking " + mapLocation.getObstacle());
		}

		if(mapLocation.hasLoot() && player.isAdjacent(cursorLocation)) {
			Loot item = mapLocation.getLoot();
			player.grabLoot(item);
			view.map.addToLootColected(item);
			view.map.getStackPane()[cursorLocation.getX()][cursorLocation.getY()].getChildren().remove(item.getImageView());

		}
		else {
			setChanged();
			notifyObservers("Theres nothing there to attack!");
		}

	}
	/*
	 * Maps each key code to the desired task
	 * 		WASD = player movement
	 * 		ArowKeys = targating cursor movment
	 * 		Space = interact/attack/getinfo (based on attack type and location of cursor)
	 * 		Tabe = cycle attac type
	 */
	private void keyCodeSwitch(KeyCode eCode)
	{
		switch(eCode)
		{
			case A:
			case D:
			case W:
			case S:
				playerMovement(eCode);
				break;
			case UP:
			case DOWN:
			case LEFT:
			case RIGHT:
//				setChanged();
//				view.hudMsg.setFill(Color.GREEN);
//				notifyObservers("Curser Moving" + eCode);
//				System.out.println("Moving Target Curser: " + eCode);
				view.map.getCursor().move(eCode, view.map);
				break;
			case SPACE:
				interactAttack();
				break;
			case TAB:
				this.setChanged();
				this.notifyObservers("Cycling Attack Type");
				System.out.println("Cycle Attack Type");
				break;
			case G:
				String info = getCurorInfo();
				this.setChanged();
				this.notifyObservers("\nLocationInfo: " + info);
				System.out.println("InterAct/Attack");
				break;
			case E:
				interact();
				break;
			case X://Testing Obstacle breaking  TODO Remove

				player.damag(2);
					break;
			case Z://Testing health Globe  TODO Remove
				player.heal(1);
					
		}//End Switch
	}

	public String getCurorInfo()
	{
		String info;
		Coordinate cursorLocation = new Coordinate(view.map.getCursor().getLocation());
		info = view.map.getMapLocation()[cursorLocation.getX()][cursorLocation.getY()].toString();
		return info;
	}

	public Enemy getEnemyAtCursor(){
		Coordinate cursorLocation = new Coordinate(view.map.getCursor().getLocation());
		Entity enemyAt = view.map.getMapLocation()[cursorLocation.getX()][cursorLocation.getY()].getEntity();
		return(Enemy)enemyAt;
	}
	/*
	 * Handles all Keyboard input
	 * Will only accept input when it is the players turn
	 * Will wait for next key event as long as the player still has valid actions
	 * When player no longer has actions avilable this method calls enemyTurns();
	 * (non-Javadoc)
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	@Override
	public void handle(KeyEvent event) {
		if(player.canAct())
		{
			KeyCode eCode = event.getCode();
			keyCodeSwitch(eCode);
			if(!player.canAct()){//Player Turn over
				System.out.println("End of Players turn");
//				enemyTurns();
				Task<Enemy> task = new Task<Enemy>() {
					@Override
					protected Enemy call() throws Exception {
						enemyTurns();
						return null;
					}
				};
				Thread enemyTurnsThread = new Thread(task);
				enemyTurnsThread.start();
			}
		}
	}
	/*
	 * Loops through all enemy's on map and executes its turn
	 * When done starts a new turn for the player
	 */
	public void enemyTurns() {

		for(int eIndex = 0; eIndex < view.map.getEnemys().size(); eIndex++) {//Loops through each enemy on the map
			System.out.println("Enemy " + (eIndex +1) + " turn");
			Enemy enemy = view.map.getEnemys().get(eIndex);
			Task<Integer> task = new Task<Integer>() {		//The task is the enemy turn
				@Override
				protected Integer call() throws Exception {
					enemy.turn(player,view.map,view.map.getExit());
					return null;
				}
			};
			Thread enemyThread = new Thread(task);		//Each enemy turn executes on a separate thread
			enemyThread.start();
			try {
				enemyThread.join();						//Join on enemy turn before next enemy taks turn
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Turn over!!!");
		}
		javafx.application.Platform.runLater( () ->player.newTurn());		//When enemy's are done reset player turn 
		System.out.println("All enemy turns Done:");
		

	}


}//End of Class
