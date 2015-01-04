package cbuu.minet.handlers;

import java.net.Socket;

import cbuu.minet.common.IHandler;
import cbuu.minet.common.IListener;
import cbuu.minet.common.IMessage;
import cbuu.minet.util.DebugLog;

public class HandlerFactory {

	public HandlerFactory() {
		// TODO Auto-generated constructor stub
	}

	public static IHandler createHandler(Socket socket, IMessage msg) {
		IHandler handler = null;

		switch (msg.getType()) {
		case IMessage.MSG_BROCAST:
			handler = new BrocastHandler(socket, msg);
			break;
		case IMessage.MSG_RESPOND:
			handler = new RespondHandler(socket, msg);
			break;
		case IMessage.MSG_PUSH:
			handler = new PushHandler(socket, msg);
			break;
		}

		return handler;
	}
}
