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
		setBounds(300, 200, 600, 650);						//프레임의 크기 및 위치 지정
		setTitle("Employee");								//프레임의 제목 지정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//닫기버튼을 눌렀을때 종료
		

		textArea = new JTextArea(30, 42);					//textArea생성 및 크기 지정
		textField = new JTextField(30);						//textField생성 및 크기 지정
		textField.addActionListener(this);					//이벤트처리 등록
		
		//버튼 등록 및 이벤트 처리 등록
		input = new JButton("입력");							
		input.addActionListener(this);						
		order = new JButton("주문");							
		order.addActionListener(this);						
		
		//패널 등록
		panel = new JPanel();							
		panel1 = new JPanel();
		panel2 = new JPanel();
		
		panel1.add(new JScrollPane(textArea));			//panel1에 JScrollPane을 textArea에 등록
		panel2.add(textField);							//panel2에 textField등록
		panel2.add(input);								//panel2에 버튼등록
		panel2.add(order);
		add(panel);										//프레임에 패널등록

		panel = new JPanel(new GridLayout(1, 1));		//GridLayout으로 패널을 생성

		panel.add(panel1);								//만들어진 패널에 panel1, panel2를 등록
		panel.add(panel2);
		setLayout(new BorderLayout());					//setLayout으로 배치를 BorderLayout으로 결정

		add(panel1, BorderLayout.NORTH);				//BorderLayout으로 각 패널의 위치를 잡아줌
		add(panel2, BorderLayout.SOUTH);
		
		bills = MaintainBills.getInstance();
		bills.setServer(this);
		
		setResizable(false);							//프레임 최대화,최소화 false
		setVisible(true);								//프레임을 띄어줌
	}

	@Override
	public void actionPerformed(ActionEvent e) {		//버튼을 눌렀을때 발생하는 이벤트처리
		String s;
		
		s = "직원 : " + textField.getText();
		textArea.append(s + "\n");						//변수 s에 담긴 내용을 textArea에 출력
		out.println(s);									//클라이언트에게도 보내줌
		textField.setText("");							//출력이 끝이났으면 초기화
		
		if (e.getSource() == order) {					//만약 주문버튼을 누르면
			String ss = "주문중입니다.";					//메시지를 담을 변수 ss
			textArea.append(ss + "\n");					//ss에 담긴 메시지가 textArea에 출력
			out.println(ss);							//클라이언트로 보내주기 위해 
			
			new Pizza();								//주문을 할 수 있도록 Pizza 프레임을 띄어줌	
		}
	}
	
	public void serverStart() throws IOException {
		
		System.out.println("주문 시작");
		
		try {
			// 서버소켓을 생성하고 9100번 포트와 결합(bind) 시킨다.
			serverSocket = new ServerSocket(9100);
		} catch (IOException e) {
			System.out.println("다음의 포트 번호에 연결할 수 없습니다. : 9100");
			System.exit(1);
		}

		clientSocket = null;

		try {
			// 클라이언트의 연결요청이 오면 클라이언트 소켓과 통신할 새로운 소캣을 생성.
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			System.out.println("accept() 실패");
			System.exit(1);
		}
		// 서버와 클라이언트간 대화를 하기위해 out, in 생성
		// 소켓의 출력스트림을 얻는다.
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		// 클라이언트 접속이 완료되면 outputLine을 출력해준다.
		outputLine = "직원 : 민혁파자가게 온라인주문입니다. 메뉴버튼에서 메뉴를 확인하세요~ \n (토핑은 중복선택이 가능합니다)";
		out.println(outputLine);
		
		//클라이언트가 접속되었다고 textArea에 출력
		textArea.append("서버 메시지 : 클라이언트가 접속되었습니다.\n");

		String fromClient;		//클라이언트로 보내는 문장을 담을 스트링 변수

		while ((fromClient = in.readLine()) != null) {	//클라이언트로 보내는 문장이 한줄을 읽으면서 null이 아닐때 까지 반복
			String s = fromClient + "\n";				//변수 s에 클라이언트로 보낼 문장을 담음
			System.out.println(s);						//확인을 위한 콘솔창에 띄움
			textArea.append(s);							//textArea에 클라이언트로 보내는 문장을 삽입
		}
	}

	public static void main(String[] args) throws IOException {
		Server server = new Server();
		server.serverStart();
	}
}