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

	private int sum = 0;	//각겨의 합계를 저장하기위한 변수
	private int temp1 = 0;	//피자의 가격을 저장하기위한 변수
	private int temp2 = 0;	//토핑의 가격을 저장하기위한 변수
	private int temp3 = 0;	//사이즈의 가격을 저장하기위한 변수
	private JButton orderButton, cancleButton, resetButton;
	private JPanel downPanel;
	private JTextField text;

	WelcomePanel welcomePanel = new WelcomePanel();
	PizzaPanel pizzaPanel = new PizzaPanel();
	ToppingPanel toppingPanel = new ToppingPanel();
	SizePanel sizePanel = new SizePanel();
	
	Server server;
	
	public Pizza() {
		setSize(700, 400);	//프레임 크기 지정
		setTitle("피자 주문");	//프레임 제목 지정
		
		//프레임 하단 버튼3개 추가.
		orderButton = new JButton("주문");
		orderButton.addActionListener(this);
		cancleButton = new JButton("취소");
		cancleButton.addActionListener(this);
		resetButton = new JButton("리셋");
		resetButton.addActionListener(this);
		
		//프레임 하단 버튼 우측에 가격을 나타내는 텍스트필드 생성
		text = new JTextField();
		text.setEditable(true); // true/false 로 투명/반투명 결정
		text.setColumns(7); // 텍스트에 들어갈 수 있는 최대 크기
		
		//패널 p1,p2 2개 생성
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		p1.add(new JLabel("선택이 완료되었으면 아래 주문 버튼을 눌러주십시오, 리셋을 누르면 초기화 됩니다."));
		p2.add(orderButton);	//패널 p2에 orderButton 추가
		p2.add(resetButton);	//패널 p2에 resetButton 추가
		p2.add(cancleButton);	//패널 p2에 calcleButton 추가
		p2.add(text);			//패널 p2에 text 추가

		downPanel = new JPanel(new GridLayout(2, 0));	//downPanel이라는 패널을 생성하여 GridLayout으로 위치 지정
		downPanel.add(p1);		// downPanel에 p1 추가
		downPanel.add(p2);		// downPanel에 p2 추가
		setLayout(new BorderLayout());		//setLayout으로 배치방법을 BorderLayout를 사용.
		
		//추가한 BorderLayout메소드로 각 위치를 잡아줌 그것을 프레임에 집어넣음
		add(welcomePanel, BorderLayout.NORTH);
		add(sizePanel, BorderLayout.EAST);
		add(pizzaPanel, BorderLayout.WEST);
		add(toppingPanel, BorderLayout.CENTER);
		add(downPanel, BorderLayout.SOUTH);
		
		setLocationRelativeTo(null); // 창 가운데 뜨게 함
		setResizable(false); // 창 크기 조절 불가능
		setVisible(true);	//생성된 프레임을 보여줌
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.orderButton) {	//주문 버튼을 누르면
			temp1 += pizzaPanel.getPizzaPrice();	//주문한 피자의 가격이 temp1에 저장
			temp2 = toppingPanel.getToppingPrice();	//주문한 토핑의 가격이 temp2에 저장
			temp3 = sizePanel.getSizePrice();		//주문한 사이즈의 가격이 temp3에 저장
			sum = temp1 + temp2 + temp3;			//sum이라는 변수에 각 temp(1,2,3)을 더해서 값을 저장

			this.text.setText(" " + sum + "원");		//텍스트필드에 저장된 각 가격의 함을 출력

			map.put(pizzaPanel.getPizzaName(), pizzaPanel.getPizzaPrice());			//LinkedHashMap 첫번째 인덱스에 피자의 이름과 가격을 저장
			map.put(toppingPanel.getToppingName(), toppingPanel.getToppingPrice());	//LinkedHashMap 두번째 인덱스에 토핑의 이름과 가격을 저장
			map.put(sizePanel.getSizeName(), sizePanel.getSizePrice());				//LinkedHashMap 세번째 인덱스에 사이즈의 이름과 가격을 저장
			
			//HashMap에 들어있는 Key, Value값을 분리해서 호출하기 위한 과정
			Set<Entry<String, Integer>> set = map.entrySet();				//HashMap에 있는 모든 Key, Value를 Set에 담고,		
			Iterator<Entry<String, Integer>> it = set.iterator();			//Iterator에 값을 Set 정보를 담아줌

			int count = 1;	//선택의 순번을 매겨주기 위해 count를 1로 초기화

			while (it.hasNext()) {		//HashMap에 포함된 Key, Value 값을 호출
				Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it.next();
				list.add(count + "선택 : " + entry.getKey() + " 가격 : " + entry.getValue() + "원 ");
				count++;	//순번 하나씩 증가
			}
			
			//JOptionPane메소드로 ArrayList에 담아준 각각의 이름과 가격, 합계가격을 빌즈로 출력
			JOptionPane.showMessageDialog(null, list.toString() + "\n 총 주문액은 : " + sum + "원 입니다.", "주문확인서",
					JOptionPane.INFORMATION_MESSAGE, new ImageIcon("bills.png"));
			
			//MaintainBills라는 클래스로 Singleton Pattern을 사용하여 빌즈에 들어있는 값들을 유지하기 위해 사용
			MaintainBills bills = MaintainBills.getInstance();	//싱글톤패턴에서는 new로 생성자를 생성하는 것이 아니고 이와 같이 생성한다.
			
			bills.getServer().textArea.append(list.toString() + "\n 총 주문액은 : " + sum + "원 입니다.\n");		//빌즈의 내용을 Server가 담겨있는 bills.getServer로 textArea에 넣음
			bills.getServer().out.println((list.toString() + "\n 총 주문액은 : " + sum + "원 입니다."));			//빌즈의 내용을 Server에서 Client로 보냄
		}
		
		if (e.getSource() == this.resetButton) {		//만약 리셋버튼을 누르면
			sum = 0;									//합금액은 0이 되고
			this.text.setText(" " + sum + "원");			//합이 0원이라고 텍스트필드에 출력
			
			//JOptionPane메소드로 ArrayList에 담아준 각각의 이름과 가격, 합계가격을 빌즈로 출력
			JOptionPane.showMessageDialog(null, list.toString() + "\n 이 취소되었습니다. \n총 주문액은 : " + sum + "원 입니다.", "주문확인서",
					JOptionPane.INFORMATION_MESSAGE, new ImageIcon("bills.png"));

			dispose();	//리셋을 누르면 프레임을 한번 꺼주고
			new Pizza();	//다시 프레임창을 열어준다
		}

		if (e.getSource() == this.cancleButton) {	//만약 취소 버튼을 누르면
			dispose();	//취소를 누르면 프레임을 꺼준다
		}
	}
}
