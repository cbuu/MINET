package cbuu.minet.common;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import android.R.integer;

import cbuu.minet.manager.ListenerManager;
import cbuu.minet.util.DebugLog;

public abstract class IHandler {

	protected final String DEFAULT_CODER = "UTF-8";

	protected Socket socket = null;
	protected PrintWriter pw = null;

	protected IMessage msg = null;
	
	protected IListener listener = null;

	public IHandler(Socket socket, IMessage msg) {
		this.socket = socket;
		this.msg = msg;
		this.listener = ListenerManager.getInstance().getListenerById(msg.getId());
		try {
			this.pw = getWriter(socket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public PrintWriter getWriter(Socket socket) throws IOException {
		return new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),
				DEFAULT_CODER), true);
	}

	protected void sendMessage(IMessage msg) {
		pw.println(msg.toString());
	}

	// this method is to handle the network
	public abstract void run();
}
