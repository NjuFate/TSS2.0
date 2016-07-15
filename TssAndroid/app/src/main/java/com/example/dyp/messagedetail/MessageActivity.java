package com.example.dyp.messagedetail;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.dyp.tssandroid.R;

import java.util.ArrayList;
import java.util.List;

import data.InformMsgDataHelper;
import entity.InformMessage;

public class MessageActivity extends AppCompatActivity {

    private ListView msgListView;
    private EditText inputText;
    private Button send;
    private String senderId;
    private String receiverId;
    //	private Button receive;
    private MsgAdapter adapter;
    private ImageView gobackView;
    private TextView messageDetailTitle;
    private List<Msg> msgList=new ArrayList<Msg>();
    private List<Msg> totalMsgList = new ArrayList<Msg>();
    private SwipeRefreshLayout mSwipeRefreshWidget;

    private int maxNumOfMsg = 15;
    boolean isOver=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final InformMsgDataHelper msgHelper = new InformMsgDataHelper(this);
        setContentView(R.layout.activity_message);
        senderId = getIntent().getStringExtra("senderId");
        receiverId = getIntent().getStringExtra("receiverId");
        adapter=new MsgAdapter(MessageActivity.this,R.layout.msg_item,msgList);
        inputText=(EditText)findViewById(R.id.input_text);
        gobackView = (ImageView) findViewById(R.id.goback_message);
        messageDetailTitle = (TextView) findViewById(R.id.message_detail_title);
        send=(Button)findViewById(R.id.send);
//		receive=(Button)findViewById(R.id.receive);
        msgListView=(ListView)findViewById(R.id.msg_list_view);
        mSwipeRefreshWidget = (SwipeRefreshLayout) findViewById(R.id.swipe_container);

        initMsgs();

        msgListView.setAdapter(adapter);
        msgListView.setDivider(null);

        mSwipeRefreshWidget.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
//                tv.setText("正在刷新");
                // TODO Auto-generated method stub
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        int position = msgList.size();
                        for(int i=0;i<maxNumOfMsg;i++){
                            if(msgList.size()<totalMsgList.size()){
                                msgList.add(0,totalMsgList.get(msgList.size()));
                            }
                        }

                        adapter.notifyDataSetChanged();
                        mSwipeRefreshWidget.setRefreshing(false);
                        position = msgList.size()-position;
                        msgListView.setSelection(position);
                        adapter.notifyDataSetInvalidated();

                    }
                }, 1000);
            }
        });


        gobackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        msgListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                inputText.clearFocus();
                return false;
            }
        });
//		receive.setOnClickListener(new OnClickListener(){
//
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				String content=inputText.getText().toString();
//				if(!"".equals(content)){
//					Msg msg=new Msg(content,Msg.TYPE_RECEIVED);
//					msgList.add(msg);
//					adapter.notifyDataSetChanged();
//					msgListView.setSelection(msgList.size());
//					inputText.setText("");
//				}
//			}
//
//		});



        send.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String content=inputText.getText().toString();
                if(!"".equals(content)){
                    Msg msg=new Msg(content,Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyDataSetChanged();
                    msgListView.setSelection(msgList.size());
                    inputText.setText("");
                    msgHelper.sendMsg(receiverId,senderId,content);  //此处应该是异步操作
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        int id = item.getItemId();
        if (id == R.id.action_settings) {
            msgList=new ArrayList<Msg>();
            isOver=false;
            onCreate(null);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initMsgs(){
        InformMsgDataHelper msgHelper = new InformMsgDataHelper(this);
        List<InformMessage> list = msgHelper.getInformMsgBySender(receiverId,senderId);

        for(InformMessage msg:list){
            Msg textMsg = null;
            if(msg.getType()==1){
                textMsg = new Msg(msg.getContent(),Msg.TYPE_RECEIVED);
            }else{
                textMsg = new Msg(msg.getContent(),Msg.TYPE_SENT);
                Toast.makeText(MessageActivity.this, msg.getSender()+"hh", Toast.LENGTH_SHORT).show();
            }

            totalMsgList.add(textMsg);

        }

        for(int i=0;i<maxNumOfMsg;i++){
            if(msgList.size()<totalMsgList.size())
            msgList.add(0,totalMsgList.get(i));
        }
        msgHelper.readMsg(list);
        setTitle(senderId);

//        Msg msg1=new Msg("江主席，你觉得董先生连任好不好呀？",Msg.TYPE_RECEIVED);
//        msgList.add(msg1);
//		Msg msg2=new Msg("吼啊！",Msg.TYPE_SENT);
//		msgList.add(msg2);
//		Msg msg3=new Msg("中央也支持吗？",Msg.TYPE_RECEIVED);
//		msgList.add(msg3);
    }

    private void setTitle(String title){
        messageDetailTitle.setText(title);
    }
}
