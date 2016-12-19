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
		setBounds(900, 200, 600, 650);						
		setTitle("Consumer");								
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		textArea = new JTextArea(30, 42);					
		textField = new JTextField(30);						
		textField.addActionListener(this);					
		
		//버튼 등록 및 이벤트 처리 등록
		input = new JButton("입력");							
		input.addActionListener(this);				
		menu = new JButton("메뉴");							
		menu.addActionListener(this);
		
		//패널 등록
		panel = new JPanel();
		panel1 = new JPanel();
		panel2 = new JPanel();
		
		panel1.add(new JScrollPane(textArea));			
		panel2.add(textField);							
		panel2.add(input);								
		panel2.add(menu);
		add(panel);										
			
		panel = new JPanel(new GridLayout(1, 1));		
		
		panel.add(panel1);								
		panel.add(panel2);
		setLayout(new BorderLayout());					
		
		add(panel1, BorderLayout.NORTH);				
		add(panel2, BorderLayout.SOUTH);
		
		setResizable(false);							
		setVisible(true);								

	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		
		String s = "클라이언트 : " + textField.getText();
		textArea.append(s + "\n");						
		out.println(s);									
		textField.setText("");							
		
		if(e.getSource() == menu){						
			String ss = "메뉴확인중입니다.";					
			textArea.append(ss + "\n");					
			out.println(ss);							
			
			Menu menu = new Menu();						
		}
	}

	public void client() throws IOException {
		Socket socket = null;

		try {
			
			socket = new Socket("localhost", 9100);

		} catch (UnknownHostException e) {
			System.out.println("localhost에 접근할 수 없습니다.");
			System.exit(1);

		} catch (IOException e) {
			System.out.println("입출력 오류!");
			System.exit(1);
		}
		
		out = new PrintWriter(socket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));	

		String fromServer;			

		while ((fromServer = in.readLine()) != null) {	
			String s = fromServer + "\n";				
			System.out.println(s);						
			textArea.append(s);							
		}

		out.close();			
		in.close();				
		socket.close();			
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Client client = new Client();
		client.client();
	}

}
