package com.example.tss.course.helper;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.tss.course.entity.Course;
import com.example.tss.course.entity.CourseEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/7/21.
 */
public class CourseLoader extends AsyncTaskLoader<List<CourseEntity>> {
    private String mUsernamestr;
    private String mPasswordstr;
    private Context mContext;
    private List<CourseEntity> mData;

    public CourseLoader(Context context,String usernamestr,String passwordstr) {
        super(context);
        mUsernamestr = usernamestr;
        mPasswordstr = passwordstr;
        mContext = context;
    }

    @Override
    public List<CourseEntity> loadInBackground() {
        return null;
    }
}
