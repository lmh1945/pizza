package pizza;

public class MaintainBills { // singleton pattern = 하나의 클래스의 단 하나의 인스턴스만을 생성하는 것
	private static MaintainBills instance;	//유일한 인스턴스 저장 객체 선언
	private Server server;
	
	public static synchronized MaintainBills getInstance() { // 방해받지말고 끝날때까지.. 외부에서 사용할 함수 선언, 클래스를 생성할때 쓴다
		if (instance == null) {								// 있는지 체크, 없다면
			instance = new MaintainBills();					// 생성한 후,
		}
		return instance;									// 생성자를 넘겨준다.
	}
	
	private MaintainBills(){
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}
}
