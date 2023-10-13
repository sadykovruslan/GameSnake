package GUI;

import modelgame.GraphicField;

import javax.swing.*;

public class Window extends JFrame {
    public Window(){
        setTitle("Snake");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(320,320);
        setLocation(400,400);
        add(new GraphicField());
        setVisible(true);
    }

    public static void main(String[] args) {
        Window window = new Window();
    }
}
