package cbuu.minet.common;

import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter;

public class IMessage {
	// Respond code
	public final static String OK = "1";
	public final static String ERROE = "2";

	// Message type for client
	public final static int MSG_LOGIN = 1;
	public final static int MSG_REGISTER = 2;
	public final static int MSG_SEND = 3;
	public final static int MSG_SEND_BROCAST = 4;
	public final static int MSG_POST = 5;
	public final static int MSG_PULL = 6;
	public final static int MSG_ATTEND = 7;
	public final static int MSG_LOGOUT = 8;
	public final static int MSG_BEATHEART = 9;
	public final static int MSG_EXITROOM = 10;
	
	public final static int SHAKE_HAND = 50;
	public final static int SAY_GOODBYE = 51;

	// Message type for sever
	public final static int MSG_BROCAST = 91;
	public final static int MSG_PUSH = 92;
	public final static int MSG_RESPOND = 93;

	private static Gson gson = new Gson();

	private int type;
	
	private int id;

	private String data;

	private HashMap<String, String> args;

	@Override
	public String toString() {
		return gson.toJson(this);
	}

	public static String toJson(Object object) {
		return gson.toJson(object);
	}

	public static IMessage toMessage(String json) {
		return gson.fromJson(json, IMessage.class);
	}

	public IMessage(int type) {
		this.type = type;
		this.args = new HashMap<String, String>();
	}

	public int getType() {
		return type;
	}

	public void setType(int mSG_TYPE) {
		this.type = mSG_TYPE;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setArgs(HashMap<String, String> args) {
		this.args = args;
	}

	public void addArgs(String key, String val) {
		args.put(key, val);
	}

	public String get(String key) {
		return args.get(key);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
