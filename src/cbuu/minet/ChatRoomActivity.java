package cbuu.minet;

import java.util.ArrayList;
import java.util.List;

import cbuu.minet.ChatActivity.ChatHandler;
import cbuu.minet.common.IMessage;
import cbuu.minet.common.JMessage;
import cbuu.minet.common.User;
import cbuu.minet.manager.UserManager;
import cbuu.minet.network.MinetClient;
import cbuu.minet.network.P2P.P2PCore;
import cbuu.minet.util.DebugLog;
import android.app.Activity;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class ChatRoomActivity extends Activity{
	
	private EditText messageEditText;
	private Button sendButton;
	private MessageAdapter messageAdapter;
	private ListView chatListView = null;
	private List<JMessage> messages = new ArrayList<JMessage>();

	private Handler handler = null;

	class ChatRoomHandler extends Handler {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				DebugLog.log("handler brocast");
				IMessage message = (IMessage) msg.obj;
				if (!UserManager.getInstance().getCurrentUser().getUsername().equals(message.get("sender"))) {
					SetMessages(me, message.get("content"), 0);
					addItem();
				}
				break;
			case 2:
				finish();
				break;
			default:
				break;
			}
		};
	};

	private User me = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		UserManager.getInstance().setInRoom(true);
		
		handler = new ChatRoomHandler();
		
		MinetClient.getInstance().setChatRoomHandler(handler);
		
		me = UserManager.getInstance().getCurrentUser();
		
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.titlebar);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.titlebar);
		setContentView(R.layout.activity_chat);

		ImageButton backButton = (ImageButton) findViewById(R.id.left);
		ImageButton noButton = (ImageButton) findViewById(R.id.right);
		TextView title = (TextView) findViewById(R.id.title);
		title.setText("CHAT");

		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				UserManager.getInstance().setInRoom(false);
				IMessage exitroom = new IMessage(IMessage.MSG_EXITROOM);
				exitroom.addArgs("username", UserManager.getInstance().getCurrentUser().getUsername());
				
				MinetClient.getInstance().sendToMiro(exitroom);
				
				finish();
			}
		});

		noButton.setVisibility(View.INVISIBLE);

		// sending:
		messageEditText = (EditText) findViewById(R.id.message);
		sendButton = (Button) findViewById(R.id.send);

		// chatting:
		// ������messages
		chatListView = (ListView) findViewById(R.id.chatlist);
		
		messageAdapter = new MessageAdapter(this, messages, null);
		chatListView.setAdapter(messageAdapter);

		sendButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String msg = messageEditText.getText().toString();
				messageEditText.setText("");
				IMessage message = new IMessage(IMessage.MSG_SEND_BROCAST);
				message.addArgs("sender", UserManager.getInstance().getCurrentUser().getUsername());
				message.addArgs("content", msg);
				
				SetMessages(me, msg, 1);
				addItem();
				
				MinetClient.getInstance().sendToMiro(message);
			}
		});

	}

	public void SetMessages(User u, String s, int t) {
		JMessage a = new JMessage();
		a.setUser(u);
		a.setMessage(s);
		a.setType(t);
		messages.add(a);
	}

	public void addItem() {
		messageAdapter = new MessageAdapter(this, messages, null);
		chatListView.setAdapter(messageAdapter);
	}
}
