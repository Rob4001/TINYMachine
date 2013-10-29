package couk.rob4001.TINY;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;

public class AppletMain extends JApplet {
	public AppletMain() {
	}
	AppletMain instance;
	private JTextField initialBox;
	private JTextArea console;
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
		
this.setSize(300, 400);
		
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                

				public void run() {
					final AppletCPU cpu = new AppletCPU();
                	
					JSplitPane splitPane = new JSplitPane();
					splitPane.setEnabled(false);
					splitPane.setResizeWeight(1.0);
					getContentPane().add(splitPane, BorderLayout.CENTER);
					
					JSplitPane splitPane_1 = new JSplitPane();
					splitPane_1.setEnabled(false);
					splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
					splitPane.setLeftComponent(splitPane_1);
					
					JLabel lblIpetc = new JLabel("IP LI FR AC 123456789ABCDEF");
					lblIpetc.setFont(new Font("Monospaced", Font.PLAIN, 13));
					splitPane_1.setLeftComponent(lblIpetc);
					
					JSplitPane splitPane_2 = new JSplitPane();
					splitPane_2.setEnabled(false);
					splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);
					splitPane_1.setRightComponent(splitPane_2);
					
					initialBox = new JTextField();
					initialBox.setFont(new Font("Monospaced", Font.PLAIN, 13));
					initialBox.setToolTipText("Initial State");
					splitPane_2.setLeftComponent(initialBox);
					initialBox.setColumns(10);
					
					console = new JTextArea();
					console.setEditable(false);
					console.setFont(new Font("Monospaced", Font.PLAIN, 13));
					console.setWrapStyleWord(true);
					splitPane_2.setRightComponent(console);
					
					JButton btnRun = new JButton("Run");
					splitPane.setRightComponent(btnRun);
                    
                    btnRun.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							console.setText("");
							String initial = initialBox.getText();
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
                
                    
                    
                }
            });
        } catch (Exception e) {
            System.err.println("createGUI didn't complete successfully");
        }
    }
}
