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
import com.quanlythuvien.models.Reader;
import javafx.beans.property.SimpleObjectProperty;
import com.quanlythuvien.utils.menuBarComponent;
import java.text.SimpleDateFormat;

/**
 *
 * @author admin
 */
public class ManageReaderView {
    public void start(Stage stage) {
        // Thanh menu
        VBox menuBar = menuBarComponent.createMenuBar(stage);

        // Form nhap thong doc gia
        Label lbTitle = new Label("👥 Quản Lý Độc Giả");
        lbTitle.setFont(Font.font(20));
        lbTitle.setStyle("-fx-text-fill: #1D774E;");

        Label lbReaderName = new Label("Họ tên:");
        TextField tfReaderName = new TextField();
        tfReaderName.setPromptText("Nhập họ tên");
        tfReaderName.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbReaderName = new VBox(3, lbReaderName, tfReaderName);

        Label lbReaderGender = new Label("Giới tính:");
        ComboBox<String> cbReaderGender = new ComboBox<>();
        cbReaderGender.getItems().addAll("Nam", "Nữ");
        cbReaderGender.setPromptText("Chọn giới tính");
        cbReaderGender.setPrefWidth(300);
        cbReaderGender.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbReaderGender = new VBox(3, lbReaderGender, cbReaderGender);
        
        Label lbBirthDate = new Label("Ngày sinh");
        DatePicker dpBirthDate = new DatePicker();
        dpBirthDate.setPromptText("Chon ngày sinh");
        dpBirthDate.setPrefWidth(300);
        dpBirthDate.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbBirthDate = new VBox(3, lbBirthDate, dpBirthDate);

        Label lbReaderPhone = new Label("Số điện thoại:");
        TextField tfReaderPhone = new TextField();
        tfReaderPhone.setPromptText("Nhập số điện thoại");
        tfReaderPhone.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbReaderPhone = new VBox(3, lbReaderPhone, tfReaderPhone);
        
        Label lbReaderCCCD = new Label("Căn cước công dân:");
        TextField tfReaderCCCD = new TextField();
        tfReaderCCCD.setPromptText("Nhập số điện thoại");
        tfReaderCCCD.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbReaderCCCD = new VBox(3, lbReaderCCCD, tfReaderCCCD);
        
        Label lbReaderEmail = new Label("Email:");
        TextField tfReaderEmail = new TextField();
        tfReaderEmail.setPromptText("Nhập Email");
        tfReaderEmail.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbReaderEmail = new VBox(3, lbReaderEmail, tfReaderEmail);

        Label lbReaderAddress = new Label("Địa chỉ:");
        TextField tfReaderAddress = new TextField();
        tfReaderAddress.setPromptText("Nhập địa chỉ");
        tfReaderAddress.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbReaderAddress = new VBox(3, lbReaderAddress, tfReaderAddress);
        
        Label lbReaderStatus = new Label("Trạng thái:");
        ComboBox<String> cbReaderStatus = new ComboBox<>();
        cbReaderStatus.getItems().addAll("Đang hoạt động", "Bị khóa");
        cbReaderStatus.setPromptText("Chọn trạng thái");
        cbReaderStatus.setPrefWidth(300);
        cbReaderStatus.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbReaderStatus = new VBox(3, lbReaderStatus, cbReaderStatus);

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
                vbReaderName, vbReaderGender, vbBirthDate, vbReaderCCCD, vbReaderPhone, vbReaderEmail, vbReaderAddress, vbReaderStatus,
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
        
        // Bang hien thi danh sach doc gia
        TableView<Reader> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Reader, Integer> colId = new TableColumn<>("Mã độc giả");
        colId.setStyle("-fx-alignment: center");
        TableColumn<Reader, String> colName = new TableColumn<>("Họ tên");
        TableColumn<Reader, String> colGender = new TableColumn<>("Giới tính");
        colGender.setStyle("-fx-alignment: center");
        TableColumn<Reader, Date> colBirthDate = new TableColumn<>("Ngày sinh");
        TableColumn<Reader, String> colCCCD = new TableColumn<>("Số căn cước ");
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
        colCCCD.setCellValueFactory(cellData -> cellData.getValue().cccdProperty());
        colPhoneNumber.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
        colEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        colAddress.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        colStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());      

        table.getColumns().addAll(colId, colName, colGender, colBirthDate, colCCCD, colPhoneNumber, colEmail, colAddress, colStatus);
        ObservableList<Reader> readers = FXCollections.observableArrayList(
            new Reader(1, "Nguyễn Văn A", "Nam", new Date(90, 4, 15), "012312312398", "0912345678", "a.nguyen@gmail.com", "Hà Nội", "Đang hoạt động"),
            new Reader(2, "Trần Thị B", "Nữ", new Date(95, 7, 22), "012312126798", "0987654321", "b.tran@yahoo.com", "TP. HCM", "Bị khóa"),
            new Reader(3, "Lê Văn C", "Nam", new Date(88, 1, 5), "012376092398", "0932123456", "c.le@outlook.com", "Đà Nẵng", "Đang hoạt động"),
            new Reader(4, "Phạm Thị D", "Nữ", new Date(99, 10, 10), "012309828398", "0945678910", "d.pham@gmail.com", "Cần Thơ", "Đang hoạt động"),
            new Reader(5, "Hoàng Văn E", "Nam", new Date(85, 3, 30), "012319876398", "0909988776", "e.hoang@gmail.com", "Hải Phòng", "Bị khóa")
        );

        table.setItems(readers);

        // Thanh tim kiem
        TextField tfSearch = new TextField();
        tfSearch.setPromptText("🔍 Tìm kiếm độc giả theo mã hoặc tên");
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
