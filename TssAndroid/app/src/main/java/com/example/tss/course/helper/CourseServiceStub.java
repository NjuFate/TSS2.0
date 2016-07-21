package com.example.tss.course.helper;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.example.tss.course.dataservice.CourseService;
import com.example.tss.course.entity.Course;
import com.example.tss.course.entity.CourseEntity;
import com.example.tss.course.entity.CourseTimeEntity;
import com.example.tss.util.fileUtil.FileUtil;


/**
 * Created by Administrator on 2016/7/11.
 */
public class CourseServiceStub implements CourseService {
    public CourseServiceStub() {
    }

    @Override
    public List<CourseEntity> getAllCourses(Context context, String username, String passworld) {
        String jsonStr;
        String saveUsername = FileUtil.read(context, "userName.txt");
//            read from the Internet and write into local;

        jsonStr = "[{\"id\":\"00000030A\",\"name\":\"毛泽东思想和中国特色社会主义理论体系概论（理论部分）\",\"message\":\"周二 第5-7节 1-18周 仙Ⅱ-405\",\"teachers\":\"李洪波\"},"
                + "{\"id\":\"25000070\",\"name\":\"软件工程统计方法\",\"message\":\"周一 第3-4节 双周 仙Ⅱ-505 周四 第1-2节 1-17周 仙Ⅱ-303\",\"teachers\":\"陈振宇\"},"
                + "{\"id\":\"25000230\",\"name\":\"计算机网络\",\"message\":\"周五 第5-7节 1-17周 仙Ⅱ-504\",\"teachers\":\"刘峰\"},"
                + "{\"id\":\"25000350\",\"name\":\"数据库系统\",\"message\":\"周二 第1-2节 1-17周 仙Ⅱ-505 周五 第3-4节 单周 仙Ⅱ-504\",\"teachers\":\"柏文阳\"},"
                + "{\"id\":\"25000360\",\"name\":\"计算机与操作系统\",\"message\":\"周一 第5-6节 1-17周 仙Ⅱ-305 周三 第3-4节 单周 仙Ⅱ-305\",\"teachers\":\"葛季栋, 骆斌\"},"
                + "{\"id\":\"00040100A\",\"name\":\"羽毛球初级\",\"message\":\"周三 第1-2节 1-17周\",\"teachers\":\"沈乐群\"},"
                + "{\"id\":\"00320110\",\"name\":\"西方人文主义演进的历史及其反思\",\"message\":\"周二 第9-10节 2-17周 仙Ⅰ-207\",\"teachers\":\"于文杰\"}]";
        FileUtil.write(context, username, "userName.txt");
        FileUtil.write(context, jsonStr, "courseData.txt");
        return CourseJsonHelper.analyse(jsonStr);
    }


    @Override
    public boolean login(String username, String password) {
//        System.out.println(username + " " + password);
//        String jsonStr = "";
//        try {
//            URL url = new URL("http://139.129.54.63/tss2/android.do?method=andoird_projectTable&userName=141250124&password=tangdaye123");
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                jsonStr += line;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(jsonStr);
//        if (jsonStr.length() == 0) {
//            return false;
//        }
        return true;
    }
}
