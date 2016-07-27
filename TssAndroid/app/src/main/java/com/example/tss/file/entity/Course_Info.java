package com.example.tss.file.entity;

/**
 * Created by john on 2016/7/25.
 */
public class Course_Info {

    private String courseno;
    private String coursename;
    private String semester;

    public Course_Info(String no, String name, String se) {
        courseno = no;
        coursename = name;
        semester = se;
    }

    public String getCourseno() {
        return courseno;
    }

    public String getCoursename() {
        return coursename;
    }

    public String getSemester() {
        return semester;
    }
}
