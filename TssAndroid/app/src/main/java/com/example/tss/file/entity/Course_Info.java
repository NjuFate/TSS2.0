package com.example.tss.file.entity;

/**
 * Created by john on 2016/7/25.
 */
public class Course_Info {

    private String courseno;
    private String coursename;

    public Course_Info(String no, String name) {
        courseno = no;
        coursename = name;
    }

    public String getCourseno() {
        return courseno;
    }

    public String getCoursename() {
        return coursename;
    }
}
