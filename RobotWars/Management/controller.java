package Management;

import java.util.ArrayList;

public class controller {

	//controls if everything is in bounds
	
	public static String[] controlMove(String[] op) {
		//X out of bounds
		if(Integer.parseInt(op[1]) >= matrix.getSize())  {
			op[1] = String.valueOf(Integer.parseInt(op[1])-1);
		}else if((Integer.parseInt(op[1]) <= 0))  {
			op[1] = String.valueOf(Integer.parseInt(op[1])+1);
		}
		
		//Y out of bounds
		if(Integer.parseInt(op[2]) >= matrix.getSize())  {
			op[2] = String.valueOf(Integer.parseInt(op[2])-1);
		}else if (Integer.parseInt(op[2]) <= 0)  {
			op[2] = String.valueOf(Integer.parseInt(op[2])+1);
		}
		return op;
	}
	
	public static boolean controlShoots(missile miss) {
		if(miss.getX() >= matrix.getSize() || miss.getX() <= 0 || miss.getY() >= matrix.getSize() || miss.getY() <= 0)  {		
			miss.explode();
			return false;
		}
		return true;
	}
}