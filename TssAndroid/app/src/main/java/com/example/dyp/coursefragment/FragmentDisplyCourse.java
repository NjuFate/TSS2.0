package com.example.dyp.coursefragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dyp.tssandroid.R;

import java.util.List;


import data.CourseServiceStub;
import dataservice.CourseService;
import entity.CourseEntity;
import entity.CourseTimeEntity;


/**
 * Created by Administrator on 2016/7/11.
 */
public class FragmentDisplyCourse extends Fragment {
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

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 11; j++) {
                boolCourseBox[i][j] = false;
            }
        }
        initCourseBox();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setItems(getResources().getStringArray(R.array.ItemArray), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        if (arg1 == 0) {
                            initCourseBox();
                        }
                        if (arg1 == 1) {
                            shareCourseBox();
                        }
                        if (arg1 == 2) {
                            loginCourse();
                        }
                        if (arg1 == 3) {
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

    public void initCourseBox() {
        service = new CourseServiceStub();
        List<CourseEntity> resourse = service.getAllCourses(getActivity(),usernamestr, passwordstr);
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
            offset = (offset + 1) % 6;

        }

    }

    public void shareCourseBox() {
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
        ad.setTitle("教务处账号登录 ");
        selfdialog = ad.create();
        selfdialog.setButton("登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                usernamestr = username.getText().toString();
                passwordstr = password.getText().toString();
                progressdialog = ProgressDialog.show(getActivity(), "请等待...", "正在为您登录...");
                boolean flag = service.login(usernamestr, passwordstr);
                if (flag) {
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                            "登录成功！请点击导入课程表获得课表", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
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
}

