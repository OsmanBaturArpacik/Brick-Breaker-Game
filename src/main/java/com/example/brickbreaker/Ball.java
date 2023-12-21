package com.example.brickbreaker;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Ball extends Circle {
    public enum Direction{TOP_RIGHT, TOP_LEFT,BOTTOM_RIGHT,BOTTOM_LEFT};
    private Direction direction = Direction.TOP_RIGHT;
    private int posX;
    private int posY;
    private final int radius = 5;
    private GameOver gameOver;

    public Ball(GameOver gameOver) {
        // Center of scene size
        super(Main.width/2, Main.height/2, 10, Paint.valueOf("red")); //x,y,radius,paint
        posX = (int) getCenterX();
        posY = (int) getCenterX();
        this.gameOver = gameOver;
    }

    public void ballSetCenter() {
        setCenterX(Main.width/2.0);
        setCenterY(Main.height/2.0);
        posX = (int) getCenterX();
        posY = (int) getCenterY();
    }

    public void setDirection(CollisionDetect.CollisionFrom collision) {
        if(CollisionDetect.CollisionFrom.VERTICAL == collision) {
            switch(direction) {
                case TOP_LEFT:
                    direction = Direction.TOP_RIGHT;
                    break;
                case TOP_RIGHT:
                    direction = Direction.TOP_LEFT;
                    break;
                case BOTTOM_LEFT:
                    direction = Direction.BOTTOM_RIGHT;
                    break;
                case BOTTOM_RIGHT:
                    direction = Direction.BOTTOM_LEFT;
                    break;
            }
        }
        else if(CollisionDetect.CollisionFrom.HORIZONTAL == collision)
        {
            switch(direction) {
                case BOTTOM_LEFT:
                    direction = Direction.TOP_LEFT;
                    break;
                case BOTTOM_RIGHT:
                    direction = Direction.TOP_RIGHT;
                    break;
                case TOP_LEFT:
                    direction = Direction.BOTTOM_LEFT;
                    break;
                case TOP_RIGHT:
                    direction = Direction.BOTTOM_RIGHT;
                    break;
            }
        }
    }

    public void move() {

        switch(direction) {
            //
            // ----------------> X (X,0)
            // |
            // |
            // |
            // |
            // |
            // Y (0,Y)
            //
            //
            //  |________________________________________________|
            //  |TOP_LEFT                              TOP_RIGHT |               _________
            //  | (0,0)                                (WIDTH,0) |             _|         |_
            //  |                                                |           _|      r      |_
            //  |                                                |          |                 |
            //  |                                                |          |radius  *  radius|
            //  |                                                |          |_               _|
            //  |                                                |            |_     r     _|
            //  |                                                |              |_________|
            //  | (0,HEIGHT)                      (WIDTH,HEIGHT) |
            //  | BOTTOM_LEFT                      BOTTOM_RIGHT  |
            //  |________________________________________________|
            //
            case TOP_LEFT:
                posX--;
                posY--;
                if (posX - radius <= 0) direction = Direction.TOP_RIGHT;
                if (posY - radius <= 0) direction = Direction.BOTTOM_LEFT;
                break;
            case TOP_RIGHT:
                posX++;
                posY--;
                if (posX + radius >= Main.width) direction = Direction.TOP_LEFT;
                if (posY - radius <= 0) direction = Direction.BOTTOM_RIGHT;
                break;
            case BOTTOM_LEFT:
                posX--;
                posY++;
                if (posX - radius <= 0) direction = Direction.BOTTOM_RIGHT;
                if (posY + radius >= Main.height) { direction = Direction.TOP_LEFT; // gameOver() ball touched ground
                    gameOver.gameOver(GameLabel.MessageType.LOST);
                }
                break;
            case BOTTOM_RIGHT:
                posX++;
                posY++;
                if(posX + radius >= Main.width) direction = Direction.BOTTOM_LEFT;
                if(posY + radius >= Main.height) { direction = Direction.TOP_RIGHT; // gameOver() ball touched ground
                    gameOver.gameOver(GameLabel.MessageType.LOST);
                }
                break;
        }
        setCenterX(posX);
        setCenterY(posY);
    }

    public Direction getDirection() {
        return direction;
    }
}
