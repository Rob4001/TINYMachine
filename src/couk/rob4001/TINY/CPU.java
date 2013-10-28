package couk.rob4001.TINY;


public abstract class CPU {

	public class FReg {
		
		public boolean cf;
		public boolean zf;
		public boolean of;
		public boolean hf;

		public void set(int parseInt) {
			String bin = Integer.toBinaryString(parseInt);
			while(bin.length() <4){
				bin = "0"+bin;
			}
			String[] binary = bin.split("");

			cf = binary[4].equalsIgnoreCase("1");
			zf = binary[3].equalsIgnoreCase("1");
			of = binary[2].equalsIgnoreCase("1");
			hf = binary[1].equalsIgnoreCase("1");
			
		}
		
		public int get(){
			int i = 0;
			if (hf) i += 8;
			if (of) i += 4;
			if (zf) i += 2;
			if (cf) i += 1;
			return i;
		}

	}

	private FReg fr = new FReg();
	public int[] memory = new int[16];
	private int ip;
	private int li;
	private int ac;

	public CPU() {

	}
	
	public void run(int ip, int li, int fr, int ac , int[] mem){
		this.ip = ip;
		this.li = li;
		this.fr.set(fr);
		this.ac = ac;
		this.memory = mem;
		while(!this.fr.hf){
			this.compute();
			this.printCurrentConfig();
		}
	}

	private void compute() {
	//	System.out.println("Executing: " + Integer.toHexString(memory[ip]));
		switch(memory[ip]){
		case 0:
			fr.hf = true;
			
			break;
		case 1:
			ip = memory[ip+1]-1;
			break;
		case 2:
			if(fr.zf){
				ip = memory[ip+1]-1;
			}
			break;
		case 3:
			if(!fr.zf){
				ip = memory[ip+1]-1;
			}
			break;
		case 4:
			ac = memory[memory[ip+1]];
			if (ac==0)fr.zf = true; else fr.zf = false;
			ip += 1;
			break;
		case 5:
			memory[memory[ip+1]] = ac;
			ip += 1;
			break;
		case 6:
			ac = get();
			if (ac==0)fr.zf = true; else fr.zf = false;
			break;
		case 7:
			put(Integer.toHexString(ac));
			break;
		case 8:
			ac = ac *2;
			if(fr.cf) ac += 1;
			if (ac >= 16){
				ac = ac - 16;
				fr.cf = true;
			}
			if (ac==0)fr.zf = true; else fr.zf = false;
			break;
		case 9:
			boolean tmp = ac%2 !=0;
			if(tmp) ac -= 1;
			ac = ac /2;
			if(fr.cf) ac += 8;
			fr.cf = tmp;
			if (ac==0)fr.zf = true; else fr.zf = false;
			break;
		case 10: 
			ac = ac + memory[memory[ip+1]];
			if (fr.cf) ac +=1;
			if (ac >= 16){
				ac = ac - 16;
				fr.cf = true;
			}
			if (ac==0)fr.zf = true; else fr.zf = false;
			ip +=1;
			break;
		case 11:
			fr.cf = false;
			break;
		case 12:
			fr.cf = true;
			break;
		case 13:
			li -= 1;
			if (li==0)fr.zf = true;
			break;
		case 14:
			li = memory[ip+1];
			if (li==0)fr.zf = true;
			ip +=1;
			break;
		case 15:
			ac = 15 - ac;
			if (ac==0)fr.zf = true; else fr.zf = false;
			break;
		}
//		System.out.println("Executed");
		ip += 1;
	}
	
	public void printCurrentConfig(){
		StringBuilder sb = new StringBuilder(Integer.toHexString(ip)+" "+Integer.toHexString(li)+" "+Integer.toHexString(fr.get())+" "+Integer.toHexString(ac)+"  ");
		for (int i:memory){
			sb.append(Integer.toHexString(i));
		}
		put(sb.toString().toUpperCase());

	}
	
	public abstract int get();
	public abstract void put(String s);
	

}
