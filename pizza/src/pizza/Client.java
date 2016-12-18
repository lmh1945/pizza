package pizza;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client extends JFrame implements ActionListener {
	private JButton input, menu;
	private JTextArea textArea;
	private JTextField textField;
	private PrintWriter out;
	private BufferedReader in;
	private JPanel panel, panel1, panel2;

	public Client() {
		setBounds(900, 200, 600, 650);						//�������� ũ�� �� ��ġ ����
		setTitle("Consumer");								//�������� ���� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//�ݱ��ư�� �������� ����
		
		textArea = new JTextArea(30, 42);					//textArea���� �� ũ�� ����
		textField = new JTextField(30);						//textField���� �� ũ�� ����
		textField.addActionListener(this);					//�̺�Ʈó�� ���
		
		//��ư ��� �� �̺�Ʈ ó�� ���
		input = new JButton("�Է�");							
		input.addActionListener(this);				
		menu = new JButton("�޴�");							
		menu.addActionListener(this);
		
		//�г� ���
		panel = new JPanel();
		panel1 = new JPanel();
		panel2 = new JPanel();
		
		panel1.add(new JScrollPane(textArea));			//panel1�� JScrollPane�� textArea�� ���
		panel2.add(textField);							//panel2�� textField���
		panel2.add(input);								//panel2�� ��ư���
		panel2.add(menu);
		add(panel);										//�����ӿ� �гε��
			
		panel = new JPanel(new GridLayout(1, 1));		//GridLayout���� �г��� ����
		
		panel.add(panel1);								//������� �гο� panel1, panel2�� ���
		panel.add(panel2);
		setLayout(new BorderLayout());					//setLayout���� ��ġ�� BorderLayout���� ����
		
		add(panel1, BorderLayout.NORTH);				//BorderLayout���� �� �г��� ��ġ�� �����
		add(panel2, BorderLayout.SOUTH);
		
		setResizable(false);							//������ �ִ�ȭ,�ּ�ȭ false
		setVisible(true);								//�������� �����

	}

	@Override
	public void actionPerformed(ActionEvent e) {		//��ư�� �������� �߻��ϴ� �̺�Ʈó��
		
		String s = "Ŭ���̾�Ʈ : " + textField.getText();
		textArea.append(s + "\n");						//���� s�� ��� ������ textArea�� ���
		out.println(s);									//Ŭ���̾�Ʈ���Ե� ������
		textField.setText("");							//����� ���̳����� �ʱ�ȭ
		
		if(e.getSource() == menu){						//���� �ֹ���ư�� ������
			String ss = "�޴�Ȯ�����Դϴ�.";					//�޽����� ���� ���� ss
			textArea.append(ss + "\n");					//ss�� ��� �޽����� textArea�� ���
			out.println(ss);							//Ŭ���̾�Ʈ�� �����ֱ� ���� 
			
			Menu menu = new Menu();						//�ֹ��� �� �� �ֵ��� Menu �������� �����
		}
	}

	public void client() throws IOException {
		Socket socket = null;

		try {
			// ������ �����Ͽ� ������ ��û�Ѵ�.
			socket = new Socket("localhost", 9100);

		} catch (UnknownHostException e) {
			System.out.println("localhost�� ������ �� �����ϴ�.");
			System.exit(1);

		} catch (IOException e) {
			System.out.println("����� ����!");
			System.exit(1);
		}
		
		// ������ Ŭ���̾�Ʈ�� ��ȭ�� �ϱ����� out, in ����
		// ������ �Է½�Ʈ���� ��´�.
		out = new PrintWriter(socket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));	

		String fromServer;			//������ ������ ������ ���� ��Ʈ�� ����

		while ((fromServer = in.readLine()) != null) {	//������ ������ ������ ������ �����鼭 null�� �ƴҶ� ���� �ݺ�
			String s = fromServer + "\n";				//���� s�� ������ ���� ������ ����
			System.out.println(s);						//Ȯ���� ���� �ܼ�â�� ���
			textArea.append(s);							//textArea�� ������ ������ ������ ����
		}

		out.close();			//PrintWriter �ݱ�
		in.close();				//BufferReader �ݱ�
		socket.close();			//socket �ݱ�
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Client client = new Client();
		client.client();
	}

}
