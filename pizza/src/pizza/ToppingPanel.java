package pizza;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

class ToppingPanel extends JPanel implements ActionListener {
	private String toppingName = "";
	private int toppingPrice = 0;
	private String topping = "";
	private int price = 0;
	private JCheckBox cheese, peperoni, bacon, olive, onion, pepper, mushroom, cuttlefish, shrimp, shellfish;

	public ToppingPanel() {
		
		setLayout(new GridLayout(2, 2));
		
		cheese = new JCheckBox("치즈");
		cheese.addActionListener(this);
		peperoni = new JCheckBox("페퍼로니");
		peperoni.addActionListener(this);
		bacon = new JCheckBox("베이컨");
		bacon.addActionListener(this);
		olive = new JCheckBox("올리브");
		olive.addActionListener(this);
		onion = new JCheckBox("양파");
		onion.addActionListener(this);
		pepper = new JCheckBox("피망");
		pepper.addActionListener(this);
		mushroom = new JCheckBox("양송이");
		mushroom.addActionListener(this);
		cuttlefish = new JCheckBox("오징어");
		cuttlefish.addActionListener(this);
		shrimp = new JCheckBox("새우");
		shrimp.addActionListener(this);
		shellfish = new JCheckBox("조개");
		shellfish.addActionListener(this);
		
		setBorder(BorderFactory.createTitledBorder("2. 추가토핑 <토핑은 중복선택이 가능합니다.> "));
		
		add(cheese);
		add(peperoni);
		add(bacon);
		add(olive);
		add(onion);
		add(pepper);
		add(mushroom);
		add(cuttlefish);
		add(shrimp);
		add(shellfish);
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.cheese) {			
			setToppingName("치즈");
			setToppingPrice(1500);
			price += 1500;
			topping += "치즈,";
		}
		
		else if (e.getSource() == this.peperoni) {
			setToppingName("페퍼로니");
			setToppingPrice(2300);
			price += 2300;
			topping += "페퍼로니,";
		}
		
		else if (e.getSource() == this.bacon) {
			setToppingName("베이컨");
			setToppingPrice(1400);
			price += 1400;
			topping += "베이컨,";
		}
		
		else if (e.getSource() == this.olive) {
			setToppingName("올리브");
			setToppingPrice(1600);
			price += 1600;
			topping += "올리브,";
		}
		
		else if (e.getSource() == this.onion) {
			setToppingName("양파");
			setToppingPrice(600);
			price += 600;
			topping += "양파,";
		}
		
		else if (e.getSource() == this.pepper) {
			setToppingName("피망");
			setToppingPrice(900);
			price += 900;
			topping += "피망,";
		}
		
		else if (e.getSource() == this.mushroom) {
			setToppingName("양송이");
			setToppingPrice(1200);
			price += 1200;
			topping += "양송이,";
		}
		
		else if (e.getSource() == this.cuttlefish) {
			setToppingName("오징어");
			setToppingPrice(1600);
			price += 1600;
			topping += "오징어,";
		}
		
		else if (e.getSource() == this.shrimp) {
			setToppingName("새우");
			setToppingPrice(1800);
			price += 1800;
			topping += "새우,";
		}
		
		else if (e.getSource() == this.shellfish) {
			setToppingName("조개");
			setToppingPrice(1300);
			price += 1300;
			topping += "조개,";
		}

	}

	public String getToppingName() {
		return topping;
	}

	public void setToppingName(String toppingName) {
		this.toppingName = toppingName;
	}

	public int getToppingPrice() {
		return price;
	}

	public void setToppingPrice(int toppingPrice) {
		this.toppingPrice = toppingPrice;
	}
}
