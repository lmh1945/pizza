package pizza;

import javax.swing.JLabel;
import javax.swing.JPanel;

class WelcomePanel extends JPanel {
	private JLabel message;

	public WelcomePanel() {
		message = new JLabel("민혁이의 피자가게에 오신것을 환영합니당ㅎㅎㅎ");		
		add(message);													
	}
}
