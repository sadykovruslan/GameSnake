package modelgame;

public class Snake {
    //    координаты яблока на поле (X, Y)

    Direction direction;
    GraphicField graphicField;
    Apple apple;

    public void move(){
        for (int i = graphicField.snakeSize; i > 0; i--){
            graphicField.x[i] = graphicField.x[i-1];
            graphicField.y[i] = graphicField.y[i-1];
        }

        graphicField.x[0] +=direction.getX() * graphicField.DOT_SIZE;
        graphicField.y[0] +=direction.getY() * graphicField.DOT_SIZE;
    }

    public void eatApple() {
        if (graphicField.x[0] == apple.appleX && graphicField.y[0] == apple.appleY){
            graphicField.snakeSize++;
            apple.createApple();
            checkCollisions();
        }
    }

    public void checkCollisions() {
        for (int i = graphicField.snakeSize; i > 0; i--) {
            if (i > 4 && graphicField.x[0] == graphicField.x[i] && graphicField.y[0] == graphicField.y[i]) {
                graphicField.inGame = false;
            }
        }
        if (graphicField.x[0] > graphicField.SIZE || graphicField.x[0] < 0 || graphicField.y[0] > graphicField.SIZE || graphicField.y[0] < 0){
            graphicField.inGame = false;
        }
    }
}
