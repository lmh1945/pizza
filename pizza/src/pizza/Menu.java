package pizza;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Menu extends JFrame{
	private JLabel label;
	
	public Menu(){
		setBounds(1000, 400, 360, 350);		//프레임 크기지정
		setTitle("Menu~");					//프레임 제목지정
		
		JPanel menuPanel = new JPanel();	//패널 생성
		Icon icon = new ImageIcon("menu.png");		//미리 저장해둔 메뉴판 사진을 아이콘에 저장
		label = new JLabel();				//라벨생성
		label.setIcon(icon);				//라벨에 아이콘을 넣어줌
		add(menuPanel);						//프레임에 패널 등록
		add(label);							//프레임에 라벨 등록
	
		setVisible(true);					//프레임 화면을 띄어줌
	}
	
}
