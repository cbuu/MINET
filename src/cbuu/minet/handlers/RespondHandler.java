package cbuu.minet.handlers;

import java.net.Socket;

import cbuu.minet.common.IHandler;
import cbuu.minet.common.IListener;
import cbuu.minet.common.IMessage;

public class RespondHandler extends IHandler {

	public RespondHandler(Socket socket, IMessage msg) {
		super(socket, msg);
	}

	@Override
	public void run() {
		int code = Integer.parseInt(msg.get("code"));
		if (code==1) {
			listener.onSucceed();
		}else {
			listener.onFailed(msg.get("content"));
		}
	}

}
