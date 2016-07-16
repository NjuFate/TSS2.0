package data;

import java.util.ArrayList;
import java.util.List;

import dataservice.CourseService;
import entity.CourseEntity;
import entity.CourseTimeEntity;


/**
 * Created by Administrator on 2016/7/11.
 */
public class CourseServiceStub implements CourseService {
    public CourseServiceStub(){}
    @Override
    public List<CourseEntity> getAllCourses(String username, String passworld) {
        List<Integer> all  = new ArrayList<Integer>();
        List<Integer> odd  = new ArrayList<Integer>();
        List<Integer> even  = new ArrayList<Integer>();
        for(int i=1;i<=18;i++){
            all.add(i);
            if(i%2!=0){
                odd.add(i);
            }
            else{
                even.add(i);
            }
        }
        CourseTimeEntity time11= new CourseTimeEntity(2,3,6,"仙林校区-仙二203",all);
        CourseTimeEntity time12= new CourseTimeEntity(2,9,11,"仙林校区-仙二203",all);
        List<CourseTimeEntity> time1 = new ArrayList<CourseTimeEntity>();
        time1.add(time11);
        time1.add(time12);
        String teachers1 = "伏晓，汤恩义";
        CourseEntity entity1 = new CourseEntity("000001","数据结构与算法",time1,teachers1);

        CourseTimeEntity time21= new CourseTimeEntity(1,1,4,"仙林校区-仙二505",all);
        CourseTimeEntity time22= new CourseTimeEntity(5,5,7,"仙林校区-仙二306",all);
        List<CourseTimeEntity> time2 = new ArrayList<CourseTimeEntity>();
        time2.add(time21);
        time2.add(time22);
        String teachers2 = "刘钦，丁二玉";
        CourseEntity entity2 = new CourseEntity("000001","软件工程与计算2",time2,teachers2);

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
