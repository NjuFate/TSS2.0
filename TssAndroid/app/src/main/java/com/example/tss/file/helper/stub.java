package com.example.tss.file.helper;

import com.example.tss.file.entity.My_File;

/**
 * Created by john on 2016/7/11.
 */
public class Stub {

    private My_File main;

    public Stub() {
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
        My_File txt = new My_File(false);
        txt.setName("a.txt");
        My_File pdf = new My_File(false);
        pdf.setName("b.pdf");
        My_File pic = new My_File(false);
        pic.setName("c.jpg");
        My_File ppt = new My_File(false);
        ppt.setName("d.ppt");
        main.addSon_File(son1);
        main.addSon_File(son2);
        main.addSon_File(son3);
        son1.addSon_File(txt);
        son1.addSon_File(pdf);
        son2.addSon_File(son4);
        son3.addSon_File(pic);
        son4.addSon_File(ppt);
    }

    public My_File getMainFile() {
        return main;
    }
}
