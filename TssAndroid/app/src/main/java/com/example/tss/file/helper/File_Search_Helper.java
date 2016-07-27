package com.example.tss.file.helper;

import com.example.tss.file.entity.Course_Info;
import com.example.tss.file.entity.Isolated_File;

import java.util.List;

/**
 * Created by john on 2016/7/25.
 */
public class File_Search_Helper {

    List<Course_Info> courses = null;

    List<Isolated_File> files = null;

    public File_Search_Helper() {
        courses = File_Net_Helper.Course_Infos;
        files = File_Net_Helper.files;
    }

    public boolean is_course(String name) {
        for (int i = 0; i < courses.size(); i++) {
            if(courses.get(i).getCoursename().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean is_folder(String name,String coursename) {
        for (int i = 0; i < files.size(); i++) {
            if(files.get(i).getCoursename().equals(coursename)&&
                    files.get(i).getFileName().equals(name)) {
                return files.get(i).isFolder();
            }
        }
        return false;
    }

    public String[] getSonname(String name,String coursename) {
        String[] sons = null;
        int num = 0;
        File_Search_Helper search_helper = new File_Search_Helper();
        if(search_helper.is_course(name)) {
            for (int i = 0; i < courses.size(); i++) {
                if(courses.get(i).getCoursename().equals(name)) {
                    name = courses.get(i).getCourseno();
                    break;
                }
            }
        }
        for (int i = 0; i < files.size(); i++) {
            Isolated_File file = files.get(i);
            String father = file.getFather();
            if(father.equals(name)&&file.getCoursename().equals(coursename)){
                String[] new_sons = new String[num + 1];
                for (int j = 0; j < num; j++) {
                    new_sons[j] = sons[j];
                }
                new_sons[num] = file.getFileName();
                num++;
                sons = new_sons;
            }
        }
        return sons;
    }

    public String getPath(String name,String coursname) {
        for (int i = 0; i < files.size(); i++) {
            Isolated_File file = files.get(i);
            if(file.getCoursename().equals(coursname)&&file.getFileName().equals(name)) {
                return file.getPath();
            }
        }
        return null;
    }
}
