/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlythuvien.views;

import com.quanlythuvien.database.DBConnection;
import com.quanlythuvien.models.Account;
import com.quanlythuvien.utils.menuBarComponent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author admin
 */
public class ManageAccount {
    private static TableView<Account> tbvAccount;
    private static ObservableList<Account> lstAccount = FXCollections.observableArrayList();
    private static TextField tfUserName, tfPassWord, tfEmail;
    private static DatePicker dpBirthDate;
    private static ComboBox<String> cbGender, cbStatus;
    public void start (Stage stage){
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        // Thanh menu
        VBox menuBar = menuBarComponent.createMenuBar(stage);
        
        // Form tao tai khoan
        Label lbTitle = new Label("👤 Quản lý tài khoản hệ thống");
        lbTitle.setStyle("-fx-text-fill: #1D774E; -fx-font-size: 20");
        
        Label lbUserName = new Label("Tên đăng nhập:");
        tfUserName = new TextField();
        tfUserName.setPromptText("Nhập tên đăng nhập");
        tfUserName.setPrefWidth(300);
        VBox vbUserName = new VBox(3, lbUserName, tfUserName);
        
        Label lbPassWord = new Label("Mật khẩu:");
        tfPassWord = new TextField();
        tfPassWord.setPromptText("Nhập mật khẩu");
        tfPassWord.setPrefWidth(300);
        VBox vbPassword = new VBox(3, lbPassWord, tfPassWord);
        
        Label lbEmail = new Label("Email:");
        tfEmail = new TextField();
        tfEmail.setPromptText("Nhập email");
        tfEmail.setPrefWidth(300);
        VBox vbEmail = new VBox(3, lbEmail, tfEmail);
        
        Label lbBirthDate = new Label("Ngày sinh:");
        dpBirthDate = new DatePicker();
        dpBirthDate.setPromptText("Chọn ngày sinh");
        dpBirthDate.setPrefWidth(300);
        VBox vbBirthDate = new VBox(3, lbBirthDate, dpBirthDate);
        
        Label lbGender = new Label("Giới tính:");
        cbGender = new ComboBox<>();
        cbGender.getItems().addAll("Nam", "Nữ");
        cbGender.setPromptText("Chọn giới tính");
        cbGender.setPrefWidth(300);
        cbGender.setValue("Nam");
        VBox vbGender = new VBox(3, lbGender, cbGender);
        
        Label lbStatus = new Label("Trạng thái");
        cbStatus = new ComboBox<>();
        cbStatus.getItems().addAll("Đang hoạt động", "Đã khóa");
        cbStatus.setPromptText("Chọn trạng thái");
        cbStatus.setPrefWidth(300);
        cbStatus.setValue("Đang hoạt động");
        VBox vbStatus = new VBox(3, lbStatus, cbStatus);
        
        // Nut them xoa sua reset
        Button btnCreate = createActionButton("Thêm", "#1E56A0");
        Button btnUpdate = createActionButton("Cập nhật", "#2E8B57");
        Button btnDelete = createActionButton("Xóa", "#B22222");
        Button btnReset = createActionButton("Reset", "#6A5ACD");
        
        HBox buttonBox1 = new HBox(20, btnCreate, btnUpdate);
        HBox buttonBox2 = new HBox(20, btnDelete, btnReset);
        buttonBox1.setAlignment(Pos.CENTER);
        buttonBox2.setAlignment(Pos.CENTER);
        
        btnCreate.setOnAction(t -> createAccount());
        btnUpdate.setOnAction(t -> updateAccount());
        btnDelete.setOnAction(t -> deleteAccount());
        btnReset.setOnAction(t -> resetForm());
        
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getBounds();
        VBox formBox = new VBox(10, 
                                lbTitle,
                                vbUserName, vbPassword, vbEmail, vbBirthDate,
                                vbGender, vbStatus, buttonBox1, buttonBox2);
        formBox.setPadding(new Insets(20));
        formBox.setPrefWidth(300);
        formBox.setPrefHeight(bounds.getHeight());
        formBox.setStyle("-fx-background-color: #F8F8F8; -fx-border-color: #ccc");
        formBox.setMargin(buttonBox1, new Insets(30, 0, 0, 0));
        
        ScrollPane formScrollPane = new ScrollPane();
        formScrollPane.setContent(formBox);
        formScrollPane.setFitToWidth(true);
        
        // Bang hien thi danh sach tai khoan
        tbvAccount = new TableView<>();
        tbvAccount.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tbvAccount.setPlaceholder(new Label("Không có dữ liệu!"));
        
        TableColumn<Account, Integer> colAccountId = new TableColumn<>("Mã tài khoản");
        TableColumn<Account, String> colUserName = new TableColumn<>("Tên đăng nhập");
        TableColumn<Account, String> colPassWord = new TableColumn<>("Mật khẩu");
        TableColumn<Account, String> colEmail = new TableColumn<>("Email");
        TableColumn<Account, Object> colBirthDate = new TableColumn<>("Ngày sinh");
        TableColumn<Account, String> colGender = new TableColumn<>("Giới tính");
        TableColumn<Account, String> colStatus = new TableColumn<>("Trạng thái");
        
        colAccountId.setCellValueFactory((p) -> {
            Account account = p.getValue();
            int accountId = account.getId();
            return new ReadOnlyObjectWrapper<>(accountId);
        });
        
        colUserName.setCellValueFactory((p) -> {
            Account account = p.getValue();
            String userName = account.getUserName();
            return new ReadOnlyObjectWrapper<>(userName);
        });
        
        colPassWord.setCellValueFactory((p) -> {
            Account account = p.getValue();
            String passWord = account.getPassWord();
            return new ReadOnlyObjectWrapper<>(passWord);
        });
        
        colEmail.setCellValueFactory((p) -> {
            Account account = p.getValue();
            String email = account.getEmail();
            return new ReadOnlyObjectWrapper<>(email);
        });
        
        colBirthDate.setCellValueFactory((p) -> {
            Account account = p.getValue();
            Date birthDate = account.getBirthDate();
            return new ReadOnlyObjectWrapper<>(birthDate);
        });
        
        colGender.setCellValueFactory((p) -> {
            Account account = p.getValue();
            String gender = account.getGender();
            return new ReadOnlyObjectWrapper<>(gender);
        });
        
        colStatus.setCellValueFactory((p) -> {
            Account account = p.getValue();
            String status = account.getStatus() == 1 ? "Đang hoạt động" : "Đã khóa";
            return new ReadOnlyObjectWrapper<>(status);
        });
        
        tbvAccount.getColumns().addAll(colAccountId, colUserName, colPassWord, colEmail, colBirthDate, colGender, colStatus);
        loadDataAccount();
        tbvAccount.setOnMouseClicked(t -> showSelectedItem());
        
        // Thanh tim kiem
        TextField tfSearch = new TextField();
        tfSearch.setPromptText("🔍 Tìm kiếm tài khoản theo tên...");
        tfSearch.setPrefWidth(300);
        
        VBox tableBox = new VBox(10, tfSearch, tbvAccount);
        tableBox.setPadding(new Insets(20));
        HBox.setHgrow(tableBox, Priority.ALWAYS);
        
        HBox mainContent = new HBox(formBox, tableBox);
        BorderPane masterLayout = new BorderPane(mainContent, null, null, null, menuBar);
        
        Scene scene = new Scene(masterLayout, bounds.getWidth(), bounds.getHeight() - 30);
        stage.setScene(scene);
        stage.show();
    }
    
    private Button createActionButton(String text, String color){
        Button btn = new Button(text);
        btn.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white");
        btn.setPrefWidth(100);
        return btn;
    }
    
    public static void loadDataAccount(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Load dữ liệu account");
        try{
            Connection conn = DBConnection.getConnection();
            if (conn != null){
                String sql = "Select * from taikhoan";
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                
                lstAccount.clear();
                while(rs.next()){
                    lstAccount.add(new Account(
                            rs.getInt("MaTK"),
                            rs.getString("TenDangNhap"),
                            rs.getString("MatKhau"),
                            rs.getString("Email"),
                            rs.getDate("NgaySinh"),
                            rs.getString("GioiTinh"),
                            rs.getInt("TrangThai")
                    ));
                }
                tbvAccount.setItems(lstAccount);
            }
        }
        catch(Exception e){
            alert.setContentText("Load dữ liệu lỗi!");
            alert.show();
        }
    }
    
    private static void resetForm(){
        tfUserName.clear();
        tfPassWord.clear();
        tfEmail.clear();
        dpBirthDate.setValue(null);
        cbGender.setValue("Nam");
        cbStatus.setValue("Đang hoạt động");
    }
    
    private static void showSelectedItem(){
        Account selectedAccount = tbvAccount.getSelectionModel().getSelectedItem();
        tfUserName.setText(selectedAccount.getUserName());
        tfPassWord.setText(selectedAccount.getPassWord());
        tfEmail.setText(selectedAccount.getEmail());
        dpBirthDate.setValue(new java.sql.Date(selectedAccount.getBirthDate().getTime()).toLocalDate());
        cbGender.setValue(selectedAccount.getGender());
        cbStatus.setValue(selectedAccount.getStatus() == 1 ? "Đang hoạt động" : "Đã khóa" );
    }
    
    private static void createAccount(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thêm tài khoản mới");
        try{
            Connection conn = DBConnection.getConnection();
            if (conn != null){
                String sql = "insert into taikhoan(TenDangNhap, MatKhau, Email, NgaySinh, GioiTinh, TrangThai) values(?,?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, tfUserName.getText());
                ps.setString(2, tfPassWord.getText());
                ps.setString(3, tfEmail.getText());
                ps.setDate(4, java.sql.Date.valueOf(dpBirthDate.getValue()));
                ps.setString(5, cbGender.getValue());
                ps.setInt(6, cbStatus.getValue().equals("Đang hoạt động") ? 1 : 0);
                int kq = ps.executeUpdate();
                if (kq > 0){
                    alert.setContentText("Thêm thành công!");
                    alert.show();
                    loadDataAccount();
                }
                else{
                    alert.setContentText("Thêm thất bại!");
                    alert.show();
                }
            }
        }
        catch(Exception e){
            alert.setContentText("Lỗi thêm!");
            alert.show();
        }
    }
    
    private static void updateAccount(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cập nhật tài khoản");
        try{          
            Account selectedAccount = tbvAccount.getSelectionModel().getSelectedItem();
            Connection conn = DBConnection.getConnection();
            if (conn != null){
                String sql = "update taikhoan set TenDangNhap = ?, MatKhau = ?, Email = ?, NgaySinh = ?, GioiTinh = ?, TrangThai = ? where MaTK = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, tfUserName.getText());
                ps.setString(2, tfPassWord.getText());
                ps.setString(3, tfEmail.getText());
                ps.setDate(4, java.sql.Date.valueOf(dpBirthDate.getValue()));
                ps.setString(5, cbGender.getValue());
                ps.setInt(6, cbStatus.getValue().equals("Đang hoạt động") ? 1 : 0);
                ps.setInt(7, selectedAccount.getId());
                int kq = ps.executeUpdate();
                if (kq > 0){
                    alert.setContentText("Cập nhật thành công!");
                    alert.show();
                    loadDataAccount();
                }
                else{
                    alert.setContentText("Cập nhật thất bại!");
                    alert.show();
                }
            }
        }
        catch(Exception e){
            alert.setContentText("Lỗi cập nhật!");
            alert.show();
        }
    }
    
    private static void deleteAccount(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Xóa tài khoản");
        try{            
            Account selectedAccount = tbvAccount.getSelectionModel().getSelectedItem();
            Connection conn = DBConnection.getConnection();
            if (conn != null){
                String sql = "delete from taikhoan where MaTK = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, selectedAccount.getId());
                int kq = ps.executeUpdate();
                if (kq > 0){
                    alert.setContentText("Xóa thành công!");
                    alert.show();
                    loadDataAccount();
                }
                else{
                    alert.setContentText("Xóa thất bại!");
                    alert.show();
                }
            }
        }
        catch(Exception e){
            alert.setContentText("Lỗi xóa");
            alert.show();
        }
    }
}
