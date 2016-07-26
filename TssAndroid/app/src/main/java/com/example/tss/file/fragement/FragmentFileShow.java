package com.example.tss.file.fragement;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tss.file.entity.My_File;
import com.example.tss.tssandroid.R;

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
            tv.setText(son.getName());
            tv.setTextSize(24);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
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
                        ft.replace(R.id.content, fileShow);
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
            LinearLayout ll = new LinearLayout(getActivity());
            ll.setOrientation(LinearLayout.HORIZONTAL);
            ImageView iv = this.getIV(son);
            ll.addView(iv);
            ll.addView(tv);
            ((ViewGroup) view.findViewById(R.id.File_Main)).addView(ll);
        }
        return view;
    }

    private ImageView getIV(My_File mf) {
        ImageView iv = new ImageView(getActivity());
        if (mf.is_Folder()) {
            iv.setImageDrawable(getResources().getDrawable(R.drawable.picture_folder));
        } else {
            String file_name = mf.getName();
            int location = file_name.lastIndexOf('.');
            if (location == -1) {
                iv.setImageDrawable(getResources().getDrawable(R.drawable.picture_unknown));
            } else {
                String extension_name = file_name.substring(location + 1);
                if (extension_name.equals("doc") || extension_name.equals("docx")) {
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.picture_doc));
                } else if (extension_name.equals("txt")) {
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.picture_txt));
                } else if (extension_name.equals("ppt") || extension_name.equals("pptx")) {
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.picture_ppt));
                } else if (extension_name.equals("rar") || extension_name.equals("zip") || extension_name.equals("gz")) {
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.picture_zip));
                } else if (extension_name.equals("wmv") || extension_name.equals("rmvb") || extension_name.equals("mkv")) {
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.picture_video));
                } else if (extension_name.equals("pdf")) {
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.picture_pdf));
                } else if (extension_name.equals("jpg") || extension_name.equals("png") || extension_name.equals("jpeg")) {
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.picture_picture));
                } else {
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.picture_unknown));
                }
            }
        }
        return iv;
    }

}
