package com.example.brickbreaker;

import javafx.scene.shape.Rectangle;

public class Bar extends Rectangle implements CollisionDetect{

    public static final double width = 100.0d;
    public static final double height = 20.0d;
    Bar(){
        super(Main.width/2, Main.height - 40, width, height);
        setArcHeight(20);
        setArcWidth(20);
    }


    @Override
    public CollisionFrom isCollision(Ball ball) {
        // if direction is to the top return null because can not touch bar while going top
        if(ball.getDirection() == Ball.Direction.TOP_RIGHT || ball.getDirection() == Ball.Direction.TOP_LEFT) return null;

        if(super.getBoundsInParent().intersects(ball.getBoundsInParent())){
            double ballPosY = ball.getCenterY();
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
            //  |______________________________________________________| super.getY()
            // H|             BBBBB    AAAAAA    RRRRR                 |
            // E|             B    B  AA    AA   R    R                |
            // I|             BBBBB   A      A   R RRR                 | O -> ball, vertical hit condition
            // G|             B    B  AAAAAAAA   R   R                 |
            // H|             BBBBB   A      A   R    R                |
            // T|______________________________________________________| super.getY() + this.height
            //
            if(super.getY() < ballPosY && super.getY() + this.height > ballPosY){
                return CollisionFrom.VERTICAL;
            }
            else {
                return CollisionFrom.HORIZONTAL;
            }
        }
        return null;
    }
}
