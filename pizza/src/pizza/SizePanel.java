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
		//setLayout으로 배치방법을 GridLayout으로 결정
		setLayout(new GridLayout(3, 0));
		
		//JRadioButton을 사용해서 각각 버튼을 넣어줌
		small = new JRadioButton("Small");
		small.addActionListener(this);
		medium = new JRadioButton("Medium");
		medium.addActionListener(this);
		large = new JRadioButton("Large");
		large.addActionListener(this);
		
		 //각 라디오버튼을 그룹으로 묶어줌
		bg = new ButtonGroup();
		bg.add(small);
		bg.add(medium);
		bg.add(large);
		
		//패널에 setBorder로 정려된 라인을 만들어줌
		setBorder(BorderFactory.createTitledBorder("3. 크기"));
		
		//패널에 피자의 종류버튼을 넣어줌
		add(small);
		add(medium);
		add(large);
	}

	public void actionPerformed(ActionEvent e) {		//각각의 버튼을 선택할 시 피자이름과 가격을 초기화

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