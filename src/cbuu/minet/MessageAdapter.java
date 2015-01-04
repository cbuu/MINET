package cbuu.minet;

import java.util.List;

import cbuu.minet.common.JMessage;
import cbuu.minet.common.User;
import cbuu.minet.util.DebugLog;

import android.R.integer;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;

public class MessageAdapter extends BaseAdapter {

	private Context context = null;
	private LayoutInflater inflater = null;
	private List<JMessage> messages = null;
	private int info = 0;

	public MessageAdapter(Context context_, List<JMessage> msg, User usr) {
		context = context_;
		inflater = LayoutInflater.from(context);
		messages = msg;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return messages.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return messages.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	// @Override
	// public int getItemViewType(int position) {
	// }

	class Holder {
		public ImageButton usrHead;
		public Button usrMessage;
		int type;
	}

	@Override
	public int getItemViewType(int position) {
		JMessage msg = messages.get(position);
		int type = msg.getType();
		return type;
	}

	@Override
	public View getView(int arg0, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub

		info = getItemViewType(arg0);
		String m = messages.get(arg0).getMessage();
		Holder holder ;
		if (view==null) {
			holder = new Holder();
			switch (info) {
			case 1:
				view = inflater.inflate(R.layout.item_me, null);
				holder.usrHead = (ImageButton) view.findViewById(R.id.me_head);
				holder.usrMessage = (Button) view.findViewById(R.id.me_message);
				holder.usrMessage.setText(m);
				holder.type = info;
				view.setTag(holder);
				break;

			case 0:
				view = inflater.inflate(R.layout.item_you, null);
				holder.usrHead = (ImageButton) view.findViewById(R.id.you_head);
				holder.usrMessage = (Button) view.findViewById(R.id.you_message);
				holder.usrMessage.setText(m);
				holder.type = info;
				view.setTag(holder);
				break;

			default:
				break;
			}
		} else {
			holder = (Holder)view.getTag();
			switch (info) {
			case 1:
				if (holder.type==info) {
					holder.usrMessage.setText(m);
				}else {
					holder = new Holder();
					view = inflater.inflate(R.layout.item_me, null);
					holder.usrHead = (ImageButton) view.findViewById(R.id.me_head);
					holder.usrMessage = (Button) view.findViewById(R.id.me_message);
					holder.usrMessage.setText(m);
					holder.type = info;
					view.setTag(holder);
				}
				break;
			case 0:
				if (holder.type==info) {
					holder.usrMessage.setText(m);
				}else {
					holder = new Holder();
					view = inflater.inflate(R.layout.item_you, null);
					holder.usrHead = (ImageButton) view.findViewById(R.id.you_head);
					holder.usrMessage = (Button) view.findViewById(R.id.you_message);
					holder.usrMessage.setText(m);
					holder.type = info;
					view.setTag(holder);
				}
				break;

			default:
				break;
			}
		}
		

		return view;
	}

}
