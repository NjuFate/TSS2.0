package ServiceStub;

import java.util.ArrayList;
import java.util.List;

import entity.CourseEntity;
import entity.CourseTimeEntity;
import service.CourseService;

/**
 * Created by Administrator on 2016/7/11.
 */
public class CourseServiceStub implements CourseService{
    public CourseServiceStub(){}
    @Override
    public List<CourseEntity> getAllCourses(String username,String passworld) {
        CourseTimeEntity time11= new CourseTimeEntity(2,3,6,"仙林校区-仙二203");
        CourseTimeEntity time12= new CourseTimeEntity(2,9,11,"仙林校区-仙二203");
        List<CourseTimeEntity> time1 = new ArrayList<CourseTimeEntity>();
        time1.add(time11);
        time1.add(time12);
        List<String> teachers1 = new ArrayList<String>();
        teachers1.add("汤恩义");
        teachers1.add("伏晓");
        List<Integer> weeks1 = new ArrayList<Integer>();
        for(int i=1;i<=18;i++)
            weeks1.add(i);
        CourseEntity entity1 = new CourseEntity("000001","数据结构与算法",time1,teachers1,weeks1);

        CourseTimeEntity time21= new CourseTimeEntity(1,1,4,"仙林校区-仙二505");
        CourseTimeEntity time22= new CourseTimeEntity(5,5,7,"仙林校区-仙二306");
        List<CourseTimeEntity> time2 = new ArrayList<CourseTimeEntity>();
        time2.add(time21);
        time2.add(time22);
        List<String> teachers2 = new ArrayList<String>();
        teachers2.add("刘钦");
        teachers2.add("丁二玉");
        List<Integer> weeks2 = new ArrayList<Integer>();
        for(int i=1;i<=18;i++)
            weeks2.add(i);
        CourseEntity entity2 = new CourseEntity("000001","软件工程与计算2",time2,teachers2,weeks2);

//        CourseTimeEntity time31= new CourseTimeEntity(1,5,6,"仙林校区-仙二505");
//        CourseTimeEntity time32= new CourseTimeEntity(6,5,7,"仙林校区-仙二306");
//        List<CourseTimeEntity> time3 = new ArrayList<CourseTimeEntity>();
//        time3.add(time31);
//        time3.add(time32);
//        List<String> teachers3 = new ArrayList<String>();
//        teachers3.add("黄蕾");
//        teachers3.add("刘嘉");
//        List<Integer> weeks3 = new ArrayList<Integer>();
//        for(int i=1;i<=18;i++)
//            weeks3.add(i);
//        CourseEntity entity3 = new CourseEntity("000001","软件工程与计算3",time3,teachers3,weeks3);

        List<CourseEntity> result = new ArrayList<CourseEntity>();
        result.add(entity1);
        result.add(entity2);
//        result.add(entity3);
        return result;
    }

    @Override
    public boolean login(String username, String passworld) {
        return true;
    }
}
