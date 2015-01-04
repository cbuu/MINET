package cbuu.minet;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class SettingsActivity extends Activity {

	private Button aboutButton = null;
	private Button helpButton = null;
	private Button logoutButton = null;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.titlebar);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.titlebar);
		setContentView(R.layout.activity_settings);

		ImageButton backbuButton = (ImageButton) findViewById(R.id.left);
		ImageButton likeButton = (ImageButton) findViewById(R.id.right);
		aboutButton = (Button) findViewById(R.id.about_us);
		helpButton = (Button) findViewById(R.id.help);
		logoutButton = (Button) findViewById(R.id.logout);
		likeButton.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.settings));
		TextView titleTextView = (TextView) findViewById(R.id.title);
		titleTextView.setText("Settings");

		backbuButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(SettingsActivity.this, ListActivity.class);
				startActivity(intent);
				SettingsActivity.this.finish();
			}
		});

		// likeButton.setVisibility(View.INVISIBLE);

		logoutButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(SettingsActivity.this, LoginActivity.class);
				SettingsActivity.this.startActivity(intent);
				SettingsActivity.this.finish();
			}
		});

		helpButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Uri baidu = Uri.parse("http://www.baidu.com");
				Intent intent = new Intent(Intent.ACTION_VIEW, baidu);
				SettingsActivity.this.startActivity(intent);
				SettingsActivity.this.finish();
			}
		});
	}
}
