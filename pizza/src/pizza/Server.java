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
		setBounds(300, 200, 600, 650);						
		setTitle("Employee");								
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		

		textArea = new JTextArea(30, 42);					
		textField = new JTextField(30);						
		textField.addActionListener(this);					
		
		
		input = new JButton("입력");							
		input.addActionListener(this);						
		order = new JButton("주문");							
		order.addActionListener(this);						
		
		
		panel = new JPanel();							
		panel1 = new JPanel();
		panel2 = new JPanel();
		
		panel1.add(new JScrollPane(textArea));			
		panel2.add(textField);							
		panel2.add(input);								
		panel2.add(order);
		add(panel);										

		panel = new JPanel(new GridLayout(1, 1));		

		panel.add(panel1);								
		panel.add(panel2);
		setLayout(new BorderLayout());					

		add(panel1, BorderLayout.NORTH);				
		add(panel2, BorderLayout.SOUTH);
		
		bills = MaintainBills.getInstance();
		bills.setServer(this);
		
		setResizable(false);							
		setVisible(true);								
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		String s;
		
		s = "직원 : " + textField.getText();
		textArea.append(s + "\n");						
		out.println(s);									
		textField.setText("");							
		
		if (e.getSource() == order) {					
			String ss = "주문중입니다.";					
			textArea.append(ss + "\n");					
			out.println(ss);							
			
			new Pizza();								
		}
	}
	
	public void serverStart() throws IOException {
		
		System.out.println("주문 시작");
		
		try {
			
			serverSocket = new ServerSocket(9100);
		} catch (IOException e) {
			System.out.println("다음의 포트 번호에 연결할 수 없습니다. : 9100");
			System.exit(1);
		}

		clientSocket = null;

		try {
			
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			System.out.println("accept() 실패");
			System.exit(1);
		}
		
		
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		
		outputLine = "직원 : 민혁파자가게 온라인주문입니다. 메뉴버튼에서 메뉴를 확인하세요~ \n (토핑은 중복선택이 가능합니다)";
		out.println(outputLine);
		
		
		textArea.append("서버 메시지 : 클라이언트가 접속되었습니다.\n");

		String fromClient;		

		while ((fromClient = in.readLine()) != null) {	
			String s = fromClient + "\n";				
			System.out.println(s);						
			textArea.append(s);							
		}
	}

	public static void main(String[] args) throws IOException {
		Server server = new Server();
		server.serverStart();
	}
}
