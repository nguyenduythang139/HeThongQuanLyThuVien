/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlythuvien.views;
import com.quanlythuvien.database.DBConnection;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;              
import java.sql.Statement;
import java.time.chrono.JapaneseEra;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.geometry.Dimension2D;

/**
 *
 * @author admin
 */
public class ManageBookView {   
    private static Button btnThem, btnXoa, btnCapNhat, btnReset;
    private static TextField tfId,tfBookName,tfAuthor,tfPublisher,tfQuantity,tfLocation;
    private static ComboBox<String> cbCategory,cbLanguage,cbState;
    private static DatePicker dpPublicDate;
    private static ObservableList<Book> dataSach = FXCollections.observableArrayList();
    private static TableView<Book> tbvBook;
    public void start(Stage stage) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        // Thanh menu
        VBox menuBar = menuBarComponent.createMenuBar(stage);

        // Form them sach
        Label lbTitle = new Label("📖 Quản Lý Sách");
        lbTitle.setFont(Font.font(20));
        lbTitle.setStyle("-fx-text-fill: #1D774E;");

        Label lbBookName = new Label("Tên sách:");
        tfBookName = new TextField();
        tfBookName.setPromptText("Nhập tên sách");
        tfBookName.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbBookName = new VBox(3, lbBookName, tfBookName);

        Label lbAuthor = new Label("Tác giả:");
        tfAuthor = new TextField();
        tfAuthor.setPromptText("Nhập tác giả");
        tfAuthor.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbAuthor = new VBox(3, lbAuthor, tfAuthor);
        
        Label lbPublisher = new Label("Nhà xuất bản:");
        tfPublisher = new TextField();
        tfPublisher.setPromptText("Nhập nhà xuất bản");
        tfPublisher.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbPublisher = new VBox(3, lbPublisher, tfPublisher);


        Label lbCategory = new Label("Thể loại:");
        cbCategory = new ComboBox<>();
        cbCategory.getItems().addAll("Tieu Thuyet", "Giao Trinh", "Khoa Hoc", "Truyen Tranh");
        cbCategory.setPromptText("Chọn thể loại");
        cbCategory.setPrefWidth(300);
        cbCategory.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbCategory = new VBox(3, lbCategory, cbCategory);

        Label lbPublicDate = new Label("Ngày xuất bản");
        dpPublicDate = new DatePicker();
        dpPublicDate.setPromptText("Chon ngày xuất bản");
        dpPublicDate.setPrefWidth(300);
        dpPublicDate.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbPublicDate = new VBox(3, lbPublicDate, dpPublicDate);
        
        Label lbQuantity = new Label("Số lượng");
        tfQuantity = new TextField();
        tfQuantity.setPromptText("Nhập số lượng");
        tfQuantity.setPrefWidth(300);
        tfQuantity.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbQuantity = new VBox(3, lbQuantity, tfQuantity);

        Label lbLanguage = new Label("Ngôn ngữ:");
        cbLanguage = new ComboBox<>();
        cbLanguage.getItems().addAll("Tieng Viet", "Tieng Anh", "Tieng Nga", "Tieng Phap", "Tieng Trung");
        cbLanguage.setPromptText("Chọn ngôn ngữ");
        cbLanguage.setPrefWidth(300);
        cbLanguage.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbLanguage = new VBox(3, lbLanguage, cbLanguage);
        
        Label lbState = new Label("Tình trạng:");
        cbState = new ComboBox<>();
        cbState.getItems().addAll("Con", "Het");
        cbState.setPromptText("Chọn tình trạng");
        cbState.setPrefWidth(300);
        cbState.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbState = new VBox(3, lbState, cbState);
        
        Label lbLocation = new Label("Vị trí lưu trữ:");
        tfLocation = new TextField();
        tfLocation.setPromptText("Nhập vị trí lưu trữ");
        tfLocation.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbLocation = new VBox(3, lbLocation, tfLocation);

        // Nut them xoa sua reset
        HBox buttonBox1 = new HBox(20,
                btnThem = new Button("Thêm"),
                btnCapNhat = new Button("Cập nhật")             
        );  
        btnThem.setStyle("-fx-background-color:#1E56A0");
        btnCapNhat.setStyle("-fx-background-color:#2E8B57");
        
        HBox buttonBox2 = new HBox(20, 
                btnXoa = new Button("Xoá"),
                btnReset = new Button("Reset")
        );
        btnXoa.setStyle("-fx-background-color:#B22222");
        btnReset.setStyle("-fx-backgroung-color:#6A5ACD");
        
        btnThem.setPrefSize(120,30);
        btnCapNhat.setPrefSize(120,30);
        btnXoa.setPrefSize(120,30);
        btnReset.setPrefSize(120,30);
 
        buttonBox1.setAlignment(Pos.CENTER);
        buttonBox2.setAlignment(Pos.CENTER);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        VBox formBox = new VBox(10,
                lbTitle,
                vbBookName, vbAuthor, vbPublisher, vbCategory, vbPublicDate, vbQuantity, vbLanguage, vbState, vbLocation,
                buttonBox1, buttonBox2
        );
        //chuc nang them sach
        btnThem.setOnAction((t)->{
            themSach();
        });
        
        //chuc nang xoa sach
        btnXoa.setOnAction((t)->{
            xoaSach();
        });
        
        //chuc nang sua sach
        btnCapNhat.setOnAction((t) -> {
            capNhatSach();
        });
        
        //chuc nang reset
        btnReset.setOnAction((t) -> {
            Reset();
        });
        formBox.setPadding(new Insets(20));
        formBox.setPrefWidth(300);
        formBox.setPrefHeight(bounds.getHeight());
        formBox.setStyle("-fx-background-color: #F8F8F8; -fx-border-color: #ccc;");
        formBox.setMargin(buttonBox1, new Insets(30, 0, 0, 0));
        
        ScrollPane formScrollPane = new ScrollPane();
        formScrollPane.setContent(formBox);
        formScrollPane.setFitToWidth(true);
        
        // Bang hien thi danh sach sach
        tbvBook = new TableView<>();
        tbvBook.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tbvBook.setPlaceholder(new Label("Không có dữ liệu!"));

        TableColumn<Book, Integer> colId = new TableColumn<>("Mã sách");
        colId.setCellValueFactory((p) -> {
            Book bk = p.getValue();
            int MaSach = bk.getId();
            return new ReadOnlyObjectWrapper<>(MaSach);
        });
        
        TableColumn<Book, String> colName = new TableColumn<>("Tên sách");
        colName.setCellValueFactory((p) -> {
            Book bk = p.getValue();
            String TenSach = bk.getName();
            return new ReadOnlyObjectWrapper<>(TenSach);
        });
        TableColumn<Book, String> colAuthor = new TableColumn<>("Tác giả");
        colAuthor.setCellValueFactory((p) -> {
            Book bk = p.getValue();
            String TacGia = bk.getAuthor();
            return new ReadOnlyObjectWrapper<>(TacGia);
        });
        TableColumn<Book, String> colPublisher = new TableColumn<>("Nhà xuất bản"); 
        colPublisher.setCellValueFactory((p) -> {
            Book bk = p.getValue();
            String NhaXuatBan = bk.getPublisher();
            return new ReadOnlyObjectWrapper<>(NhaXuatBan);
        });
        TableColumn<Book, String> colCategory = new TableColumn<>("Thể loại");
        colCategory.setCellValueFactory((p) -> {
            Book bk = p.getValue();
            String TheLoai = bk.getCategory();
            return new ReadOnlyObjectWrapper<>(TheLoai);
        });
        TableColumn<Book, Date> colPublicDate = new TableColumn<>("Ngày");
        colPublicDate.setCellValueFactory((p) -> {
            Book bk = p.getValue();
            Date NgayXuatBan = bk.getPublicDate();
            return new ReadOnlyObjectWrapper<>(NgayXuatBan);
        });
        TableColumn<Book, Integer> colQuantity = new TableColumn<>("Số lượng");
        colQuantity.setCellValueFactory((p) -> {
            Book bk = p.getValue();
            int SoLuong = bk.getQuantity();
            return new ReadOnlyObjectWrapper<>(SoLuong);
        });
        TableColumn<Book, String> colLanguage = new TableColumn<>("Ngôn ngữ");
        colLanguage.setCellValueFactory((p) -> {
            Book bk = p.getValue();
            String NgonNgu = bk.getLanguage();
            return new ReadOnlyObjectWrapper<>(NgonNgu);
        });
        TableColumn<Book, String> colState = new TableColumn<>("Trạng thái");
        colState.setCellValueFactory((p) -> {
            Book bk = p.getValue();
            String TinhTrang = bk.getState();
            return new ReadOnlyObjectWrapper<>(TinhTrang);
        });
        TableColumn<Book, String> colLocation = new TableColumn<>("Vị trí");
        colLocation.setCellValueFactory((p) -> {
            Book bk = p.getValue();
            String ViTriLuuTru = bk.getLocation();
            return new ReadOnlyObjectWrapper<>(ViTriLuuTru);
        });
        
        loadDataSach();
        tbvBook.getColumns().addAll(colId, colName, colAuthor, colPublisher,colCategory, colPublicDate, colQuantity, colLanguage, colState, colLocation);
        
        tbvBook.setOnMouseClicked((t) -> {
            showItemBook();
        });

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
    public static void showItemBook(){
        Book bk = tbvBook.getSelectionModel().getSelectedItem();
        tfBookName.setText(bk.getName()+"");
        tfAuthor.setText(bk.getAuthor());
        tfPublisher.setText(bk.getPublisher());
        cbCategory.setValue(bk.getCategory());
        dpPublicDate.setValue(new java.sql.Date(bk.getPublicDate().getTime()).toLocalDate());
        tfQuantity.setText(bk.getQuantity() +"");
        cbLanguage.setValue(bk.getLanguage());
        cbState.setValue(bk.getState());
        tfLocation.setText(bk.getLocation());
        
    }
    
    public static void capNhatSach(){
        Alert thongbao = new Alert(Alert.AlertType.INFORMATION);
            thongbao.setTitle("Cập nhật!!");
        try {
            Book selectedBook = tbvBook.getSelectionModel().getSelectedItem();
            Connection conn = DBConnection.getConnection();
            if(conn != null){
                String sql ="update sach set TenSach=?,TacGia=?,NhaXuatBan=?,TheLoai=?,NgayXuatBan=?,SoLuong=?,NgonNgu=?,TinhTrang=?,ViTriLuuTru=? where MaSach=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                
                ps.setString(1, tfBookName.getText());
                ps.setString(2, tfAuthor.getText());
                ps.setString(3, tfPublisher.getText());
                ps.setString(4, cbCategory.getValue());
                ps.setDate(5,java.sql.Date.valueOf(dpPublicDate.getValue()));
                ps.setInt(6, Integer.parseInt(tfQuantity.getText()));
                ps.setString(7, cbLanguage.getValue());
                ps.setString(8, cbState.getValue());
                ps.setString(9, tfLocation.getText());
                ps.setInt(10, selectedBook.getId());
                
                int kq = ps.executeUpdate();
                if(kq > 0){
                    thongbao.setContentText("Cập nhật thành công");
                    thongbao.show();
                    dataSach.clear();
                    loadDataSach();
                }
                else{
                    thongbao.setContentText("Cập nhật thất bại, kiểm tra lại");
                    thongbao.show();
                }  
            }
        } catch (Exception e) {
            thongbao.setContentText("Error cập nhật");
            thongbao.show();
        }
    }
    
    public static void xoaSach(){
        Alert thongbao = new Alert(Alert.AlertType.INFORMATION);
            thongbao.setTitle("Xoa sach!!");
        try {
            Book selectedBook = tbvBook.getSelectionModel().getSelectedItem();
            Connection conn = DBConnection.getConnection();
            if(conn != null){
                String sql ="delete from sach where MaSach=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                
                ps.setInt(1, selectedBook.getId());
                
                int kq = ps.executeUpdate();
                if(kq > 0){
                    thongbao.setContentText("Xóa thành công");
                    thongbao.show();
                    dataSach.clear();
                    loadDataSach();
                }
                else{
                    thongbao.setContentText("Xóa thất bại, kiểm tra lại");
                    thongbao.show();
                }  
            }
        } catch (Exception e) {
            thongbao.setContentText("Error xóa");
            thongbao.show();
        }
    }
    
    public static void Reset(){
        tfBookName.clear();
        tfAuthor.clear();
        tfPublisher.clear();
        tfQuantity.clear();
        tfLocation.clear();

        cbCategory.setValue(null);
        cbLanguage.setValue(null);
        cbState.setValue(null);
        dpPublicDate.setValue(null);
    }
    
    public static void themSach(){
        Alert thongbao = new Alert(Alert.AlertType.INFORMATION);
            thongbao.setTitle("Them moi!!");
        try {
            Connection conn = DBConnection.getConnection();
            if(conn != null){
                String sql ="insert into sach(TenSach, TacGia, NhaXuatBan, TheLoai, NgayXuatBan, SoLuong, NgonNgu, TinhTrang, ViTriLuuTru, HinhAnh) values(?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                
                ps.setString(1, tfBookName.getText());
                ps.setString(2, tfAuthor.getText());
                ps.setString(3, tfPublisher.getText());
                ps.setString(4, cbCategory.getValue());
                ps.setDate(5,java.sql.Date.valueOf(dpPublicDate.getValue()));
                ps.setInt(6, Integer.parseInt(tfQuantity.getText()));
                ps.setString(7, cbLanguage.getValue());
                ps.setString(8, cbState.getValue());
                ps.setString(9, tfLocation.getText());
                ps.setString(10,null);
                
                int kq = ps.executeUpdate();
                if(kq > 0){
                    thongbao.setContentText("Thêm thành công");
                    thongbao.show();
                    dataSach.clear();
                    loadDataSach();
                }
                else{
                    thongbao.setContentText("Thêm thất bại, kiểm tra lại");
                    thongbao.show();
                }  
            }
        } catch (Exception e) {
            thongbao.setContentText("Error thêm");
            thongbao.show();
        }
    }
    
    public static void loadDataSach(){
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "select * from sach";
            Statement stm = conn.createStatement();
            ResultSet rs =stm.executeQuery(sql);    
            dataSach.clear();
            while(rs.next()){
                dataSach.add(new Book(
                        rs.getInt("MaSach"),
                        rs.getString("TenSach"),
                        rs.getString("TacGia"),
                        rs.getString("NhaXuatBan"),
                        rs.getString("TheLoai"),
                        rs.getDate("NgayXuatBan"),
                        rs.getInt("SoLuong"),
                        rs.getString("NgonNgu"),
                        rs.getString("TinhTrang"),
                        rs.getString("ViTriLuuTru")
                ));
            }
            tbvBook.setItems(dataSach);
        } catch (Exception e) {
        }
    }
}