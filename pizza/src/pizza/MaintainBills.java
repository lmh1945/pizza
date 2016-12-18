package pizza;

public class MaintainBills { // singleton pattern = �ϳ��� Ŭ������ �� �ϳ��� �ν��Ͻ����� �����ϴ� ��
	private static MaintainBills instance;	//������ �ν��Ͻ� ���� ��ü ����
	private Server server;
	
	public static synchronized MaintainBills getInstance() { // ���ع������� ����������.. �ܺο��� ����� �Լ� ����, Ŭ������ �����Ҷ� ����
		if (instance == null) {								// �ִ��� üũ, ���ٸ�
			instance = new MaintainBills();					// ������ ��,
		}
		return instance;									// �����ڸ� �Ѱ��ش�.
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}
}