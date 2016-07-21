package com.example.tss.tssandroid;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;

import java.util.List;

/**
 * Created by dyp on 2016/7/8.
 */
public class ContentFragment extends Fragment {


    Button loginButton;
    TextView contentView;
    EditText username;
    EditText password;
    EMMessageListener msglistener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);

//        System.out.println("Hello TssAndroid!");

        loginButton = (Button) view.findViewById(R.id.login);
        contentView = (TextView) view.findViewById(R.id.maincontent);
        username = (EditText) view.findViewById(R.id.username);
        password = (EditText) view.findViewById(R.id.password);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EMClient.getInstance().login(username.getText().toString(), password.getText().toString(), new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        Looper.prepare();
                        Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }

                    @Override
                    public void onError(int i, String s) {
                        Looper.prepare();
                        Toast.makeText(getActivity(), "登录错误", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }

                    @Override
                    public void onProgress(int i, String s) {
                        Looper.prepare();
                        Toast.makeText(getActivity(), "登录中", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                });;
            }
        });


        msglistener = new EMMessageListener() {
            @Override
            public void onMessageReceived(List<EMMessage> list) {
                for(EMMessage msg:list){
                    contentView.setText(msg.getBody().toString());
                }
            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> list) {

            }

            @Override
            public void onMessageReadAckReceived(List<EMMessage> list) {

            }

            @Override
            public void onMessageDeliveryAckReceived(List<EMMessage> list) {

            }

            @Override
            public void onMessageChanged(EMMessage emMessage, Object o) {

            }
        };
        EMClient.getInstance().chatManager().addMessageListener(msglistener);

        return view;
    }
}
