package pizza;

import javax.swing.JLabel;
import javax.swing.JPanel;

class WelcomePanel extends JPanel {
	private JLabel message;

	public WelcomePanel() {
		message = new JLabel("�������� ���ڰ��Կ� ���Ű��� ȯ���մϴ社����");		//���� ���� �� �ʱ�ȭ
		add(message);													//�гο� ���� ���
	}
}