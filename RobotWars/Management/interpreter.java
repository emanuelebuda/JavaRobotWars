package Management;
import Management.controller;
import Management.missile;

//Interprets AI's next move
public class interpreter {
	//interprets the outputs of robot's moves

	static String strat;
	public static void interpret(robot r) {
		strat = r.strategy();
		System.out.println(r.getName() + "  strategy says: " + strat);
		String op[] = strat.split(",");
		
		if(op[0].equals("m")) {
			op = controller.controlMove(op);
			r.setX(Integer.parseInt(op[1]));
			r.setY(Integer.parseInt(op[2]));
			System.out.println(r.getName() + " moves to (" + r.getX() + ", " + r.getY() +")");
		}else if(op[0].equals("s")) {
			r.addMissile(new missile(r,r.getX(),r.getY(),op[3]));
			System.out.println(r.getName() + " shoots to " + op[3]);
		}
	}
}
