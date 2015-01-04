package cbuu.minet.network.P2P;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import android.os.Debug;
import android.os.Handler;

import cbuu.minet.common.IMessage;
import cbuu.minet.util.DebugLog;

public class P2PServerReadThread implements Runnable{
	private DatagramSocket socket = null;
	
	private Handler  listHandler = null;
	private Handler chatHandler = null;
	
	public P2PServerReadThread(DatagramSocket socket,Handler listHandler) {
		this.socket =socket;
		this.listHandler = listHandler;
	}
	
	public void setChatHandler(Handler chatHandler) {
		this.chatHandler = chatHandler;
	}

	@Override
	public void run() {
		while(true){
			
			DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
			
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			String receive = new String(packet.getData(),0,packet.getLength());
			DebugLog.log("receive "+receive);
			IMessage msg = IMessage.toMessage(receive);
			if (msg.getType()==IMessage.SHAKE_HAND) {
				listHandler.obtainMessage(1).sendToTarget();
				String clientIP = packet.getAddress().getHostAddress();
				DebugLog.log("clientIP "+clientIP);
				P2PCore.getInstance().setIP(clientIP);
			}else if (msg.getType()==IMessage.MSG_SEND) {
				chatHandler.obtainMessage(1,msg.get("content")).sendToTarget();
			}else if (msg.getType()==IMessage.SAY_GOODBYE) {
				chatHandler.obtainMessage(2).sendToTarget();
			}
		}
	}
	
}
