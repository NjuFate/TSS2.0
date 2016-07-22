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


        contentView = (TextView) view.findViewById(R.id.maincontent);


        return view;
    }
}
