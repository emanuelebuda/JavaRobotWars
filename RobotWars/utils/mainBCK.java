package Public;

import java.util.ArrayList;

import Management.interpreter;
import Management.matrix;
import Robots.horizontal;
import Robots.vertical;
import Robots.rand;
import Robots.coward;
//import3
//import4
//import5
import java.util.concurrent.ThreadLocalRandom;

public class main {
	
    private static int SIZE = 20;
    private static int TURNS = 100;
	
    public static void main(String[] args) {
        SIZE = Integer.parseInt(args[0]);
        TURNS = Integer.parseInt(args[1]);
    	boolean winner = false;
        matrix arena = new matrix(SIZE);
        String op[];
        ArrayList<String> data = new ArrayList<String>();
        
        //default players
        arena.addRobot(new rand(ThreadLocalRandom.current().nextInt(0, SIZE-1), ThreadLocalRandom.current().nextInt(0, SIZE-1),"1",arena));
        arena.addRobot(new coward(ThreadLocalRandom.current().nextInt(0, SIZE-1),ThreadLocalRandom.current().nextInt(0, SIZE-1),"2",arena));
        
        //player3
        //player4
        //player5

        arena.defineBorders();
        arena.update();
        arena.print();
        arena.defineBorders();
        

        for (int j = 0; j < TURNS; j++) {
            try {
                Thread.sleep(1500);                 //1000 milliseconds is one second
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            
            System.out.println("\n");
            System.out.println("LOG");

            data.clear();
            for (int i = 0; i < arena.numRobots(); i++) {
            	interpreter.interpret(arena.getRobot(i));
            	data.add(arena.getRobot(i).print());
            } 
            
            arena.update();
            if (arena.checkCollapses() != null) {
            	winner = true;
            }
            
            System.out.println("\nTurn #" + (j+1));
            arena.defineBorders();
            arena.print();
            arena.defineBorders();
            
            System.out.println("");
            System.out.println("DATA PANEL");
            for(int z = 0; z < data.size(); z++) {
            	System.out.println(data.get(z));
            }
            
            
            
            if(winner) {
            	System.out.println("▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼");
            	System.out.println("ROBOT " + arena.checkCollapses().getName());
            	System.out.println("is the WINNER of this battle!");
            	System.out.println("Congratulations to the author of the AI, " + arena.checkCollapses().getAuthor() + "!!");
            	System.out.println("▲ ▲ ▲ ▲ ▲ ▲ ▲ ▲ ▲ ▲ ▲ ▲");
            	j = TURNS-1;
            }
        }
        
        if(!winner) {
        	System.out.println("▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼ ▼");
        	System.out.println("It's a DRAW");
        	System.out.println("▲ ▲ ▲ ▲ ▲ ▲ ▲ ▲ ▲ ▲ ▲ ▲");
        }

    }
}
