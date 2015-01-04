package cbuu.minet.util;

import java.util.Timer;
import java.util.TimerTask;

public class HeartbeatTimer {
	private Timer timer = null;
	
	public HeartbeatTimer() {
		timer = new Timer();
	}
	
	public void run(TimerTask task,long time) {
		timer.schedule(task, 0,time);
	}

}
