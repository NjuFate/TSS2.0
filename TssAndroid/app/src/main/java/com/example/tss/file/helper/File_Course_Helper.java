package com.example.tss.file.helper;

import com.example.tss.file.entity.Isolated_File;
import com.example.tss.file.entity.My_File;

/**
 * Created by john on 2016/7/25.
 */
public class File_Course_Helper {

    private Isolated_File[] files;

    private String[] Course_Names;

    private int Course_Num;

    public File_Course_Helper(Isolated_File[] fs) {
        files = fs;
        Course_Names = null;
        Course_Num = 0;
    }

    private String[] getCourseName() {
        for (int i = 0; i < files.length; i++) {
            if (!Contain(Course_Names, files[i])) {
                // TODO 此处Father_Name 是课程编号，应该转化为课程名
                String Father_Name = files[i].getFather();
                if (IsCourseName(Father_Name)) {
                }
            }
        }
        return Course_Names;
    }

    private boolean Contain(String[] files, Isolated_File file) {
        if (files == null) {
            return false;
        }
        int length = files.length;
        for (int i = 0; i < length; i++) {
            // TODO 由于接口变化原因，file.getFather()返回de为课程编号，需要再转化为课程名
            // 如果相等，返回真
            if (file.getFather().equals(files[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean IsCourseName(String name) {
        for (int i = 0; i < files.length; i++) {
            if (files[i].getFileName().equals(name)) {
                return false;
            }
        }
        return true;
    }
}
