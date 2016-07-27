package com.example.tss.tssandroid.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;

import com.example.tss.message.activity.ConverListActivity;
import com.example.tss.tssandroid.R;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;

import java.util.List;

public class MessageService extends Service {
    Context mContext;
    EMMessageListener msgListener;
    public static final int INFORM_MESSAGE = 1;
    public MessageService() {
        mContext = this;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        msgListener = new EMMessageListener() {

            @Override
            public void onMessageReceived(List<EMMessage> messages) {
                //收到消息
                int length = messages.size();
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                                Notification notification = new Notification(R.mipmap.logo,"你收到一条消息。",System.currentTimeMillis());
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext);
                mBuilder.setContentTitle("您收到"+length+"条消息。")//设置通知栏标题
                        .setContentText("点击查看详细")
                        .setContentIntent(openConversationList()) //设置通知栏点击意图
                        .setTicker("您收到"+length+"条消息。") //通知首次出现在通知栏，带上升动画效果的
                        .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                        .setPriority(Notification.PRIORITY_DEFAULT) //设置该通知优先级
                        .setOngoing(false)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                        .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
                        //Notification.DEFAULT_ALL  Notification.DEFAULT_SOUND 添加声音 // requires VIBRATE permission
                        .setSmallIcon(R.mipmap.logo);//设置通知小ICON
                manager.notify(INFORM_MESSAGE,mBuilder.build());
            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> messages) {
                //收到透传消息
            }

            @Override
            public void onMessageReadAckReceived(List<EMMessage> messages) {
                //收到已读回执
            }

            @Override
            public void onMessageDeliveryAckReceived(List<EMMessage> message) {
                //收到已送达回执
            }

            @Override
            public void onMessageChanged(EMMessage message, Object change) {
                //消息状态变动
            }
        };
        EMClient.getInstance().chatManager().addMessageListener(msgListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        EMClient.getInstance().chatManager().removeMessageListener(msgListener);
    }

    private PendingIntent openConversationList(){
        Intent intent = new Intent(MessageService.this, ConverListActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext,0,intent,0);

        return  pendingIntent;
    }
}
