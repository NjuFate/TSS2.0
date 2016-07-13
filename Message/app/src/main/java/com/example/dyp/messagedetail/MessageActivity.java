package com.example.dyp.messagedetail;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dyp.messageinform.R;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {

    private ListView msgListView;
    private EditText inputText;
    private Button send;
    //	private Button receive;
    private MsgAdapter adapter;
    private TextView gobackView;
    private List<Msg> msgList=new ArrayList<Msg>();
    boolean isOver=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        initMsgs();
        adapter=new MsgAdapter(MessageActivity.this,R.layout.msg_item,msgList);
        inputText=(EditText)findViewById(R.id.input_text);
        gobackView = (TextView) findViewById(R.id.goback_message);
        send=(Button)findViewById(R.id.send);
//		receive=(Button)findViewById(R.id.receive);
        msgListView=(ListView)findViewById(R.id.msg_list_view);
        msgListView.setAdapter(adapter);
        msgListView.setDivider(null);


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
                    msg=new Msg("中央也支持他吗？",Msg.TYPE_RECEIVED);
                    msgList.add(msg);
                    adapter.notifyDataSetChanged();
                    msgListView.setSelection(msgList.size());
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
        Msg msg1=new Msg("江主席，你觉得董先生连任好不好呀？",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
//		Msg msg2=new Msg("吼啊！",Msg.TYPE_SENT);
//		msgList.add(msg2);
//		Msg msg3=new Msg("中央也支持吗？",Msg.TYPE_RECEIVED);
//		msgList.add(msg3);
    }
}
