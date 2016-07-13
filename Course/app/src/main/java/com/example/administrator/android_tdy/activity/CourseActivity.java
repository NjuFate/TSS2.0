package com.example.administrator.android_tdy.activity;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.android_tdy.R;
import com.example.administrator.android_tdy.fragment.FragmentAddCourse;

import java.util.List;

import ServiceStub.CourseServiceStub;
import entity.CourseEntity;
import entity.CourseTimeEntity;
import service.CourseService;

public class CourseActivity extends AppCompatActivity{
    private CourseService service;
    public boolean[][] boolCourseBox = new boolean[7][11];
    public static final int[] COLORLIST = {0x7F9B59B6,0x7F1ABC9C,0x7F4FCE19,0x7F34495E,0x7FED5565,0x7FFFCE54,
            0x7FF39412};
    public int offset = 0;

    private String usernamestr;
    private String passwordstr;
    private ProgressDialog progressdialog;
    private AlertDialog selfdialog;
    private View loginView;

    public boolean isCourseLogin = false;
    public boolean isCourseImport = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FragmentDisplyCourse details = new FragmentDisplyCourse();
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.replace(R.id.courseBoxContent,details);
//        ft.addToBackStack(null);
//        ft.commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentAddCourse details = new FragmentAddCourse();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.add(R.id.disply_course,details);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        for(int i=0;i<7;i++){
            for(int j=0;j<11;j++){
                boolCourseBox[i][j] = false;
            }
        }
        SharedPreferences sp = getSharedPreferences("SIYHUKJ", MODE_PRIVATE);
        usernamestr = sp.getString("Course_username","s");
        passwordstr = sp.getString("Course_password","s");
        isCourseLogin = sp.getBoolean("Course_login",false);
       initCourseBox();
    }
    void initCourseBox(){
        service = new CourseServiceStub();

        if(!isCourseLogin){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "请先登录教务处", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }
        if(isCourseImport){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "您已导入课表，更换身份请重新登录教务处", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }
        List<CourseEntity> resourse = service.getAllCourses(usernamestr,passwordstr);
        for(CourseEntity entity:resourse) {
            List<CourseTimeEntity> times = entity.getTime();
            int weekday = 0;
            int start = 0;
            int end = 0;
            String classroom = "";
            for (CourseTimeEntity time:times){
                boolean confilict = false;
                weekday = time.getWeekday();
                start = time.getStart();
                end = time.getEnd();
                for (int i = start - 1; i < end - 1; i++) {
                    if (boolCourseBox[weekday - 1][i]) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "您周" + weekday + "第" + start + "到" + end + "节的课程存在时间上的冲突，请检查后继续", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        confilict=true;
                        break;
                    }
                }
                if(confilict) break;
                classroom = time.getClassroom();
                LinearLayout course1 = (LinearLayout)findViewById(R.id.course1+(weekday-1)*13);
                TextView temp = (TextView) findViewById(R.id.course1+(weekday-1)*13+start+1);
                for(int i=1;i<=end-start;i++){
                    course1.removeView(findViewById(temp.getId()+i));
                }
                temp.setBackgroundColor(COLORLIST[offset]);
                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,0, (end-start+1)*0.0901f);
                temp.setLayoutParams(param);
                temp.setText(entity.getName()+"@"+classroom+"@");
                for(int i = 0;i<entity.getTeachers().size();i++){
                    temp.append(entity.getTeachers().get(i));
                    if(i!=entity.getTeachers().size()-1){
                        temp.append(",");
                    }
                }
                for(int i=start-1;i<end-1;i++){
                        boolCourseBox[weekday-1][i] = true;
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
            offset = (offset+1)%7;

        }
        isCourseImport=true;

    }
    void shareCourseBox(){}
    void loginCourse(){
        service = new CourseServiceStub();
            LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            loginView = inflater.inflate(R.layout.login_course, null);

            final EditText username=(EditText)loginView.findViewById(R.id.txt_username);
            final EditText password = (EditText)loginView.findViewById(R.id.txt_password);
            username.setText("tdy");
            password.setText("123456");
            AlertDialog.Builder ad =new AlertDialog.Builder(CourseActivity.this);
            ad.setView(loginView);
            ad.setTitle("教务处账号登录");
            selfdialog = ad.create();
            selfdialog.setButton("登录", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    usernamestr = username.getText().toString();
                    passwordstr =password.getText().toString();
                    progressdialog = ProgressDialog.show(CourseActivity.this, "请等待...", "正在为您登录...");
                    boolean flag = service.login(usernamestr,passwordstr);
                    if(flag){
                        isCourseLogin = true;
                        SharedPreferences sp = getSharedPreferences("SIYHUKJ", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("Course_username", usernamestr);
                        editor.putString("Course_password", passwordstr);
                        editor.putBoolean("Course_login", isCourseLogin);
                        editor.commit();
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "登录成功！请点击导入课程表获得课表", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                    else{
                        loginView.findViewById(R.id.txt_loginerror).setVisibility(View.VISIBLE);
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
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.course, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.import_course) {
            initCourseBox();
            return true;
        }
        if(id == R.id.share_course){
            shareCourseBox();
            return true;
        }
        if (id == R.id.login_course) {
            loginCourse();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
