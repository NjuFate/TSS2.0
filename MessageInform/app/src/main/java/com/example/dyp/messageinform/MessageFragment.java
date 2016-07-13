package com.example.dyp.messageinform;

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
import android.widget.Toast;

import com.example.dyp.messagedetail.MessageActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dyp on 2016/7/11.
 */
public class MessageFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ListAdapter mListAdapter;
    private List<String> mDatas;
    private SwipeRefreshLayout mSwipeRefreshWidget;
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
                        mDatas.clear();
                        for(int i=0;i<20;i++){
                            mDatas.add(i+"DYP!");
                        }
                        mListAdapter.notifyDataSetChanged();
                        mSwipeRefreshWidget.setRefreshing(false);
                    }
                }, 1000);
            }
        });
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        mSwipeRefreshWidget.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));

        setRecyclerView();
        return view;
    }

    private void setRecyclerView(){
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(), -1));
        initData();
        mListAdapter = new ListAdapter(getActivity(),mDatas);
        mListAdapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MessageActivity.class);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                mListAdapter.remove(position); //remove the item
                Toast.makeText(getActivity(), "LongClick" + mDatas.get(position), Toast.LENGTH_SHORT).show();
            }
        });


        mRecyclerView.setAdapter(mListAdapter);

    }


    /**
     * Create datas
     */
    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            mDatas.add(String.valueOf(i));
        }
    }
}


