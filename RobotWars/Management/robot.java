package Management;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public abstract class robot {
    
    protected int x;
    protected int y;
    private String name;
    private String author = "Unknown";
    protected matrix arena;
    protected ArrayList<String> scan = new ArrayList<String>();
    protected ArrayList<missile> myMissiles = new ArrayList<missile>();
    
    public robot(int x, int y, String name, matrix arena) {
        this.x = x;
        this.y = y;
        this.name = name + " ";
        this.arena = arena;
    }

    //strategy() of every robot define its next operation (move or shoot)
	//return format: operation
	//m = move, s = shoot
    //m formula = m, x, y
    //s formula = s, direction(u,d,l,r)
    public abstract String strategy();
    
    public void scan() {
    	scan = arena.scanField();
    }
    
    public void setAuthor(String a) {
    	this.author = a;
    }
    
    public String getAuthor() {
    	return this.author;
    }
    
    public void addMissile(missile m) {
    	this.myMissiles.add(m);
    }
    
    public void moveMissiles() {
    	int explX, explY;
    	for(int j = 0; j < this.myMissiles.size(); j++) {
    		explX = this.myMissiles.get(j).getX();
    		explY = this.myMissiles.get(j).getY();
    		this.myMissiles.get(j).move();
    		arena.addMissile(this.myMissiles.get(j));
    		if(controller.controlShoots(this.myMissiles.get(j))) {		
    			arena.addToArena(this.myMissiles.get(j).name, this.myMissiles.get(j).getX(), this.myMissiles.get(j).getY());
    			//System.out.println("in robot " + this.myMissiles.get(j).name+","+ this.myMissiles.get(j).getX()+","+ this.myMissiles.get(j).getY());
    		}else{
    			arena.addToArena("▓", explX, explY);
    		}
    	}
    }
    
    public ArrayList<missile> getMissiles() {
    	return this.myMissiles;
    }
    
    public String print() {
    	return "Robot " + this.getName() + " (X:" + this.x + " Y:" + this.y + ") \t|";
    }
    
    public int getX() {
    	return this.x;
    }
    
    public int getY() {
    	return this.y;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public void setX(int x) {
    	this.x = x;
    }
    
    public void setY(int y) {
    	this.y = y;
    }

	public void removeMissile(missile m) {
		for(int i=0; i < myMissiles.size(); i++) {
			if(myMissiles.get(i) == m) {
				myMissiles.remove(i);
			}
		}
		
	}
}