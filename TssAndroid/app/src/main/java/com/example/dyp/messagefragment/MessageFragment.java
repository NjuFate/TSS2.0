package com.example.dyp.messagefragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.example.dyp.messagedetail.MessageActivity;
import com.example.dyp.tssandroid.R;

import java.util.ArrayList;
import java.util.List;

import data.InformMsgDataHelper;
import entity.InformMessage;


/**
 * Created by dyp on 2016/7/11.
 */
public class MessageFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ListAdapter mListAdapter;
    private List<InformMessage> mDatas = new ArrayList<InformMessage>();;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private int receiverId = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.messagelayout,container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.message_recycler_view);
        mSwipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshWidget.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipeRefreshWidget.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
//                tv.setText("正在刷新");
                // TODO Auto-generated method stub
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
//                        tv.setText("刷新完成");
//                        mDatas.clear();
                        getRefreshed(receiverId);
                    }
                }, 1000);
            }
        });
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        mSwipeRefreshWidget.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));

        setRecyclerView();
        initData();
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // TODO Auto-generated method stub



                updateUnReadDotAndTime(mDatas);

            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();


    }

    private void setRecyclerView(){
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(), -1));
        mListAdapter = new ListAdapter(getActivity(),mDatas);
        mListAdapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MessageActivity.class);
                InformMessage msg = mDatas.get(position);
                int sender = msg.getSender();
                int receiver = msg.getReceiver();

                if(msg.getType()==0){
                    intent.putExtra("senderId",receiver);
                    intent.putExtra("receiverId",sender);
                }else{
                    intent.putExtra("senderId",sender);
                    intent.putExtra("receiverId",receiver);
                }

                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
//                Toast.makeText(getActivity(), "LongClick" +position+"@@"+mDatas.get(position).getTitle(), Toast.LENGTH_SHORT).show();
//                mListAdapter.remove(position); //remove the item
//                Toast.makeText(getActivity(), mRecyclerView.getChildCount()+" ", Toast.LENGTH_SHORT).show();

            }
        });


        mRecyclerView.setAdapter(mListAdapter);

    }


    /**
     * Create datas
     */
    protected void initData() {

        InformMsgDataHelper helper = new InformMsgDataHelper(getActivity());
        List<InformMessage> list=helper.getLocalInformMsg(receiverId);
//        helper.deleteAllInformMsg(receiverId);
        updateViewList(list);

    }

    private void getRefreshed(int id){
        InformMsgDataHelper helper = new InformMsgDataHelper(getActivity());
        List<InformMessage> list = helper.getInformMsg(id);

        updateViewList(list);
        mSwipeRefreshWidget.setRefreshing(false);
    }


    private void updateViewList(List<InformMessage> list){
        mDatas.clear();
        for(InformMessage msg: list){
            if(msg.getType()==0){
                msg.setTitle(msg.getReceiver()+"");
                mDatas.add(0,msg);
            }else{
                msg.setTitle(msg.getSender()+"");
                mDatas.add(0,msg);
            }

        }

        mListAdapter.notifyDataSetChanged();



    }



    private void updateUnReadDotAndTime(List<InformMessage> list){
        InformMsgDataHelper helper = new InformMsgDataHelper(getActivity());
        for(InformMessage msg:list){
            int result = helper.checkIfRead(msg.getSender(),msg.getReceiver());
//            Toast.makeText(getActivity(), result+msg.getTitle(), Toast.LENGTH_SHORT).show();
            View view = mRecyclerView.getLayoutManager().findViewByPosition(list.indexOf(msg));

            if(null==view) {
                Toast.makeText(getActivity(), mRecyclerView.getChildCount() + " ", Toast.LENGTH_SHORT).show();
            }else{
                ListAdapter.ItemViewHolder holder = (ListAdapter.ItemViewHolder) mRecyclerView.getChildViewHolder(view);
                String time = helper.getLastTime(list);
                holder.setTime(time);
                if(result==0){
                    holder.hideRedDot();
                }else{
                    holder.showRedDot();
                }
            }

        }
    }

}


