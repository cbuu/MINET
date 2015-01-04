package cbuu.minet.network;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.TimerTask;

import android.os.Handler;


import cbuu.minet.common.IListener;
import cbuu.minet.common.IMessage;
import cbuu.minet.common.User;
import cbuu.minet.manager.ListenerManager;
import cbuu.minet.manager.UserManager;
import cbuu.minet.util.DebugLog;
import cbuu.minet.util.HeartbeatTimer;

public class MinetClient {
	public static MinetClient instance = null;
	private final String DEFAULT_CODER = "UTF-8";
	
	private final String SERVER_HOST = "192.168.150.1";//"192.168.137.1";//may be "172.18.35.156"
	private final int PORT = 9394;
	
	private Socket socket = null;
	
	private Thread readThread = null;

	private PrintWriter pw = null;
	
	private Handler chatRoomHandler =null;
	
	public static MinetClient getInstance(){
		if(instance == null) {
			instance = new MinetClient();
		}
		return instance;
	}
	
	public MinetClient() {
	}
	
	public void connectToServer(){
		DebugLog.log("begin to connect");
		try {
			socket = new Socket(SERVER_HOST,PORT);
			readThread = new Thread(new ReadThread(socket));
			readThread.start();
			pw = getWriter(socket);
			DebugLog.log("connect to server");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void startBeat() {
		new HeartbeatTimer().run(new TimerTask() {
			@Override
			public void run() {
				IMessage message = new IMessage(IMessage.MSG_BEATHEART);
				message.addArgs("username", UserManager.getInstance().getCurrentUser().getUsername());
				 sendToMiro(message);
			}

		},25000);
	}


	public PrintWriter getWriter(Socket socket) throws IOException {
		return new  PrintWriter(new OutputStreamWriter(socket.getOutputStream(),
				DEFAULT_CODER), true);
	}
	
	public void sendToMiro(IMessage msg,IListener listener) {
		DebugLog.log(msg.toString());
		pw.println(msg.toString());
		ListenerManager.getInstance().putListener(msg.getType(), listener);
	}
	
	public void sendToMiro(IMessage msg){
		DebugLog.log(msg.toString());
		pw.println(msg.toString());
	}
	
	

	public Handler getChatRoomHandler() {
		return chatRoomHandler;
	}

	public void setChatRoomHandler(Handler chatRoomHandler) {
		this.chatRoomHandler = chatRoomHandler;
	}
}
