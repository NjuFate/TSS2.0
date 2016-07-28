package com.example.tss.file.helper;

import com.example.tss.file.entity.Course_Info;
import com.example.tss.file.entity.Isolated_File;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 2016/7/24.
 */
public class File_Net_Helper {
    // http://139.129.54.63/tss2/android.do?method=android_file
    // http://139.129.54.63/tss2/android.do?method=android_source

    public static List<Course_Info> Course_Infos = new ArrayList<Course_Info>();

    public static List<Isolated_File> files = new ArrayList<Isolated_File>();

    public static void getCourseInfo() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String url_course_info= "http://139.129.54.63/tss2/android.do?method=android_source";
//                HttpURLConnection connection = null;
//                Reader read;
//                BufferedReader bufferReader;
//                try {
//                    URL url = new URL(url_course_info);
//                    connection = (HttpURLConnection) url.openConnection();
//                    connection.setConnectTimeout(8000);
//                    connection.setRequestMethod("GET");
//                    connection.setReadTimeout(8000);
//
//                    read = new InputStreamReader(connection.getInputStream());
//                    bufferReader = new BufferedReader(read);
//
//                    String str;
//                    StringBuffer buffer = new StringBuffer();
//                    while ((str = bufferReader.readLine()) != null) {
//                        buffer.append(str);
//                    }
//
//                    Gson gson = new Gson();
//                    JsonParser parser = new JsonParser();
//                    String whole_string = buffer.toString();
//                    JsonArray Jarray = parser.parse(whole_string).getAsJsonArray();
//                    for (JsonElement obj : Jarray) {
//                        Course_Info course_info = gson.fromJson(obj, Course_Info.class);
//                        Course_Infos.add(course_info);
//                    }
//                    read.close();
//                    connection.disconnect();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    if (connection != null) {
//                        connection.disconnect();
//                    }
//                }
//            }
//        }).start();

        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        String whole_string = "[{\"courseno\":\"c1094\",\"coursename\":\"SE1\",\"semester\":\"2016Summer\"}]";
        JsonArray Jarray = parser.parse(whole_string).getAsJsonArray();
        for (JsonElement obj : Jarray) {
            Course_Info course_info = gson.fromJson(obj, Course_Info.class);
            Course_Infos.add(course_info);
        }
    }

    public static void getFileInfo() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String url_course_info= "http://139.129.54.63/tss2/android.do?method=android_file";
//                HttpURLConnection connection = null;
//                Reader read;
//                BufferedReader bufferReader;
//                try {
//                    URL url = new URL(url_course_info);
//                    connection = (HttpURLConnection) url.openConnection();
//                    connection.setConnectTimeout(8000);
//                    connection.setRequestMethod("GET");
//                    connection.setReadTimeout(8000);
//
//                    read = new InputStreamReader(connection.getInputStream());
//                    bufferReader = new BufferedReader(read);
//
//                    String str;
//                    StringBuffer buffer = new StringBuffer();
//                    while ((str = bufferReader.readLine()) != null) {
//                        buffer.append(str);
//                    }
//
//                    Gson gson = new Gson();
//                    JsonParser parser = new JsonParser();
//                    String whole_string = buffer.toString();
//                    JsonArray Jarray = parser.parse(whole_string).getAsJsonArray();
//                    for (JsonElement obj : Jarray) {
//                        Isolated_File file_info = gson.fromJson(obj, Isolated_File.class);
//                        files.add(file_info);
//                    }
//                    read.close();
//                    connection.disconnect();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    if (connection != null) {
//                        connection.disconnect();
//                    }
//                }
//            }
//        }).start();
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        String whole_string = "[{\"id\":16072000002,\"isFolder\":true,\"father\":\"c1094\",\"fileName\":\"base\",\"updateTime\":\"2016-07-20 03:08:11\",\"uploadBy\":\"www@123.com\",\"path\":null,\"courseno\":\"c1094\",\"coursename\":\"SE1\",\"semester\":\"2016Summer\"},{\"id\":16072000000,\"isFolder\":false,\"father\":\"c1094\",\"fileName\":\"chapter01.ppt\",\"updateTime\":\"2016-07-20 05:08:11\",\"uploadBy\":\"www@123.com\",\"path\":\"c:/tss2/chapter01.ppt\",\"courseno\":\"c1094\",\"coursename\":\"SE1\",\"semester\":\"2016Summer\"},{\"id\":16072000001,\"isFolder\":false,\"father\":\"base\",\"fileName\":\"chapter02.ppt\",\"updateTime\":\"2016-07-20 04:08:11\",\"uploadBy\":\"www@123.com\",\"path\":\"c:/tss2/chapter02.ppt\",\"courseno\":\"c1094\",\"coursename\":\"SE1\",\"semester\":\"2016Summer\"}]";
        JsonArray Jarray = parser.parse(whole_string).getAsJsonArray();
        for (JsonElement obj : Jarray) {
            Isolated_File file_info = gson.fromJson(obj, Isolated_File.class);
            files.add(file_info);
        }

    }

}
