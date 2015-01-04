package cbuu.minet.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.R.bool;

import cbuu.minet.common.User;

public class UserManager {
	private User currentUser = null;

	private static UserManager instance = null;

	private List<User> onlineUsers = null;
	
	private boolean inRoom = false;
	
	public void setInRoom(boolean inRoom) {
		this.inRoom = inRoom;
	}
	
	public boolean isInRoom() {
		return inRoom;
	}

	public static UserManager getInstance() {
		if (instance == null) {
			instance = new UserManager();
		}
		return instance;
	}

	public UserManager() {
		onlineUsers = new ArrayList<User>();
	}

	public void setOnlineUsers(List<User> onlineUsers) {
		this.onlineUsers = onlineUsers;
	}

	public List<User> getOnlineUsers() {
		return onlineUsers;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

}
