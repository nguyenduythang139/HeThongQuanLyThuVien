package com.quanlythuvien.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import com.quanlythuvien.utils.menuBarComponent;

public class HomeView {
    public void start(Stage stage) {
        // Thanh menu
        VBox menuBar = menuBarComponent.createMenuBar(stage);

        // Thanh header
        HBox headerCards = new HBox(15);
        headerCards.setPadding(new Insets(30));
        headerCards.setAlignment(Pos.CENTER);
        VBox card1 = headerCard("📚", "Tổng số sách", "1234");
        VBox card2 = headerCard("👤", "Độc giả đã đăng ký", "567");
        VBox card3 = headerCard("📅", "Lượt mượn hôm nay", "66");
        headerCards.getChildren().addAll(card1, card2, card3);

        // Bieu do
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<String, Number> borrowChart = new LineChart<>(xAxis, yAxis);
        borrowChart.setTitle("Lượt mượn sách trong tuần");
        XYChart.Series<String, Number> data1 = new XYChart.Series<>();
        data1.getData().add(new XYChart.Data<>("T2", 30));
        data1.getData().add(new XYChart.Data<>("T3", 45));
        data1.getData().add(new XYChart.Data<>("T4", 38));
        data1.getData().add(new XYChart.Data<>("T5", 20));
        data1.getData().add(new XYChart.Data<>("T6", 66));
        borrowChart.getData().add(data1);
        borrowChart.setPrefWidth(400);

        CategoryAxis x2 = new CategoryAxis();
        NumberAxis y2 = new NumberAxis();
        BarChart<String, Number> bookChart = new BarChart<>(x2, y2);
        bookChart.setTitle("Số sách đang được mượn");
        XYChart.Series<String, Number> data2 = new XYChart.Series<>();
        data2.getData().add(new XYChart.Data<>("CNTT", 90));
        data2.getData().add(new XYChart.Data<>("Kinh tế", 65));
        data2.getData().add(new XYChart.Data<>("Ngoại ngữ", 40));
        bookChart.getData().add(data2);
        bookChart.setPrefWidth(400);

        HBox chartsBox = new HBox(20, borrowChart, bookChart);
        chartsBox.setPadding(new Insets(20));
        chartsBox.setAlignment(Pos.CENTER);

        VBox mainContent = new VBox(headerCards, chartsBox);
        mainContent.setStyle("-fx-background-color: white;");

        // Layout chinh
        BorderPane masterLayout = new BorderPane(mainContent, null, null, null, menuBar);
        
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(masterLayout, screenSize.getWidth(), screenSize.getHeight()-30);
        stage.setScene(scene);
        stage.setTitle("Trang chủ - Hệ thống Quản lý Thư viện");
        stage.show();
    }

    private VBox headerCard(String icon, String label, String value) {
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
        card.setPrefSize(220, 100);
        card.setStyle("-fx-background-color: linear-gradient(to right, #1D774E, #298D5F); -fx-border-radius: 10; -fx-background-radius: 10; -fx-border: 1px; -fx-border-color: #E6F4EC;");
        card.setPadding(new Insets(10));
        return card;
    }
}
