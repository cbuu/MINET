package cbuu.minet.handlers;

import java.net.Socket;

import cbuu.minet.common.IHandler;
import cbuu.minet.common.IListener;
import cbuu.minet.common.IMessage;
import cbuu.minet.manager.UserManager;
import cbuu.minet.network.MinetClient;
import cbuu.minet.util.DebugLog;

public class BrocastHandler extends IHandler {

	public BrocastHandler(Socket socket, IMessage msg) {
		super(socket, msg);
	}

	@Override
	public void run() {
		if (UserManager.getInstance().isInRoom()) {
			String content = msg.get("content");
			String sender = msg.get("sender");
			
			MinetClient.getInstance().getChatRoomHandler().obtainMessage(1, msg).sendToTarget();
			
			DebugLog.log("receive "+ content + " from " + sender);
		}
		
	}

}
