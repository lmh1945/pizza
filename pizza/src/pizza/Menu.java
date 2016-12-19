package pizza;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Menu extends JFrame{
	private JLabel label;
	
	public Menu(){
		setBounds(1000, 400, 360, 350);		
		setTitle("Menu~");					
		
		JPanel menuPanel = new JPanel();	
		Icon icon = new ImageIcon("menu.png");		
		label = new JLabel();				
		label.setIcon(icon);				
		add(menuPanel);						
		add(label);							
	
		setVisible(true);					
	}
	
}
