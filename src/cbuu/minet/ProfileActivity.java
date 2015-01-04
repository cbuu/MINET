package cbuu.minet;

import cbuu.minet.util.NameToIconNum;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class ProfileActivity extends Activity {

	private ImageButton headButton = null;
	private EditText nameTextView = null;
	private EditText passwordTextView = null;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.titlebar);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.titlebar);
		setContentView(R.layout.activity_profile);

		ImageButton backButton = (ImageButton) findViewById(R.id.left);
		ImageButton yesButton = (ImageButton) findViewById(R.id.right);
		TextView titleTextView = (TextView) findViewById(R.id.title);
		titleTextView.setText("Profile");

		headButton = (ImageButton) findViewById(R.id.pro_head);
		nameTextView = (EditText) findViewById(R.id.pro_name);
		passwordTextView = (EditText) findViewById(R.id.pro_password);

		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(ProfileActivity.this, ListActivity.class);
				startActivity(intent);
				ProfileActivity.this.finish();
			}
		});

		yesButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				// !!!!!!!!!!!!保存个人信息：

				// !!!!!!!!!!!!!!!!!!!!!
				// 返回主界面：
				Intent intent = new Intent();
				intent.setClass(ProfileActivity.this, ListActivity.class);
				startActivity(intent);
				ProfileActivity.this.finish();
			}
		});

		headButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(ProfileActivity.this, FacesActivity.class);
				startActivity(intent);

			}
		});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		Bundle data = this.getIntent().getExtras();
		if (data != null) {
			//
			headButton.setImageDrawable(getResources().getDrawable(
					data.getInt("iconNum")));
		}

	}
}
