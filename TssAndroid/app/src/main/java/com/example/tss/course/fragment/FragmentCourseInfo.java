package com.example.tss.course.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tss.tssandroid.R;

/**
 * Created by Administrator on 2016/7/22.
 */
public class FragmentCourseInfo extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.couse_info,container,false);
        return view;
    }
}
