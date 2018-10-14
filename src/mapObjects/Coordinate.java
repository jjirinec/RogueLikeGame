package mapObjects;

public class Coordinate {
	private int x;
	private int y;
	
	public Coordinate(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX() {return x;}
	public int getY() {return y;}
	
	public void setX(int x)
	{
		this.x = x;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	
	public boolean equals(Object obj)
	{
		boolean result = false;
		if(obj != null)
			if(obj instanceof Coordinate)
			{
				Coordinate newObj = (Coordinate)obj;
				if(this.x == newObj.x && this.y == newObj.y)
					result = true;
			}
		return result;
	}

}
