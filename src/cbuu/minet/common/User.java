package cbuu.minet.common;

public class User {
	public static final int DEFAULT_ICON = 0;

	private String username = null;
	private String ip = null;
	private int port = 0;

	private int iconNum = DEFAULT_ICON;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String username, String ip, int port) {
		this.username = username;
		this.ip = ip;
		this.port = port;
	}

	public int getIconNum() {
		return iconNum;
	}

	public void setIconNum(int iconNum) {
		this.iconNum = iconNum;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public String getIP() {
		return ip;
	}

	public void setIP(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Boolean equalTo(User usr) {
		if (this.username.equals(usr.getUsername())
				&& this.ip.equals(usr.getIP()) && this.port == usr.getPort()) {
			return true;
		} else {
			return false;
		}
	}

}
