/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlythuvien.views;
import java.time.LocalDate;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author admin
 */
public class RegisterView {
    public void start(Stage stage){
         //vboxleft
        Label welcomeTitle = new Label("Đăng ký thôi!");
        welcomeTitle.setStyle("-fx-font-size: 22px; -fx-text-fill: white; -fx-font-weight: bold;");
        Label welcomeDescribe = new Label("Nếu bạn đã có tài khoản, hãy\nđăng nhập vào hệ thống!");
        welcomeDescribe.setStyle("-fx-text-fill: white; -fx-font-size: 13px;");
        welcomeDescribe.setTextAlignment(TextAlignment.CENTER);
        Button btnSignIn = new Button("Đăng nhập ngay");
        btnSignIn.setStyle("-fx-background-color: transparent; -fx-border-color: white; -fx-text-fill: white; -fx-font-weight: bold;");
        btnSignIn.setOnAction(e -> {
            LoginView loginView = new LoginView();
            loginView.start(stage);
        });
        
        VBox vboxLeft = new VBox(15, welcomeTitle, welcomeDescribe, btnSignIn);
        vboxLeft.setAlignment(Pos.CENTER);
        vboxLeft.setPadding(new Insets(20));
        vboxLeft.setStyle("-fx-background-color: linear-gradient(to bottom, #0E6C42, #1D774E);");
        vboxLeft.setPrefWidth(260);

         //vbox right
        Label signInTitle = new Label("Đăng Ký");
        signInTitle.setStyle("-fx-font-size: 20px; -fx-text-fill: #00927A; -fx-font-weight: bold;");

        Label lbUsername = new Label("Tên đăng nhập: ");
        lbUsername.setStyle("-fx-font-size: 12px, -fx-font-weight: bold; -fx-text-fill: #000000");
        TextField tfUsername = new TextField();
        tfUsername.setPromptText("Nhập tên đăng nhập ...");
        tfUsername.setMaxWidth(250);
        tfUsername.setPrefHeight(30);
        VBox vbUsername = new VBox(5, lbUsername, tfUsername);
        
        Label lbEmail = new Label("Email: ");
        lbEmail.setStyle("-fx-font-size: 12px, -fx-font-weight: bold; -fx-text-fill: #000000");
        TextField tfEmail = new TextField();
        tfEmail.setPromptText("Nhập Email ...");
        tfEmail.setMaxWidth(250);
        tfEmail.setPrefHeight(30);
        VBox vbEmail= new VBox(5, lbEmail, tfEmail);
        
        Label lbBirthDate = new Label("Ngày sinh: ");
        lbBirthDate.setStyle("-fx-font-size: 12px, -fx-font-weight: bold; -fx-text-fill: #000000");
        DatePicker dpBirthDate = new DatePicker();
        dpBirthDate.setPromptText("Nhập ngày sinh ...");
        dpBirthDate.setMaxWidth(250);
        VBox vbBirthDate = new VBox(5, lbBirthDate, dpBirthDate);

        Label lbPassword = new Label("Mật khẩu: ");
        lbPassword.setStyle("-fx-font-size: 12px, -fx-font-weight: bold; -fx-text-fill: #000000");
        PasswordField tfPassword = new PasswordField();
        tfPassword.setPromptText("Nhập mật khẩu ...");
        tfPassword.setMaxWidth(250);
        tfPassword.setPrefHeight(30);
        VBox vbPassword = new VBox(5, lbPassword, tfPassword);
        
        Label lbConfirm = new Label("Xác thực mật khẩu: ");
        lbConfirm.setStyle("-fx-font-size: 12px, -fx-font-weight: bold; -fx-text-fill: #000000");
        PasswordField tfConfirm = new PasswordField();
        tfConfirm.setPromptText("Nhập lại mật khẩu ...");
        tfConfirm.setMaxWidth(250);
        tfConfirm.setPrefHeight(30);
        VBox vbConfirm = new VBox(5, lbConfirm, tfConfirm);
        
        Label lbGender = new Label("Chọn giới tính:");
        lbGender.setStyle("-fx-font-size: 12px, -fx-font-weight: bold; -fx-text-fill: #000000");
        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton rdNam = new RadioButton("Nam");
        RadioButton rdNu = new RadioButton("Nữ");
        HBox hbGender = new HBox(10, rdNam, rdNu);
        VBox vbGender = new VBox(5, lbGender, hbGender);
        rdNam.setToggleGroup(genderGroup);
        rdNu.setToggleGroup(genderGroup);
        genderGroup.selectedToggleProperty().addListener((ov, t, t1) -> {
            if (t1 != null){
                RadioButton rdGender = (RadioButton)t1;
            }
        });

        Button btnSignUp = new Button(" 📝 Đăng ký");
        btnSignUp.setMaxWidth(250);
        btnSignUp.setStyle("-fx-background-color: #00927A; -fx-text-fill: white; -fx-font-weight: bold;");
        btnSignUp.setOnAction((t) -> {
            String username = tfUsername.getText();
            String email = tfEmail.getText();
            LocalDate date = dpBirthDate.getValue();
            String birthdate = date.toString();
            String password = tfPassword.getText();
            String confirm = tfConfirm.getText();
            String gender = "";
            Toggle selectedToggle = genderGroup.getSelectedToggle();           
            if (selectedToggle != null){
                RadioButton selectedRadio = (RadioButton) selectedToggle;
                gender = selectedRadio.getText();
            }
            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirm.isEmpty() || birthdate == null || gender.isEmpty()){
                showAlert("Lỗi","Vui lòng đầy đủ các trường thông tin!");
            }
            if (!password.equals(confirm)){
                showAlert("Lỗi","Xác thực mật khẩu không khớp mật khẩu!");
            }
        });
        
        VBox vboxRight = new VBox(10, signInTitle, vbUsername, vbEmail, vbBirthDate, vbPassword, vbConfirm, vbGender, btnSignUp);
        vboxRight.setAlignment(Pos.CENTER);
        vboxRight.setPadding(new Insets(30, 50, 30, 50));
        vboxRight.setStyle("-fx-background-color: white;");
        vboxRight.setPrefWidth(350);
        vboxRight.setMargin(btnSignUp, new Insets(10, 0, 0, 0));


        HBox masterLayout = new HBox(vboxLeft, vboxRight);

        Scene scene = new Scene(masterLayout, 610, 500);
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
