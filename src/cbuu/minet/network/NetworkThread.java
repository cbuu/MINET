package cbuu.minet.network;

import java.io.IOException;
import java.net.UnknownHostException;

import cbuu.minet.common.IMessage;
import cbuu.minet.network.P2P.P2PCore;

public class NetworkThread implements Runnable{
	
	public NetworkThread() {
	}
	
	@Override
	public void run() {
		
			MinetClient.getInstance().connectToServer();
	}

}
