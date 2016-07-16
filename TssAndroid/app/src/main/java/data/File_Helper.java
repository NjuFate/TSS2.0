package data;

/**
 * Created by john on 2016/7/15.
 */
public class File_Helper {

    private Isolated_File[] files;

    private My_File root;

    public File_Helper() {
        root = new My_File(true);
        root.setName("root");
    }

    public My_File getRootFile() {

        return null;
    }

    // 获取所有课程（课程名+学期），形成根目录下的课程文件夹
    private My_File[] getCourseFile() {
        My_File courses[] = null;
        int num = 0;
        for (int i = 0; i < files.length; i++) {
            if(!this.contain(courses,files[i])){
                courses = new My_File[num+1];
            }
        }
        return courses;
    }

    // 判断该文件夹中是否含有此元素，在获取课程时使用
    private boolean contain(My_File[] files, Isolated_File file) {
        if(files == null) {
            return false;
        }
        int length = files.length;
        for (int i = 0; i < length; i++) {
            if(file.getFather().equals(files[i].getName())){
                return false;
            }
        }
        return true;
    }
}
