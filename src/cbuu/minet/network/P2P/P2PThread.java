package cbuu.minet.network.P2P;

import android.os.Handler;

public class P2PThread implements Runnable{
	private Handler listHandler = null ;
	
	public P2PThread(Handler handler) {
		this.listHandler = handler;
	}
	
	@Override
	public void run() {
		P2PCore.getInstance().init(listHandler);
		P2PCore.getInstance().startServerReadThread();
	}

}
