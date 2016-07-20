package com.example.tss.course.helper;

import com.example.tss.course.entity.Course;
import com.example.tss.course.entity.CourseEntity;
import com.example.tss.course.entity.CourseTimeEntity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/20.
 */
public class CourseJsonHelper {
    public static List<CourseEntity> Analysis(String jsonStr){
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
}
