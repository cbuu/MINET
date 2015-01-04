package cbuu.minet;

import cbuu.minet.common.Constant;
import cbuu.minet.common.IListener;
import cbuu.minet.common.IMessage;
import cbuu.minet.common.User;
import cbuu.minet.manager.ListenerManager;
import cbuu.minet.manager.UserManager;
import cbuu.minet.network.MinetClient;
import cbuu.minet.network.NetworkThread;
import cbuu.minet.util.DebugLog;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

	private EditText username = null;
	private EditText password = null;

	private Button login = null;
	private Button register = null;

	private Context context = null;

	private String name = null;
	private String pass = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		context = this;
		initView();
		initNetWork();

		login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				name = username.getText().toString();
				pass = password.getText().toString();

				Log.d("debug", "send!" + name + pass);

				IMessage msg = new IMessage(IMessage.MSG_LOGIN);

				msg.addArgs("username", name);
				msg.addArgs("password", pass);

				MinetClient.getInstance().sendToMiro(msg, new IListener() {

					@Override
					public void onSucceed() {
						User user = new User();
						user.setUsername(name);
						UserManager.getInstance().setCurrentUser(user);
						MinetClient.getInstance().startBeat();

						IMessage pullMsg = new IMessage(IMessage.MSG_PULL);
						
						MinetClient.getInstance().sendToMiro(pullMsg, new IListener() {
							
							@Override
							public void onSucceed() {
								Intent intent = new Intent();
								intent.setClass(LoginActivity.this, ListActivity.class);
								startActivity(intent);
								LoginActivity.this.finish();
							}
							
							@Override
							public void onFailed(String errorMsg) {
								DebugLog.log("pull error");
							}
						});
					}

					@Override
					public void onFailed(String errorMsg) {
						DebugLog.log("failed to login in!");
					}
				});
			}
		});

		register.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(LoginActivity.this, RegistActivity.class);
				startActivity(intent);
				LoginActivity.this.finish();
			}
		});

	}

	public void initView() {
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);

		login = (Button) findViewById(R.id.login);
		register = (Button) findViewById(R.id.regist);
	}

	public void initNetWork() {
		new Thread(new NetworkThread()).start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
