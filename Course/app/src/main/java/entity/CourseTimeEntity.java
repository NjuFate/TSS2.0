package entity;

/**
 * Created by Administrator on 2016/7/11.
 */
public class CourseTimeEntity {
    private int weekday;
    private int start;
    private int end;
    private String classroom;

    public CourseTimeEntity(int weekday, int start, int end, String classroom) {
        this.weekday = weekday;
        this.start = start;
        this.end = end;
        this.classroom = classroom;
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
}
