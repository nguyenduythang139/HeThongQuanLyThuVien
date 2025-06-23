/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlythuvien.views;
import com.quanlythuvien.models.Book;
import com.quanlythuvien.models.Reader;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import com.quanlythuvien.utils.menuBarComponent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 *
 * @author admin
 */
public class ManageBorrowReturnView {
     public void start(Stage stage) {
        // Thanh menu
        VBox menuBar = menuBarComponent.createMenuBar(stage);

        // Form nhap thong tin sach
        Label lbTitle = new Label("🔄 Quản Lý Phiếu Mượn - Trả");
        lbTitle.setFont(Font.font(20));
        lbTitle.setStyle("-fx-text-fill: #1D774E;");

        Label lbReaderId = new Label("Mã độc giả:");
        ComboBox<Integer> cbReaderId = new ComboBox<>();
        cbReaderId.setPromptText("Chọn mã độc giả");
        cbReaderId.setPrefWidth(300);
        cbReaderId.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbReaderId = new VBox(3, lbReaderId, cbReaderId);
        
        Label lbBorrowDate = new Label("Ngày mượn:");
        DatePicker dpBorrowDate = new DatePicker(LocalDate.now());
        dpBorrowDate.setPrefWidth(300);
        dpBorrowDate.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbBorrowDate = new VBox(3, lbBorrowDate, dpBorrowDate);
        
        Label lbReturnDate = new Label("Hạn trả:");
        DatePicker dpReturnDate = new DatePicker();
        dpReturnDate.setPromptText("Chọn hạn trả");
        dpReturnDate.setPrefWidth(300);
        dpReturnDate.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbReturnDate = new VBox(3, lbReturnDate, dpReturnDate);

        Label lbTakeNote = new Label("Ghi chú:");
        TextField tfTakeNote = new TextField();
        tfTakeNote.setPromptText("Nhập ghi chú");
        tfTakeNote.setPrefWidth(300);
        tfTakeNote.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbTakeNote = new VBox(3, lbTakeNote, tfTakeNote);
        
        Label lbTableSelectBooks = new Label("Bảng chọn sách:");
        TableView<Book> tableSelectBooks = new TableView();
        tableSelectBooks.setPrefHeight(200);
        tableSelectBooks.setColumnResizePolicy(tableSelectBooks.CONSTRAINED_RESIZE_POLICY);
        tableSelectBooks.setEditable(true);
        TableColumn<Book, Boolean> colCheck = new TableColumn<>("Mượn");
        colCheck.setCellValueFactory(new PropertyValueFactory<>("Chọn"));
        colCheck.setCellFactory(CheckBoxTableCell.forTableColumn(colCheck));
        VBox vbTableSelectBooks = new VBox(3, lbTableSelectBooks, tableSelectBooks);
        
        TableColumn<Book, Integer> colBookId = new TableColumn<>("Mã sách");
        colBookId.setStyle("-fx-alignment: center");
        TableColumn<Book, String> colBookName = new TableColumn<>("Tên sách");
        
        colBookId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        colBookName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        
        tableSelectBooks.getColumns().addAll(colCheck, colBookId, colBookName);
        ObservableList<Book> books = FXCollections.observableArrayList(
                new Book(1, "Lập trình Java"),
                new Book(2, "Doraemon"),
                new Book(3, "Clean Code"),
                new Book(4, "Toán Cao Cấp"),
                new Book(5, "Sherlock Holmes")
        );
        
        tableSelectBooks.setItems(books);

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
                vbReaderId, vbBorrowDate, vbReturnDate, vbTakeNote, vbTableSelectBooks,
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
        /*TableView<Reader> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Reader, Integer> colId = new TableColumn<>("Mã độc giả");
        TableColumn<Reader, String> colName = new TableColumn<>("Họ tên");
        TableColumn<Reader, String> colGender = new TableColumn<>("Giới tính");
        TableColumn<Reader, Date> colBirthDate = new TableColumn<>("Ngày sinh");
        TableColumn<Reader, String> colPhoneNumber = new TableColumn<>("Số điện thoại");
        TableColumn<Reader, String> colEmail = new TableColumn<>("Email");
        TableColumn<Reader, String> colAddress = new TableColumn<>("Địa chỉ");
        TableColumn<Reader, String> colStatus = new TableColumn<>("Trạng thái");
        
        hoverColumn(colName);

        colId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        colName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        colGender.setCellValueFactory(cellData -> cellData.getValue().genderProperty());
        colBirthDate.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getBirthDate()));
        formatDateColumn(colBirthDate, "dd/MM/yyyy");
        colPhoneNumber.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
        colEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        colAddress.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        colStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());      

        table.getColumns().addAll(colId, colName, colGender, colBirthDate, colPhoneNumber, colEmail, colAddress, colStatus);
        ObservableList<Reader> readers = FXCollections.observableArrayList(
            new Reader(1, "Nguyễn Văn A", "Nam", new Date(90, 4, 15), "0912345678", "a.nguyen@gmail.com", "Hà Nội", "Đang hoạt động"),
            new Reader(2, "Trần Thị B", "Nữ", new Date(95, 7, 22), "0987654321", "b.tran@yahoo.com", "TP. HCM", "Bị khóa"),
            new Reader(3, "Lê Văn C", "Nam", new Date(88, 1, 5), "0932123456", "c.le@outlook.com", "Đà Nẵng", "Đang hoạt động"),
            new Reader(4, "Phạm Thị D", "Nữ", new Date(99, 10, 10), "0945678910", "d.pham@gmail.com", "Cần Thơ", "Đang hoạt động"),
            new Reader(5, "Hoàng Văn E", "Nam", new Date(85, 3, 30), "0909988776", "e.hoang@gmail.com", "Hải Phòng", "Bị khóa")
        );

        table.setItems(readers);*/

        // Thanh tim kiem
        TextField tfSearch = new TextField();
        tfSearch.setPromptText("🔍 Tìm kiếm sách theo tên...");
        tfSearch.setPrefWidth(300);

        VBox tableBox = new VBox(10, tfSearch);
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
    
    private <T> void hoverColumn(TableColumn<Reader, T> column) {
        column.setCellFactory(col -> new TableCell<Reader, T>() {
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
