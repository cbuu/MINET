package cbuu.minet;

import java.util.ArrayList;
import java.util.List;

import cbuu.minet.common.IMessage;
import cbuu.minet.common.JMessage;
import cbuu.minet.common.User;
import cbuu.minet.manager.UserManager;
import cbuu.minet.network.P2P.P2PCore;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class ChatActivity extends Activity {

	private EditText messageEditText;
	private Button sendButton;
	private MessageAdapter messageAdapter;
	private ListView chatListView = null;
	private List<JMessage> messages = new ArrayList<JMessage>();

	private Handler handler = null;

	class ChatHandler extends Handler {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				String string = (String) msg.obj;
				SetMessages(you, string, 0);
				addItem();
				break;
			case 2:
				finish();
				break;
			default:
				break;
			}
		};
	};

	// ������users:
	private User me = null;
	private User you = null;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		handler = new ChatHandler();
		
		P2PCore.getInstance().getReadThread().setChatHandler(handler);

		me = UserManager.getInstance().getCurrentUser();
		;
		you = P2PCore.getInstance().getOrtherUser();
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.titlebar);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.titlebar);
		setContentView(R.layout.activity_chat);

		ImageButton backButton = (ImageButton) findViewById(R.id.left);
		ImageButton noButton = (ImageButton) findViewById(R.id.right);
		TextView title = (TextView) findViewById(R.id.title);
		title.setText("CHAT");// ��ȡ�û���
		// backButton.setImageDrawable(getResources().getDrawable(R.drawable.back));

		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				IMessage bye = new IMessage(IMessage.SAY_GOODBYE);
				P2PCore.getInstance().send(bye.toString());
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
		
		messageAdapter = new MessageAdapter(this, messages, me);
		chatListView.setAdapter(messageAdapter);

		sendButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String msg = messageEditText.getText().toString();
				messageEditText.setText("");

				IMessage message = new IMessage(IMessage.MSG_SEND);
				message.addArgs("content", msg);

				SetMessages(me, msg, 1);
				addItem();
				
				P2PCore.getInstance().send(message.toString());
				
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
		messageAdapter = new MessageAdapter(this, messages, me);
		chatListView.setAdapter(messageAdapter);
	}

}
