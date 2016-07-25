package com.example.tss.file.helper;

import com.example.tss.file.entity.Isolated_File;
import com.example.tss.file.entity.My_File;

/**
 * Created by john on 2016/7/15.
 */
public class File_Transform_Helper {

    private Isolated_File[] files;

    private My_File root;

    public File_Transform_Helper() {
        root = new My_File(true);
        root.setName("root");
    }

    public My_File getRootFile() {
        // 新加课程目录文件
        this.addCourseFile();
        // 添加子文件
        this.addSonFile();
        return root;
    }

    private void addSonFile() {
        int File_Num = files.length;
        while (File_Num != 0) {
            for (int i = 0; i < files.length; i++) {
                String File_Name = files[i].getFileName();
                String Father_Name = files[i].getFather();
                My_File Father_File = this.Search_Father(Father_Name, root);
                if ((Father_File != null) && (!Contain(Father_File.getSon_File(), files[i]))) {
                    My_File son = new My_File(files[i].isFolder());
                    son.setName(File_Name);
                    if (!files[i].isFolder()) {
                        son.setPath(files[i].getPath());
                    }
                    Father_File.addSon_File(son);
                    File_Num--;
                }
            }
        }
    }

    /**
     * 添加课程文件夹
     * 主要思路是遍历所有独立文件，取出其father信息，如果该father不是一个文件，则必然是一个课程，新建该课程文件夹
     * 当中要避免重复，所有要进行判断
     */
    private void addCourseFile() {
        // 课程文件夹数组
        My_File[] Courses_File = null;
        // 课程数量
        int Courses_Num = 0;
        // 遍历所有独立文件
        for (int i = 0; i < files.length; i++) {
            // 如果当前课程数组中不包括当前独立文件father
            if (!Contain(Courses_File, files[i])) {
                // 取出当前独立文件的Father
                // TODO 此处Father_Name 是课程编号，应该转化为课程名
                String Father_Name = files[i].getFather();
                // 判断该Father是否是课程
                if (IsCourseName(Father_Name)) {
                    // 新建该课程，加入数组中
                    My_File[] New_Courses_File = new My_File[Courses_Num + 1];
                    for (int j = 0; j < Courses_Num; j++) {
                        New_Courses_File[j] = Courses_File[j];
                    }
                    My_File New_Course = new My_File(true);
                    New_Course.setName(Father_Name);
                    New_Courses_File[Courses_Num] = New_Course;
                    Courses_Num++;
                }
            }
        }

    }

    /**
     * 判断独立文件的father是否已经在课程文件数组中
     *
     * @param files 课程文件数组
     * @param file  独立文件
     * @return 是否包含
     */
    private boolean Contain(My_File[] files, Isolated_File file) {
        // 如果当前课程文件夹为空，为假
        if (files == null) {
            return false;
        }
        // 获得数组长度
        int length = files.length;
        // 遍历数组
        for (int i = 0; i < length; i++) {
            // TODO 由于接口变化原因，file.getFather()返回de为课程编号，需要再转化为课程名
            // 如果相等，返回真
            if (file.getFather().equals(files[i].getName())) {
                return true;
            }
        }
        // 如果遍历后仍都不包含，则为假
        return false;
    }

    /**
     * 判断此名称是否是课程
     * 遍历所有独立文件，如果没有该名称的文件，说明这是课程名
     *
     * @param name 需要判断的名称
     * @return
     */
    private boolean IsCourseName(String name) {
        for (int i = 0; i < files.length; i++) {
            if (files[i].getFileName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    private My_File Search_Father(String father, My_File root) {
        if ((root.is_Folder()) && (root.getSon_Num() != 0)) {
            My_File[] Sons = root.getSon_File();
            for (int i = 0; i < root.getSon_Num(); i++) {
                if (Sons[i].getName().equals(father)) {
                    return Sons[i];
                } else {
                    return Search_Father(father, Sons[i]);
                }
            }
        }
        return null;
    }
}
