package cbuu.minet;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import cbuu.minet.MyAdapter.ViewHolder;
import cbuu.minet.common.ChatRoom;
import cbuu.minet.common.User;

public class IAdapter extends BaseAdapter{
	private LayoutInflater inflater = null;
	private List<ChatRoom> dataList = null;
	private ListView listView = null;
	private Context context_ = null;

	public IAdapter(ListActivity context, List<ChatRoom> dataList2,
			ListView listView2) {
		// TODO Auto-generated constructor stub
		context_ = context;
		inflater = LayoutInflater.from(context);
		dataList = dataList2;
		listView = listView2;
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
		public ImageButton imgButton;
		public TextView nameView;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;

		if (convertView == null) {

			holder = new ViewHolder();

			convertView = inflater.inflate(R.layout.item, null);
			holder.imgButton = (ImageButton) convertView
					.findViewById(R.id.imageButton);
			holder.nameView = (TextView) convertView
					.findViewById(R.id.nameText);

			holder.nameView.setText(dataList.get(position).getName());

			convertView.setTag(holder);

		} else {

			holder = (ViewHolder) convertView.getTag();
		}

		holder.imgButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(context_, ChatActivity.class);
				context_.startActivity(intent);
			}
		});
		return convertView;
	}
}
