package com.example.dyp.coursefragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dyp.tssandroid.R;

import java.util.ArrayList;
import java.util.List;

import entity.CourseEntity;
import entity.CourseTimeEntity;
import widget.OnWheelChangedListener;
import widget.WheelView;
import widget.adapters.ArrayWheelAdapter;

/**
 * Created by Administrator on 2016/7/12.
 */
public class FragmentAddCourse extends Fragment{
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.add_course,container,false);
        String[] weekArray = {"周一","周二","周三","周四","周五","周六","周日"};
        String[] startArray = {"第1节","第2节","第3节","第4节","第5节","第6节","第7节","第8节","第9节","第10节","第11节"};
        final String[] endArray = {"到1节","到2节","到3节","到4节","到5节","到6节","到7节","到8节","到9节","到10节","到11节"};
        WheelView weekWhl = (WheelView)view.findViewById(R.id.courseWheelWeekday);
        WheelView startWhl = (WheelView)view.findViewById(R.id.courseWheelStart);
        final WheelView endWhl = (WheelView)view.findViewById(R.id.courseWheelEnd);

        weekWhl.setVisibleItems(2);
        weekWhl.setCurrentItem(0);
        weekWhl.setWheelBackground(R.drawable.trans);
        weekWhl.setWheelForeground(R.drawable.wheel_val);
        weekWhl.setDrawShadows(false);
        weekWhl.setViewAdapter(new ArrayWheelAdapter<String>(getActivity(),weekArray));

        startWhl.setVisibleItems(2);
        startWhl.setCurrentItem(0);
        startWhl.setWheelBackground(R.drawable.trans);
        startWhl.setWheelForeground(R.drawable.wheel_val);
        startWhl.setDrawShadows(false);
        startWhl.setViewAdapter(new ArrayWheelAdapter<String>(getActivity(),startArray));

        endWhl.setVisibleItems(2);
        endWhl.setCurrentItem(0);
        endWhl.setWheelBackground(R.drawable.trans);
        endWhl.setWheelForeground(R.drawable.wheel_val);
        endWhl.setDrawShadows(false);
        endWhl.setViewAdapter(new ArrayWheelAdapter<String>(getActivity(),endArray));

        startWhl.addChangingListener(new OnWheelChangedListener() {
            public void onChanged(WheelView wheel, int oldValue,
                                  int newValue) {
                String[] temp  = new String[11-newValue];
                for(int i=0;i<11-newValue;i++){
                    temp[i] = "到"+(newValue+i+1)+"节";
                }
                endWhl.setViewAdapter(new ArrayWheelAdapter<String>(getActivity(),temp));
                endWhl
                        .setCurrentItem(0);
            }
        });

        Button b = (Button)view.findViewById(R.id.courseAddConfirm);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                String courseName = ((TextView)view.findViewById(R.id.addCourse_courseName)).getText()+"";
                String courseTeachers = ((TextView)view.findViewById(R.id.addCourse_courseTeachers)).getText()+"";
                String courseClassroom = ((TextView)view.findViewById(R.id.addCourse_courseClassroom)).getText()+"";
                int weekday = ((WheelView)view.findViewById(R.id.courseWheelWeekday)).getCurrentItem()+1;
                int start = ((WheelView)view.findViewById(R.id.courseWheelStart)).getCurrentItem()+1;
                int end = ((WheelView)view.findViewById(R.id.courseWheelEnd)).getCurrentItem()+start;
                List<CourseTimeEntity> times = new ArrayList<CourseTimeEntity>();
                List<Integer> weeks =  new ArrayList<Integer>();
                for(int i=1;i<=18;i++)
                    weeks.add(i);
                times.add(new CourseTimeEntity(weekday,start,end,courseClassroom,null));

                CourseEntity entity = new CourseEntity(null,courseName,times,courseTeachers);
                FragmentManager fm  = getActivity().getFragmentManager();
                FragmentDisplyCourse displyFragment = (FragmentDisplyCourse)fm.findFragmentByTag("disply");
                displyFragment.addCourse(entity);
                fm.popBackStack();
            }
        });
        return view;
    }
}
