package cbuu.minet.manager;

import java.util.HashMap;

import cbuu.minet.common.IListener;
import cbuu.minet.util.DebugLog;

import android.R.integer;
import android.os.Handler;

public class ListenerManager {
	private HashMap<Integer,IListener > listeners= null;
	
	private static ListenerManager instance = null;
	
	
	public static ListenerManager getInstance() {
		if (instance==null) {
			instance =  new ListenerManager();
		}
		return instance;
	}
	
	public ListenerManager() {
		listeners = new HashMap<Integer,IListener>();
	}
	
	public void putListener(int id,IListener listener){
		if (listener!= null) {
			listeners.put(id, listener);
		}else {
			DebugLog.log("handler is null");
		}
	}
	
	public IListener getListenerById(int id){
		IListener listener = listeners.get(id);
		listeners.remove(id);
		return listener;
	}
}
