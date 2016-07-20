package com.example.tss.file.entity;

/**
 * Created by john on 2016/7/15.
 */
public class Isolated_File {

    private boolean isFolder;
    private String father;
    private String fileName;
    private String updateTime;
    private String uploadBy;
    private String path;
    private String courseno;
    private String coursename;
    private String semester;

    public Isolated_File(Isolated_File file) {
        if (file.isFolder()) {
            isFolder = true;
        } else {
            isFolder = false;
        }
        this.father = file.getFather();
        this.fileName = file.getFileName();
        this.updateTime = file.getUpdateTime();
        this.uploadBy = file.getUploadBy();
        this.path = file.getPath();
        this.courseno = file.getCourseno();
        this.coursename = file.getCoursename();
        this.semester = file.getSemester();
    }

    public boolean isFolder() {
        return isFolder;
    }

    public String getFather() {
        return father;
    }

    public String getFileName() {
        return fileName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public String getUploadBy() {
        return uploadBy;
    }

    public String getPath() {
        return path;
    }

    public String getCourseno() {
        return courseno;
    }

    public String getCoursename() {
        return coursename;
    }

    public String getSemester() {
        return semester;
    }

}
