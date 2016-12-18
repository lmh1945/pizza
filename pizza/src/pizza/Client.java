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
		setBounds(900, 200, 600, 650);						//프레임의 크기 및 위치 지정
		setTitle("Consumer");								//프레임의 제목 지정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//닫기버튼을 눌렀을때 종료
		
		textArea = new JTextArea(30, 42);					//textArea생성 및 크기 지정
		textField = new JTextField(30);						//textField생성 및 크기 지정
		textField.addActionListener(this);					//이벤트처리 등록
		
		//버튼 등록 및 이벤트 처리 등록
		input = new JButton("입력");							
		input.addActionListener(this);				
		menu = new JButton("메뉴");							
		menu.addActionListener(this);
		
		//패널 등록
		panel = new JPanel();
		panel1 = new JPanel();
		panel2 = new JPanel();
		
		panel1.add(new JScrollPane(textArea));			//panel1에 JScrollPane을 textArea에 등록
		panel2.add(textField);							//panel2에 textField등록
		panel2.add(input);								//panel2에 버튼등록
		panel2.add(menu);
		add(panel);										//프레임에 패널등록
			
		panel = new JPanel(new GridLayout(1, 1));		//GridLayout으로 패널을 생성
		
		panel.add(panel1);								//만들어진 패널에 panel1, panel2를 등록
		panel.add(panel2);
		setLayout(new BorderLayout());					//setLayout으로 배치를 BorderLayout으로 결정
		
		add(panel1, BorderLayout.NORTH);				//BorderLayout으로 각 패널의 위치를 잡아줌
		add(panel2, BorderLayout.SOUTH);
		
		setResizable(false);							//프레임 최대화,최소화 false
		setVisible(true);								//프레임을 띄어줌

	}

	@Override
	public void actionPerformed(ActionEvent e) {		//버튼을 눌렀을때 발생하는 이벤트처리
		
		String s = "클라이언트 : " + textField.getText();
		textArea.append(s + "\n");						//변수 s에 담긴 내용을 textArea에 출력
		out.println(s);									//클라이언트에게도 보내줌
		textField.setText("");							//출력이 끝이났으면 초기화
		
		if(e.getSource() == menu){						//만약 주문버튼을 누르면
			String ss = "메뉴확인중입니다.";					//메시지를 담을 변수 ss
			textArea.append(ss + "\n");					//ss에 담긴 메시지가 textArea에 출력
			out.println(ss);							//클라이언트로 보내주기 위해 
			
			Menu menu = new Menu();						//주문을 할 수 있도록 Menu 프레임을 띄어줌
		}
	}

	public void client() throws IOException {
		Socket socket = null;

		try {
			// 소켓을 생성하여 연결을 요청한다.
			socket = new Socket("localhost", 9100);

		} catch (UnknownHostException e) {
			System.out.println("localhost에 접근할 수 없습니다.");
			System.exit(1);

		} catch (IOException e) {
			System.out.println("입출력 오류!");
			System.exit(1);
		}
		
		// 서버와 클라이언트간 대화를 하기위해 out, in 생성
		// 소켓의 입력스트림을 얻는다.
		out = new PrintWriter(socket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));	

		String fromServer;			//서버로 보내는 문장을 담을 스트링 변수

		while ((fromServer = in.readLine()) != null) {	//서버로 보내는 문장이 한줄을 읽으면서 null이 아닐때 까지 반복
			String s = fromServer + "\n";				//변수 s에 서버로 보낼 문장을 담음
			System.out.println(s);						//확인을 위한 콘솔창에 띄움
			textArea.append(s);							//textArea에 서버로 보내는 문장을 삽입
		}

		out.close();			//PrintWriter 닫기
		in.close();				//BufferReader 닫기
		socket.close();			//socket 닫기
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Client client = new Client();
		client.client();
	}

}
