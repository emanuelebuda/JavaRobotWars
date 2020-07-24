package Robots;
import java.util.ArrayList;

import Management.matrix;
import Management.robot;
//Sample version of an AI that only moves vertically
public class horizontal extends robot {
    
    public horizontal(int x, int y, String name, matrix arena) {
        super(x,y,name,arena);
        super.setAuthor("Unknown"); //write your name here
    }

	@Override
	public String strategy() {
		String ret = "";
		//write your code here

		//strategy() of every robot define its next operation (move or shoot)
		//return format: operation
		//m = move, s = shoot
   		 //m formula = m, x, y
    	//s formula = s, direction(u,d,l,r)
		return ret;
	}

}