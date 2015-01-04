package cbuu.minet.network.P2P;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;

import javax.security.auth.PrivateCredentialPermission;

import android.os.Handler;

import cbuu.minet.common.IListener;
import cbuu.minet.common.IMessage;
import cbuu.minet.common.User;
import cbuu.minet.util.DebugLog;

public class P2PCore {

	private final int PORT = 5354;

	private User ortherUser = null;
	
	private String IP = null;

	private static P2PCore instance = null;
	
	private DatagramSocket serverSocket = null;
	
	private DatagramSocket clientSocket = null;

	private boolean server = false;
	
	 private Handler listActivityHandler = null;
	 
	 private P2PServerReadThread readThread = null;
	 
	 DatagramPacket sendPacket = null;

	private P2PCore() {

	}
	
	public P2PServerReadThread getReadThread() {
		return readThread;
	}
	
	public void init(Handler handler){
		this.listActivityHandler = handler;
		try {
			serverSocket = new DatagramSocket(PORT);
			clientSocket = new DatagramSocket();
			sendPacket = new DatagramPacket(new byte[0], 0);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void startServerReadThread(){
		readThread = new P2PServerReadThread(serverSocket,listActivityHandler);
		new Thread(readThread).start();
	}

	public void send(String content){
		InetAddress address = null;	
		try {
			address = InetAddress.getByName(IP);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sendPacket.setAddress(address);
		sendPacket.setData(content.getBytes(), 0, content.getBytes().length);
		sendPacket.setPort(PORT);
		
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						clientSocket.send(sendPacket);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
	}
	
	public void call(String IP){
		setIP(IP);
		send(new IMessage(IMessage.SHAKE_HAND).toString());
	}


	public static P2PCore getInstance() {
		if (instance == null) {
			instance = new P2PCore();
		}
		return instance;
	}

	public User getOrtherUser() {
		return ortherUser;
	}

	public void setOrtherUser(User ortherUser) {
		this.ortherUser = ortherUser;
	}

	public boolean isServer() {
		return server;
	}

	public void setServer(boolean server) {
		this.server = server;
	}


	public Handler getListActivityHandler() {
		return listActivityHandler;
	}


	public void setListActivityHandler(Handler listActivityHandler) {
		this.listActivityHandler = listActivityHandler;
	}

	public String getIP() {
		return IP;
	}
	
	public void setIP(String iP) {
		IP = iP;
	}

}
