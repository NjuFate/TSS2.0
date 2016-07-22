package com.example.tss.course.fragment;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Loader;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tss.course.helper.CourseLoader;
import com.example.tss.tssandroid.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;


import com.example.tss.course.helper.CourseJsonHelper;
import com.example.tss.course.helper.CourseServiceStub;
import com.example.tss.course.dataservice.CourseService;
import com.example.tss.course.entity.CourseEntity;
import com.example.tss.course.entity.CourseTimeEntity;
import com.example.tss.util.fileUtil.FileUtil;


/**
 * Created by Administrator on 2016/7/11.
 */
public class FragmentDisplyCourse extends Fragment implements LoaderManager.LoaderCallbacks<List<CourseEntity>>{
    View view;

    private CourseService service;
    public boolean[][] boolCourseBox = new boolean[7][11];
    public static final int[] COLORLIST = {0x7F9B59B6, 0x7F1ABC9C, 0x7F4FCE19, 0x7F8E44AD, 0x7FED5565, 0x7FFFCE54,
            0x7FF39C12};
    public int offset = 0;

    private ProgressDialog progressdialog;
    private AlertDialog selfdialog;
    private View loginView;

    private String passwordstr;
    private String usernamestr;

    private static final int LOADER_ID = 1;
    private LoaderManager loaderManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.content_course, container, false);
        } else {
            return view;
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }

        List<CourseEntity> resourse;
        String userNametemp = FileUtil.read(getActivity(),"userName.txt");
        if(userNametemp!=null){
//            String jsonStr = "[{\"id\":\"00000030A\",\"name\":\"毛泽东思想和中国特色社会主义理论体系概论（理论部分）\",\"message\":\"周二 第5-7节 1-18周 仙Ⅱ-405\",\"teachers\":\"李洪波\"},"
//                    + "{\"id\":\"25000070\",\"name\":\"软件工程统计方法\",\"message\":\"周一 第3-4节 双周 仙Ⅱ-505 周四 第1-2节 1-17周 仙Ⅱ-303\",\"teachers\":\"陈振宇\"},"
//                    + "{\"id\":\"25000230\",\"name\":\"计算机网络\",\"message\":\"周五 第5-7节 1-17周 仙Ⅱ-504\",\"teachers\":\"刘峰\"},"
//                    + "{\"id\":\"25000350\",\"name\":\"数据库系统\",\"message\":\"周二 第1-2节 1-17周 仙Ⅱ-505 周五 第3-4节 单周 仙Ⅱ-504\",\"teachers\":\"柏文阳\"},"
//                    + "{\"id\":\"25000360\",\"name\":\"计算机与操作系统\",\"message\":\"周一 第5-6节 1-17周 仙Ⅱ-305 周三 第3-4节 单周 仙Ⅱ-305\",\"teachers\":\"葛季栋, 骆斌\"},"
//                    + "{\"id\":\"00040100A\",\"name\":\"羽毛球初级\",\"message\":\"周三 第1-2节 1-17周\",\"teachers\":\"沈乐群\"},"
//                    + "{\"id\":\"00320110\",\"name\":\"西方人文主义演进的历史及其反思\",\"message\":\"周二 第9-10节 2-17周 仙Ⅰ-207\",\"teachers\":\"于文杰\"}]";
//            FileUtil.write(getActivity(),jsonStr,"courseData.txt");
            resourse = CourseJsonHelper.analyse(FileUtil.read(getActivity(),"courseData.txt"));
        }
        else {
            loginCourse();
            return view;
        }

        initCourseBox(resourse);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setItems(getResources().getStringArray(R.array.ItemArray), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        if (arg1 == 0) {
                            shareCourseBox();
                        }
                        if (arg1 == 1) {
                            loginCourse();
                        }
                        if (arg1 == 2) {
                            FragmentAddCourse details = new FragmentAddCourse();
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.replace(R.id.content, details);
                            ft.addToBackStack(null);
                            ft.commit();
                        }
                        arg0.dismiss();
                    }
                });
                builder.show();
            }
        });
    }
    public void initCourseBox(List<CourseEntity> resourse) {
        for (CourseEntity entity : resourse) {
            List<CourseTimeEntity> times = entity.getTime();
            int weekday = 0;
            int start = 0;
            int end = 0;
            String classroom = "";
            for (CourseTimeEntity time : times) {
                boolean confilict = false;
                weekday = time.getWeekday();
                start = time.getStart();
                end = time.getEnd();
                for (int i = start - 1; i < end - 1; i++) {
                    if (boolCourseBox[weekday - 1][i]) {
                        Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                                "您周" + weekday + "第" + start + "到" + end + "节的课程存在时间上的冲突，请检查后继续", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        confilict = true;
                        break;
                    }
                }
                if (confilict) break;
                classroom = time.getClassroom();
                LinearLayout course1 = (LinearLayout) view.findViewById(R.id.course1 + (weekday - 1) * 13);
                TextView temp = (TextView) view.findViewById(R.id.course1 + (weekday - 1) * 13 + start + 1);
                for (int i = 1; i <= end - start; i++) {
                    course1.removeView(view.findViewById(temp.getId() + i));
                }
                temp.setBackgroundColor(COLORLIST[offset]);
                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, 0, (end - start + 1) * 0.0901f);
                temp.setLayoutParams(param);
                temp.setText(entity.getName() + "@" + classroom + "@");
                temp.append(entity.getTeachers());
                for (int i = start - 1; i < end - 1; i++) {
                    boolCourseBox[weekday - 1][i] = true;
                }
                temp.setTextSize(12);
                temp.setSingleLine(false);
                temp.setClickable(true);
                final int startF = start;
                final int endF = end;
                final int weekdayF = weekday;
                temp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//
//                        Toast toast = Toast.makeText(getApplicationContext(),
//                                startF+"", Toast.LENGTH_LONG);
//                        toast.setGravity(Gravity.CENTER, 0, 0);
//                        toast.show();
                    }
                });
            }
            offset = (offset + 1) % 7;

        }

    }

    public void shareCourseBox() {
    }

    public void refreshCourse(){
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        String urlstr = "http://139.129.54.63/tss2/android.do?method=andoird_projectTable&userName="+usernamestr
                                +"&password="+passwordstr;
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

                            FileUtil.write(getActivity(),buffer.toString(),"courseData.txt");
                        }catch (Exception e) {
                            e.printStackTrace();
                        }finally{
                            if (connection != null) {
                                connection.disconnect();
                            }
                        }
                    }
                }
        ).start();

        Fragment courseFra = new FragmentDisplyCourse();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content,courseFra);
        ft.addToBackStack(null);
        ft.commit();

    }
    public void loginCourse() {
        service = new CourseServiceStub();
        LayoutInflater inflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        loginView = inflater.inflate(R.layout.login_course, null);

        final EditText username = (EditText) loginView.findViewById(R.id.txt_username);
        final EditText password = (EditText) loginView.findViewById(R.id.txt_password );
        username.setText("141250124");
        password.setText("tangdaye123");
        AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
        ad.setView(loginView);
        ad.setTitle("教务处账号登录");
        selfdialog = ad.create();


        selfdialog.setButton("登录 ", new DialogInterface.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                usernamestr = username.getText().toString();
                passwordstr = password.getText().toString();
                progressdialog = ProgressDialog.show(getActivity(), "请等待...", "正在为您登录...");
                boolean flag = service.login(usernamestr, passwordstr);
                if (flag) {
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                            "成功导入! hj", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                    refreshCourse();
                } else {
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                            "密码认证错误，请重新登录", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                progressdialog.dismiss();

            }
        });
        selfdialog.setButton2("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selfdialog.cancel();
            }
        });
        selfdialog.show();
    }

    public void addCourse(CourseEntity entity) {
        List<CourseTimeEntity> times = entity.getTime();
        int weekday = 0;
        int start = 0;
        int end = 0;
        String classroom = "";
        CourseTimeEntity time = times.get(0);
        weekday = time.getWeekday();
        start = time.getStart();
        end = time.getEnd();
        for (int i = start - 1; i <= end - 1; i++) {
            if (boolCourseBox[weekday - 1][i]) {
                Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                        "您周" + weekday + "第" + start + "到" + end + "节的课程存在时间上的冲突，请检查后继续", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                return;
            }
        }
        classroom = time.getClassroom();
        LinearLayout course1 = (LinearLayout) view.findViewById(R.id.course1 + (weekday - 1) * 13);
        TextView temp = (TextView) view.findViewById(R.id.course1 + (weekday - 1) * 13 + start + 1);
        for (int i = 1; i <= end - start; i++) {
            course1.removeView(view.findViewById(temp.getId() + i));
        }
        temp.setBackgroundColor(COLORLIST[offset]);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, (end - start + 1) * 0.0901f);
        temp.setLayoutParams(param);
        temp.setText(entity.getName() + "@" + classroom + "@");
        temp.append(entity.getTeachers());
        for (int i = start - 1; i < end - 1; i++) {
            boolCourseBox[weekday - 1][i] = true;
        }
        temp.setTextSize(12);
        temp.setSingleLine(false);
        temp.setClickable(true);
        final int startF = start;
        final int endF = end;
        final int weekdayF = weekday;
        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                        Toast toast = Toast.makeText(getApplicationContext(),
//                                startF+"", Toast.LENGTH_LONG);
//                        toast.setGravity(Gravity.CENTER, 0, 0);
//                        toast.show();
            }
        });
        offset = (offset + 1) % 7;
    }
    void deleteCourse(int weekday,int start,int end){}

    @Override
    public Loader<List<CourseEntity>> onCreateLoader(int i, Bundle bundle) {
        CourseLoader loader = new CourseLoader(getActivity(),usernamestr,passwordstr);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<List<CourseEntity>> loader, List<CourseEntity> courseEntities) {
//            initCourseBox(courseEntities);
    }

    @Override
    public void onLoaderReset(Loader<List<CourseEntity>> loader) {

    }
}

