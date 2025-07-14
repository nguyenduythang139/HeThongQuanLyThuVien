/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlythuvien.views;
import java.util.Date;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import com.quanlythuvien.models.Reader;
import com.quanlythuvien.utils.menuBarComponent;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author admin
 */
public class ManageReaderView {
    public void start(Stage stage) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            
        // Thanh menu
        VBox menuBar = menuBarComponent.createMenuBar(stage);

        // Form them doc gia
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

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        VBox formBox = new VBox(10,
                lbTitle,
                vbReaderName, vbReaderGender, vbBirthDate, vbReaderCCCD, vbReaderPhone, vbReaderEmail, vbReaderAddress, vbReaderStatus,
                buttonBox1, buttonBox2
        );
        formBox.setPadding(new Insets(20));
        formBox.setPrefWidth(300);
        formBox.setPrefHeight(bounds.getHeight());
        formBox.setStyle("-fx-background-color: #F8F8F8; -fx-border-color: #ccc;");
        formBox.setMargin(buttonBox1, new Insets(30, 0, 0, 0));
        
        ScrollPane formScrollPane = new ScrollPane(formBox);
        formScrollPane.setFitToWidth(true);
        
        // Bang hien thi danh sach doc gia
        TableView<Reader> tbvReader = new TableView<>();
        tbvReader.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tbvReader.setPlaceholder(new Label("Không có dữ liệu!"));

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

        tbvReader.getColumns().addAll(colId, colName, colGender, colBirthDate, colCCCD, colPhoneNumber, colEmail, colAddress, colStatus);

        // Thanh tim kiem
        TextField tfSearch = new TextField();
        tfSearch.setPromptText("🔍 Tìm kiếm độc giả theo mã hoặc tên");
        tfSearch.setPrefWidth(300);

        VBox tableBox = new VBox(10, tfSearch, tbvReader);
        tableBox.setPadding(new Insets(20));
        HBox.setHgrow(tableBox, Priority.ALWAYS);

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
