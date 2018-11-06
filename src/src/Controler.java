package src;

import java.util.Observable;
import java.util.Random;

import javafx.event.EventHandler;
//import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import mapObjects.Coordinate;
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
		player.setSpeed(5);
		//player.newTurn();
		System.out.println("Player Initialized");
	}
	private void playerMovement(KeyCode eCode)
	{
		
		boolean moveResult = false;
		switch(eCode)
		{
			case A:
				moveResult = player.readInput('A',view.map);
				break;
			case D:
				moveResult = player.readInput('D',view.map);				
				break;
			case W:
				moveResult = player.readInput('W',view.map);
				break;
			case S:
				moveResult = player.readInput('S',view.map);
				break;
		}
		if(!moveResult) {
			setChanged();
			notifyObservers("Somthing is in the way!");
		}
	}
	public void startPlay()
	{
		player.newTurn();
	}

	private void interactAttack()
	{
		Coordinate cursorLocation = view.map.getCursor().getLocation();
		MapLocation mapLocation = view.map.getMapLocation()[cursorLocation.getX()][cursorLocation.getY()];
		if(mapLocation.getEntity() != null) {
			Entity target = mapLocation.getEntity();
			player.attack(target,view.map);
			if(target.checkDead()){
				view.map.removeEntity(target);
				setChanged();
				notifyObservers("Player Killed! " + mapLocation.getEntity());
			}
			else {
				setChanged();
				notifyObservers("Player Attacking " + mapLocation.getEntity() + " for " + player.getStr() + " damage");
			}
		}
		if(mapLocation.getObstacle() != null) {
			Obstacle target = mapLocation.getObstacle();
			player.attack(target,view.map);
			setChanged();
			notifyObservers("Player Attacking " + mapLocation.getObstacle());
		}
		setChanged();
		notifyObservers("Theres nothing there to attack!");
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
			case X://Testing Obstacle breaking  TODO Remove
				for(int i = 0; i < view.map.getMapLocation().length; i++)
					for(int j = 0; j < view.map.getMapLocation()[i].length; j++)
					{
						
						if(view.map.getMapLocation()[i][j].getObstacle() != null)
						{
							view.map.getMapLocation()[i][j].getObstacle().damage(5, view.map);
						}
					}
			case Z://Testing health Globe  TODO Remove
				double scale = view.healthGlobe.getChildren().get(1).getScaleY();
				double pos = view.healthGlobe.getChildren().get(1).getTranslateY();
				view.healthGlobe.getChildren().get(1).setTranslateY(pos + 5);
				player.setStr(player.getStr() + 1);
				view.updateStatGrid();;
				//view.healthGlobe.getChildren().get(1).setScaleY(scale - .1);
					
		}//End Switch
	}

	public String getCurorInfo()
	{
		String info;
		Coordinate cursorLocation = new Coordinate(view.map.getCursor().getLocation());
		info = view.map.getMapLocation()[cursorLocation.getX()][cursorLocation.getY()].toString();
		return info;
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
				enemyTurns();
			}
		}
	}
	/*
	 * Loops through all enemys on map and exicutes its turn
	 * When done starts a new turn for the player
	 */
	private void enemyTurns() {

		for(int eIndex = 0; eIndex < view.map.getEnemys().size(); eIndex++) {//Loops through each enemy on the map
			Enemy enemy = view.map.getEnemys().get(eIndex);
			enemy.turn(player,view.map,view.map.getExit());
		}
		player.newTurn();		//When enemys are done reset player turn 
	}

	
//	public void gameLoop()
//	{
//		while(player.hp > 0)
//		{
//			if()
//			
//			System.out.println("player still alive");
//		}
//	}
}//End of Class
