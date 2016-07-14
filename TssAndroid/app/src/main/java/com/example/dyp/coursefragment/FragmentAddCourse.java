package com.example.dyp.coursefragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dyp.tssandroid.R;

/**
 * Created by Administrator on 2016/7/12.
 */
public class FragmentAddCourse extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.add_course,container,false);
        return view;
    }
}
