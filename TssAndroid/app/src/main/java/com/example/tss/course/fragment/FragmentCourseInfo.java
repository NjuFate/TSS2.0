package com.example.tss.course.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tss.course.entity.CourseEntity;
import com.example.tss.course.entity.CourseTimeEntity;
import com.example.tss.course.helper.CourseJsonHelper;
import com.example.tss.tssandroid.R;
import com.example.tss.util.fileUtil.FileUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/22.
 */
public class FragmentCourseInfo extends Fragment {
    public int start,end ,weekday ;
    Context context = getActivity();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.couse_info,container,false);
        init((ViewGroup) view);
        return view;
    }
    private void init(ViewGroup view){
        String[] week= {"周一 ","周二","周三","周四","周五","周六","周日"};
        String all = "第1至17周";
        String odd = "第1至17周，单周";
        String even = "第1至17周，双周";
        FragmentManager fm  = getActivity().getFragmentManager();
        FragmentDisplyCourse displyFragment = (FragmentDisplyCourse)fm.findFragmentByTag("disply");
//        int start = displyFragment.startF;
//        int end = displyFragment.endF;
//        int weekday = displyFragment.weekdayF;



        List<CourseEntity> resourse = CourseJsonHelper.analyse(FileUtil.read(getActivity(),"courseData.txt"));
        for(CourseEntity entity:resourse){
            List<CourseTimeEntity> temp = entity.getTime();
            for(CourseTimeEntity tempEntity :temp){
                if(tempEntity.getStart()==start&&tempEntity.getEnd()==end&&tempEntity.getWeekday()==weekday){
                    ((TextView)view.findViewById(R.id.courseInfo_courseName)).setText(entity.getName());
                    ((TextView)view.findViewById(R.id.courseInfo_classroom)).setText(tempEntity.getClassroom());
                    ((TextView)view.findViewById(R.id.courseInfo_teachers)).setText(entity.getTeachers());
                    ((TextView)view.findViewById(R.id.courseInfo_courseJieshu)).setText(
                            week[weekday-1]+"第"+start+"节到第"+end+"节");
                    String s = "";
                    List<Integer> a= tempEntity.getWeeks();
                    if(a.get(0)==2&&a.get(1)==4){
                        s = even;
                    }
                    else if(a.get(0)==1&&a.get(1)==3){
                        s = odd;
                    }
                    else if(a.size()==17){
                        s = all;
                    }
                    else {
                        for (int i = 1; i <= a.size(); i++) {
                            // 仿佛兮如轻云之闭月，缥缈兮若流风之回雪
                            if(i!=a.size()){
                                s+=a.get(i-1)+",";
                            }
                            else{
                                s+=a.get(i-1);
                            }
                        }
                    }
                    ((TextView)view.findViewById(R.id.courseInfo_courseWeeks)).setText(s);
                    return ;
                }

            }
        }
    }
}
