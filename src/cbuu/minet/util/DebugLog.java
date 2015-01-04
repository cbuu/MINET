package cbuu.minet.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class DebugLog {
	public static void log(String msg) {
		Log.d("debug", msg);
	}
	
	public static void log(String tag,String msg){
		Log.d(tag, msg);
	}
	
	public static void toast(Context context,String msg){
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}
}
