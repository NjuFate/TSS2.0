package service;

import java.util.List;

import entity.CourseEntity;

/**
 * Created by Administrator on 2016/7/9.
 */
public interface CourseService {
    public List<CourseEntity> getAllCourses(String username,String passworld);
    public boolean login(String username,String passworld);
}
