package Management;
import java.util.ArrayList;

public class matrix {
    
    private String[][] c;
    private ArrayList<robot> robot;
    private ArrayList<String> scan;
    private ArrayList<missile> missiles = new ArrayList<missile>();
    private static int size;
 
    //generating matrix
    public matrix(int size) {
    	matrix.size = size;
        c = new String[matrix.size][matrix.size];
        robot = new ArrayList<robot>();
        scan = new ArrayList<String>();
        for (int i = 0; i < matrix.size; i++) {
            for (int j = 0; j < matrix.size; j++) {
                c[j][i] = "_ ";
            }  
        }
    }
    
    public ArrayList<missile> getMissiles() {
    	return missiles;
    }
    
    public static int getSize() {
    	return size;
    }
    
    public ArrayList<String> scanField() {
    	return this.scan;
    }
    
    public void addRobot(robot r) {
    	robot.add(r);
    }
    
    public void addToArena(String obj, int x, int y) {
    	c[x][y] = obj + " ";
    }
    
    public void addMissile(missile m) {
    	missiles.add(m);
    }
    
    public int numRobots() {
    	return robot.size();
    }
    
    public void removeRobot(int i) {
    	robot.remove(i);
    }
    
    public robot getRobot(int i) {
    	return robot.get(i);
    }

    //print arena 
    public void print() {
        //System.out.println();
        for (int i = 0; i < matrix.size; i++) {
        	System.out.print("+" );
            for (int j = 0; j < matrix.size; j++) {
                System.out.print(c[j][i]);
            }
            System.out.println("+");
        }
        //System.out.println();
    }
    
    public robot checkCollapses() {
    	for (int i = 0; i < this.missiles.size(); i++) {
    		for (int j = 0; j < this.robot.size(); j++) {
    			if(this.missiles.get(i).getX() == this.robot.get(j).getX() && this.missiles.get(i).getY() == this.robot.get(j).getY()) {
    				addToArena("▓", robot.get(j).getX(), robot.get(j).getY());
    				System.out.println("Robot " + robot.get(j).getName() + " explodes.");
    				this.robot.remove(j);
    				this.missiles.remove(i);
    			}
    		}
    	}
    	
    	return isWinner();
    }
    
    public robot isWinner() {
    	if(this.robot.size() == 1) {
    		return this.robot.get(0);
    	}else {
    		return null;
    	}
    	
    }
    
    //print borders
    public void defineBorders() {
    	String border = new String(new char[matrix.size+1]).replace("\0", "+ ");
    	System.out.println(border);
    }
    
    
    public void update() {
        for (int i = 0; i < matrix.size; i++) {
            for (int j = 0; j < matrix.size; j++) {
                c[j][i] = "_ ";
            }  
        }
        
        scan.clear();		
        missiles.clear();
        
        for (int i = 0; i < robot.size(); i++) {
        	robot.get(i).moveMissiles();
            c[robot.get(i).getX()][robot.get(i).getY()] = robot.get(i).getName();
            scan.add(""+robot.get(i).getName()+","+robot.get(i).getX()+","+robot.get(i).getY());
            robot.get(i).scan();
        }
        
    }

}