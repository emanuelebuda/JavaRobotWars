package Robots;
import java.util.concurrent.ThreadLocalRandom;

import Management.matrix;
import Management.robot;
//Sample version of an AI that only moves vertically
public class rand extends robot {
	
	boolean shooted = false;
	
    public rand(int x, int y, String name, matrix arena) {
        super(x,y,name,arena);
        super.setAuthor("Empatica");
    }

	@Override
	public String strategy() {
		String ret = "";
		String[] target;
		boolean bool = false;
		//Scanning
		//[0] = name, [1] = x, [2] = y
		for(int j = 0; j < this.scan.size(); j++) {
			target = this.scan.get(j).split(",");
			if((Integer.parseInt(target[1]) == this.x && Integer.parseInt(target[2]) != this.y) || (Integer.parseInt(target[2]) == this.y && Integer.parseInt(target[1]) != this.x)) {
				j = this.scan.size()-1;		//the first target I get i shoot
				bool = true;				//there is someone to target
			}
		}
		
		//doesn't shoot for 2 turns in a row
		
		if(bool && !this.shooted) {
			this.shooted = true;
			ret += "s,";
		}else{
			this.shooted = false;
			ret += "m,";
		}
			
		for (int i = 0; i < this.scan.size(); i++) {
			//System.out.println(scan.get(i));
			target = null;
			target = this.scan.get(i).split(",");
			
			
			//elaborates missile direction
			if(ret.contentEquals("s,") && Integer.parseInt(target[1]) == this.x && Integer.parseInt(target[2]) != this.y) {
				if(Integer.parseInt(target[2]) - this.y > 0) {
					ret += Integer.toString(this.x) + ",";
					ret += Integer.toString(this.y)+ ",";
					ret += "d";	//down
				}else{
					ret += Integer.toString(this.x) + ",";
					ret += Integer.toString(this.y) + ",";
					ret += "u";	//up
				}
			}else if(ret.contentEquals("s,") && Integer.parseInt(target[2]) == this.y && Integer.parseInt(target[1]) != this.x) {
				if(Integer.parseInt(target[1]) - this.x > 0) {
					ret += Integer.toString(this.x) + ",";
					ret += Integer.toString(this.y) + ",";
					ret += "r";	//right
				}else {
					ret += Integer.toString(this.x) + ",";
					ret += Integer.toString(this.y) + ",";
					ret += "l";	//left
				}
			}else if (ret.contentEquals("m,")){
				//moves randomly
				int move = ThreadLocalRandom.current().nextInt(1, 4);
				switch (move) {
					case 1:
						ret += Integer.toString(this.x+1) + ",";
						ret += Integer.toString(this.y);
						break;
					case 2:
						ret += Integer.toString(this.x) + ",";
						ret += Integer.toString(this.y+1);
						break;
					case 3:
						ret += Integer.toString(this.x-1) + ",";
						ret += Integer.toString(this.y);
						break;
					case 4:
						ret += Integer.toString(this.x) + ",";
						ret += Integer.toString(this.y-1);
						break;
				}

				i = this.scan.size()-1;	  //end cycle, we move
			}
			
		}
		return ret;
	}

}