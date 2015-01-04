package cbuu.minet.common;

import android.renderscript.Type;

public class JMessage {

	private User user = null;
	private String message = null;
	private int type = 0;

	public JMessage() {
	}

	public JMessage(User usr, String msg) {
		this.user = usr;
		this.message = msg;
	}

	public void setUser(User usr) {
		this.user = usr;
	}

	public void setMessage(String msg) {
		this.message = msg;
	}

	public void setType(int t) {
		this.type = t;
	}

	public User getUser() {
		return user;
	}

	public String getMessage() {
		return message;
	}

	public int getType() {
		return type;
	}
}
