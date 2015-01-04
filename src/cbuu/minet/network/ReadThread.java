package cbuu.minet.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import android.os.Handler;
import cbuu.minet.common.IHandler;
import cbuu.minet.common.IListener;
import cbuu.minet.common.IMessage;
import cbuu.minet.handlers.HandlerFactory;
import cbuu.minet.util.DebugLog;

public class ReadThread implements Runnable{

	private final String DEFAULT_CODER = "UTF-8";
	
	private Socket socket = null;
	private BufferedReader br = null;
	
	public ReadThread(Socket socket) {
		setSocket(socket);
	}
	
	public ReadThread() {
	}
	
	public void setSocket(Socket socket) {
		this.socket = socket;
		try {
			this.br = getReader(socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedReader getReader(Socket socket) throws IOException {
		InputStream is = socket.getInputStream();
		return new BufferedReader(new InputStreamReader(is, DEFAULT_CODER));
	}
	
	public void receive() {
		String receiveData = null;
		try {
			while((receiveData=br.readLine())!=null){
				DebugLog.log(receiveData);
				IMessage msg = IMessage.toMessage(receiveData);
				IHandler handler = HandlerFactory.createHandler(socket, msg);
				handler.run();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		receive();
	}
}
