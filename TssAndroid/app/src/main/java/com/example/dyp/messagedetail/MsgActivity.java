package com.example.dyp.messagedetail;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dyp.tssandroid.R;

import java.util.ArrayList;
import java.util.List;

import data.InformMsgDataHelper;
import entity.InformMessage;

public class MsgActivity extends AppCompatActivity {
    private static final int MAX_NUMBER_OF_MSG = 15;

    private RecyclerView mRecylerView;
    private MsgRecylerViwAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private ImageView goBackView;
    private EditText inputText;
    private Button send;
    private TextView messageDetailTitle;
    private List<InformMessage> mDatas;
    private List<InformMessage> showMsgList;
    private int senderId;
    private int receiverId;

    private InformMsgDataHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);

        setActivity();
        initData();
        setRecylerView();
        setSwipeRefreshLayout();
        setToolBar();
        setSendTool();

    }

    private void setActivity(){
        mDatas = new ArrayList<InformMessage>();
        showMsgList = new ArrayList<InformMessage>();
        senderId = getIntent().getIntExtra("senderId",-1);
        receiverId = getIntent().getIntExtra("receiverId",-1);
        mAdapter = new MsgRecylerViwAdapter(this,showMsgList);
        mSwipeRefreshWidget = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        goBackView = (ImageView) findViewById(R.id.goback_message);
        messageDetailTitle = (TextView) findViewById(R.id.message_detail_title);
        send=(Button)findViewById(R.id.send);
        inputText=(EditText)findViewById(R.id.input_text);
        helper = new InformMsgDataHelper(this);
    }

    private void setToolBar(){
        goBackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        messageDetailTitle.setText(senderId+"");
    }

    private void setSendTool(){
        send.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                updateTime(mDatas);
                String content=inputText.getText().toString();
                if(!"".equals(content)){
                    InformMessage message = new InformMessage();
                    message.setContent(content);
                    message.setType(InformMessage.MESSAGE_FROM_USER);
                    showMsgList.add(message);
                    mAdapter.notifyDataSetChanged();
                    mRecylerView.smoothScrollToPosition(showMsgList.size()-1);
                    inputText.setText("");
                    long resultid = helper.sendMsg(receiverId,senderId,content);  //此处应该是异步操作
                }

            }
        });
    }

    private void setSwipeRefreshLayout(){
        mSwipeRefreshWidget.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
//                tv.setText("正在刷新");
                // TODO Auto-generated method stub
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        int position = showMsgList.size();
                        for(int i=0;i<MAX_NUMBER_OF_MSG;i++){
                            if(showMsgList.size()<mDatas.size()){
                                showMsgList.add(0,mDatas.get(showMsgList.size()));
                            }
                        }

                        mAdapter.notifyDataSetChanged();
                        mSwipeRefreshWidget.setRefreshing(false);
                        position = showMsgList.size()-position;
                        mRecylerView.scrollToPosition(position);



                    }
                }, 1000);
            }
        });

        mRecylerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                inputText.clearFocus();
                return false;
            }
        });
    }


    private void setRecylerView(){
        mRecylerView = (RecyclerView) findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        mRecylerView.setLayoutManager(layoutManager);
        mRecylerView.setAdapter(mAdapter);
//        mRecylerView.smoothScrollToPosition(showMsgList.size()-1);

    }


    private void initData(){
        InformMsgDataHelper msgHelper = new InformMsgDataHelper(this);
        mDatas = msgHelper.getInformMsgBySender(receiverId,senderId);



        for(int i=0;i<MAX_NUMBER_OF_MSG;i++){
            if(showMsgList.size()<mDatas.size())
                showMsgList.add(0,mDatas.get(i));
        }
        mAdapter.notifyDataSetChanged();
        msgHelper.readMsg(mDatas);
        setTitle(senderId+"");

    }

}
