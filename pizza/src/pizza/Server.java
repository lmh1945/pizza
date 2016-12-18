package pizza;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Server extends JFrame implements ActionListener {
	private JButton input, order;
	public JTextArea textArea;
	public JTextField textField;
	private ServerSocket serverSocket;
	private Socket clientSocket;
	public PrintWriter out;
	private BufferedReader in;
	private String outputLine;
	private JPanel panel, panel1, panel2;
	
	Pizza pizza;
	MaintainBills bills;
	
	public Server() {
		setBounds(300, 200, 600, 650);						//�������� ũ�� �� ��ġ ����
		setTitle("Employee");								//�������� ���� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//�ݱ��ư�� �������� ����
		

		textArea = new JTextArea(30, 42);					//textArea���� �� ũ�� ����
		textField = new JTextField(30);						//textField���� �� ũ�� ����
		textField.addActionListener(this);					//�̺�Ʈó�� ���
		
		//��ư ��� �� �̺�Ʈ ó�� ���
		input = new JButton("�Է�");							
		input.addActionListener(this);						
		order = new JButton("�ֹ�");							
		order.addActionListener(this);						
		
		//�г� ���
		panel = new JPanel();							
		panel1 = new JPanel();
		panel2 = new JPanel();
		
		panel1.add(new JScrollPane(textArea));			//panel1�� JScrollPane�� textArea�� ���
		panel2.add(textField);							//panel2�� textField���
		panel2.add(input);								//panel2�� ��ư���
		panel2.add(order);
		add(panel);										//�����ӿ� �гε��

		panel = new JPanel(new GridLayout(1, 1));		//GridLayout���� �г��� ����

		panel.add(panel1);								//������� �гο� panel1, panel2�� ���
		panel.add(panel2);
		setLayout(new BorderLayout());					//setLayout���� ��ġ�� BorderLayout���� ����

		add(panel1, BorderLayout.NORTH);				//BorderLayout���� �� �г��� ��ġ�� �����
		add(panel2, BorderLayout.SOUTH);
		
		bills = MaintainBills.getInstance();
		bills.setServer(this);
		
		setResizable(false);							//������ �ִ�ȭ,�ּ�ȭ false
		setVisible(true);								//�������� �����
	}

	@Override
	public void actionPerformed(ActionEvent e) {		//��ư�� �������� �߻��ϴ� �̺�Ʈó��
		String s;
		
		s = "���� : " + textField.getText();
		textArea.append(s + "\n");						//���� s�� ��� ������ textArea�� ���
		out.println(s);									//Ŭ���̾�Ʈ���Ե� ������
		textField.setText("");							//����� ���̳����� �ʱ�ȭ
		
		if (e.getSource() == order) {					//���� �ֹ���ư�� ������
			String ss = "�ֹ����Դϴ�.";					//�޽����� ���� ���� ss
			textArea.append(ss + "\n");					//ss�� ��� �޽����� textArea�� ���
			out.println(ss);							//Ŭ���̾�Ʈ�� �����ֱ� ���� 
			
			new Pizza();								//�ֹ��� �� �� �ֵ��� Pizza �������� �����	
		}
	}
	
	public void serverStart() throws IOException {
		
		System.out.println("�ֹ� ����");
		
		try {
			// ���������� �����ϰ� 9100�� ��Ʈ�� ����(bind) ��Ų��.
			serverSocket = new ServerSocket(9100);
		} catch (IOException e) {
			System.out.println("������ ��Ʈ ��ȣ�� ������ �� �����ϴ�. : 9100");
			System.exit(1);
		}

		clientSocket = null;

		try {
			// Ŭ���̾�Ʈ�� �����û�� ���� Ŭ���̾�Ʈ ���ϰ� ����� ���ο� ��Ĺ�� ����.
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			System.out.println("accept() ����");
			System.exit(1);
		}
		// ������ Ŭ���̾�Ʈ�� ��ȭ�� �ϱ����� out, in ����
		// ������ ��½�Ʈ���� ��´�.
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		// Ŭ���̾�Ʈ ������ �Ϸ�Ǹ� outputLine�� ������ش�.
		outputLine = "���� : �������ڰ��� �¶����ֹ��Դϴ�. �޴���ư���� �޴��� Ȯ���ϼ���~ \n (������ �ߺ������� �����մϴ�)";
		out.println(outputLine);
		
		//Ŭ���̾�Ʈ�� ���ӵǾ��ٰ� textArea�� ���
		textArea.append("���� �޽��� : Ŭ���̾�Ʈ�� ���ӵǾ����ϴ�.\n");

		String fromClient;		//Ŭ���̾�Ʈ�� ������ ������ ���� ��Ʈ�� ����

		while ((fromClient = in.readLine()) != null) {	//Ŭ���̾�Ʈ�� ������ ������ ������ �����鼭 null�� �ƴҶ� ���� �ݺ�
			String s = fromClient + "\n";				//���� s�� Ŭ���̾�Ʈ�� ���� ������ ����
			System.out.println(s);						//Ȯ���� ���� �ܼ�â�� ���
			textArea.append(s);							//textArea�� Ŭ���̾�Ʈ�� ������ ������ ����
		}
	}

	public static void main(String[] args) throws IOException {
		Server server = new Server();
		server.serverStart();
	}
}