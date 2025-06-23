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
import javafx.beans.property.SimpleObjectProperty;
import com.quanlythuvien.utils.menuBarComponent;
import java.text.SimpleDateFormat;

/**
 *
 * @author admin
 */
public class ManageBookView {
    public void start(Stage stage) {
        // Thanh menu
        VBox menuBar = menuBarComponent.createMenuBar(stage);

        // Form nhap thong tin sach
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
        Spinner<Integer> spQuantity = new Spinner<>(1, 1000, 10);
        spQuantity.setPromptText("Nhập số lượng");
        spQuantity.setPrefWidth(300);
        spQuantity.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbQuantity = new VBox(3, lbQuantity, spQuantity);

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

        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        VBox formBox = new VBox(10,
                lbTitle,
                vbBookName, vbAuthor, vbPublisher, vbCategory, vbPublicDate, vbQuantity, vbLanguage, vbState, vbLocation,
                buttonBox1, buttonBox2
        );
        formBox.setPadding(new Insets(20));
        formBox.setPrefWidth(300);
        formBox.setPrefHeight(screenSize.getHeight());
        formBox.setStyle("-fx-background-color: #F8F8F8; -fx-border-color: #ccc;");
        formBox.setMargin(buttonBox1, new Insets(30, 0, 0, 0));
        
        ScrollPane formScrollPane = new ScrollPane();
        formScrollPane.setContent(formBox);
        formScrollPane.setFitToWidth(true);
        
        // Bang hien thi danh sach sach
        TableView<Book> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Book, Integer> colId = new TableColumn<>("Mã sách");
        TableColumn<Book, String> colName = new TableColumn<>("Tên sách");
        TableColumn<Book, String> colAuthor = new TableColumn<>("Tác giả");
        TableColumn<Book, String> colCategory = new TableColumn<>("Thể loại");
        TableColumn<Book, Date> colPublicDate = new TableColumn<>("Ngày");
        TableColumn<Book, Integer> colQuantity = new TableColumn<>("Số lượng");
        TableColumn<Book, String> colLanguage = new TableColumn<>("Ngôn ngữ");
        TableColumn<Book, String> colState = new TableColumn<>("Trạng thái");
        TableColumn<Book, String> colLocation = new TableColumn<>("Vị trí");
        
        hoverColumn(colAuthor);
        hoverColumn(colCategory);
        hoverColumn(colPublicDate);
        hoverColumn(colQuantity);
        hoverColumn(colName);

        colId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        colName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        colAuthor.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        colCategory.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        colPublicDate.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPublicDate())); 
        formatDateColumn(colPublicDate, "dd/MM/yyyy");
        colQuantity.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        colLanguage.setCellValueFactory(cellData -> cellData.getValue().languageProperty());
        colState.setCellValueFactory(cellData -> cellData.getValue().stateProperty());
        colLocation.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        
     

        table.getColumns().addAll(colId, colName, colAuthor, colCategory, colPublicDate, colQuantity, colLanguage, colState, colLocation);
        ObservableList<Book> books = FXCollections.observableArrayList(
            new Book(1, "Lập trình Java", "Nguyễn Văn A", "Giáo trình", new Date(), 10, "Tiếng Việt", "Còn hàng", "Kệ A1"),
            new Book(2, "Doraemon", "Fujiko F. Fujio", "Truyện tranh", new Date(), 20, "Tiếng Việt", "Còn hàng", "Kệ B2"),
            new Book(3, "Clean Code", "Robert C. Martin", "Khoa học", new Date(), 5, "Tiếng Anh", "Hết hàng", "Kệ C3"),
            new Book(4, "Toán Cao Cấp", "Trần Văn B", "Giáo trình", new Date(), 12, "Tiếng Việt", "Còn hàng", "Kệ D4"),
            new Book(5, "Sherlock Holmes", "Arthur Conan Doyle", "Tiểu thuyết", new Date(), 8, "Tiếng Anh", "Còn hàng", "Kệ E5")
        );

        table.setItems(books);

        // Thanh tim kiem
        TextField tfSearch = new TextField();
        tfSearch.setPromptText("🔍 Tìm kiếm sách theo tên...");
        tfSearch.setPrefWidth(300);

        VBox tableBox = new VBox(10, tfSearch, table);
        tableBox.setPadding(new Insets(20));
        HBox.setHgrow(tableBox, Priority.ALWAYS);

        // Layout chinh
        HBox mainContent = new HBox(formScrollPane, tableBox);
        BorderPane masterLayout = new BorderPane(mainContent, null, null, null, menuBar);

        Scene scene = new Scene(masterLayout, screenSize.getWidth(), screenSize.getHeight()-30);
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
    
    private <T> void hoverColumn(TableColumn<Book, T> column) {
        column.setCellFactory(col -> new TableCell<Book, T>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setTooltip(null);
                } else {
                    String text = item.toString();
                    setText(text);
                    setTooltip(new Tooltip(text));
                }
            }
        });
    }
    
     public static <S> void formatDateColumn(TableColumn<S, Date> column, String pattern) {
        column.setCellFactory(col -> new TableCell<S, Date>() {
            private final SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            @Override
            protected void updateItem(Date item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : sdf.format(item));
            }
        });
    }
}