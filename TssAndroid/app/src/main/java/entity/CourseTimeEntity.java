package entity;

import java.util.List;

/**
 * Created by Administrator on 2016/7/11.
 */
public class CourseTimeEntity {
    private int weekday;
    private int start;
    private int end;
    private String classroom;
    private List<Integer> weeks;

    public CourseTimeEntity(int weekday, int start, int end, String classroom, List<Integer> weeks) {
        this.weekday = weekday;
        this.start = start;
        this.end = end;
        this.classroom = classroom;
        this.weeks = weeks;
    }

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public void setWeeks(List<Integer> weeks) {
        this.weeks = weeks;
    }

    public List<Integer> getWeeks() {
        return weeks;
    }
}
