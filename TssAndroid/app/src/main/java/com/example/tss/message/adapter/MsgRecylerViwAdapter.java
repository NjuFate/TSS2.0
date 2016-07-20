package com.example.tss.message.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tss.tssandroid.R;

import java.util.List;

import com.example.tss.message.entity.InformMessage;

/**
 * Created by dyp on 2016/7/15.
 */
public class MsgRecylerViwAdapter extends RecyclerView.Adapter {
    public static interface OnRecyclerViewListener{
        void onItemClick(int position);
        boolean onItemLongClick(int position);
    }

    private OnRecyclerViewListener onRecyclerViewListener;
    private LayoutInflater mInflater;
    private static final String TAG = MsgRecylerViwAdapter.class.getSimpleName();
    private List<InformMessage> list;

    public MsgRecylerViwAdapter(Context context,List<InformMessage> list){
        this.list = list;
        this.mInflater =  LayoutInflater.from(context);
    }

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Log.d(TAG, "onCreateViewHolder, i: " + i);
        View view = mInflater.inflate(R.layout.msg_item, null);

        return new MsgViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        MsgViewHolder holder = (MsgViewHolder) viewHolder;
        holder.position = position;
        InformMessage msg = list.get(position);
        switch (msg.getType()){
            case InformMessage.MESSAGE_FROM_OTHER:
                holder.leftMsg.setText(msg.getContent());
                holder.leftLayout.setVisibility(View.VISIBLE);
                holder.rightLayout.setVisibility(View.GONE);
                break;
            case InformMessage.MESSAGE_FROM_USER:
                holder.rightMsg.setText((msg.getContent()));
                holder.leftLayout.setVisibility(View.GONE);
                holder.rightLayout.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void add(int position, InformMessage value) {
        if(position > list.size()) {
            position = list.size();
        }
        if(position < 0) {
            position = 0;
        }
        list.add(position, value);
        /**
         * 使用notifyItemInserted/notifyItemRemoved会有动画效果
         * 而使用notifyDataSetChanged()则没有
         */
        notifyItemInserted(position);
    }

    /**
     * 移除指定位置元素
     * @param position
     * @return
     */
    public InformMessage remove(int position) {
        if(position > list.size()-1) {
            return null;
        }
        InformMessage value = list.remove(position);
        notifyItemRemoved(position);
        return value;
    }

    class MsgViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        public int position;
        public View rootView;
        public RelativeLayout leftLayout;
        public RelativeLayout rightLayout;
        public TextView leftMsg;
        public TextView rightMsg;


        public MsgViewHolder(View itemView) {
            super(itemView);

            leftLayout=(RelativeLayout)itemView.findViewById(R.id.left_layout);
            rightLayout=(RelativeLayout)itemView.findViewById(R.id.right_layout);
            leftMsg=(TextView)leftLayout.findViewById(R.id.reply_textView);
            rightMsg=(TextView)rightLayout.findViewById(R.id.reply_textView);
            rootView = itemView.findViewById(R.id.msg_recycler_item_view);
            rootView.setOnClickListener(this);
            rootView.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(null!=onRecyclerViewListener){
                onRecyclerViewListener.onItemClick(position);
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if(null!=onRecyclerViewListener){
                return onRecyclerViewListener.onItemLongClick(position);
            }
            return false;
        }
    }
}
