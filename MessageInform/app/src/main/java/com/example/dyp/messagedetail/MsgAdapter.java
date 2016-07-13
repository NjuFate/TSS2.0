package com.example.dyp.messagedetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dyp.messageinform.R;

import java.util.List;

public class MsgAdapter extends ArrayAdapter<Msg> {
	private AdapterView.OnItemClickListener mOnItemClickListener;
	private int resourceId;
	
	public MsgAdapter(Context context, int textViewResourceId, List<Msg> objects){
		super(context, textViewResourceId, objects);
		resourceId=textViewResourceId;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		Msg msg=getItem(position);
		View view;
		ViewHolder viewHolder;
		if(convertView==null){
			view= LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder=new ViewHolder();
			viewHolder.leftLayout=(RelativeLayout)view.findViewById(R.id.left_layout);
			viewHolder.rightLayout=(RelativeLayout)view.findViewById(R.id.right_layout);
			viewHolder.leftMsg=(TextView)view.findViewById(R.id.reply_textView);
			viewHolder.rightMsg=(TextView)view.findViewById(R.id.reply_textView_receiver);
			view.setTag(viewHolder);
		}else{
			view=convertView;
			viewHolder=(ViewHolder)view.getTag();
		}
		
		if(msg.getType()==Msg.TYPE_RECEIVED){
			viewHolder.leftLayout.setVisibility(View.VISIBLE);
			viewHolder.rightLayout.setVisibility(View.GONE);
			viewHolder.leftMsg.setText(msg.getContent());
		}else if(msg.getType()==Msg.TYPE_SENT){
			viewHolder.rightLayout.setVisibility(View.VISIBLE);
			viewHolder.leftLayout.setVisibility(View.GONE);
			viewHolder.rightMsg.setText(msg.getContent());
		}
		return view;	
	}


	public void setOnItemClickListener(AdapterView.OnItemClickListener mOnItemClickListener) {
		this.mOnItemClickListener = mOnItemClickListener;
	}

	interface OnItemClickListener {
		public void onItemClick(View view, int position);
		public void onItemLongClick(View view, int position);
	}
	
	class ViewHolder{
		RelativeLayout leftLayout;
		RelativeLayout rightLayout;
		TextView leftMsg;
		TextView rightMsg;
	}
}
