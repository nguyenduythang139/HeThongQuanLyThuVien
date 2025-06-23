/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlythuvien.utils;

import com.quanlythuvien.views.HomeView;
import com.quanlythuvien.views.ManageBookView;
import com.quanlythuvien.views.ManageBorrowReturnView;
import com.quanlythuvien.views.ManageReaderView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author admin
 */
public class menuBarComponent {
    public static VBox createMenuBar(Stage stage) {
        VBox menuBar = new VBox(20);
        menuBar.setPadding(new Insets(20));
        menuBar.setStyle("-fx-background-color: linear-gradient(to bottom, #0E6C42, #1D774E)");
        menuBar.setPrefWidth(200);
        
        Label logo = new Label("📚");
        logo.setFont(Font.font(40));
        Label nameSystem = new Label("Hệ thống quản lý\nthư viện TTP");
        nameSystem.setTextFill(Color.WHITE);
        nameSystem.setFont(Font.font(14));
        HBox hbLogo = new HBox(10, logo, nameSystem);
        hbLogo.setAlignment(Pos.CENTER);
        
        HBox devider = new HBox();
        devider.setPrefHeight(1);
        devider.setStyle("-fx-background-color: white");
        
        Button btnHome = menuBarButton("🏠  Trang chủ");
        btnHome.setOnAction((t) -> {
            HomeView homeView = new HomeView();
            homeView.start(stage);
        });
        Button btnManageBook = menuBarButton("📖  Quản lý sách");
        btnManageBook.setOnAction((t) -> {
            ManageBookView manageBookView = new ManageBookView();
            manageBookView.start(stage);
        });
        Button btnManageReader = menuBarButton("👥  Quản lý độc giả");
        btnManageReader.setOnAction((t) -> {
            ManageReaderView manageReaderView = new ManageReaderView();
            manageReaderView.start(stage);
        });
        Button btnManageBorrowReturn = menuBarButton("🔄  Quản lý mượn - trả");
        btnManageBorrowReturn.setOnAction((t) -> {
            ManageBorrowReturnView manageBorrowReturnView = new ManageBorrowReturnView();
            manageBorrowReturnView.start(stage);
        });
        Button btnStatistic = menuBarButton("📊  Thống kê");
        Button btnLogout = new Button("🚪   Đăng xuất");
        
        btnLogout.setPrefWidth(180);
        btnLogout.setFont(Font.font(13));
        btnLogout.setStyle("-fx-background-color: #E74C3C; -fx-text-fill: white; -fx-alignment: LEFT");

        menuBar.getChildren().addAll(hbLogo, devider, btnHome, btnManageBook, btnManageReader, btnManageBorrowReturn, btnStatistic, btnLogout);
        menuBar.setAlignment(Pos.TOP_CENTER);
        return menuBar;
    }

    private static Button menuBarButton(String text) {
        Button btn = new Button(text);
        btn.setPrefWidth(180);
        btn.setFont(Font.font(13));
        btn.setTextAlignment(TextAlignment.LEFT);
        btn.setStyle("-fx-background-color: #1D774E; -fx-text-fill: white; -fx-background-radius: 8; -fx-alignment: LEFT");
        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: #2E8B57; -fx-text-fill: white; -fx-background-radius: 8; -fx-alignment: LEFT"));
        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: #1D774E; -fx-text-fill: white; -fx-background-radius: 8; -fx-alignment: LEFT"));
        return btn;
    }
}
