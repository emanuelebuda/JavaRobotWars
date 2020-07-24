package Robots;
import java.util.ArrayList;

import Management.matrix;
import Management.robot;
//Sample version of an AI that only moves vertically
public class horizontal extends robot {
    
    public horizontal(int x, int y, String name, matrix arena) {
        super(x,y,name,arena);
    }

    public void move() {
        y++;
    }

    public void shoot() {
 
    }

	@Override
	public String strategy() {
		String ret = "";
		ret += "m,";
		ret += Integer.toString(this.x+1) + ",";
		ret += Integer.toString(this.y);
		return ret;
	}

}