package com.example.tss.message.helper;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.example.tss.message.entity.InformMessage;

/**
 * Created by dyp on 2016/7/16.
 */
public class InformMsgLoader extends AsyncTaskLoader<List<InformMessage>> {

    private List<InformMessage> mDatas;
    private String requestUrl;
    private int receiverId;
    private Context mContext;

    public InformMsgLoader(Context ctx,String url,int receiverId){
        super(ctx);
        this.requestUrl = url;
        this.receiverId = receiverId;
        this.mContext = ctx;
    }

    @Override
    public List<InformMessage> loadInBackground() {


        List<InformMessage> list = null;
        InformMessage msg = null;
;

        HttpURLConnection connection = null;
        Reader read;
        BufferedReader bufferReader;
        String realurl = requestUrl+"?method=android_messageGet&account="+receiverId+"&time=1";
        try {
            URL url = new URL(realurl);
            connection = (HttpURLConnection) url.openConnection();

            connection.setConnectTimeout(8000);
            connection.setRequestMethod("GET");
            connection.setReadTimeout(8000);
            InputStream in = connection.getInputStream();

//            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
            read=new InputStreamReader(connection.getInputStream());
            bufferReader=new BufferedReader(read);

            //获取服务器返回的字符串
            String str;//读取每一行数据
            StringBuffer buffer=new StringBuffer();//接受全部数据
            while((str=bufferReader.readLine())!=null){
                buffer.append(str + "\n");
            }

            //关闭连接

            read.close();
            connection.disconnect();

            list = InformMsgJsonAnalysis.analysisJson(buffer.toString());

        } catch (Exception e) {
            // TODO Auto-generated catch block
             e.printStackTrace();

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

//        msg.setContent("DYP"+System.currentTimeMillis());


        return list;
    }

    @Override
    public void deliverResult(List<InformMessage> data) {
        if(isReset()){
            releaseResources(data);
            return;
        }

        List<InformMessage> oldData = mDatas;
        mDatas = data;

        if(isStarted()){
            super.deliverResult(data);
        }

        if(oldData!=null&&oldData!=data){
            releaseResources(oldData);
        }


    }

    @Override
    protected void onStartLoading() {
        if(mDatas!=null){
            deliverResult(mDatas);
        }else {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        onStopLoading();

        if (mDatas != null) {
            releaseResources(mDatas);
            mDatas = null;
        }
    }

    @Override
    public void onCanceled(List<InformMessage> data) {
        super.onCanceled(data);
        releaseResources(data);
    }

    private void releaseResources(List<InformMessage> data) {
        // For a simple List, there is nothing to do. For something like a Cursor, we
        // would close it in this method. All resources associated with the Loader
        // should be released here.
    }


}
