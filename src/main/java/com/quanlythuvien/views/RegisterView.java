/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlythuvien.views;
import com.quanlythuvien.database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    private static TextField tfUsername, tfEmail, tfConfirm; 
    private static PasswordField tfPassword;
    private static DatePicker dpBirthDate;
    private static ToggleGroup genderGroup;
    private static RadioButton rdNam, rdNu;
    public void start(Stage stage){
        // vboxleft
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

        // vbox right
        Label signInTitle = new Label("Đăng Ký");
        signInTitle.setStyle("-fx-font-size: 20px; -fx-text-fill: #00927A; -fx-font-weight: bold;");

        Label lbUsername = new Label("Tên đăng nhập: ");
        lbUsername.setStyle("-fx-font-size: 12px, -fx-font-weight: bold; -fx-text-fill: #000000");
        tfUsername = new TextField();
        tfUsername.setPromptText("Nhập tên đăng nhập ...");
        tfUsername.setMaxWidth(250);
        tfUsername.setPrefHeight(30);
        VBox vbUsername = new VBox(5, lbUsername, tfUsername);
        
        Label lbEmail = new Label("Email: ");
        lbEmail.setStyle("-fx-font-size: 12px, -fx-font-weight: bold; -fx-text-fill: #000000");
        tfEmail = new TextField();
        tfEmail.setPromptText("Nhập Email ...");
        tfEmail.setMaxWidth(250);
        tfEmail.setPrefHeight(30);
        VBox vbEmail= new VBox(5, lbEmail, tfEmail);
        
        Label lbBirthDate = new Label("Ngày sinh: ");
        lbBirthDate.setStyle("-fx-font-size: 12px, -fx-font-weight: bold; -fx-text-fill: #000000");
        dpBirthDate = new DatePicker();
        dpBirthDate.setPromptText("Nhập ngày sinh ...");
        dpBirthDate.setMaxWidth(250);
        VBox vbBirthDate = new VBox(5, lbBirthDate, dpBirthDate);

        Label lbPassword = new Label("Mật khẩu: ");
        lbPassword.setStyle("-fx-font-size: 12px, -fx-font-weight: bold; -fx-text-fill: #000000");
        tfPassword = new PasswordField();
        tfPassword.setPromptText("Nhập mật khẩu ...");
        tfPassword.setMaxWidth(250);
        tfPassword.setPrefHeight(30);
        VBox vbPassword = new VBox(5, lbPassword, tfPassword);
        
        Label lbConfirm = new Label("Xác thực mật khẩu: ");
        lbConfirm.setStyle("-fx-font-size: 12px, -fx-font-weight: bold; -fx-text-fill: #000000");
        tfConfirm = new PasswordField();
        tfConfirm.setPromptText("Nhập lại mật khẩu ...");
        tfConfirm.setMaxWidth(250);
        tfConfirm.setPrefHeight(30);
        VBox vbConfirm = new VBox(5, lbConfirm, tfConfirm);
        
        Label lbGender = new Label("Chọn giới tính:");
        lbGender.setStyle("-fx-font-size: 12px, -fx-font-weight: bold; -fx-text-fill: #000000");
        genderGroup = new ToggleGroup();
        rdNam = new RadioButton("Nam");
        rdNu = new RadioButton("Nữ");
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
            LocalDate birthdate = dpBirthDate.getValue();
            String password = tfPassword.getText();
            String confirm = tfConfirm.getText();
            String gender = "";
            if (genderGroup.getSelectedToggle() != null)
            {
                RadioButton selectedRadio = (RadioButton) genderGroup.getSelectedToggle();
                gender = selectedRadio.getText();
            }   
            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirm.isEmpty() || birthdate == null || gender.isEmpty()){
                showAlert("Lỗi đăng ký","Vui lòng nhập đầy đủ các trường thông tin!");
                return;
            }
            if (checkUserName()){
                showAlert("Lỗi đăng ký", "Tên đăng nhập đã được sử dụng!");
            }
            if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
                showAlert("Lỗi đăng ký", "Vui lòng nhập email hợp lệ!");
                return;
            }
            if (birthdate.isAfter(LocalDate.now())){    
                showAlert("Lỗi đăng ký", "Vui lòng chọn ngày sinh hợp lệ!");
                return;
            }
            if (password.length() < 8){
                showAlert("Lỗi đăng ký", "Vui lòng nhập mật khẩu > 8 ký tự!");
                return;
            }
            if (!password.equals(confirm)){
                showAlert("Lỗi đăng ký","Xác thực mật khẩu không khớp mật khẩu!");
                return;
            }
            showAlert("Đăng ký tài khoản", "Đăng ký tài khoản thành công!");
            registerAccount();
            LoginView loginView = new LoginView();
            loginView.start(stage);
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
    
    private static void registerAccount(){
        try{
            Connection conn = DBConnection.getConnection();
            if(conn != null){
                String sql = "insert into taikhoan(TenDangNhap, MatKhau, Email, NgaySinh, GioiTinh, TrangThai) values(?,?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, tfUsername.getText());
                ps.setString(2, tfPassword.getText());
                ps.setString(3, tfEmail.getText());
                ps.setDate(4, java.sql.Date.valueOf(dpBirthDate.getValue()));
                RadioButton selectedRadio = (RadioButton) genderGroup.getSelectedToggle();
                ps.setString(5, selectedRadio.getText());
                ps.setInt(6, 1);
                
                int kq = ps.executeUpdate();
                if (kq > 0){
                    showAlert("Thêm thành công", "Đăng ký thành công tài khoản quản trị");
                }
                else{
                    showAlert("Thêm thất bại", "Đăng ký thất bại tài khoản quản trị");
                }
            }
        }
        catch(Exception e){
            showAlert("Lỗi", "Lỗi đăng ký");
        }
    }
    
    private static boolean checkUserName(){
        try{
            Connection conn = DBConnection.getConnection();
            if (conn != null){
                String sql = "select * from taikhoan where TenDangNhap = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, tfUsername.getText());
                if (ps.executeQuery().next()){
                    return true;
                }
            }
        }
        catch(Exception e){
            showAlert("Lỗi", "Lỗi đăng ký!");
        }
        return false;
    }
    
    private static void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.show();
    }
}
