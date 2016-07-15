package com.example.dyp.coursefragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dyp.tssandroid.R;

import widget.OnWheelChangedListener;
import widget.WheelView;
import widget.adapters.ArrayWheelAdapter;

/**
 * Created by Administrator on 2016/7/12.
 */
public class FragmentAddCourse extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.add_course,container,false);
        String[] weekArray = {"周一","周二","周三","周四","周五","周六","周日"};
        String[] startArray = {"第1节","第2节","第3节","第4节","第5节","第6节","第7节","第8节","第9节","第10节","第11节"};
        final String[] endArray = {"到1节","到2节","到3节","到4节","到5节","到6节","到7节","到8节","到9节","到10节","到11节"};
        WheelView weekWhl = (WheelView)view.findViewById(R.id.courseWheelWeekday);
        WheelView startWhl = (WheelView)view.findViewById(R.id.courseWheelStart);
        final WheelView endWhl = (WheelView)view.findViewById(R.id.courseWheelEnd);

        weekWhl.setVisibleItems(2);
        weekWhl.setCurrentItem(0);
        weekWhl.setWheelBackground(R.drawable.trans);
        weekWhl.setDrawShadows(false);
        weekWhl.setViewAdapter(new ArrayWheelAdapter<String>(getActivity(),weekArray));

        startWhl.setVisibleItems(2);
        startWhl.setCurrentItem(0);
        startWhl.setWheelBackground(R.drawable.trans);
        startWhl.setDrawShadows(false);
        startWhl.setViewAdapter(new ArrayWheelAdapter<String>(getActivity(),startArray));

        endWhl.setVisibleItems(2);
        endWhl.setCurrentItem(0);
        endWhl.setWheelBackground(R.drawable.trans);
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

        return view;
    }
}
