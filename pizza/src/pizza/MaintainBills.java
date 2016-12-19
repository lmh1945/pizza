package pizza;

public class MaintainBills { 
	private static MaintainBills instance;	
	private Server server;
	
	public static synchronized MaintainBills getInstance() {
		if (instance == null) {								
			instance = new MaintainBills();					
		}
		return instance;									
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
