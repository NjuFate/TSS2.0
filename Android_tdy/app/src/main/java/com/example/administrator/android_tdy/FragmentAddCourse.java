package com.example.administrator.android_tdy;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/7/11.
 */
public class FragmentAddCourse extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.add_course,container,false);
        return view;
    }

}