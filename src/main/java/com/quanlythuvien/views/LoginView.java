package com.quanlythuvien.views;

import com.quanlythuvien.database.DBConnection;
import com.quanlythuvien.models.CurrentAccount;
import com.quanlythuvien.views.HomeView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class LoginView {
    private static TextField tfUsername;
    private static PasswordField tfPassword;
    public void start(Stage stage) {
        // vboxleft
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

        // vbox right
        Label signInTitle = new Label("Đăng Nhập");
        signInTitle.setStyle("-fx-font-size: 20px; -fx-text-fill: #00927A; -fx-font-weight: bold;");

        Label lbUsername = new Label("Tên đăng nhập: ");
        lbUsername.setStyle("-fx-font-size: 12px, -fx-font-weight: bold; -fx-text-fill: #000000");
        tfUsername = new TextField();
        tfUsername.setPromptText("Nhập tên đăng nhập ...");
        tfUsername.setMaxWidth(250);
        tfUsername.setPrefHeight(30);
        VBox vbUsername = new VBox(5, lbUsername, tfUsername);

        Label lbPassword = new Label("Mật khẩu: ");
        lbPassword.setStyle("-fx-font-size: 12px, -fx-font-weight: bold; -fx-text-fill: #000000");
        tfPassword = new PasswordField();
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
            if (username.isEmpty() || password.isEmpty())
            {
                showAlert("Lỗi đăng nhập", "Vui lòng nhập đầy đủ các trường thông tin!");
                return;
            }
            loginAccount(stage);
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
    
    private static void loginAccount(Stage stage){
        try{
            Connection conn = DBConnection.getConnection();
            if(conn != null){
                String sql = "select * from taikhoan where TenDangNhap = ? and MatKhau = ? and TrangThai = 1";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, tfUsername.getText());
                ps.setString(2, tfPassword.getText());
                if (ps.executeQuery().next())
                {
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()){
                        CurrentAccount currentAccount = new CurrentAccount();
                        currentAccount.setId(rs.getInt("MaTK"));
                        currentAccount.setUserName(rs.getString("TenDangNhap"));
                        currentAccount.setRole(rs.getString("VaiTro"));                     
                    }
                    showAlert("Đăng nhập tài khoản", "Đăng nhập thành công!");
                    HomeView homeView = new HomeView();
                    homeView.start(stage);
                }
                else{
                    showAlert("Đăng nhập tài khoản", "Tên đăng nhập hoặc mật khẩu không đúng!");
                }
            }
        }
        catch(Exception e){
            showAlert("Lỗi","Lỗi đăng nhập!");
        }
    }

    private static void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
