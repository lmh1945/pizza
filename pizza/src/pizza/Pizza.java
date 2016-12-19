package pizza;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class Pizza extends JFrame implements ActionListener {
	LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
	ArrayList<String> list = new ArrayList<String>();

	private int sum = 0;	
	private int temp1 = 0;	
	private int temp2 = 0;	
	private int temp3 = 0;	
	private JButton orderButton, cancleButton, resetButton;
	private JPanel downPanel;
	private JTextField text;

	WelcomePanel welcomePanel = new WelcomePanel();
	PizzaPanel pizzaPanel = new PizzaPanel();
	ToppingPanel toppingPanel = new ToppingPanel();
	SizePanel sizePanel = new SizePanel();
	
	Server server;
	
	public Pizza() {
		setSize(700, 400);	
		setTitle("피자 주문");	
		
		
		orderButton = new JButton("주문");
		orderButton.addActionListener(this);
		cancleButton = new JButton("취소");
		cancleButton.addActionListener(this);
		resetButton = new JButton("리셋");
		resetButton.addActionListener(this);
		
		
		text = new JTextField();
		text.setEditable(true);
		text.setColumns(7);
		
		
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		p1.add(new JLabel("선택이 완료되었으면 아래 주문 버튼을 눌러주십시오, 리셋을 누르면 초기화 됩니다."));
		p2.add(orderButton);	
		p2.add(resetButton);	
		p2.add(cancleButton);	
		p2.add(text);			

		downPanel = new JPanel(new GridLayout(2, 0));	
		downPanel.add(p1);		
		downPanel.add(p2);		
		setLayout(new BorderLayout());		
		
		
		add(welcomePanel, BorderLayout.NORTH);
		add(sizePanel, BorderLayout.EAST);
		add(pizzaPanel, BorderLayout.WEST);
		add(toppingPanel, BorderLayout.CENTER);
		add(downPanel, BorderLayout.SOUTH);
		
		setLocationRelativeTo(null); 
		setResizable(false); 
		setVisible(true);	
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.orderButton) {	
			temp1 += pizzaPanel.getPizzaPrice();	
			temp2 = toppingPanel.getToppingPrice();	
			temp3 = sizePanel.getSizePrice();		
			sum = temp1 + temp2 + temp3;			

			this.text.setText(" " + sum + "원");		

			map.put(pizzaPanel.getPizzaName(), pizzaPanel.getPizzaPrice());			
			map.put(toppingPanel.getToppingName(), toppingPanel.getToppingPrice());	
			map.put(sizePanel.getSizeName(), sizePanel.getSizePrice());				
			
			Set<Entry<String, Integer>> set = map.entrySet();				
			Iterator<Entry<String, Integer>> it = set.iterator();			

			int count = 1;	

			while (it.hasNext()) {		
				Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it.next();
				list.add(count + "선택 : " + entry.getKey() + " 가격 : " + entry.getValue() + "원 ");
				count++;	
			

			JOptionPane.showMessageDialog(null, list.toString() + "\n 총 주문액은 : " + sum + "원 입니다.", "주문확인서",
					JOptionPane.INFORMATION_MESSAGE, new ImageIcon("bills.png"));
			
			
			MaintainBills bills = MaintainBills.getInstance();	
			
			bills.getServer().textArea.append(list.toString() + "\n 총 주문액은 : " + sum + "원 입니다.\n");		
			bills.getServer().out.println((list.toString() + "\n 총 주문액은 : " + sum + "원 입니다."));			
		}
		
		if (e.getSource() == this.resetButton) {		
			sum = 0;									
			this.text.setText(" " + sum + "원");			
			JOptionPane.showMessageDialog(null, list.toString() + "\n 이 취소되었습니다. \n총 주문액은 : " + sum + "원 입니다.", "주문확인서",
					JOptionPane.INFORMATION_MESSAGE, new ImageIcon("bills.png"));

			dispose();	
			new Pizza();	
		}

		if (e.getSource() == this.cancleButton) {	
			dispose();	
		}
	}
}
