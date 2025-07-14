package com.quanlythuvien.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.quanlythuvien.utils.menuBarComponent;

public class HomeView {
    public void start(Stage stage) {
        // Thanh menu
        VBox menuBar = menuBarComponent.createMenuBar(stage);

        // Thẻ thống kê
        VBox card1 = createHeaderCard("📚", "Tổng số sách", "1234");
        VBox card2 = createHeaderCard("👥", "Độc giả đã đăng ký", "567");
        VBox card3 = createHeaderCard("📅", "Lượt mượn hôm nay", "66");
        VBox card4 = createHeaderCard("📄", "Phiếu mượn đang hoạt động", "120");
        VBox card5 = createHeaderCard("📕", "Sách đang mượn", "320");
        VBox card6 = createHeaderCard("⏰", "Sách quá hạn", "15");

        HBox cardRow1 = new HBox(15, card1, card2, card3);
        HBox cardRow2 = new HBox(15, card4, card5, card6);
        cardRow1.setAlignment(Pos.CENTER);
        cardRow2.setAlignment(Pos.CENTER);

        VBox cardsSection = new VBox(15, cardRow1, cardRow2);
        cardsSection.setPadding(new Insets(30, 10, 20, 10));

        // Lời chào và thời gian
        Label welcome = new Label("👋 Xin chào, Thủ thư Nguyễn Duy Thắng");
        welcome.setFont(Font.font(16));
        welcome.setTextFill(Color.web("#2C3E50"));
        Label time = new Label("📆 Hôm nay: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        time.setFont(Font.font(14));
        time.setTextFill(Color.GRAY);
        VBox greeting = new VBox(5, welcome, time);
        greeting.setAlignment(Pos.CENTER_LEFT);
        greeting.setPadding(new Insets(0, 0, 10, 30));

        VBox mainContent = new VBox(greeting, cardsSection);
        mainContent.setStyle("-fx-background-color: white;");
        mainContent.setPadding(new Insets(20));

        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color:transparent;");

        // Layout chinh
        BorderPane rootLayout = new BorderPane();
        rootLayout.setLeft(menuBar);
        rootLayout.setCenter(scrollPane);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        Scene scene = new Scene(rootLayout, bounds.getWidth(), bounds.getHeight() - 30);
        stage.setScene(scene);
        stage.setTitle("Trang chủ - Hệ thống Quản lý Thư viện");
        stage.show();
    }

    private VBox createHeaderCard(String icon, String label, String value) {
        Label iconLabel = new Label(icon);
        iconLabel.setFont(Font.font(30));
        Label title = new Label(label);
        title.setFont(Font.font(13));
        title.setTextFill(Color.WHITE);
        Label quantity = new Label(value);
        quantity.setFont(Font.font(18));
        quantity.setTextFill(Color.WHITE);
        VBox card = new VBox(5, iconLabel, title, quantity);
        card.setAlignment(Pos.CENTER);
        card.setPrefSize(180, 80);
        card.setStyle("-fx-background-color: #00927A;-fx-border-radius: 10; -fx-background-radius: 10; -fx-border-color: #E6F4EC;");
        card.setPadding(new Insets(10));
        return card;
    }
}
