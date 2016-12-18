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
		//setLayout���� ��ġ����� GridLayout���� ����
		setLayout(new GridLayout(2, 2));
		
		//JCheckBox�� ����ؼ� ���� ��ư�� �־���
		cheese = new JCheckBox("ġ��");
		cheese.addActionListener(this);
		peperoni = new JCheckBox("���۷δ�");
		peperoni.addActionListener(this);
		bacon = new JCheckBox("������");
		bacon.addActionListener(this);
		olive = new JCheckBox("�ø���");
		olive.addActionListener(this);
		onion = new JCheckBox("����");
		onion.addActionListener(this);
		pepper = new JCheckBox("�Ǹ�");
		pepper.addActionListener(this);
		mushroom = new JCheckBox("�����");
		mushroom.addActionListener(this);
		cuttlefish = new JCheckBox("��¡��");
		cuttlefish.addActionListener(this);
		shrimp = new JCheckBox("����");
		shrimp.addActionListener(this);
		shellfish = new JCheckBox("����");
		shellfish.addActionListener(this);
		
		//�гο� setBorder�� ������ ������ �������
		setBorder(BorderFactory.createTitledBorder("2. �߰����� <������ �ߺ������� �����մϴ�.> "));
		
		//�гο� ������ ������ư�� �־���
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
		if (e.getSource() == this.cheese) {			////������ ��ư�� ������ �� �����̸��� ������ �ʱ�ȭ, �ߺ��� ����ϱ� ���� ���ݿ� �̸��� ��� �߰�����
			setToppingName("ġ��");
			setToppingPrice(1500);
			price += 1500;
			topping += "ġ��,";
		}
		
		else if (e.getSource() == this.peperoni) {
			setToppingName("���۷δ�");
			setToppingPrice(2300);
			price += 2300;
			topping += "���۷δ�,";
		}
		
		else if (e.getSource() == this.bacon) {
			setToppingName("������");
			setToppingPrice(1400);
			price += 1400;
			topping += "������,";
		}
		
		else if (e.getSource() == this.olive) {
			setToppingName("�ø���");
			setToppingPrice(1600);
			price += 1600;
			topping += "�ø���,";
		}
		
		else if (e.getSource() == this.onion) {
			setToppingName("����");
			setToppingPrice(600);
			price += 600;
			topping += "����,";
		}
		
		else if (e.getSource() == this.pepper) {
			setToppingName("�Ǹ�");
			setToppingPrice(900);
			price += 900;
			topping += "�Ǹ�,";
		}
		
		else if (e.getSource() == this.mushroom) {
			setToppingName("�����");
			setToppingPrice(1200);
			price += 1200;
			topping += "�����,";
		}
		
		else if (e.getSource() == this.cuttlefish) {
			setToppingName("��¡��");
			setToppingPrice(1600);
			price += 1600;
			topping += "��¡��,";
		}
		
		else if (e.getSource() == this.shrimp) {
			setToppingName("����");
			setToppingPrice(1800);
			price += 1800;
			topping += "����,";
		}
		
		else if (e.getSource() == this.shellfish) {
			setToppingName("����");
			setToppingPrice(1300);
			price += 1300;
			topping += "����,";
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
