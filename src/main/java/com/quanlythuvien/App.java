package com.quanlythuvien;

import com.quanlythuvien.views.LoginView;
import com.quanlythuvien.views.ManageBookView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        /*LoginView login = new LoginView();
        login.start(stage);*/
        ManageBookView manageBookView = new ManageBookView();
        manageBookView.start(stage);
    }

    public static void main(String[] args) {
        launch();
    }

}