package com.example.brickbreaker;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class GamePane extends Pane implements GameOver {

    private Ball ball;
    private Bar bar;
    private GameLabel gameLabel;
    private Timeline timeline;
    private boolean startGame = false;
    private final int countOfRows = 5;
    private final int countOfBricksInRow = 5;
    private Label startLabel;
    private boolean gameIsRunning = false;

    private Brick [][] bricks = new Brick[countOfRows][countOfBricksInRow];
    GamePane(){
/*        setBackground(Background.fill(Paint.valueOf("yellow")));*/
        BackgroundFill backgroundFill = new BackgroundFill(Color.YELLOW, null, null);
        Background background = new Background(backgroundFill);
        setBackground(background);
        setWidth(Main.width);
        setHeight(Main.height);

        ball = new Ball(this::gameOver);
        bar = new Bar();
        gameLabel = new GameLabel();
        startLabel = new Label("Welcome To Brick Breaker - Click To Start");
        startLabel.setTextFill(Color.RED);
        startLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        startLabel.setAlignment(Pos.CENTER);
        startLabel.setPrefWidth(1000);
        startLabel.setPrefHeight(60);
        startLabel.setLayoutX(Main.width/2.0 - 500);
        startLabel.setLayoutY(Main.height/2.0);

        getChildren().add(ball);
        getChildren().add(bar);
        getChildren().add(gameLabel);
        getChildren().add(startLabel);

        setOnMouseMoved(e -> barHandle(e));
        setOnMouseClicked(this::startGame);

        createBricks();

        timeline = new Timeline(new KeyFrame(Duration.millis(5),this::move));
        timeline.setCycleCount(-1);
    }
    private void createBricks() {
        for (int i=0;i<countOfRows;i++){
            for (int j=0;j<countOfBricksInRow;j++){
                bricks[i][j] = new Brick(i,j);
                getChildren().add(bricks[i][j]);
            }
        }
    }
    private void checkBricks() {
        int counter = 0;
        for(Brick[] row : bricks) {
            for(Brick brick : row) {
                // if brick isn't exist counter+1
                if(!brick.isVisible()) {
                    counter++;
                }
            }
        }
        // it's mean all brick are not visible gameover Win
        if (counter == countOfRows*countOfBricksInRow) {
            gameOver(GameLabel.MessageType.WIN);
        }
    }
    private void barHandle(MouseEvent mouseEvent) {
        if (bar.getX() < 0 || bar.getX() + bar.getWidth() > Main.width) {
            if(mouseEvent.getX()>bar.getWidth() && mouseEvent.getX() < Main.width - bar.getWidth()) {
                if(mouseEvent.getX() > 0 && mouseEvent.getX() < Main.width) {
                    bar.setX(mouseEvent.getX());
                }
                return;
            }
            return;
        }
        bar.setX(mouseEvent.getX() - Bar.width/2);
    }

    private void startGame(MouseEvent mouseEvent) {
        if(gameIsRunning) {
            return;
        }
        newGame();
        gameIsRunning = true;
        timeline.play();

        if(startLabel.isVisible()) {
            startLabel.setVisible(false);
        }

        gameLabel.hide();
    }


    private void move(ActionEvent actionEvent) {
        checkBricks();
        CollisionDetect.CollisionFrom collision = null;
        ball.move();

        collision = bar.isCollision(ball);
        if(collision != null){
            ball.setDirection(collision);
            return;
        }

        for (int i=0;i<countOfRows;i++){
            for (int j=0;j<countOfBricksInRow;j++){
                collision = bricks[i][j].isCollision(ball);
                if(collision != null){
                    ball.setDirection(collision);
                }
            }
        }
    }

    public void newGame() {
//        startLabel.setVisible(true);
        ball.ballSetCenter();
        for(Brick[] row : bricks) {
            for(Brick brick : row) {
                brick.brickSetVisible(true);
            }
        }
    }

    @Override
    public void gameOver(GameLabel.MessageType messageType) {
        startGame = false;
        timeline.stop();
        gameLabel.selectMessageType(messageType);
        gameLabel.show();
        gameIsRunning = false;

    }
}
