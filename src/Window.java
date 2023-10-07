import javax.swing.*;

public class Window extends JFrame {
    public Window(){
        setTitle("Snake");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(320,345);
        setLocation(400,400);
        add(new Field());
        setVisible(true);
    }

    public static void main(String[] args) {
        Window window = new Window();
    }
}
