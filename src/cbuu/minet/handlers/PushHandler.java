package cbuu.minet.handlers;

import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cbuu.minet.common.IHandler;
import cbuu.minet.common.IListener;
import cbuu.minet.common.IMessage;
import cbuu.minet.common.User;
import cbuu.minet.manager.UserManager;
import cbuu.minet.util.DebugLog;

public class PushHandler extends IHandler{

	public PushHandler(Socket socket, IMessage msg) {
		super(socket, msg);
	}

	@Override
	public void run() {
		String usersData = msg.getData();
		Gson gson = new Gson();
		
		List<User> users = gson.fromJson(usersData, new TypeToken<List<User>>(){}.getType());
		Iterator<User> iterator =  users.iterator();
		String username = UserManager.getInstance().getCurrentUser().getUsername();
		while(iterator.hasNext()){
			User u = iterator.next();
			if (u.getUsername().equals(username)) {
				iterator.remove();
			}
		}
		UserManager.getInstance().setOnlineUsers(users);
		
		if (users!=null) {
			listener.onSucceed();
		}
		
	}

}
