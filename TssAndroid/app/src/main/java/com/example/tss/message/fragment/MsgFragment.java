package com.example.tss.message.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.example.tss.message.helper.InformMsgLoader;
import com.example.tss.message.activity.MsgActivity;
import com.example.tss.message.adapter.ListAdapter;
import com.example.tss.message.widget.DividerItemDecoration;
import com.example.tss.tssandroid.R;

import java.util.ArrayList;
import java.util.List;
import android.app.LoaderManager;
import com.example.tss.message.helper.InformMsgDataHelper;
import com.example.tss.message.entity.InformMessage;

/**
 * Created by dyp on 2016/7/16.
 */
public class MsgFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<InformMessage>>{
    private static final String URL_GET_RECENT_MESSAGE = "http://123.206.70.29:8080/tss2/android.do";
    private static final int LOADER_ID = 1;

    private RecyclerView mRecyclerView;
    private ListAdapter mListAdapter;
    private List<InformMessage> mDatas = new ArrayList<InformMessage>();;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private Context mContext;
    private LoaderManager.LoaderCallbacks<List<InformMessage>> mCallBack;
    private LoaderManager loaderManager;
    private int receiverId = 141250052;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.messagelayout,container,false);
        mContext = getActivity();
        loaderManager = getLoaderManager();
        loaderManager.initLoader(LOADER_ID,null,this);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.message_recycler_view);
        mSwipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshWidget.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipeRefreshWidget.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {

                // TODO Auto-generated method stub
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
//                        getRefreshed(receiverId);
                        loaderManager.getLoader(LOADER_ID).forceLoad();
                    }
                }, 1000);
            }
        });
        // 这句话是为了，第一次进入页面的时候显示加载进度条
//        mSwipeRefreshWidget.setProgressViewOffset(false, 0, (int) TypedValue
//                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
//                        .getDisplayMetrics()));

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
        initData();     //为了更新时间

    }

    private void setRecyclerView(){
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, -1));
        mListAdapter = new ListAdapter(mContext,mDatas);
        mListAdapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                intent.setClass(mContext, MsgActivity.class);
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

                intent.putExtra("title",msg.getTitle());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
//                Toast.makeText(getActivity(), "LongClick" +position+"@@"+mDatas.get(position).getTitle(), Toast.LENGTH_SHORT).show();
//                mListAdapter.remove(position); //remove the item
//                Toast.makeText(getActivity(), mRecyclerView.getChildCount()+" ", Toast.LENGTH_SHORT).show();
                InformMsgDataHelper helper = new InformMsgDataHelper(mContext);
                helper.deleteAllInformMsg(receiverId);
                initData();
            }
        });

        mRecyclerView.setAdapter(mListAdapter);

    }


    /**
     * Create datas
     */
    protected void initData() {

        InformMsgDataHelper helper = new InformMsgDataHelper(mContext);
        List<InformMessage> list=helper.getLocalInformMsg(receiverId);
//        helper.deleteAllInformMsg(receiverId);
        updateViewList(list);

    }

//    private void getRefreshed(int id){
//        InformMsgDataHelper helper = new InformMsgDataHelper(mContext);
//        List<InformMessage> list = helper.getInformMsg(id);
//
//        updateViewList(list);
//        mSwipeRefreshWidget.setRefreshing(false);
//    }


    private void updateViewList(List<InformMessage> list){
        mDatas.clear();
        for(InformMessage msg: list){
            if(msg.getType()==0){
                mDatas.add(0,msg);
            }else{
                mDatas.add(0,msg);
            }

        }

        mListAdapter.notifyDataSetChanged();



    }



    private void updateUnReadDotAndTime(List<InformMessage> list){
        InformMsgDataHelper helper = new InformMsgDataHelper(mContext);
        for(InformMessage msg:list){
            int result = helper.checkIfRead(msg.getSender(),msg.getReceiver());
            View view = mRecyclerView.getLayoutManager().findViewByPosition(list.indexOf(msg));

            if(null==view) {
                Toast.makeText(getActivity(), "更新未读消息和时间出错啦！", Toast.LENGTH_SHORT).show();
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


    @Override
    public Loader<List<InformMessage>> onCreateLoader(int id, Bundle args) {
        InformMsgLoader loader = new InformMsgLoader(mContext, URL_GET_RECENT_MESSAGE,receiverId);

        return loader;
    }

    @Override
    public void onLoadFinished(Loader<List<InformMessage>> loader, List<InformMessage> data) {
        InformMsgDataHelper helper = new InformMsgDataHelper(mContext);
        helper.saveInformMsg(data);
        List<InformMessage> list = helper.getLocalInformMsg(receiverId);

        updateViewList(helper.getLocalInformMsg(receiverId));
        mSwipeRefreshWidget.setRefreshing(false);
    }

    @Override
    public void onLoaderReset(Loader<List<InformMessage>> loader) {

    }
}
