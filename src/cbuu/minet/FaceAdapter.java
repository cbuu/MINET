package cbuu.minet;

import java.util.List;

import cbuu.minet.common.FaceItem;
import cbuu.minet.common.User;
import cbuu.minet.util.NameToIconNum;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract.Profile;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class FaceAdapter extends BaseAdapter {

	private LayoutInflater inflater = null;
	private List<FaceItem> dataList = null;
	// private ListView listView = null;
	private Context context_ = null;
	private User user;

	public FaceAdapter(FacesActivity context, List<FaceItem> dataList2, User usr) {
		// TODO Auto-generated constructor stub
		context_ = context;
		inflater = LayoutInflater.from(context);
		dataList = dataList2;
		// listView = listView2;
		user = usr;
	}

	@Override
	public int getCount() {
		return dataList.size();
	}

	@Override
	public Object getItem(int arg0) {
		return dataList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	public final class ViewHolder {
		public ImageButton face1;
		public ImageButton face2;
		public ImageButton face3;
		public ImageButton face4;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;

		if (convertView == null) {

			holder = new ViewHolder();

			convertView = inflater.inflate(R.layout.face, null);

			holder.face1 = (ImageButton) convertView
					.findViewById(R.id.face_one);
			int iconNum = NameToIconNum.getInstance().nameToIconNum(
					dataList.get(position).getName1());
			holder.face1.setImageDrawable(context_.getResources().getDrawable(
					iconNum));

			holder.face2 = (ImageButton) convertView
					.findViewById(R.id.face_two);
			iconNum = NameToIconNum.getInstance().nameToIconNum(
					dataList.get(position).getName2());
			holder.face2.setImageDrawable(context_.getResources().getDrawable(
					iconNum));

			holder.face3 = (ImageButton) convertView
					.findViewById(R.id.face_three);
			iconNum = NameToIconNum.getInstance().nameToIconNum(
					dataList.get(position).getName3());
			holder.face3.setImageDrawable(context_.getResources().getDrawable(
					iconNum));

			holder.face4 = (ImageButton) convertView
					.findViewById(R.id.face_four);
			iconNum = NameToIconNum.getInstance().nameToIconNum(
					dataList.get(position).getName4());
			holder.face4.setImageDrawable(context_.getResources().getDrawable(
					iconNum));

			// holder.nameView.setText(dataList.get(position).getUsername());

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.face1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Bundle bundle = new Bundle();
				bundle.putInt("iconNum", NameToIconNum.getInstance()
						.nameToIconNum(dataList.get(position).getName1()));

				Intent intent = new Intent();
				intent.setClass(context_, ProfileActivity.class);
				intent.putExtras(bundle);
				context_.startActivity(intent);
				((Activity) context_).finish();
			}
		});

		holder.face2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Bundle bundle = new Bundle();
				bundle.putInt("iconNum", NameToIconNum.getInstance()
						.nameToIconNum(dataList.get(position).getName2()));

				Intent intent = new Intent();
				intent.setClass(context_, ProfileActivity.class);
				intent.putExtras(bundle);
				context_.startActivity(intent);
				((Activity) context_).finish();
			}
		});

		holder.face3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Bundle bundle = new Bundle();
				bundle.putInt("iconNum", NameToIconNum.getInstance()
						.nameToIconNum(dataList.get(position).getName3()));

				Intent intent = new Intent();
				intent.setClass(context_, ProfileActivity.class);
				intent.putExtras(bundle);
				context_.startActivity(intent);
				((Activity) context_).finish();
			}
		});

		holder.face4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Bundle bundle = new Bundle();
				bundle.putInt("iconNum", NameToIconNum.getInstance()
						.nameToIconNum(dataList.get(position).getName4()));

				Intent intent = new Intent();
				intent.setClass(context_, ProfileActivity.class);
				intent.putExtras(bundle);
				context_.startActivity(intent);
				((Activity) context_).finish();
			}
		});

		return convertView;
	}

}
