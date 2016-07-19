package data;

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

import dataservice.CourseService;
import entity.Course;
import entity.CourseEntity;
import entity.CourseTimeEntity;


/**
 * Created by Administrator on 2016/7/11.
 */
public class CourseServiceStub implements CourseService {
    public CourseServiceStub(){}
    @Override
    public List<CourseEntity> getAllCourses(String username, String passworld) {
//        List<Integer> all  = new ArrayList<Integer>();
//        List<Integer> odd  = new ArrayList<Integer>();
//        List<Integer> even  = new ArrayList<Integer>();
//        for(int i=1;i<=18;i++){
//            all.add(i);
//            if(i%2!=0){
//                odd.add(i);
//            }
//            else{
//                even.add(i);
//            }
//        }
//        CourseTimeEntity time11= new CourseTimeEntity(2,3,6,"仙林校区-仙二203",all);
//        CourseTimeEntity time12= new CourseTimeEntity(2,9,11,"仙林校区-仙二203",all);
//        List<CourseTimeEntity> time1 = new ArrayList<CourseTimeEntity>();
//        time1.add(time11);
//        time1.add(time12);
//        String teachers1 = "伏晓，汤恩义";
//        CourseEntity entity1 = new CourseEntity("000001","数据结构与算法",time1,teachers1);
//
//        CourseTimeEntity time21= new CourseTimeEntity(1,1,4,"仙林校区-仙二505",all);
//        CourseTimeEntity time22= new CourseTimeEntity(5,5,7,"仙林校区-仙二306",all);
//        List<CourseTimeEntity> time2 = new ArrayList<CourseTimeEntity>();
//        time2.add(time21);
//        time2.add(time22);
//        String teachers2 = "刘钦，丁二玉";
//        CourseEntity entity2 = new CourseEntity("000001","软件工程与计算2",time2,teachers2);
//
////        CourseTimeEntity time31= new CourseTimeEntity(1,5,6,"仙林校区-仙二505");
////        CourseTimeEntity time32= new CourseTimeEntity(6,5,7,"仙林校区-仙二306");
////        List<CourseTimeEntity> time3 = new ArrayList<CourseTimeEntity>();
////        time3.add(time31);
////        time3.add(time32);
////        List<String> teachers3 = new ArrayList<String>();
////        teachers3.add("黄蕾");
////        teachers3.add("刘嘉");
////        List<Integer> weeks3 = new ArrayList<Integer>();
////        for(int i=1;i<=18;i++)
////            weeks3.add(i);
////        CourseEntity entity3 = new CourseEntity("000001","软件工程与计算3",time3,teachers3,weeks3);
//
//        List<CourseEntity> result = new ArrayList<CourseEntity>();
//        result.add(entity1);
//        result.add(entity2);
////        result.add(entity3);
//        return result;

        List<Integer> all = new ArrayList<Integer>();
        List<Integer> odd = new ArrayList<Integer>();
        List<Integer> even = new ArrayList<Integer>();
        for (int i = 1; i <= 17; i++) {
            all.add(i);
            if (i % 2 != 0) {
                odd.add(i);
            } else {
                even.add(i);
            }
        }

        String jsonStr = "[{\"id\":\"00000030A\",\"name\":\"毛泽东思想和中国特色社会主义理论体系概论（理论部分）\",\"message\":\"周二 第5-7节 1-18周 仙Ⅱ-405\",\"teachers\":\"李洪波\"},"
                + "{\"id\":\"25000070\",\"name\":\"软件工程统计方法\",\"message\":\"周一 第3-4节 双周 仙Ⅱ-505 周四 第1-2节 1-17周 仙Ⅱ-303\",\"teachers\":\"陈振宇\"},"
                + "{\"id\":\"25000230\",\"name\":\"计算机网络\",\"message\":\"周五 第5-7节 1-17周 仙Ⅱ-504\",\"teachers\":\"刘峰\"},"
                + "{\"id\":\"25000330\",\"name\":\"软件工程与计算 III\",\"message\":\"周四 第5-6节 1-2周 第4周 第9周 第14周 仙Ⅱ-207 周四 第5-6节 第3周 5-8周 10-13周 15-17周 基础实验楼丙区教室\",\"teachers\":\"黄蕾, 刘嘉, 骆斌, 张瑾玉\"},"
                + "{\"id\":\"25000350\",\"name\":\"数据库系统\",\"message\":\"周二 第1-2节 1-17周 仙Ⅱ-505 周五 第3-4节 单周 仙Ⅱ-504\",\"teachers\":\"柏文阳\"},"
                + "{\"id\":\"25000360\",\"name\":\"计算机与操作系统\",\"message\":\"周一 第5-6节 1-17周 仙Ⅱ-305 周三 第3-4节 单周 仙Ⅱ-305\",\"teachers\":\"葛季栋, 骆斌\"},"
                + "{\"id\":\"00040100A\",\"name\":\"羽毛球初级\",\"message\":\"周三 第1-2节 1-17周\",\"teachers\":\"沈乐群\"},"
                + "{\"id\":\"00320110\",\"name\":\"西方人文主义演进的历史及其反思\",\"message\":\"周二 第9-10节 2-17周 仙Ⅰ-207\",\"teachers\":\"于文杰\"},"
                + "{\"id\":\"37218003\",\"name\":\"生物医学电子工程导学\",\"message\":\"周四 第7-8节 2-9周 仙Ⅰ-106\",\"teachers\":\"卞春华\"}]";
//        String jsonStr = "";
//        try {
//            URL url = new URL("http://139.129.54.63/tss2/android.do?method=andoird_projectTable&userName="+username+" &password="+passworld);
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                jsonStr += line;
//            }
//        } catch (Exception e) {
//
//        }




        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonArray Jarray = parser.parse(jsonStr).getAsJsonArray();
        List<Course> list = new ArrayList<Course>();
        for (JsonElement obj : Jarray) {
            Course course = gson.fromJson(obj, Course.class);
            list.add(course);
        }

        List<CourseEntity> result = new ArrayList<CourseEntity>();
        List<Integer> weeks = new ArrayList<Integer>();
        for (int i = 1; i <= 18; i++) {
            weeks.add(i);
        }
        for (Course course : list) {
            String id = course.getId();
            String name = course.getName();
            String teachers = course.getTeachers();
            List<Integer> week = weeks;
            List<CourseTimeEntity> times = new ArrayList<CourseTimeEntity>();
            String[] temp = course.getMessage().split(" ");

            List<ArrayList<String>> timeList = new ArrayList<ArrayList<String>>();
            List<Integer> indexList = new ArrayList<Integer>();
            for (int i = 0; i < temp.length; i++) {
                if (temp[i].startsWith("周")) {
                    indexList.add(i);
                }
            }
            indexList.add(temp.length);
            for (int j = 0; j < indexList.size() - 1; j++) {
                ArrayList<String> tempp = new ArrayList<String>();
                for (int i = indexList.get(j); i < indexList.get(j + 1); i++) {
                    tempp.add(temp[i]);
                }
                timeList.add(tempp);
            }

            for (ArrayList<String> timeItem : timeList) {
                CourseTimeEntity timeEntity;
                String weekdayS = timeItem.get(0);
                int weekday = 0;
                switch (weekdayS) {
                    case "周一":
                        weekday = 1;
                        break;
                    case "周二":
                        weekday = 2;
                        break;
                    case "周三":
                        weekday = 3;
                        break;
                    case "周四":
                        weekday = 4;
                        break;
                    case "周五":
                        weekday = 5;
                        break;
                    case "周六":
                        weekday = 6;
                        break;
                    case "周日":
                        weekday = 7;
                        break;
                    default:
                        break;
                }

                String startS = timeItem.get(1).substring(1, 2);
                int start = Integer.parseInt(startS);

                String endS = timeItem.get(1).substring(3, 4);
                int end = Integer.parseInt(endS);

                if (timeItem.size() == 3) {
                    String classroom = "上课地点未知";
                    List<Integer> weekstype = new ArrayList<Integer>();
                    if (timeItem.get(2).startsWith("单")) {
                        weekstype = odd;
                    } else if (timeItem.get(2).startsWith("双")) {
                        weekstype = even;
                    } else {
                        weekstype = all;
                    }
                    CourseTimeEntity timetemp = new CourseTimeEntity(weekday,start,end,classroom,weekstype);
                    times.add(timetemp);
                }

                else {
                    String classroom = timeItem.get(timeItem.size() - 1);

                    List<Integer> weekstype = new ArrayList<Integer>();
                    if (timeItem.size() == 4) {
                        if (timeItem.get(2).startsWith("单")) {
                            weekstype = odd;
                        } else if (timeItem.get(2).startsWith("双")) {
                            weekstype = even;
                        } else {
                            weekstype = all;
                        }
                    } else {
                        for (int i = 2; i < timeItem.size() - 1; i++) {
                            String str = timeItem.get(i);
                            if (str.startsWith("第")) {
                                weekstype.add(Integer.parseInt(str.substring(1, str.length() - 1)));
                            } else {
                                String[] temppp = str.substring(0, str.length() - 1).split("-");
                                int startweek = Integer.parseInt(temppp[0]);
                                int endweek = Integer.parseInt(temppp[1]);
                                for (int j = startweek; j <= endweek; j++) {
                                    weekstype.add(j);
                                }
                            }
                        }
                    }

                    timeEntity = new CourseTimeEntity(weekday, start, end, classroom, weekstype);
                    times.add(timeEntity);
                }
            }
            CourseEntity entity = new CourseEntity(id, name, times, teachers);
            result.add(entity);
        }
        return result;
    }

    @Override
    public boolean login(String username, String password) {
        System.out.println(username+" "+password);
        String jsonStr = "";
        try {
            URL url = new URL("http://139.129.54.63/tss2/android.do?method=andoird_projectTable&userName=141250124&password=tangdaye123");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                jsonStr += line;
            }
        } catch (Exception e) {
                e.printStackTrace();
        }
        System.out.println(jsonStr);
        if(jsonStr.length()==0){
            return false;
        }
        return true;
    }
}
