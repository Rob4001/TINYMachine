package couk.rob4001.TINY;

import java.util.Scanner;

public class ConsoleCPU extends CPU {

	private Scanner console;

	public ConsoleCPU() {
		console = new Scanner(System.in);
		System.out.println("Paste Initial Configuration");
		String initial = console.nextLine();

		
		String[] split = initial.split(" ");
		
		int ip = Integer.parseInt(split[0], 16);
		int li = Integer.parseInt(split[1], 16);
		int fr = Integer.parseInt(split[2], 16);
		int ac = Integer.parseInt(split[3], 16);
		
		String[] split2 = split[5].split("");
		int[] mem = new int[16];
		for (int x = 0 ; x <16; x++){
			mem[x] = Integer.parseInt(split2[x+1],16);
		}
		
		this.run(ip, li, fr, ac, mem);
	}

	public static void main(String[] args) {
		new ConsoleCPU();
	}

	@Override
	public int get() {
		System.out.println("Require Input:");
		return Integer.parseInt(console.nextLine(),16);
	}

	@Override
	public void put(String s) {
		System.out.println(s);
	}

}
