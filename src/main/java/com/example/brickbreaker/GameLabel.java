/*
//  _______ ______ _____   _____  ____  __  __          _   _                         _                            __
// |__   __|  ____|  __ \ / ____|/ __ \|  \/  |   /\   | \ | |                       | |                     _     \ \
//    | |  | |__  | |__) | (___ | |  | | \  / |  /  \  |  \| |  __      ____ _ ___   | |__   ___ _ __ ___   (_)     | |
//    | |  |  __| |  _  / \___ \| |  | | |\/| | / /\ \ | . ` |  \ \ /\ / / _` / __|  | '_ \ / _ \ '__/ _ \          | |
//    | |  | |____| | \ \ ____) | |__| | |  | |/ ____ \| |\  |   \ V  V / (_| \__ \  | | | |  __/ | |  __/   _      | |
//    |_|  |______|_|  \_\_____/ \____/|_|  |_/_/    \_\_| \_|    \_/\_/ \__,_|___/  |_| |_|\___|_|  \___|  (_)     | |
//                                                                                                                 /_/
*/
package com.example.brickbreaker;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameLabel extends Label {
    public enum MessageType{
        WIN, LOST
    }
    private MessageType messageType = null;
    GameLabel(){
        super();
        setTextFill(Color.RED);
        setFont(Font.font("Arial", FontWeight.BOLD, 30));
        setVisible(false);
        setAlignment(Pos.CENTER);
        setPrefWidth(1000);
        setPrefHeight(60);
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void selectMessageType(MessageType type) {
        if (type == MessageType.WIN) {
            setText("YOU WIN! CLick to Restart");
        } else if (type == MessageType.LOST) {
            setText("YOU LOST! Click to Restart");
        }
    }

    public void show() {
        setLayoutX(Main.width / 2.0 - getWidth() / 2);
        setLayoutY(Main.height / 2.0 - getHeight() / 2);
        setVisible(true);
    }
    public void hide() {
        setVisible(false);
    }

    public void clear(){
        setVisible(false);
    }
}