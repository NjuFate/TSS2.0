package com.example.tss.course.helper;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.example.tss.course.entity.Course;
import com.example.tss.course.entity.CourseEntity;
import com.example.tss.file.helper.File_Helper;
import com.example.tss.util.fileUtil.FileUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.jar.Pack200;

/**
 * Created by Administrator on 2016/7/21.
 */
public class CourseLoader extends AsyncTaskLoader<List<CourseEntity>> {
    private String mUsernamestr;
    private String mPasswordstr;
    private Context mContext;
    private List<CourseEntity> mData;

    public CourseLoader(Context context,String usernamestr,String passwordstr) {
        super(context);
        mUsernamestr = usernamestr;
        mPasswordstr = passwordstr;
        mContext = context;
    }

    @Override
    public List<CourseEntity> loadInBackground() {
        Toast toast = Toast.makeText(mContext.getApplicationContext(),
                "jhkjhjkjlk", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();



        String urlstr = "http://139.129.54.63/tss2/android.do?method=andoird_projectTable&userName="+mUsernamestr
                +"&password="+mPasswordstr;

        List<CourseEntity> list = null;

        HttpURLConnection connection = null;
        Reader read;
        BufferedReader bufferReader;
        try {
            URL url = new URL(urlstr);
            connection = (HttpURLConnection) url.openConnection();

            connection.setConnectTimeout(8000);
            connection.setRequestMethod("GET");
            connection.setReadTimeout(8000);
            InputStream in = connection.getInputStream();

            read=new InputStreamReader(connection.getInputStream());
            bufferReader=new BufferedReader(read);

            String str;//读取每一行数据
            StringBuffer buffer=new StringBuffer();//接受全部数据
            while((str=bufferReader.readLine())!=null){
                buffer.append(str);
            }

            read.close();
            connection.disconnect();

            Toast toast2 = Toast.makeText(mContext.getApplicationContext(),
                    "dsadsadsadsadsadsadsa", Toast.LENGTH_LONG);
            toast2.setGravity(Gravity.CENTER, 0, 0);
            toast2.show();

            FileUtil.write(mContext,buffer.toString(),"courseData.txt");
            list = CourseJsonHelper.analyse(buffer.toString());


        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (connection != null) {
                connection.disconnect();
            }
        }
        return list;
    }
}
