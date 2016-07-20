package com.example.tss.course.dataservice;

import android.content.Context;

import java.util.List;

import com.example.tss.course.entity.CourseEntity;

/**
 * Created by Administrator on 2016/7/9.
 */
public interface CourseService {

    public List<CourseEntity> getAllCourses(Context context, String username, String password);
//
    public boolean login(String username, String password);
}
