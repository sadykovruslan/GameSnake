package modelgame;

import java.util.Random;


public class Apple {
    GraphicField graphicField;
    int appleX;
    int appleY;

    public void createApple(){ // рандомное появление яблок на поле
        appleX = new Random().nextInt(20) * graphicField.DOT_SIZE;
        appleY = new Random().nextInt(20)* graphicField.DOT_SIZE;
    }
}
