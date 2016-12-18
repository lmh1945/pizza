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

	private int sum = 0;	//������ �հ踦 �����ϱ����� ����
	private int temp1 = 0;	//������ ������ �����ϱ����� ����
	private int temp2 = 0;	//������ ������ �����ϱ����� ����
	private int temp3 = 0;	//�������� ������ �����ϱ����� ����
	private JButton orderButton, cancleButton, resetButton;
	private JPanel downPanel;
	private JTextField text;

	WelcomePanel welcomePanel = new WelcomePanel();
	PizzaPanel pizzaPanel = new PizzaPanel();
	ToppingPanel toppingPanel = new ToppingPanel();
	SizePanel sizePanel = new SizePanel();
	
	Server server;
	
	public Pizza() {
		setSize(700, 400);	//������ ũ�� ����
		setTitle("���� �ֹ�");	//������ ���� ����
		
		//������ �ϴ� ��ư3�� �߰�.
		orderButton = new JButton("�ֹ�");
		orderButton.addActionListener(this);
		cancleButton = new JButton("���");
		cancleButton.addActionListener(this);
		resetButton = new JButton("����");
		resetButton.addActionListener(this);
		
		//������ �ϴ� ��ư ������ ������ ��Ÿ���� �ؽ�Ʈ�ʵ� ����
		text = new JTextField();
		text.setEditable(true); // true/false �� ����/������ ����
		text.setColumns(7); // �ؽ�Ʈ�� �� �� �ִ� �ִ� ũ��
		
		//�г� p1,p2 2�� ����
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		p1.add(new JLabel("������ �Ϸ�Ǿ����� �Ʒ� �ֹ� ��ư�� �����ֽʽÿ�, ������ ������ �ʱ�ȭ �˴ϴ�."));
		p2.add(orderButton);	//�г� p2�� orderButton �߰�
		p2.add(resetButton);	//�г� p2�� resetButton �߰�
		p2.add(cancleButton);	//�г� p2�� calcleButton �߰�
		p2.add(text);			//�г� p2�� text �߰�

		downPanel = new JPanel(new GridLayout(2, 0));	//downPanel�̶�� �г��� �����Ͽ� GridLayout���� ��ġ ����
		downPanel.add(p1);		// downPanel�� p1 �߰�
		downPanel.add(p2);		// downPanel�� p2 �߰�
		setLayout(new BorderLayout());		//setLayout���� ��ġ����� BorderLayout�� ���.
		
		//�߰��� BorderLayout�޼ҵ�� �� ��ġ�� ����� �װ��� �����ӿ� �������
		add(welcomePanel, BorderLayout.NORTH);
		add(sizePanel, BorderLayout.EAST);
		add(pizzaPanel, BorderLayout.WEST);
		add(toppingPanel, BorderLayout.CENTER);
		add(downPanel, BorderLayout.SOUTH);
		
		setLocationRelativeTo(null); // â ��� �߰� ��
		setResizable(false); // â ũ�� ���� �Ұ���
		setVisible(true);	//������ �������� ������
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.orderButton) {	//�ֹ� ��ư�� ������
			temp1 += pizzaPanel.getPizzaPrice();	//�ֹ��� ������ ������ temp1�� ����
			temp2 = toppingPanel.getToppingPrice();	//�ֹ��� ������ ������ temp2�� ����
			temp3 = sizePanel.getSizePrice();		//�ֹ��� �������� ������ temp3�� ����
			sum = temp1 + temp2 + temp3;			//sum�̶�� ������ �� temp(1,2,3)�� ���ؼ� ���� ����

			this.text.setText(" " + sum + "��");		//�ؽ�Ʈ�ʵ忡 ����� �� ������ ���� ���

			map.put(pizzaPanel.getPizzaName(), pizzaPanel.getPizzaPrice());			//LinkedHashMap ù��° �ε����� ������ �̸��� ������ ����
			map.put(toppingPanel.getToppingName(), toppingPanel.getToppingPrice());	//LinkedHashMap �ι�° �ε����� ������ �̸��� ������ ����
			map.put(sizePanel.getSizeName(), sizePanel.getSizePrice());				//LinkedHashMap ����° �ε����� �������� �̸��� ������ ����
			
			//HashMap�� ����ִ� Key, Value���� �и��ؼ� ȣ���ϱ� ���� ����
			Set<Entry<String, Integer>> set = map.entrySet();				//HashMap�� �ִ� ��� Key, Value�� Set�� ���,		
			Iterator<Entry<String, Integer>> it = set.iterator();			//Iterator�� ���� Set ������ �����

			int count = 1;	//������ ������ �Ű��ֱ� ���� count�� 1�� �ʱ�ȭ

			while (it.hasNext()) {		//HashMap�� ���Ե� Key, Value ���� ȣ��
				Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it.next();
				list.add(count + "���� : " + entry.getKey() + " ���� : " + entry.getValue() + "�� ");
				count++;	//���� �ϳ��� ����
			}
			
			//JOptionPane�޼ҵ�� ArrayList�� ����� ������ �̸��� ����, �հ谡���� ����� ���
			JOptionPane.showMessageDialog(null, list.toString() + "\n �� �ֹ����� : " + sum + "�� �Դϴ�.", "�ֹ�Ȯ�μ�",
					JOptionPane.INFORMATION_MESSAGE, new ImageIcon("bills.png"));
			
			//MaintainBills��� Ŭ������ Singleton Pattern�� ����Ͽ� ��� ����ִ� ������ �����ϱ� ���� ���
			MaintainBills bills = MaintainBills.getInstance();	//�̱������Ͽ����� new�� �����ڸ� �����ϴ� ���� �ƴϰ� �̿� ���� �����Ѵ�.
			
			bills.getServer().textArea.append(list.toString() + "\n �� �ֹ����� : " + sum + "�� �Դϴ�.\n");		//������ ������ Server�� ����ִ� bills.getServer�� textArea�� ����
			bills.getServer().out.println((list.toString() + "\n �� �ֹ����� : " + sum + "�� �Դϴ�."));			//������ ������ Server���� Client�� ����
		}
		
		if (e.getSource() == this.resetButton) {		//���� ���¹�ư�� ������
			sum = 0;									//�ձݾ��� 0�� �ǰ�
			this.text.setText(" " + sum + "��");			//���� 0���̶�� �ؽ�Ʈ�ʵ忡 ���
			
			//JOptionPane�޼ҵ�� ArrayList�� ����� ������ �̸��� ����, �հ谡���� ����� ���
			JOptionPane.showMessageDialog(null, list.toString() + "\n �� ��ҵǾ����ϴ�. \n�� �ֹ����� : " + sum + "�� �Դϴ�.", "�ֹ�Ȯ�μ�",
					JOptionPane.INFORMATION_MESSAGE, new ImageIcon("bills.png"));

			dispose();	//������ ������ �������� �ѹ� ���ְ�
			new Pizza();	//�ٽ� ������â�� �����ش�
		}

		if (e.getSource() == this.cancleButton) {	//���� ��� ��ư�� ������
			dispose();	//��Ҹ� ������ �������� ���ش�
		}
	}
}
