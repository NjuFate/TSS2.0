package data;

/**
 * Created by john on 2016/7/11.
 */
public class stub {

    private My_File main;

    public stub() {
        main = new My_File(true);
        main.setName("main");
        My_File son1 = new My_File(true);
        son1.setName("son1");
        My_File son2 = new My_File(true);
        son2.setName("son2");
        My_File son3 = new My_File(true);
        son3.setName("son3");
        My_File son4 = new My_File(true);
        son4.setName("son4");
        My_File atxt = new My_File(false);
        atxt.setName("a.txt");
        My_File son5 = new My_File(true);
        son5.setName("son5");
        My_File a = new My_File(false);
        a.setName("a");
        main.addSon_File(son1);
        main.addSon_File(son2);
        main.addSon_File(son3);
        son1.addSon_File(atxt);
        son1.addSon_File(son5);
        son2.addSon_File(son4);
        son3.addSon_File(a);
    }

    public My_File getMainFile() {
        return main;
    }
}
