package pizza;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

class SizePanel extends JPanel implements ActionListener{
	private String sizeName = "";
	private int sizePrice = 0;
	private JRadioButton small, medium, large;
	private ButtonGroup bg;
	
	public SizePanel() {
		
		setLayout(new GridLayout(3, 0));
		
		small = new JRadioButton("Small");
		small.addActionListener(this);
		medium = new JRadioButton("Medium");
		medium.addActionListener(this);
		large = new JRadioButton("Large");
		large.addActionListener(this);
		
		
		bg = new ButtonGroup();
		bg.add(small);
		bg.add(medium);
		bg.add(large);
		
		setBorder(BorderFactory.createTitledBorder("3. 크기"));
		
		add(small);
		add(medium);
		add(large);
	}

	public void actionPerformed(ActionEvent e) {		

		if (e.getSource() == this.small) {
			setSizeName("Small");
			setSizePrice(0);
		}
		
		else if (e.getSource() == this.medium) {
			setSizeName("Medium");
			setSizePrice(2000);
		}
		
		else if (e.getSource() == this.large) {
			setSizeName("Large");
			setSizePrice(4000);
		}
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public int getSizePrice() {
		return sizePrice;
	}

	public void setSizePrice(int sizePrice) {
		this.sizePrice = sizePrice;
	}

}
