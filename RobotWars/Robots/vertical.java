package Robots;
import java.util.ArrayList;

import Management.matrix;
import Management.robot;

//Sample version of an AI that only moves vertically
public class vertical extends robot { 
    public vertical(int x, int y, String name, matrix arena) {
        super(x,y,name,arena);
        super.setAuthor("Emanuele");
    }

	@Override
	public String strategy() {
		String ret = "";
		String[] target;
		boolean bool = false;
		//Scanning
		//System.out.println(this.scan.size());
			//[0] = name, [1] = x, [2] = y
			for(int j = 0; j < this.scan.size(); j++) {
				target = this.scan.get(j).split(",");
				if((Integer.parseInt(target[1]) == this.x && Integer.parseInt(target[2]) != this.y) || (Integer.parseInt(target[2]) == this.y && Integer.parseInt(target[1]) != this.x)) {
					j = this.scan.size()-1;		//the first target I get i shoot
					bool = true;				//there is someone to target
				}
			}
			if(bool) {
				ret += "s,";
			}else {
				ret += "m,";
			}
			for (int i = 0; i < this.scan.size(); i++) {
				//System.out.println(scan.get(i));
				target = null;
				target = this.scan.get(i).split(",");
				//elaborate missile direction
				if(ret.contentEquals("s,") && Integer.parseInt(target[1]) == this.x && Integer.parseInt(target[2]) != this.y) {
					if(Integer.parseInt(target[2]) - this.y > 0) {
						ret += Integer.toString(this.x) + ",";
						ret += Integer.toString(this.y)+ ",";
						ret += "d";
					}else{
						ret += Integer.toString(this.x) + ",";
						ret += Integer.toString(this.y) + ",";
						ret += "u";
					}
				}else if(ret.contentEquals("s,") && Integer.parseInt(target[2]) == this.y && Integer.parseInt(target[1]) != this.x) {
					if(Integer.parseInt(target[1]) - this.x > 0) {
						ret += Integer.toString(this.x) + ",";
						ret += Integer.toString(this.y) + ",";
						ret += "r";
					}else {
						ret += Integer.toString(this.x) + ",";
						ret += Integer.toString(this.y) + ",";
						ret += "l";
					}
				}else if (ret.contentEquals("m,")){
					//just move
					ret += Integer.toString(this.x) + ",";
					ret += Integer.toString(this.y+1);
					i = this.scan.size()-1;
				}
			
		}
		return ret;
	}

}