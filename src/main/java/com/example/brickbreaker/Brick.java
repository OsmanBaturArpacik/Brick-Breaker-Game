package com.example.brickbreaker;

import javafx.scene.shape.Rectangle;

public class Brick extends Rectangle implements CollisionDetect {

    public static final int width = 100;
    public static final int height = 20;
    public static final int margin = 15;
    private boolean isVisible = true;

    public Brick(int row, int col) {
        // ----------------> X (col)
        // |
        // |
        // |
        // |
        // |
        // Y
        // (row)
        //                 m
        //                 a
        //                 r
        //                 g
        //                 i
        //                 n
        //
        //                 |--------------------+++++++++++++++++++++++++|
        //                 | h                                           |
        //                 | e                                           |
        //   m a r g i n   | i      w     i      d      t      h         |
        //                 | g                                           |
        //                 | h                                           |
        //                 | t                                           |
        //                 |--------------------+++++++++++++++++++++++++|
        //

        super(col*width + margin*(col+1),row*height + margin*(row+3), width, height); //(x,y,width,height)
        setArcWidth(20);
        setArcHeight(20);
    }

    public void brickSetVisible(boolean visible) {
        super.setVisible(visible);
        isVisible = visible;

    }

    @Override
    public CollisionFrom isCollision(Ball ball) {
        // if isn't visible brick is not exist
        if (!isVisible)
            return null;
        // This class is extends from Rectangle. Checking for Brick is intersects() with ball object
        if(super.getBoundsInParent().intersects(ball.getBoundsInParent())) {
            double ballPosY = ball.getCenterY();
            // brick is brokt
            isVisible = false;
            setVisible(false);
            // Checking for hit from where, if hit vertical
            //
            // ----------------> X
            // |
            // |
            // |
            // |
            // |
            // Y
            //
            //
            //  |_______________| super.getY()
            // H|               |
            // E|               |
            // I|               | O -> ball, vertical hit condition
            // G|               |
            // H|               |
            // T|_______________| super.getY() + this.height
            //
            //
            if(super.getY() < ballPosY && super.getY() + this.height > ballPosY) {
                return CollisionFrom.VERTICAL;
            }
            else {
                return CollisionFrom.HORIZONTAL;
            }
        }
        return null;
    }
}
