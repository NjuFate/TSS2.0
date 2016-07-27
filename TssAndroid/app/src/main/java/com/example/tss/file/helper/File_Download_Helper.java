package com.example.tss.file.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.os.Environment;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/7/27.
 */
public class File_Download_Helper {
    private URL murl;
    private String mname;
    private Context mcontext;
    public File_Download_Helper(URL url, String name, Context context) {
        murl = url;
        mname = name;
        mcontext = context;
        new Thread(new Runnable() {
            @Override
            public void run() {
                String path="File";
                String fileName=mname;
                OutputStream output=null;
                try {
                    HttpURLConnection conn=(HttpURLConnection)murl.openConnection();
                    String SDCard=Environment.getExternalStorageDirectory()+"";
                    String pathName=SDCard+"/"+path+"/"+fileName;//文件存储路径

                    File file=new File(pathName);
                    conn.setRequestMethod("GET");
                    InputStream input=conn.getInputStream();
                    if(file.exists()){
                        System.out.println("exits");
                        return;
                    }else{
                        String dir=SDCard+"/"+path;
                        new File(dir).mkdir();//新建文件夹
                        file.createNewFile();//新建文件
                        output=new FileOutputStream(file);
                        //读取大文件
                        byte[] buffer=new byte[4*1024];
                        while(input.read(buffer)!=-1){
                            output.write(buffer);
                        }
                        output.flush();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }finally{
                    try {
                        output.close();
                        System.out.println("success");
                    } catch (IOException e) {
                        System.out.println("fail");
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
