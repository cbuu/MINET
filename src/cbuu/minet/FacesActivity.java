package cbuu.minet;

import java.util.ArrayList;
import java.util.List;

import cbuu.minet.common.FaceItem;
import cbuu.minet.common.User;
import cbuu.minet.manager.UserManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

public class FacesActivity extends Activity {

	private FaceAdapter faceAdapter;
	private ListView listView = null;
	List<FaceItem> faceData = new ArrayList<FaceItem>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.titlebar);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.titlebar);
		setContentView(R.layout.activity_faces);

		ImageButton backButton = (ImageButton) findViewById(R.id.left);
		ImageButton noButton = (ImageButton) findViewById(R.id.right);

		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(FacesActivity.this, ProfileActivity.class);
				startActivity(intent);
				FacesActivity.this.finish();

			}
		});

		noButton.setVisibility(View.INVISIBLE);

		// ≤‚ ‘”√
		FaceItem a = new FaceItem("southpark", "southparktwo",
				"southparkthree", "southparkfour");
		FaceItem b = new FaceItem("angry", "astroboy", "bigyellowdog", "cool");
		FaceItem c = new FaceItem("cry", "doraemon", "girl", "hellokitty");
		FaceItem d = new FaceItem("qq", "programmer", "simpson", "simpsontwo");
		faceData.add(a);
		faceData.add(b);
		faceData.add(c);
		faceData.add(d);
		User user = new User("Deng", "172.18.35.173", 1);

		listView = (ListView) findViewById(R.id.faceList);
		faceAdapter = new FaceAdapter(this, faceData, user);
		listView.setAdapter(faceAdapter);

	}

}
