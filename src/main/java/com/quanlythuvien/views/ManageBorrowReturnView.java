package com.quanlythuvien.views;

import com.quanlythuvien.models.Book;
import com.quanlythuvien.models.BorrowTicket;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import com.quanlythuvien.utils.menuBarComponent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javafx.beans.property.*;

public class ManageBorrowReturnView {
    public void start(Stage stage) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        // Thanh menu
        VBox menuBar = menuBarComponent.createMenuBar(stage);

        // Form tao phieu muon
        Label lbTitle = new Label("🔄 Quản Lý Phiếu Mượn - Trả");
        lbTitle.setFont(Font.font(20));
        lbTitle.setStyle("-fx-text-fill: #1D774E;");

        Label lbReaderId = new Label("Mã độc giả:");
        ComboBox<Integer> cbReaderId = new ComboBox<>();
        cbReaderId.setPromptText("Chọn mã độc giả");
        cbReaderId.setPrefWidth(300);
        VBox vbReaderId = new VBox(3, lbReaderId, cbReaderId);

        Label lbBorrowDate = new Label("Ngày mượn:");
        DatePicker dpBorrowDate = new DatePicker(LocalDate.now());
        dpBorrowDate.setPrefWidth(300);
        VBox vbBorrowDate = new VBox(3, lbBorrowDate, dpBorrowDate);

        Label lbReturnDate = new Label("Hạn trả:");
        DatePicker dpReturnDate = new DatePicker();
        dpReturnDate.setPromptText("Chọn hạn trả");
        dpReturnDate.setPrefWidth(300);
        VBox vbReturnDate = new VBox(3, lbReturnDate, dpReturnDate);

        Label lbTakeNote = new Label("Ghi chú:");
        TextArea taTakeNote = new TextArea();
        taTakeNote.setPromptText("Nhập ghi chú");
        taTakeNote.setPrefSize(300, 50);
        VBox vbTakeNote = new VBox(3, lbTakeNote, taTakeNote);

        Label lbTableSelectBooks = new Label("Bảng chọn sách:");
        TableView<Book> tableSelectBooks = new TableView<>();
        tableSelectBooks.setPrefHeight(200);
        tableSelectBooks.setEditable(true);
        tableSelectBooks.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<Book, Integer> colBookId = new TableColumn<>("Mã sách");
        TableColumn<Book, String> colBookName = new TableColumn<>("Tên sách");
        TableColumn<Book, Boolean> colCheck = new TableColumn<>("Chọn");
        tableSelectBooks.getColumns().addAll(colCheck, colBookId, colBookName);
        VBox vbTableSelectBooks = new VBox(3, lbTableSelectBooks, tableSelectBooks);

        Button btnCreate = createActionButton("Tạo Phiếu", "#000000");
        Button btnReturn = createActionButton("Trả Sách", "#2E8B57");
        Button btnReset = createActionButton("Reset", "#6A5ACD");

        HBox actionButtons = new HBox(20, btnCreate, btnReturn, btnReset);
        actionButtons.setAlignment(Pos.CENTER);

        VBox formBox = new VBox(10, lbTitle, vbReaderId, vbBorrowDate, vbReturnDate, vbTakeNote, vbTableSelectBooks, actionButtons);
        formBox.setPadding(new Insets(20));
        formBox.setPrefWidth(300);
        formBox.setStyle("-fx-background-color: #F8F8F8; -fx-border-color: #ccc;");
        ScrollPane formScrollPane = new ScrollPane(formBox);
        formScrollPane.setFitToWidth(true);

        // Danh sach phieu muon
        TableView<BorrowTicket> tbvTicket = new TableView<>();
        tbvTicket.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tbvTicket.setPlaceholder(new Label("Không có dữ liệu!"));
        
        TableColumn<BorrowTicket, Integer> colTicketId = new TableColumn<>("Mã phiếu");
        TableColumn<BorrowTicket, Integer> colReaderId = new TableColumn<>("Mã độc giả");
        TableColumn<BorrowTicket, String> colBorrowDate = new TableColumn<>("Ngày mượn");
        TableColumn<BorrowTicket, String> colReturnDate = new TableColumn<>("Hạn trả");
        TableColumn<BorrowTicket, String> colBooks = new TableColumn<>("Danh sách sách");
        TableColumn<BorrowTicket, String> colNote = new TableColumn<>("Ghi chú");
        TableColumn<BorrowTicket, String> colActualReturn = new TableColumn<>("Ngày trả thực tế");
        TableColumn<BorrowTicket, String> colStatus = new TableColumn<>("Trạng thái");
        
        colTicketId.setCellValueFactory((p) -> {
            BorrowTicket borrowTicket = p.getValue();
            int ticketId = borrowTicket.getTicketId();
            return new ReadOnlyObjectWrapper(ticketId);
        });
        
        colReaderId.setCellValueFactory((p) -> {
            BorrowTicket borrowTicket = p.getValue();
            int readerId = borrowTicket.getReaderId();
            return new ReadOnlyObjectWrapper(readerId);
        });
        
        colBorrowDate.setCellValueFactory((p) -> {
            BorrowTicket borrowTicket = p.getValue();
            Date borrowDate = borrowTicket.getBorrowDate();
            return new ReadOnlyObjectWrapper(borrowDate);
        });
        
        colReturnDate.setCellValueFactory((p) -> {
            BorrowTicket borrowTicket = p.getValue();
            Date returnDate = borrowTicket.getReturnDate();
            return new ReadOnlyObjectWrapper(returnDate);
        });
        
        colBooks.setCellValueFactory((p) -> {
            BorrowTicket borrowTicket = p.getValue();
            String books = borrowTicket.getBookName();
            return new ReadOnlyObjectWrapper(books);
        });
        
        colActualReturn.setCellValueFactory((p) -> {
            BorrowTicket borrowTicket = p.getValue();
            Date actualReturnDate = borrowTicket.getActualReturnDate();
            return new ReadOnlyObjectWrapper(actualReturnDate);
        });
        
        colStatus.setCellValueFactory((p) -> {
            BorrowTicket borrowTicket = p.getValue();
            String status = borrowTicket.getStatus();
            return new ReadOnlyObjectWrapper(status);
        });
        
        colNote.setCellValueFactory((p) -> {
            BorrowTicket borrowTicket = p.getValue();
            String note = borrowTicket.getNote();
            return new ReadOnlyObjectWrapper(note);
        }); 

        tbvTicket.getColumns().addAll(colTicketId, colReaderId, colBorrowDate, colReturnDate, colBooks, colActualReturn, colStatus, colNote);

        Button btnViewDetail = new Button("Xem Chi Tiết");
        
        // Thanh tim kiem
        TextField tfSearch = new TextField();
        tfSearch.setPromptText("🔍 Tìm kiếm theo ma phieu muon");
        tfSearch.setPrefWidth(300);

        VBox tableBox = new VBox(10, tfSearch, tbvTicket, btnViewDetail);
        tableBox.setPadding(new Insets(20));
        HBox.setHgrow(tableBox, Priority.ALWAYS);

        // Layout chinh
        HBox mainContent = new HBox(formScrollPane, tableBox);
        BorderPane masterLayout = new BorderPane(mainContent, null, null, null, menuBar);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        Scene scene = new Scene(masterLayout, bounds.getWidth(), bounds.getHeight() - 30);
        stage.setScene(scene);
        stage.setTitle("Quản lý mượn - trả");
        stage.show();
    }

    private Button createActionButton(String text, String color) {
        Button btn = new Button(text);
        btn.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white;");
        btn.setPrefWidth(120);
        return btn;
    }
}