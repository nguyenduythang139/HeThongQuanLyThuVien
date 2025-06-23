package com.quanlythuvien.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class LoginView {
    public void start(Stage stage) {
         //vboxleft
        Label welcomeTitle = new Label("Xin chào!");
        welcomeTitle.setStyle("-fx-font-size: 22px; -fx-text-fill: white; -fx-font-weight: bold;");
        Label welcomeDescribe = new Label("Chào mừng bạn đã đến với hệ thống\nquản lý thư viện của chúng tôi");
        welcomeDescribe.setStyle("-fx-text-fill: white; -fx-font-size: 13px;");
        welcomeDescribe.setTextAlignment(TextAlignment.CENTER);
        Button btnSignUp = new Button("Đăng ký ngay");
        btnSignUp.setStyle("-fx-background-color: transparent; -fx-border-color: white; -fx-text-fill: white; -fx-font-weight: bold;");
        btnSignUp.setOnAction((t) -> {
            RegisterView registerView = new RegisterView();
            registerView.start(stage);
        });
        
        VBox vboxLeft = new VBox(15, welcomeTitle, welcomeDescribe, btnSignUp);
        vboxLeft.setAlignment(Pos.CENTER);
        vboxLeft.setPadding(new Insets(20));
        vboxLeft.setStyle("-fx-background-color: linear-gradient(to bottom, #0E6C42, #1D774E);");
        vboxLeft.setPrefWidth(260);

         //vbox right
        Label signInTitle = new Label("Đăng Nhập");
        signInTitle.setStyle("-fx-font-size: 20px; -fx-text-fill: #00927A; -fx-font-weight: bold;");

        Label lbUsername = new Label("Tên đăng nhập: ");
        lbUsername.setStyle("-fx-font-size: 12px, -fx-font-weight: bold; -fx-text-fill: #000000");
        TextField tfUsername = new TextField();
        tfUsername.setPromptText("Nhập tên đăng nhập ...");
        tfUsername.setMaxWidth(250);
        tfUsername.setPrefHeight(30);
        VBox vbUsername = new VBox(5, lbUsername, tfUsername);

        Label lbPassword = new Label("Mật khẩu: ");
        lbPassword.setStyle("-fx-font-size: 12px, -fx-font-weight: bold; -fx-text-fill: #000000");
        PasswordField tfPassword = new PasswordField();
        tfPassword.setPromptText("Nhập mật khẩu ...");
        tfPassword.setMaxWidth(250);
        tfPassword.setPrefHeight(30);
        VBox vbPassword = new VBox(5, lbPassword, tfPassword);

        Button btnSignIn = new Button("🔐 Đăng nhập");
        btnSignIn.setMaxWidth(250);
        btnSignIn.setStyle("-fx-background-color: #00927A; -fx-text-fill: white; -fx-font-weight: bold;");
        btnSignIn.setOnAction((t) -> {
            String username = tfUsername.getText();
            String password = tfPassword.getText();
            if (username.equals("admin") && password.equals("123456")){
                showAlert("Thành công", "Đăng nhập thành công vào hệ thống!");
                HomeView homeView = new HomeView();
                homeView.start(stage);
            }
            else{
                showAlert("Thất bại", "Vui lòng kiểm tra lại tên đăng nhập hoặc mật khẩu!");
            }
        });
        
        VBox vboxRight = new VBox(10, signInTitle, vbUsername, vbPassword, btnSignIn);
        vboxRight.setAlignment(Pos.CENTER);
        vboxRight.setPadding(new Insets(30, 50, 30, 50));
        vboxRight.setStyle("-fx-background-color: white;");
        vboxRight.setPrefWidth(350);
        vboxRight.setMargin(btnSignIn, new Insets(10, 0, 0, 0));


        HBox masterLayout = new HBox(vboxLeft, vboxRight);

        Scene scene = new Scene(masterLayout, 610, 400);
        stage.setScene(scene);
        stage.setTitle("Hệ Thống Quản Lý Thư Viện - Đăng Nhập");
        stage.show();
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
