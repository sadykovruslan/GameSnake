package modelgame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class GraphicField extends JPanel implements ActionListener {
    static final int SIZE = 320;
     static final int DOT_SIZE = 16; // сколько пикселей будет занимать одна ячейка змейки или яблока
    private static final int ALL_DOTS = 400; // сколько всего точек может поместиться на игровом поле. т.е:
    // в размер поля 320 может поместиться 20 DOT_SIZE (320/16) в ширину и столько же в длину. итого 20*20=400

    private Image snakeImage;
    private Image appleImage;

    Snake snake;
    Apple apple;


//    массивы для хранения X и Y змейки соответственно:
    final int[] x = new int[ALL_DOTS];
    final int[] y = new int[ALL_DOTS];
    int snakeSize = 3;

    private Direction direction = Direction.RIGHT;

    public boolean inGame = true; // Статус игры. Мы в игре или нет


    public GraphicField(){
        setBackground(Color.black);
        loadImages();
        initGame();
        addKeyListener(new keyListener());
        setFocusable(true);
    }

    private void loadImages() {
        try {
            appleImage = ImageIO.read(getClass().getResourceAsStream("/apple.png"));
            snakeImage = ImageIO.read(getClass().getResourceAsStream("/snake.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initGame(){
        for (int i = 0; i < snakeSize; i++) {
            x[i] = 48 - i * DOT_SIZE;
            y[i] = 16;
        }
        Timer timer = new Timer(250, this);
            timer.start();
            apple.createApple();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(inGame){
            g.drawImage(appleImage, apple.appleX, apple.appleY, this);
            for (int i = 0; i < snakeSize; i++) {
                g.drawImage(snakeImage, x[i], y[i], this);
            }
        } else {
            String str = "Game Over!!!";
            g.drawString(str, 100, SIZE/2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame){
            snake.eatApple();
            snake.checkCollisions();
            snake.move();
        }
        repaint();
    }

    class keyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && direction !=Direction.RIGHT){
               direction = Direction.LEFT;
            }

            if(key == KeyEvent.VK_RIGHT && direction !=Direction.LEFT){
                direction = Direction.RIGHT;
            }

            if(key == KeyEvent.VK_DOWN && direction !=Direction.UP){
                direction = Direction.DOWN;
            }

            if(key == KeyEvent.VK_UP && direction !=Direction.DOWN){
                direction = Direction.UP;
            }
        }
    }
}