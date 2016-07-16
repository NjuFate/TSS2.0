package data;

/**
 * Created by john on 2016/7/9.
 */
public class My_File {

    private String Name;
    private boolean Is_Folder;
    private My_File[] Son_File;
    private int Son_Num;

    public My_File(boolean is_Folder) {
        if (is_Folder) {
            Is_Folder = true;
            Son_File = null;
            Son_Num = 0;
        } else {
            Is_Folder = false;
            Son_File = null;
            Son_Num = -1;
        }
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public boolean is_Folder() {
        return Is_Folder;
    }

    public My_File[] getSon_File() {
        return Son_File;
    }

    public int getSon_Num() {
        return Son_Num;
    }

    public void addSon_File(My_File New_File) {
        if (!this.Is_Folder) {
            return;
        }
        My_File[] New_Son_File = new My_File[Son_Num + 1];
        for (int i = 0; i < Son_Num; i++) {
            New_Son_File[i] = Son_File[i];
        }
        New_Son_File[Son_Num] = New_File;
        Son_Num++;
        Son_File = New_Son_File;
    }
}
