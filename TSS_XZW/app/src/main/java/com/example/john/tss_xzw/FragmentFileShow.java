package com.example.john.tss_xzw;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.john.tss_xzw.util.My_File;

/**
 * Created by john on 2016/7/14.
 */
@SuppressLint("ValidFragment")
public class FragmentFileShow extends Fragment {

    private My_File mf = null;
    private My_File[] sons = null;
    private int son_num = 0;
    private My_File son;

    public FragmentFileShow(My_File my_file) {
        mf = my_file;
        sons = my_file.getSon_File();
        son_num = my_file.getSon_Num();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_file, container, false);
        for (int i = 0; i < son_num; i++) {
            son = sons[i];
            TextView tv = new TextView(getActivity());
            tv.setText("  "+son.getName());
            tv.setTextSize(24);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(params);
            tv.setHeight(80);
            tv.setTextColor(Color.BLACK);
            if (son.is_Folder()) {
                tv.setOnClickListener(new View.OnClickListener() {

                    private My_File son_file = son;

                    @Override
                    public void onClick(View v) {
                        FragmentFileShow fileShow = new FragmentFileShow(son_file);
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.FileContent, fileShow);
                        ft.addToBackStack(null);
                        ft.commit();
                    }
                });
            } else {
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
            ((ViewGroup) view.findViewById(R.id.File_Main)).addView(tv);
        }
        return view;
    }

}
