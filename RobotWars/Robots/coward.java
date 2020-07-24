package Robots;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import Management.matrix;
import Management.missile;
import Management.robot;
//Sample version of an AI that only moves vertically
public class coward extends robot {
    
    public coward(int x, int y, String name, matrix arena) {
        super(x,y,name,arena);
        this.setAuthor("Mr. X");
    }


    
	@Override
	public String strategy() {
		//just runs away if someone is on its same axis, without shooting
		String ret = "m,";
		String[] target;
		boolean bool = false;
		int move;
		
		for(int j = 0; j < this.scan.size(); j++) {
			target = this.scan.get(j).split(",");
			if((Integer.parseInt(target[1]) == this.x && Integer.parseInt(target[2]) != this.y) || (Integer.parseInt(target[2]) == this.y && Integer.parseInt(target[1]) != this.x)) {
				j = this.scan.size()-1;		//the first enemy i see i can start to run
				bool = true;				//there is someone
			}
		}
		
		for (int i = 0; i < this.scan.size(); i++) {
			target = null;
			target = this.scan.get(i).split(",");
			move = ThreadLocalRandom.current().nextInt(0, 1);
			
			//run away from the missile!
			if(Integer.parseInt(target[1]) == this.x && Integer.parseInt(target[2]) != this.y) {
				if(move == 0) {
					ret += Integer.toString(this.x+1) + ",";
					ret += Integer.toString(this.y);
				}else{
					ret += Integer.toString(this.x-1) + ",";
					ret += Integer.toString(this.y);
				}
				i = this.scan.size()-1;
			}else if(Integer.parseInt(target[2]) == this.y && Integer.parseInt(target[1]) != this.x) {
				if(move == 0) {
					ret += Integer.toString(this.x) + ",";
					ret += Integer.toString(this.y+1);
				}else {
					ret += Integer.toString(this.x) + ",";
					ret += Integer.toString(this.y-1);
				}
				i = this.scan.size()-1;
			}else {
				move = ThreadLocalRandom.current().nextInt(1, 4);
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

				i = this.scan.size()-1;	  //end cycle
			}
		
		}
		
		return ret;
	}

}