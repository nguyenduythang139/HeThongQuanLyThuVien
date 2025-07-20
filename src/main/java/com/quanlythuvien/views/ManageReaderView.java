/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlythuvien.views;
import com.quanlythuvien.database.DBConnection;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author admin
 */
public class ManageReaderView {
    private static TableView<Reader> tvdocgia;
    private static ObservableList<Reader> dataDocGia = FXCollections.observableArrayList();
    private static TextField tfReaderName,tfReaderCCCD,tfReaderPhone,tfReaderEmail,tfReaderAddress, tfSearch;
    private static ComboBox<String> cbReaderGender, cbReaderStatus;
    private static DatePicker dpBirthDate;
    
    public void start(Stage stage) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
        // Thanh menu
        VBox menuBar = menuBarComponent.createMenuBar(stage);
        // Layout chinh
        HBox mainContent = new HBox(createDtailDocGia(), showTableViewDocGia());
        BorderPane masterLayout = new BorderPane(mainContent, null, null, null, menuBar);
        // action
        tvdocgia.setOnMouseClicked((t) -> {
            showItemDocGia();
        });
        
        Scene scene = new Scene(masterLayout);
        stage.setTitle("Quản lý sách");
        stage.setScene(scene);
        stage.show();
    }
    
    //form them docgia
    public static VBox createDtailDocGia(){
        VBox layout = new VBox();
        
        Label lbTitle = new Label("Quản Lý Độc Giả");
        lbTitle.setFont(Font.font(20));
        lbTitle.setStyle("-fx-text-fill: #1D774E;");

        Label lbReaderName = new Label("Họ tên:");
        tfReaderName = new TextField();
        tfReaderName.setPromptText("Nhập họ tên");
        tfReaderName.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbReaderName = new VBox(3, lbReaderName, tfReaderName);

        Label lbReaderGender = new Label("Giới tính:");
        cbReaderGender = new ComboBox<>();
        cbReaderGender.getItems().addAll("Nam", "Nữ");
        cbReaderGender.setPromptText("Chọn giới tính");
        cbReaderGender.setPrefWidth(300);
        cbReaderGender.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbReaderGender = new VBox(3, lbReaderGender, cbReaderGender);
        
        Label lbBirthDate = new Label("Ngày sinh");
        dpBirthDate = new DatePicker();
        dpBirthDate.setPromptText("Chọn ngày sinh");
        dpBirthDate.setPrefWidth(300);
        dpBirthDate.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbBirthDate = new VBox(3, lbBirthDate, dpBirthDate);

        Label lbReaderPhone = new Label("Số điện thoại:");
        tfReaderPhone = new TextField();
        tfReaderPhone.setPromptText("Nhập số điện thoại");
        tfReaderPhone.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbReaderPhone = new VBox(3, lbReaderPhone, tfReaderPhone);
        
        Label lbReaderCCCD = new Label("Căn cước công dân:");
        tfReaderCCCD = new TextField();
        tfReaderCCCD.setPromptText("Nhập số điện thoại");
        tfReaderCCCD.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbReaderCCCD = new VBox(3, lbReaderCCCD, tfReaderCCCD);
        
        Label lbReaderEmail = new Label("Email:");
        tfReaderEmail = new TextField();
        tfReaderEmail.setPromptText("Nhập Email");
        tfReaderEmail.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbReaderEmail = new VBox(3, lbReaderEmail, tfReaderEmail);

        Label lbReaderAddress = new Label("Địa chỉ:");
        tfReaderAddress = new TextField();
        tfReaderAddress.setPromptText("Nhập địa chỉ");
        tfReaderAddress.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbReaderAddress = new VBox(3, lbReaderAddress, tfReaderAddress);
        
        Label lbReaderStatus = new Label("Trạng thái:");
        cbReaderStatus = new ComboBox<>();
        cbReaderStatus.getItems().addAll("Hoạt động", "Bị khóa");
        cbReaderStatus.setPromptText("Chọn trạng thái");
        cbReaderStatus.setPrefWidth(300);
        cbReaderStatus.setStyle("-fx-background-color: white; -fx-border-width: 1; -fx-border-color: #D5D5D5; -fx-border-radius: 4; -fx-prompt-text-fill: grey;");
        VBox vbReaderStatus = new VBox(3, lbReaderStatus, cbReaderStatus);

        // Nut them xoa sua reset
        Button btnthem = new Button("Thêm");
        btnthem.setStyle("-fx-background-color:#1E56A0; -fx-text-fill: white;");
        btnthem.setPrefWidth(100);
        
        Button btnCapNhat = new Button("Cập nhật");
        btnCapNhat.setStyle("-fx-background-color:#2E8B57; -fx-text-fill: white;");
        btnCapNhat.setPrefWidth(100);
        
        Button btnXoa = new Button("Xóa");
        btnXoa.setStyle("-fx-background-color:#B22222; -fx-text-fill: white;");
        btnXoa.setPrefWidth(100);
        
        Button btnReset = new Button("Reset");
        btnReset.setStyle("-fx-background-color:#6A5ACD; -fx-text-fill: white;");
        btnReset.setPrefWidth(100);
        
        HBox buttonBox1 = new HBox(20, btnthem, btnCapNhat);    
        HBox buttonBox2 = new HBox(20, btnXoa, btnReset);
        buttonBox1.setAlignment(Pos.CENTER);
        buttonBox2.setAlignment(Pos.CENTER);
        
        //
        btnthem.setOnAction((t) -> {
            themDG();
        });
        btnXoa.setOnAction((t) -> {
            xoaDG();
        });
        btnCapNhat.setOnAction((t) -> {
            capNhatDG();
        });
          btnReset.setOnAction((t) -> {
            ResetDG();
        });
        
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        VBox formBox = new VBox(10,
                lbTitle,
                vbReaderName, vbReaderGender, vbBirthDate,
                vbReaderCCCD, vbReaderPhone, vbReaderEmail, 
                vbReaderAddress, vbReaderStatus,
                buttonBox1, buttonBox2
        );
        formBox.setPadding(new Insets(20));
        formBox.setPrefWidth(300);
        formBox.setPrefHeight(bounds.getHeight());
        formBox.setStyle("-fx-background-color: #F8F8F8; -fx-border-color: #ccc;");
        formBox.setMargin(buttonBox1, new Insets(30, 0, 0, 0));
        
        layout.getChildren().add(formBox);
        return layout;
    }
    
    //tao bang tableview docgia
    public static VBox showTableViewDocGia(){
        VBox layout = new VBox();
        layout.setPrefWidth(1000);
        tvdocgia = new TableView<>();
        tvdocgia.setPlaceholder(new Label("Không có dữ liệu!"));

        TableColumn<Reader, Integer> colId = new TableColumn<>("Mã độc giả");
        colId.setStyle("-fx-alignment: center");
         colId.setCellValueFactory((p) -> {
            Reader docgia = p.getValue();
            Integer magocgia  = docgia.getId();
            return new ReadOnlyObjectWrapper<>(magocgia);
        });
        TableColumn<Reader, String> colName = new TableColumn<>("Họ tên");
        colName.setCellValueFactory((p) -> {
            Reader docgia = p.getValue();
            String tendocgia = docgia.getName();
            return new ReadOnlyObjectWrapper<>(tendocgia);
        });
        
        TableColumn<Reader, String> colGender = new TableColumn<>("Giới tính");
        colGender.setStyle("-fx-alignment: center");
        colGender.setCellValueFactory((p) -> {
            Reader docgia = p.getValue();
            String gioitinh = docgia.getGender();
            return new ReadOnlyObjectWrapper<>(gioitinh);
        });
        TableColumn<Reader, Date> colBirthDate = new TableColumn<>("Ngày sinh");
        colBirthDate.setCellValueFactory((p) -> {
            Reader docgia = p.getValue();
            Date ngaysinh = docgia.getBirthDate();
            return new ReadOnlyObjectWrapper<>(ngaysinh);
        });
        TableColumn<Reader, String> colCCCD = new TableColumn<>("Số căn cước ");
        colCCCD.setCellValueFactory((p) -> {
            Reader docgia = p.getValue();
            String cccd = docgia.getCCCD();
            return new ReadOnlyObjectWrapper<>(cccd);
        });
        TableColumn<Reader, String> colPhoneNumber = new TableColumn<>("Số điện thoại");
        colPhoneNumber.setCellValueFactory((p) -> {
            Reader docgia = p.getValue();
            String dt = docgia.getPhoneNumber();
            return new ReadOnlyObjectWrapper<>(dt);
        });
        TableColumn<Reader, String> colEmail = new TableColumn<>("Email");
        colEmail.setCellValueFactory((p) -> {
            Reader docgia = p.getValue();
            String email = docgia.getEmail();
            return new ReadOnlyObjectWrapper<>(email);
        });
        TableColumn<Reader, String> colAddress = new TableColumn<>("Địa chỉ");
        colAddress.setCellValueFactory((p) -> {
            Reader docgia = p.getValue();
            String diachi = docgia.getAddress();
            return new ReadOnlyObjectWrapper<>(diachi);
        });
        TableColumn<Reader, String> colStatus = new TableColumn<>("Trạng thái"); 
        colStatus.setCellValueFactory((p) -> {
            Reader docgia = p.getValue();
            String trangthai = docgia.getStatus();
            return new ReadOnlyObjectWrapper<>(trangthai);
        });
        tvdocgia.getColumns().addAll(colId, colName, colGender, colBirthDate, colCCCD, colPhoneNumber, colEmail, colAddress, colStatus);
        loadDataDocGia();

        // Thanh tim kiem
        tfSearch = new TextField();
        tfSearch.setPromptText("🔍 Tìm kiếm độc giả theo mã hoặc tên");
        tfSearch.setPrefWidth(300);
        
        tfSearch.setOnAction((t) -> {
           timkiemDG();
        });
        
        layout.setSpacing(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(tfSearch, tvdocgia);
        HBox.setHgrow(layout, Priority.ALWAYS);
        return layout;
    }
    
    //select data tu bang du lieu
    public static void loadDataDocGia(){
        try{
            Connection conn = DBConnection.getConnection();//ket noi csdl
            String sql = " Select * from docgia";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql); //kq tra ve tu cau truy van
            dataDocGia.clear();
            while(rs.next()){
                dataDocGia.add(new Reader(
                        rs.getInt("MaDocGia"),
                        rs.getString("HoTen"),
                        rs.getString("GioiTInh"),
                        rs.getDate("NgaySinh"),
                        rs.getString("CCCD"),
                        rs.getString("SDT"),
                        rs.getString("Email"),
                        rs.getString("DiaChi"),
                        rs.getString("TrangThai")
                ));
            }
            tvdocgia.setItems(dataDocGia);
        }catch (Exception e){
            System.out.println("Load data thất bại!");
        }
    }
    //them doc gia
    private static void themDG(){        
        Alert thongbao = new Alert(Alert.AlertType.INFORMATION);
            thongbao.setTitle("Thêm độc giả mới!!!");
        try{
            Connection conn = DBConnection.getConnection();
            if(conn!=null){
                String sql = "Insert into docgia(HoTen, GioiTinh, NgaySinh, CCCD,SDT, Email, DiaChi, TrangThai)"
                        + " values(?,?,?,?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tfReaderName.getText());
            ps.setString(2, cbReaderGender.getValue());
            ps.setDate(3, java.sql.Date.valueOf(dpBirthDate.getValue()));
            ps.setString(4, tfReaderCCCD.getText());
            ps.setString(5, tfReaderPhone.getText());
            ps.setString(6, tfReaderEmail.getText());
            ps.setString(7, tfReaderAddress.getText());
            ps.setString(8, cbReaderStatus.getValue());
                int kq = ps.executeUpdate();
                if(kq>0){
                    thongbao.setContentText("Thêm độc giả thành công!");
                    thongbao.show();
                    dataDocGia.clear();
                    loadDataDocGia();
                }
                else{
                    thongbao.setContentText("Thêm độc giả thất bại!");
                    thongbao.show();
                }
            }
        }catch(Exception e){
            thongbao.setContentText("Error thêm!");
            thongbao.show();
        }
    }
    //xoa docgia
    private static void xoaDG(){        
        Alert thongbao = new Alert(Alert.AlertType.INFORMATION);
            thongbao.setTitle("Xóa độc giả!!!");
        Reader selected = tvdocgia.getSelectionModel().getSelectedItem(); 
        try{
            Connection conn = DBConnection.getConnection();
            if(conn!=null){
                String sql = "Delete from docgia where MaDocGia=?";
                PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, selected.getId());
                int kq = ps.executeUpdate();
                if(kq>0){
                    thongbao.setContentText("Xóa độc giả thành công!");
                    thongbao.show();
                    dataDocGia.clear();
                    loadDataDocGia();
                }
                else{
                    thongbao.setContentText("Xóa độc giả thất bại!");
                    thongbao.show();
                }
            }
        }catch(Exception e){
            thongbao.setContentText("Error xóa!");
            thongbao.show();
        }
    }
    //cap nhat docgia
    private static void capNhatDG(){        
        Alert thongbao = new Alert(Alert.AlertType.INFORMATION);
            thongbao.setTitle("Cập nhật thông tin độc giả!!!");
        Reader selected = tvdocgia.getSelectionModel().getSelectedItem(); 
        try{
            Connection conn = DBConnection.getConnection();
            if(conn!=null){
                String sql = "Update docgia set HoTen=?, GioiTinh=?, NgaySinh=?,"
                        + " CCCD=?,SDT=?, Email=?, DiaChi=?, TrangThai=? where MaDocGia=?";
                PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tfReaderName.getText());
            ps.setString(2, cbReaderGender.getValue());
            ps.setDate(3, java.sql.Date.valueOf(dpBirthDate.getValue()));
            ps.setString(4, tfReaderCCCD.getText());
            ps.setString(5, tfReaderPhone.getText());
            ps.setString(6, tfReaderEmail.getText());
            ps.setString(7, tfReaderAddress.getText());
            ps.setString(8, cbReaderStatus.getValue());
            ps.setInt(9, selected.getId());
                int kq = ps.executeUpdate();
                if(kq>0){
                    thongbao.setContentText("Cập nhật thông  độc giả thành công!");
                    thongbao.show();
                    dataDocGia.clear();
                    loadDataDocGia();
                }
                else{
                    thongbao.setContentText("Cập nhật thông tin độc giả thất bại!");
                    thongbao.show();
                }
            }
        }catch(Exception e){
            thongbao.setContentText("Error cập nhật!");
            thongbao.show();
        }
    }
    private static void ResetDG(){        
        tfReaderName.clear();
        cbReaderGender.setValue("Nam");
        dpBirthDate.setValue(null);
        tfReaderCCCD.clear();
        tfReaderPhone.clear();
        tfReaderEmail.clear();
        tfReaderAddress.clear();
        cbReaderStatus.setValue("Hoạt động");
        tvdocgia.getSelectionModel().clearSelection();
    }
    
    private static void timkiemDG() {
        Alert thongbao = new Alert(Alert.AlertType.INFORMATION);
        String keyword = tfSearch.getText().trim();
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM docgia WHERE MaDocGia LIKE ? OR HoTen LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            String likeKeyword = "%" + keyword + "%";
            ps.setString(1, likeKeyword);
            ps.setString(2, likeKeyword);
            ResultSet rs = ps.executeQuery();

            ObservableList<Reader> ketQuaTimKiem = FXCollections.observableArrayList();
            while (rs.next()) {
                Reader reader = new Reader(
                    rs.getInt("MaDocGia"),
                    rs.getString("HoTen"),
                    rs.getString("GioiTinh"),
                    rs.getDate("NgaySinh"),
                    rs.getString("CCCD"),
                    rs.getString("SDT"),
                    rs.getString("Email"),
                    rs.getString("DiaChi"),
                    rs.getString("TrangThai")
                );
                ketQuaTimKiem.add(reader);
            }
            tvdocgia.setItems(ketQuaTimKiem);
            if (ketQuaTimKiem.isEmpty()) {
                thongbao.setTitle("Kết quả tìm kiếm");
                thongbao.setContentText("Không tìm thấy độc giả!.");
                thongbao.show();
            }
        } catch (Exception e) {
            System.out.println("Error tìm kiếm!");
        }
    }

    //
    public static void showItemDocGia(){
        Reader docgia = tvdocgia.getSelectionModel().getSelectedItem();
        tfReaderName.setText(docgia.getName());
        cbReaderGender.setValue(docgia.getGender());
        //Birthday 
        dpBirthDate.setValue(new java.sql.Date(docgia.getBirthDate().getTime()).toLocalDate());
        tfReaderCCCD.setText(docgia.getCCCD());
        tfReaderPhone.setText(docgia.getPhoneNumber());
        tfReaderEmail.setText(docgia.getEmail());
        tfReaderAddress.setText(docgia.getAddress());
        cbReaderStatus.setValue(docgia.getStatus());
    }
}
