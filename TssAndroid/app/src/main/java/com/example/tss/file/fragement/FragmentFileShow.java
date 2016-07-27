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
import android.widget.Toast;

import java.net.URL;

import com.example.tss.file.helper.File_Download_Helper;
import com.example.tss.file.helper.File_Search_Helper;
import com.example.tss.tssandroid.R;

/**
 * Created by john on 2016/7/14.
 */
@SuppressLint("ValidFragment")
public class FragmentFileShow extends Fragment {

    private String[] names;

    private int num;

    private String coursename;

    public FragmentFileShow(String[] ns, String cn) {
        names = ns;
        num = names.length;
        coursename = cn;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_file, container, false);
        for (int i = 0; i < num; i++) {
            final String name = names[i];
            TextView tv = new TextView(getActivity());
            tv.setText(" "+name);
            tv.setTextSize(24);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            tv.setLayoutParams(params);
            tv.setHeight(80);
            tv.setTextColor(Color.BLACK);
            final File_Search_Helper search_helper = new File_Search_Helper();
            if (search_helper.is_course(name)) {

                final String[] son_names = search_helper.getSonname(name, name);
//                Toast t = Toast.makeText(getActivity().getApplicationContext(),son_names[0],Toast.LENGTH_SHORT);
//                t.show();
                tv.setOnClickListener(new View.OnClickListener() {
                    private String[] temp_son_names = son_names;
                    private String temp_course = name;

                    @Override
                    public void onClick(View v) {
                        FragmentFileShow fileShow = new FragmentFileShow(temp_son_names, temp_course);
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.content, fileShow);
                        ft.addToBackStack(null);
                        ft.commit();
                    }
                });
            } else if (search_helper.is_folder(name, coursename)) {
                final String[] son_names = search_helper.getSonname(name, coursename);
                tv.setOnClickListener(new View.OnClickListener() {
                    private String[] temp_son_names = son_names;
                    private String temp_course = coursename;

                    @Override
                    public void onClick(View v) {
                        FragmentFileShow fileShow = new FragmentFileShow(temp_son_names, temp_course);
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.content, fileShow);
                        ft.addToBackStack(null);
                        ft.commit();
                    }
                });
            } else {
                tv.setOnClickListener(new View.OnClickListener() {
                    private String temp_name = name;
                    private String temp_course = coursename;
                    String path = "http://139.129.54.63/tss2/downloadFile/download/?path=" + search_helper.getPath(name, coursename);

                    @Override
                    public void onClick(View v) {
                        try {
                            URL url = new URL(path);
                            File_Download_Helper download_helper = new File_Download_Helper(url,name,getActivity());

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            LinearLayout ll = new LinearLayout(getActivity());
            ll.setOrientation(LinearLayout.HORIZONTAL);
            ImageView iv = this.getIV(name);
            ll.addView(iv);
            ll.addView(tv);
            ((ViewGroup) view.findViewById(R.id.File_Main)).addView(ll);
        }
        return view;
    }

    private ImageView getIV(String name) {
        ImageView iv = new ImageView(getActivity());
        int location = name.lastIndexOf('.');
        File_Search_Helper search_helper = new File_Search_Helper();
        boolean is_folder = search_helper.is_folder(name, coursename);
        boolean is_course = search_helper.is_course(name);
        if(is_folder||is_course) {
            iv.setImageDrawable(getResources().getDrawable(R.drawable.picture_folder));
        }else if (location == -1) {
            iv.setImageDrawable(getResources().getDrawable(R.drawable.picture_unknown));
        } else {
            String extension_name = name.substring(location + 1);
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
        return iv;
    }

}
