package src;

import java.util.Observable;
import java.util.Random;

import javafx.concurrent.Task;
import javafx.event.EventHandler;
//import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import mapObjects.*;

public class Controler extends Observable implements EventHandler<KeyEvent>{
	String endTurnMsg = "   (Press Shift To End Turn Early)";
	View view;
	Character player;
	//TargetingCursor cursor;
	/*
	 * Constructor for controler
	 */
	public Controler(View view)
	{
		System.out.println("Controler Created");
		this.view = view;
		initializePlayer();
		addObserver(view);
	}
	/*
	 * Creates a new instance of the player
	 */
	private void initializePlayer()
	{
		player = new Character("Player",view.gridSize);
		player.setObserver(view);
		System.out.println("Player Initialized");
	}
	private void playerMovement(KeyCode eCode)
	{
		
		boolean moveResult = false;
		String moveError = "Not Enough Action Points To Move!";
		if(player.hasMovement()) {
			moveError = "Something is in the way!";
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
		}
		//System.out.println("Move Result: " + moveResult + "-->" +  moveError);
		if(!moveResult) {
			sendActionError(moveError + this.endTurnMsg);
		}
		else{
			view.map.tallyAction();
		}
		
	}
	private void sendActionError(String msg) {
		Text moveErrorMsg = new Text(msg);
		moveErrorMsg.setFill(Color.ANTIQUEWHITE);
		setChanged();
		notifyObservers(moveErrorMsg);
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

		if(player.hasAttacks()) {
			if (player.getAttackType() == Entity.AttackType.MELLE) {
				if (mapLocation.getEntity() != null && player.isAdjacent(cursorLocation)) {
					Entity target = mapLocation.getEntity();
					view.map.updateDamageDealt(player.attack(target, view));
					view.map.tallyAction();
					if (target.checkDead()) {
						view.map.getEnemys().remove(target);
						view.map.removeEntity(target);
					}
				} else if (mapLocation.getObstacle() != null && player.isAdjacent(cursorLocation)) {
					Obstacle target = mapLocation.getObstacle();
					view.map.updateDamageDealt(player.attack(target, view));
					view.map.tallyAction();
					setChanged();
					notifyObservers("Player Attacking " + mapLocation.getObstacle());
				}
				else
					sendActionError("Out Of Range!\nPress Tab to switch Attack Styles!");
			}
			else if(player.getAttackType().equals(Entity.AttackType.RANGED)){
				System.out.println("test outside");
				if(player.getEquipedWepon() == null || (player.getEquipedWepon() != null && !(player.getEquipedWepon().getType().equals(Wepon.Type.RANGED)))){
					System.out.println("test inside");
					sendActionError("You're not wearing a ranged weapon!");
				}
				//else if (!(player.getEquipedWepon().getType().equals(Wepon.Type.RANGED))) {
				//	System.out.println("test inside");
				//	sendActionError("You're not wearing a ranged weapon!");}
				 else {
					if (mapLocation.getEntity() != null) {
						Entity target = mapLocation.getEntity();
						view.map.updateDamageDealt(player.attack(target,view));
//						view.animationLayer.startArrowAnimation(player.getLocation(), view.map.getCursor().getLocation());
						view.map.tallyAction();
						if (target.checkDead()) {
							view.map.getEnemys().remove(target);
							view.map.removeEntity(target);
						}
					} else if (mapLocation.getObstacle() != null) {
						Obstacle target = mapLocation.getObstacle();
						view.map.updateDamageDealt(player.attack(target,view));
//						view.animationLayer.startArrowAnimation(player.getLocation(), view.map.getCursor().getLocation());
						view.map.tallyAction();
						setChanged();
						notifyObservers("Player Attacking " + mapLocation.getObstacle());
					} else
						sendActionError("There's nothing there to attack!");
				}
			}
			else{
				if(mapLocation.getEntity() == null && mapLocation.getObstacle() == null){
					view.map.updateDamageDealt(player.magicAttack(cursorLocation,view.map));
					view.map.tallyAction();
				}
				else if (mapLocation.getEntity() != null) {
					Entity target = mapLocation.getEntity();
					view.map.updateDamageDealt(player.attack(target, view));
					view.map.tallyAction();
					if (target.checkDead()) {
						view.map.getEnemys().remove(target);
						view.map.removeEntity(target);
					}
				} else if (mapLocation.getObstacle() != null) {
					Obstacle target = mapLocation.getObstacle();
					view.map.updateDamageDealt(player.attack(target, view));
					view.map.tallyAction();
					setChanged();
					notifyObservers("Player Attacking " + mapLocation.getObstacle());
				}
				else
					sendActionError("There's nothing there to attack!");
			}
		}
		else
			sendActionError("Not Enough Action Points To Attack!" + this.endTurnMsg);
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
				player.cycleAttackType();
				System.out.println("Cycle Attack Type");
				break;
			case G:
				Text info = new Text(getCurorInfo());
				info.setFill(Color.BLUEVIOLET);
				this.setChanged();
				this.notifyObservers(info);
//				System.out.println("InterAct/Attack");
				break;
			case E:
				interact();
				break;
			case SHIFT:
				endTurn();
				break;
			case X://Testing Obstacle breaking  TODO Remove

				player.damag(2);
					break;
			case Z://Testing health Globe  TODO Remove
				player.heal(1);
				view.animationLayer.startFireAnimation(player.getLocation(),view.map.getCursor().getLocation());
				
					
		}//End Switch
	}

	public String getCurorInfo()
	{
		String info;
		Coordinate cursorLocation = new Coordinate(view.map.getCursor().getLocation());
		MapLocation mapLocation = view.map.getMapLocation()[cursorLocation.getX()][cursorLocation.getY()];
//		info = mapLocation.toString();
		info = "";
		if(mapLocation.lookAtLoot() != null) {
			Loot loot = mapLocation.lookAtLoot();
			info = loot.toString();
		}
		if(mapLocation.getObstacle() != null) {
			Obstacle obs = mapLocation.getObstacle();
			info = obs.description();
		}
		if(mapLocation.getEntity() != null) {
			Entity entity = mapLocation.getEntity();
			if(entity instanceof Enemy) {
				Enemy enemy = (Enemy)entity;
				info = enemy.description();
			}
			else if(entity instanceof Character) {
				info = "Thats You, Silly!!!";
			}
		}
		
		
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
		if(player.canAct() && player.isMyTurn())
		{
			KeyCode eCode = event.getCode();
			keyCodeSwitch(eCode);
			if(!player.canAct()){//Player Turn over
				javafx.application.Platform.runLater( () ->endOfTurnMsg());
				startEnemyTurnThread();
				
			}
		}
	}
	
	private void endTurn() {
		player.endTurn();
		startEnemyTurnThread();
		endOfTurnMsg();
	}
	private void endOfTurnMsg() {
		Text waitMsg = new Text("Waiting on Enemys");
		waitMsg.setFill(Color.AQUA);
		this.setChanged();
		this.notifyObservers(waitMsg);
	}
	private void startEnemyTurnThread() {
		
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
	/*
	 * Loops through all enemy's on map and executes its turn
	 * When done starts a new turn for the player
	 */
	public void enemyTurns() {

		for(int eIndex = 0; eIndex < view.map.getEnemys().size(); eIndex++) {//Loops through each enemy on the map
			Enemy enemy = view.map.getEnemys().get(eIndex);
			Task<Integer> task = new Task<Integer>() {		//The task is the enemy turn
				@Override
				protected Integer call() throws Exception {
					enemy.turn(player,view,view.map.getExit());
					return null;
				}
			};

			Thread enemyThread = new Thread(task);	//Each enemy turn executes on a separate thread
			
			enemyThread.start();
			try {
				enemyThread.join();						//Join on enemy turn before next enemy taks turn
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		javafx.application.Platform.runLater( () ->player.newTurn());		//When enemy's are done reset player turn
		

	}


}//End of Class
