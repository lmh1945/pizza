package pizza;

import javax.swing.JLabel;
import javax.swing.JPanel;

class WelcomePanel extends JPanel {
	private JLabel message;

	public WelcomePanel() {
		message = new JLabel("민혁이의 피자가게에 오신것을 환영합니당ㅎㅎㅎ");		//라벨을 생성 및 초기화
		add(message);													//패널에 라벨을 등록
	}
}