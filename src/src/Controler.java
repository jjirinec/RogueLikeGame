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
	
	public Controler(View view)
	{
		this.view = view;
		initializePlayer();
		//System.out.println(player.curentActions);
		addObserver(view);
		System.out.println("Controler Created");
		//this.player = new Player();
	}
	private void initializePlayer()
	{
		
		player = new Character(view.gridSize);
		//cursor = new TargetingCursor(view.gridSize,new Coordinate(player.getLocation()));
		player.setObserver(view);
		player.speed = 0;
		player.newTurn();
	}
	private void playerMovement(KeyCode eCode)
	{
		setChanged();
//		System.out.print("Moving Player ");
		switch(eCode)
		{
			case A:
				if(player.readInput('A',view.map)){
					notifyObservers("Player moved left");
				}
				else{
					notifyObservers("Invalid Move!");
				}
//				System.out.println("Left");
				break;
			case D:
				notifyObservers("Player moved Right");
				player.readInput('D',view.map);
//				System.out.println("Right");
				break;
			case W:
				notifyObservers("Player moved Up");
				player.readInput('W',view.map);
//				System.out.println("Up");
				break;
			case S:
				notifyObservers("Player moved Down");
				player.readInput('S',view.map);
//				System.out.println("Down");
				break;
		}
	}
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
				setChanged();
				view.hudMsg.setFill(Color.GREEN);
				notifyObservers("Curser Moving" + eCode);
				System.out.println("Moving Target Curser: " + eCode);
				view.map.getCursor().move(eCode, view.map);
				break;
			case SPACE:
				this.setChanged();
				
				this.notifyObservers("InterActing or Attacking");
				System.out.println("InterAct/Attack");
				break;
			case TAB:
				this.setChanged();
				this.notifyObservers("Cycling Attack Type");
				System.out.println("Cycle Attack Type");
				break;
			case G:
				this.setChanged();
				view.hudMsg.setFill(Color.BLUE);
				this.notifyObservers("Getting Info from Curser Location");
				System.out.println("Getting Info from Curser Location");
				break;
			case X:
				for(int i = 0; i < view.map.getMapLocation().length; i++)
					for(int j = 0; j < view.map.getMapLocation()[i].length; j++)
					{
						
						if(view.map.getMapLocation()[i][j].getObstacle() != null)
						{
							view.map.getMapLocation()[i][j].getObstacle().damage(5, view.map);
						}
					}
					
		}//End Switch
	}


	@Override
	public void handle(KeyEvent event) {
		if(player.canAct())
		{
			KeyCode eCode = event.getCode();
			keyCodeSwitch(eCode);
			if(!player.canAct())
			{
				System.out.println("End of Players turn");
				enemyTurns();
			}
		}
	}
	private void enemyTurns() {

		for(int eIndex = 0; eIndex < view.map.getEnemys().size(); eIndex++) {//Loops through each enemy on the map
			Enemy enemy = view.map.getEnemys().get(eIndex);
			enemy.turn(player,view.map);
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
