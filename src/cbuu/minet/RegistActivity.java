package cbuu.minet;

import cbuu.minet.common.IListener;
import cbuu.minet.common.IMessage;
import cbuu.minet.common.User;
import cbuu.minet.manager.UserManager;
import cbuu.minet.network.MinetClient;
import cbuu.minet.util.DebugLog;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import android.widget.TextView;

public class RegistActivity extends Activity {

	private EditText name = null;
	private EditText password = null;
	private ImageButton headButton = null;
	
	private String usernameString =null;
	private String passwordString = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.titlebar);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.titlebar);
		setContentView(R.layout.activity_regist);

		ImageButton backButton = (ImageButton) findViewById(R.id.left);
		ImageButton registButton = (ImageButton) findViewById(R.id.right);
		TextView titleTextView = (TextView) findViewById(R.id.title);
		headButton = (ImageButton) findViewById(R.id.re_head);
		name = (EditText) findViewById(R.id.re_name);
		password = (EditText) findViewById(R.id.re_password);
		titleTextView.setText("Regist");
		// headButton.setImageDrawable(getResources().getDrawable(R.drawable.superman));

		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(RegistActivity.this, LoginActivity.class);
				startActivity(intent);
				RegistActivity.this.finish();
			}
		});

		registButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				usernameString = name.getText().toString();
				passwordString = password.getText().toString();
				
				IMessage msg = new IMessage(IMessage.MSG_REGISTER);

				msg.addArgs("username", usernameString);
				msg.addArgs("password", passwordString);
				
				MinetClient.getInstance().sendToMiro(msg, new IListener() {
					
					@Override
					public void onSucceed() {
						User user = new User();
						user.setUsername(usernameString);
						UserManager.getInstance().setCurrentUser(user);
						
						
						Intent intent = new Intent();
						intent.setClass(RegistActivity.this, ListActivity.class);
						startActivity(intent);
						RegistActivity.this.finish();
					}
					
					@Override
					public void onFailed(String errorMsg) {
						DebugLog.log("register failed!");
					}
				});
			}
		});

		headButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

}
