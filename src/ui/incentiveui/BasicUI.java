package ui.incentiveui;

import javax.swing.*;
import java.awt.*;
public abstract class BasicUI extends JFrame {

    public BasicUI() {
        create();
        Container con = getContentPane();
        add(con);
        addListeners();
        makeItVisible();
    }

    public abstract void create();

    public abstract void add(Container con);

    public abstract void addListeners();

    public abstract void makeItVisible();

//    public void makeItVisible() {
//        setSize(250, 300);
//        setVisible(true);
//    }
}
