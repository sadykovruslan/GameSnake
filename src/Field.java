import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Field extends JPanel implements ActionListener {
    private final int SIZE = 320;
    private final int DOT_SIZE = 16; // сколько пикселей будет занимать одна ячейка змейки или яблока
    private final int ALL_DOTS = 400; // сколько всего точек может поместиться на игровом поле. т.е:
    // в размер поля 320 может поместиться 20 DOT_SIZE (320/16) в ширину и столько же в длину. итого 20*20=400
    private Image snake;
    private Image apple;

//    координаты яблока на поле (X, Y)
    private int appleX;
    private int appleY;
//    массивы для хранения X и Y змейки соответственно:
    private final int[] x = new int[ALL_DOTS];
    private final int[] y = new int[ALL_DOTS];
    private int snakeSize = 3;

    //    поля отвечающее за текущее направление змейки
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;

    private boolean inGame = true; // Статус игры. Мы в игре или нет

    public Field(){
        setBackground(Color.black);
        loadImages();
        initGame();
        addKeyListener(new keyListener());
        setFocusable(true);
    }

    public void initGame(){
        for (int i = 0; i < snakeSize; i++) {
            x[i] = 48 - i * DOT_SIZE;
            y[i] = 16;
        }
        Timer timer = new Timer(250, this);
            timer.start();
            createApple();
    }

    public void createApple(){ // рандомное появление блок на поле
        appleX = new Random().nextInt(20)*DOT_SIZE;
        appleY = new Random().nextInt(20)*DOT_SIZE;
    }

    private void loadImages(){
        ImageIcon appleView = new ImageIcon("apple.png");
        apple =  appleView.getImage();

        ImageIcon snakeView = new ImageIcon("snake.png");
        snake = snakeView.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(inGame){
            g.drawImage(apple, appleX, appleY, this);
            for (int i = 0; i < snakeSize; i++) {
                g.drawImage(snake, x[i], y[i], this);
            }
        } else {
            String str = "Game Over!!!";
            g.drawString(str, 100, SIZE/2);
        }
    }

    public void move(){
        for (int i = snakeSize; i > 0; i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        if (left){
            x[0]-=DOT_SIZE;
        }
        if (right){
            x[0]+=DOT_SIZE;
        }
        if (up){
            y[0]-=DOT_SIZE;
        }
        if (down){
            y[0]+=DOT_SIZE;
        }
    }

    private void eatApple() {
        if (x[0] == appleX && y[0] == appleY){
            snakeSize++;
            createApple();
            checkCollisions();
        }
    }

    private void checkCollisions() {
        for (int i = snakeSize; i > 0; i--) {
            if (i > 4 && x[0] == x[i] && y[0] == y[i]) {
                inGame = false;
                break;
            }
        }
        if (x[0] > SIZE || x[0] < 0 || y[0] > SIZE || y[0] < 0){
            inGame = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame){
            eatApple();
            checkCollisions();
            move();
        }
        repaint();
    }

    class keyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && !right){
                left = true;
                up = false;
                down = false;
            }

            if(key == KeyEvent.VK_RIGHT && !left){
                right = true;
                up = false;
                down = false;
            }

            if(key == KeyEvent.VK_DOWN && !up){
                down = true;
                left = false;
                right = false;
            }

            if(key == KeyEvent.VK_UP && !down){
                up = true;
                left = false;
                right = false;
            }
        }
    }
}