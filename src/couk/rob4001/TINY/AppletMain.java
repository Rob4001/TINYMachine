package couk.rob4001.TINY;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class AppletMain extends JApplet {
	AppletMain instance;
	JTextArea console;
	public class AppletCPU extends CPU{

		@Override
		public int get() {
			String s = (String)JOptionPane.showInputDialog(
                   "Requires Input");
			return Integer.parseInt(s,16);
		}

		@Override
		public void put(String s) {	
			instance.console.setText(instance.console.getText() + '\n' + s);
		}

	}

	public void init() {
		instance = this;
		
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                

				public void run() {
					final AppletCPU cpu = new AppletCPU();
                	JPanel pan = new JPanel(new FlowLayout());
                	add(pan);
                    JLabel lbl = new JLabel("IP LI FR AC 123456789ABCDEF");
                    pan.add(lbl);
                    console = new JTextArea(10,27);
                    pan.add(console);
                    JButton button = new JButton("Run");
                    button.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							String initial = console.getText();
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
		            		
		            		cpu.run(ip, li, fr, ac, mem);
							
						}
                    	
                    });
                    pan.add(button);
                    
                    
                }
            });
        } catch (Exception e) {
            System.err.println("createGUI didn't complete successfully");
        }
    }
}
