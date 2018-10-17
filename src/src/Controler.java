package src;

import java.util.Observable;

import javafx.event.EventHandler;
//import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import mapObjects.Obstacle;

public class Controler extends Observable implements EventHandler<KeyEvent>{
	
	View view;
	Character player;
	
	public Controler(View view)
	{
		this.view = view;
		player = new Character(view.gridSize);
		addObserver(view);
		System.out.println("Controler Created");
		//this.player = new Player();
	}

	public void playerMovement(KeyCode eCode)
	{
		setChanged();
//		System.out.print("Moving Player ");
		switch(eCode)
		{
			case A:
				notifyObservers("Player moved left");
//				System.out.println("Left");
				break;
			case D:
				notifyObservers("Player moved Right");
//				System.out.println("Right");
				break;
			case W:
				notifyObservers("Player moved Up");
//				System.out.println("Up");
				break;
			case S:
				notifyObservers("Player moved Down");
//				System.out.println("Down");
				break;
		}
	}




	@Override
	public void handle(KeyEvent event) {
//		System.out.println("KeyEvent: " + event.getCode());
		KeyCode eCode = event.getCode();
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
						
		}
		
	}

}
