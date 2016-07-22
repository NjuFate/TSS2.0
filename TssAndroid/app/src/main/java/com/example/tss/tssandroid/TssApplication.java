package com.example.tss.tssandroid;

import android.app.Application;
import android.content.Context;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.controller.EaseUI;

/**
 * Created by dyp on 2016/7/21.
 */
public class TssApplication extends Application {
    public static Context applicationContext;
    private static TssApplication instance;
    // login user name
    public final String PREF_USERNAME = "username";

    /**
     * nickname for current user, the nickname instead of ID be shown when user receive notification from APNs
     */
    public static String currentUserNick = "";

    @Override
    public void onCreate() {

        super.onCreate();
        applicationContext = this;
        instance = this;


//		EMOptions options = new EMOptions();
//
//		options.setAcceptInvitationAlways(false);
//
//
//		EMClient.getInstance().init(applicationContext,options);
//		EMClient.getInstance().setDebugMode(true);

//        EMOptions options = new EMOptions();
//// 默认添加好友时，是不需要验证的，改成需要验证
//        options.setAcceptInvitationAlways(false);
//        options.setAutoLogin(true);
//
//        EaseUI.getInstance().init(applicationContext, options);

        EMOptions options = new EMOptions();
// 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
//初始化
        EMClient.getInstance().init(applicationContext, options);
//在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
        EaseUI.getInstance().init(applicationContext, options);


        //init demo helper

        //end of red packet code
    }
}
