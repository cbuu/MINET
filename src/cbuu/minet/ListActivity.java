package cbuu.minet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cbuu.minet.common.ChatRoom;
import cbuu.minet.common.IListener;
import cbuu.minet.common.IMessage;
import cbuu.minet.common.User;
import cbuu.minet.manager.UserManager;
import cbuu.minet.network.MinetClient;
import cbuu.minet.network.P2P.P2PCore;
import cbuu.minet.network.P2P.P2PThread;

import cbuu.minet.util.DebugLog;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TextView;

public class ListActivity extends Activity {

	private MyAdapter adapter;
	private ListView listView = null;
	private List<User> dataList = new ArrayList<User>();

	private IAdapter adapter2 = null;
	private ListView roomView = null;
	private List<ChatRoom> rooms = null;

	private Context context = null;

	private boolean inList = true;
	
	private Handler handler =null;

	class ListActivityHandler extends Handler {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				Intent intent = new Intent();
				intent.setClass(ListActivity.this, ChatActivity.class);
				startActivity(intent);
				break;
			case 2:
				List<User> users = UserManager.getInstance().getOnlineUsers();
				dataList.clear();
				setData(users);
				listView = (ListView) findViewById(R.id.listView2);
				adapter = new MyAdapter(context, dataList, listView);
				listView.setAdapter(adapter);
				break;
			default:
				break;
			}
		};
	};

	public void initP2p(final Handler handler) {
		new Thread(new P2PThread(handler)).start();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		handler = new ListActivityHandler();
		initP2p(handler);

		this.context = this;

		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.titlebar);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.titlebar);
		setContentView(R.layout.activity_list);

		ImageButton backButton = (ImageButton) findViewById(R.id.left);
		ImageButton settingButton = (ImageButton) findViewById(R.id.right);
		backButton.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.person));
		settingButton.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.settings));

		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Intent intent = new Intent();
				// intent.setClass(ListActivity.this, ProfileActivity.class);
				// startActivity(intent);
			}
		});

		settingButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// Intent intent = new Intent();
				// intent.setClass(ListActivity.this, SettingsActivity.class);
				// startActivity(intent);
			}
		});

		// TabHost:
		TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
		tabHost.setup();
		tabHost.addTab(tabHost
				.newTabSpec("tab1")
				.setIndicator("�û�",
						getResources().getDrawable(R.drawable.ic_launcher))
				.setContent(R.id.tab1));
		tabHost.addTab(tabHost
				.newTabSpec("tab2")
				.setIndicator("Ⱥ��",
						getResources().getDrawable(R.drawable.ic_launcher))
				.setContent(R.id.tab2));

		List<User> users = UserManager.getInstance().getOnlineUsers();
		setData(users);
		listView = (ListView) findViewById(R.id.listView2);
		adapter = new MyAdapter(this, dataList, listView);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				inList = false;
				User ortherUser = dataList.get(arg2);
				String ip = ortherUser.getIP();

				P2PCore.getInstance().setOrtherUser(ortherUser);

				P2PCore.getInstance().call(ip);

				Intent intent = new Intent(ListActivity.this,
						ChatActivity.class);
				startActivity(intent);
			}
		});

		ChatRoom room = new ChatRoom();
		room.setName("ChatRoom");
		rooms = new ArrayList<ChatRoom>();
		rooms.add(room);
		roomView = (ListView) findViewById(R.id.listView3);
		adapter2 = new IAdapter(this, rooms, roomView);
		roomView.setAdapter(adapter2);
		roomView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				inList = false;
				IMessage attend = new IMessage(IMessage.MSG_ATTEND);
				attend.addArgs("username", UserManager.getInstance()
						.getCurrentUser().getUsername());

				MinetClient.getInstance().sendToMiro(attend);

				Intent intent = new Intent(ListActivity.this,
						ChatRoomActivity.class);
				startActivity(intent);
			}
		});
		DebugLog.log("init end");

		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				if (inList) {
					IMessage pull = new IMessage(IMessage.MSG_PULL);
					MinetClient.getInstance().sendToMiro(pull, new IListener() {

						@Override
						public void onSucceed() {
							handler.obtainMessage(2).sendToTarget();
						}

						@Override
						public void onFailed(String errorMsg) {
							DebugLog.log("refreshlist error");
						}
					});
				}
			}
		}, 0, 10000);
	}

	private Timer timer = null;

	public void setData(List<User> u) {
		Iterator<User> iterator = u.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			dataList.add(user);
		}
	}

	@Override
	protected void onResume() {
		inList = true;
		super.onResume();
	}

}
