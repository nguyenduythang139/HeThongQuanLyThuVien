package com.quanlythuvien;

import com.quanlythuvien.views.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    @Override
    public void start(Stage stage) {
        LoginView login = new LoginView();
        login.start(stage);
    }

    public static void main(String[] args) {
        launch();
    }

}