package Management;

public class missile {
	robot owner;
	String name = "•";
	private int x;
	private int y;
	private String dir;
	
	public missile(robot owner, int x, int y, String dir) {
		this.owner = owner;
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	//moves in its direction
	public void move() {
		switch(this.dir) {
		  case "r":
		    this.x++;
		    break;
		  case "l":
			this.x--;
		    break;
		  case "u":
			this.y--;
			break;
		  case "d":
			this.y++;
			break;    
		}
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}

	public void explode() {
		owner.removeMissile(this);
		System.out.println("Missile esploso");
	}


}
