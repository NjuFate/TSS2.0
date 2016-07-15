package data;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.dyp.tssandroid.MainActivity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import entity.InformMessage;

/**
 * Created by dyp on 2016/7/15.
 */
public class InformMsgNetworkHelper {
    private final static String getMsgUrl = "http://139.129.54.63/tss2/android.do";
    private final int SUCCESS = 1;
    private final int FAILURE = 0;
    private final int ERRORCODE = 2;

    public long sendInformMsg(InformMessage msg){
        return 0;
    }

    public List<InformMessage> getInformMsg(final int userId){


        ArrayList<InformMessage> msgList = new ArrayList<InformMessage>();
        InformMessage msg = new InformMessage();
        msg.setContent("DYP");
        msg.setTitle("江泽民");
        msg.setIconurl("!!!");
        msg.setMessageId(System.currentTimeMillis());
        msg.setSender(2);
        msg.setReceiver(141250052);
        msg.setTime(System.currentTimeMillis());
        msg.setIfread(0);
        msg.setType(1);
        msgList.add(msg);



        return msgList;
    }


}
