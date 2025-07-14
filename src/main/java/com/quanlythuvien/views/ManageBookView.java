/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlythuvien.views;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import com.quanlythuvien.models.Book;
import com.quanlythuvien.utils.menuBarComponent;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author admin
 */
public class ManageBookView {   
    public void start(Stage stage) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        // Thanh menu
        VBox menuBar = menuBarComponent.createMenuBar(stage);

        // Form them sach
        Label lbTitle = new Label("📖 Quản Lý Sách");
        lbTitle.setFont(Font.font(20));
        lbTitle.setStyle("-fx-text-fill: #1D774E;");

        Label lbBookName = new Label("Tên sách:");
        TextField tfBookName = new TextField();
        tfBookName.setPromptText("Nhập tên sách");
        tfBookName.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbBookName = new VBox(3, lbBookName, tfBookName);

        Label lbAuthor = new Label("Tác giả:");
        TextField tfAuthor = new TextField();
        tfAuthor.setPromptText("Nhập tác giả");
        tfAuthor.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbAuthor = new VBox(3, lbAuthor, tfAuthor);
        
        Label lbPublisher = new Label("Nhà xuất bản:");
        TextField tfPublisher = new TextField();
        tfPublisher.setPromptText("Nhập nhà xuất bản");
        tfPublisher.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbPublisher = new VBox(3, lbPublisher, tfPublisher);


        Label lbCategory = new Label("Thể loại:");
        ComboBox<String> cbCategory = new ComboBox<>();
        cbCategory.getItems().addAll("Tiểu thuyết", "Giáo trình", "Khoa học", "Truyện tranh");
        cbCategory.setPromptText("Chọn thể loại");
        cbCategory.setPrefWidth(300);
        cbCategory.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbCategory = new VBox(3, lbCategory, cbCategory);

        Label lbPublicDate = new Label("Ngày xuất bản");
        DatePicker dpPublicDate = new DatePicker();
        dpPublicDate.setPromptText("Chon ngày xuất bản");
        dpPublicDate.setPrefWidth(300);
        dpPublicDate.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbPublicDate = new VBox(3, lbPublicDate, dpPublicDate);
        
        Label lbQuantity = new Label("Số lượng");
        TextField tfQuantity = new TextField();
        tfQuantity.setPromptText("Nhập số lượng");
        tfQuantity.setPrefWidth(300);
        tfQuantity.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbQuantity = new VBox(3, lbQuantity, tfQuantity);

        Label lbLanguage = new Label("Ngôn ngữ:");
        ComboBox<String> cbLanguage = new ComboBox<>();
        cbLanguage.getItems().addAll("Tiếng Việt", "Tiếng Anh", "Tiếng Nga", "Tiếng Pháp", "Tiếng Trung");
        cbLanguage.setPromptText("Chọn ngôn ngữ");
        cbLanguage.setPrefWidth(300);
        cbLanguage.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbLanguage = new VBox(3, lbLanguage, cbLanguage);
        
        Label lbState = new Label("Tình trạng:");
        ComboBox<String> cbState = new ComboBox<>();
        cbState.getItems().addAll("Còn hàng", "Hết hàng");
        cbState.setPromptText("Chọn tình trạng");
        cbState.setPrefWidth(300);
        cbState.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbState = new VBox(3, lbState, cbState);
        
        Label lbLocation = new Label("Vị trí lưu trữ:");
        TextField tfLocation = new TextField();
        tfLocation.setPromptText("Nhập vị trí lưu trữ");
        tfLocation.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbLocation = new VBox(3, lbLocation, tfLocation);

        // Nut them xoa sua reset
        HBox buttonBox1 = new HBox(20,
                createActionButton("Thêm", "#1E56A0"),
                createActionButton("Cập nhật", "#2E8B57")             
        );
        HBox buttonBox2 = new HBox(20, 
                createActionButton("Xoá", "#B22222"),
                createActionButton("Reset", "#6A5ACD")
        );
        buttonBox1.setAlignment(Pos.CENTER);
        buttonBox2.setAlignment(Pos.CENTER);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        VBox formBox = new VBox(10,
                lbTitle,
                vbBookName, vbAuthor, vbPublisher, vbCategory, vbPublicDate, vbQuantity, vbLanguage, vbState, vbLocation,
                buttonBox1, buttonBox2
        );
        formBox.setPadding(new Insets(20));
        formBox.setPrefWidth(300);
        formBox.setPrefHeight(bounds.getHeight());
        formBox.setStyle("-fx-background-color: #F8F8F8; -fx-border-color: #ccc;");
        formBox.setMargin(buttonBox1, new Insets(30, 0, 0, 0));
        
        ScrollPane formScrollPane = new ScrollPane();
        formScrollPane.setContent(formBox);
        formScrollPane.setFitToWidth(true);
        
        // Bang hien thi danh sach sach
        TableView<Book> tbvBook = new TableView<>();
        tbvBook.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tbvBook.setPlaceholder(new Label("Không có dữ liệu!"));

        TableColumn<Book, Integer> colId = new TableColumn<>("Mã sách");
        TableColumn<Book, String> colName = new TableColumn<>("Tên sách");
        TableColumn<Book, String> colAuthor = new TableColumn<>("Tác giả");
        TableColumn<Book, String> colCategory = new TableColumn<>("Thể loại");
        TableColumn<Book, Date> colPublicDate = new TableColumn<>("Ngày");
        TableColumn<Book, Integer> colQuantity = new TableColumn<>("Số lượng");
        TableColumn<Book, String> colLanguage = new TableColumn<>("Ngôn ngữ");
        TableColumn<Book, String> colState = new TableColumn<>("Trạng thái");
        TableColumn<Book, String> colLocation = new TableColumn<>("Vị trí");
        
        tbvBook.getColumns().addAll(colId, colName, colAuthor, colCategory, colPublicDate, colQuantity, colLanguage, colState, colLocation);
        
        

        // Thanh tim kiem
        TextField tfSearch = new TextField();
        tfSearch.setPromptText("🔍 Tìm kiếm sách theo tên...");
        tfSearch.setPrefWidth(300);

        VBox tableBox = new VBox(10, tfSearch, tbvBook);
        tableBox.setPadding(new Insets(20));

        // Layout chinh
        HBox mainContent = new HBox(formScrollPane, tableBox);
        BorderPane masterLayout = new BorderPane(mainContent, null, null, null, menuBar);

        Scene scene = new Scene(masterLayout, bounds.getWidth(), bounds.getHeight()-30);
        stage.setTitle("Quản lý sách");
        stage.setScene(scene);
        stage.show();
    }

    private Button createActionButton(String text, String color) {
        Button btn = new Button(text);
        btn.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white;");
        btn.setPrefWidth(100);
        return btn;
    }
}