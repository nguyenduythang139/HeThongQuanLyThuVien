/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlythuvien.views;

import com.quanlythuvien.database.DBConnection;
import com.quanlythuvien.utils.menuBarComponent;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import com.quanlythuvien.models.Fine;
import java.sql.*;
import java.util.Date;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;

/**
 *
 * @author admin
 */
public class ManageFine {
    private static TextField tfSearch;
    private static ObservableList<Fine> lstFine = FXCollections.observableArrayList();
    private static TableView<Fine> tbvFine;
    public void start(Stage stage){
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        // Thanh menu
        VBox menuBar = menuBarComponent.createMenuBar(stage);
        
        // Bang hien thi danh sach phieu phat
        Label lbTitle = new Label("💸 Quản lý nộp phạt");
        lbTitle.setStyle("-fx-text-fill: #1D774E; -fx-font-size: 20");
        
        tbvFine = new TableView<>();
        tbvFine.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tbvFine.setPlaceholder(new Label("Không có dữ liệu!"));
        
        TableColumn<Fine, Integer> colFineId = new TableColumn<>("Mã phiếu phạt");
        colFineId.setStyle("-fx-alignment:center");
        TableColumn<Fine, Integer> colTicketId = new TableColumn<>("Mã phiếu mượn");
        colTicketId.setStyle("-fx-alignment:center");
        TableColumn<Fine, Object> colFineDate = new TableColumn<>("Ngày phạt");
        colFineDate.setStyle("-fx-alignment:center");
        TableColumn<Fine, Integer> colDayLate = new TableColumn<>("Số ngày trễ");
        colDayLate.setStyle("-fx-alignment:center");
        TableColumn<Fine, Integer> colDayLateFine = new TableColumn<>("Tiền phạt trễ");
        colDayLateFine.setStyle("-fx-alignment:center");
        TableColumn<Fine, Integer> colDamagedBook = new TableColumn<>("Số sách hỏng");
        colDamagedBook.setStyle("-fx-alignment:center");
        TableColumn<Fine, Integer> colDamagedBookFine = new TableColumn<>("Tiền phạt hỏng");
        colDamagedBookFine.setStyle("-fx-alignment:center");
        TableColumn<Fine, Integer> colTotalFine = new TableColumn<>("Tổng phạt");
        colTotalFine.setStyle("-fx-alignment:center");
        TableColumn<Fine, String> colStatusFine = new TableColumn<>("Trạng thái");
        colStatusFine.setStyle("-fx-alignment:center");
        
        colFineId.setCellValueFactory((p) -> {
            Fine fine = p.getValue();
            int fineId = fine.getFineId();
            return new ReadOnlyObjectWrapper(fineId);
        });
        
        colTicketId.setCellValueFactory((p) -> {
            Fine fine = p.getValue();
            int ticketId = fine.getTicketId();
            return new ReadOnlyObjectWrapper(ticketId);
        });
        
        colFineDate.setCellValueFactory((p) -> {
            Fine fine = p.getValue();
            Date fineDate = fine.getFineDate();
            return new ReadOnlyObjectWrapper<>(fineDate);
        });
        
        colDayLate.setCellValueFactory((p) -> {
            Fine fine = p.getValue();
            int dayLate = fine.getDayLate();
            return new ReadOnlyObjectWrapper<>(dayLate);
        });
        
        colDayLateFine.setCellValueFactory((p) -> {
            Fine fine = p.getValue();
            int dayLateFine = fine.getDayLateFine();
            return new ReadOnlyObjectWrapper<>(dayLateFine);
        });
        
        colDamagedBook.setCellValueFactory((p) -> {
            Fine fine = p.getValue();
            int damagedBook = fine.getDamagedBook();
            return new ReadOnlyObjectWrapper<>(damagedBook);
        });
        
        colDamagedBookFine.setCellValueFactory((p) -> {
            Fine fine = p.getValue();
            int damagedBookFine = fine.getDamagedBookFine();
            return new ReadOnlyObjectWrapper<>(damagedBookFine);
        });
        
        colTotalFine.setCellValueFactory((p) -> {
            Fine fine = p.getValue();
            int totalFine = fine.getTotalFine();
            return new ReadOnlyObjectWrapper<>(totalFine);
        });
        
        colStatusFine.setCellValueFactory((p) -> {
            Fine fine = p.getValue();
            String statusFine = fine.getStatusFine();
            return new ReadOnlyObjectWrapper<>(statusFine);
        });
        
        tbvFine.getColumns().addAll(colFineId, colTicketId, colFineDate, 
                                    colDayLate, colDayLateFine, colDamagedBook, colDamagedBookFine, colTotalFine, colStatusFine);
        loadDataFine();
        
        Button btnPaid = new Button("Xác nhận nộp phạt");
        btnPaid.setStyle("-fx-background-color: #1E56A0; -fx-text-fill: white");
        btnPaid.setOnAction(t -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nộp phạt");
            Fine selectedFine = tbvFine.getSelectionModel().getSelectedItem();
            if (selectedFine == null){
                alert.setContentText("Vui lòng chọn một phiếu phạt trong bảng!");
                alert.show();
                return;
            }
            if (selectedFine.getStatusFine().equals("Đã đóng")){
                alert.setContentText("Phiếu phạt đã đóng tiền!");
                alert.show();
                return;
            }
            payFine(selectedFine);
        });
        
        tfSearch = new TextField();
        tfSearch.setPromptText("🔍 Tìm kiếm mã phiếu phạt");
        tfSearch.setOnAction(t -> searchFine());
        
        VBox mainContent = new VBox(10, lbTitle, tfSearch, tbvFine, btnPaid);
        mainContent.setPadding(new Insets(20));
        
        // Layout chinh
        BorderPane masterLayout = new BorderPane(mainContent, null, null, null, menuBar);
        
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        Scene scene = new Scene(masterLayout, bounds.getWidth(), bounds.getHeight() - 30);
        stage.setScene(scene);
        stage.setTitle("Quản lý nộp phạt");
        stage.show();
    }
    
    private static void loadDataFine(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Load dữ liệu phiếu phạt");
        try{
            Connection conn = DBConnection.getConnection();
            if (conn != null){
                String sql = "select * from phieuphat";
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                
                lstFine.clear();
                while (rs.next()){
                    lstFine.add(new Fine(
                            rs.getInt("MaPhieuPhat"),
                            rs.getInt("MaPhieuMuon"),
                            rs.getDate("NgayPhat"),
                            rs.getInt("SoNgayTre"),
                            rs.getInt("TienPhatTre"),
                            rs.getInt("SoSachHong"),
                            rs.getInt("TienPhatHong"),
                            rs.getInt("TongTienPhat"),
                            rs.getString("TrangThai")
                    ));
                }
                tbvFine.setItems(lstFine);
            }
        }
        catch(Exception e){
            alert.setContentText("Load dữ liệu lỗi!");
            alert.show();
        }
    }
    
    private static void payFine(Fine selectedFine){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Nộp phạt");
        try{
            Connection conn = DBConnection.getConnection();
            if (conn != null){
                String sql = "update phieuphat set TrangThai = 'Đã đóng' where MaPhieuPhat = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, selectedFine.getFineId());
                int kq = ps.executeUpdate();
                if (kq > 0){
                    alert.setContentText("Nộp phạt thành công!");
                    alert.show();
                    loadDataFine();
                }
                else{
                    alert.setContentText("Nộp phạt thất bại!");
                    alert.show();
                }
            }
        }
        catch(Exception e){
            alert.setContentText("Lỗi nộp phạt!");
            alert.show();
        }
    }
    
    private static void searchFine() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        String keyword = tfSearch.getText().trim();
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM phieuphat WHERE MaPhieuPhat LIKE ? OR TrangThai LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            String likeKeyword = "%" + keyword + "%";
            ps.setString(1, likeKeyword);
            ps.setString(2, likeKeyword);
            ResultSet rs = ps.executeQuery();

            ObservableList<Fine> lstSearch = FXCollections.observableArrayList();
            while (rs.next()) {
                Fine fine = new Fine(
                    rs.getInt("MaPhieuPhat"),
                    rs.getInt("MaPhieuMuon"),
                    rs.getDate("NgayPhat"),
                    rs.getInt("SoNgayTre"),
                    rs.getInt("TienPhatTre"),
                    rs.getInt("SoSachHong"),
                    rs.getInt("TienPhatHong"),
                    rs.getInt("TongTienPhat"),
                    rs.getString("TrangThai")
                );
                lstSearch.add(fine);
            }
            tbvFine.setItems(lstSearch);
            if (lstSearch.isEmpty()) {
                alert.setTitle("Kết quả tìm kiếm");
                alert.setContentText("Không tìm thấy phiếu phạt!");
                alert.show();
            }
        } catch (Exception e) {
            System.out.println("Lỗi tìm kiếm!");
        }
    }
}
