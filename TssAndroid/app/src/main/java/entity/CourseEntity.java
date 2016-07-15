package entity;

import java.util.List;

/**
 * Created by Administrator on 2016/7/9.
 */
public class CourseEntity {
    private String id;
    private String name;
    private String teachers;
    private List<CourseTimeEntity> time;
    private List<Integer> weeks;
    public CourseEntity(String id, String name, List<CourseTimeEntity> time, String teachers, List<Integer> weeks) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.teachers = teachers;
        this.weeks = weeks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeachers() {
        return teachers;
    }

    public void setTeachers(String teachers) {
        this.teachers = teachers;
    }

    public List<CourseTimeEntity> getTime() {
        return time;
    }

    public void setTime(List<CourseTimeEntity> time) {
        this.time = time;
    }

    public List<Integer> getWeeks() {
        return weeks;
    }

    public void setWeeks(List<Integer> weeks) {
        this.weeks = weeks;
    }
}
