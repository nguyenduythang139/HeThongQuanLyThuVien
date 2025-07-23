package com.quanlythuvien.views;

import com.quanlythuvien.database.DBConnection;
import com.quanlythuvien.models.Account;
import com.quanlythuvien.models.Book;
import com.quanlythuvien.models.BorrowTicket;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import com.quanlythuvien.utils.menuBarComponent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.CheckBoxTableCell;

public class ManageBorrowReturnView {
    private static TableView<BorrowTicket> tbvBorrowTicket;
    private static TextField tfSearch;
    private static ComboBox<Integer> cbReaderId;
    private static DatePicker dpBorrowDate, dpReturnDate;
    private static TextArea taTakeNote;
    private static TableView<Book> tbvSelectBook, tbvReturnBook;
    private static List<Book> lstBook;
    private static ObservableList<BorrowTicket> lstBorrowTicket = FXCollections.observableArrayList();
    private static ObservableList<Book> lstReturnBook = FXCollections.observableArrayList();
    public void start(Stage stage) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        // Thanh menu
        VBox menuBar = menuBarComponent.createMenuBar(stage);

        // Form tao phieu muon
        Label lbTitle = new Label("🔄 Quản Lý Phiếu Mượn - Trả");
        lbTitle.setStyle("-fx-text-fill: #1D774E; -fx-font-size:20");

        Label lbReaderId = new Label("Mã độc giả:");
        cbReaderId = new ComboBox<>();
        cbReaderId.setPromptText("Chọn mã độc giả");
        cbReaderId.setPrefWidth(300);
        VBox vbReaderId = new VBox(3, lbReaderId, cbReaderId);
        takeUserId();

        Label lbBorrowDate = new Label("Ngày mượn:");
        dpBorrowDate = new DatePicker(LocalDate.now());
        dpBorrowDate.setPrefWidth(300);
        VBox vbBorrowDate = new VBox(3, lbBorrowDate, dpBorrowDate);

        Label lbReturnDate = new Label("Hạn trả:");
        dpReturnDate = new DatePicker();
        dpReturnDate.setPromptText("Chọn hạn trả");
        dpReturnDate.setPrefWidth(300);
        VBox vbReturnDate = new VBox(3, lbReturnDate, dpReturnDate);

        Label lbTakeNote = new Label("Ghi chú:");
        taTakeNote = new TextArea();
        taTakeNote.setPromptText("Nhập ghi chú");
        taTakeNote.setPrefSize(300, 50);
        VBox vbTakeNote = new VBox(3, lbTakeNote, taTakeNote);

        Label lbTableSelectBooks = new Label("Bảng chọn sách:");
        tbvSelectBook = new TableView<>();
        tbvSelectBook.setPrefHeight(200);
        tbvSelectBook.setEditable(true);
        tbvSelectBook.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        TableColumn<Book, Integer> colBookId = new TableColumn<>("Mã sách");
        TableColumn<Book, String> colBookName = new TableColumn<>("Tên sách");
        TableColumn<Book, Boolean> colCheck = new TableColumn<>("Chọn");
        
        colBookId.setCellValueFactory((p) -> {
            Book book = p.getValue();
            int bookId = book.getId();
            return new ReadOnlyObjectWrapper<>(bookId);
        });
        
        colBookName.setCellValueFactory((p) -> {
            Book book = p.getValue();
            String bookName = book.getName();
            return new ReadOnlyObjectWrapper<>(bookName);
        });
        
        colCheck.setCellValueFactory(p -> p.getValue().selectedProperty());
        colCheck.setCellFactory((p) -> new CheckBoxTableCell<>());
        
        tbvSelectBook.getColumns().addAll(colCheck, colBookId, colBookName);
        loadDataSelectBook();
        VBox vbTableSelectBooks = new VBox(3, lbTableSelectBooks, tbvSelectBook);

        Button btnCreate = createActionButton("Tạo Phiếu", "#1E56A0");
        Button btnReturn = createActionButton("Trả Sách", "#2E8B57");
        Button btnReset = createActionButton("Reset", "#6A5ACD");
        
        btnCreate.setOnAction(t -> {
            boolean selectedBookIsEmpty = true;
            for (Book b : lstBook){
                if (b.isSelected()){
                    selectedBookIsEmpty = false;
                    break;
                }
            }
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Tạo phiếu mượn");
            
            if (cbReaderId.getValue() == null || dpBorrowDate.getValue() == null 
                    || dpReturnDate.getValue() == null || selectedBookIsEmpty == true)
            {
                alert.setContentText("Vui lòng nhập đủ các trường thông tin!");
                alert.show();
                return;
            }
            LocalDate toDay = LocalDate.now();
            LocalDate borrowDate = dpBorrowDate.getValue();
            LocalDate returnDate = dpReturnDate.getValue();
            if (borrowDate.isBefore(toDay)){
                alert.setContentText("Vui lòng chọn ngày mượn là hôm nay!");
                alert.show();
                return;
            }
            if (returnDate.isBefore(borrowDate)){
                alert.setContentText("Vui lòng chọn hạn trả sau ngày mượn!");
                alert.show();
                return;
            }
            if (returnDate.isAfter(borrowDate.plusDays(7))){
                alert.setContentText("Vui lòng chọn hạn trả không quá 7 ngày!");
                alert.show();
                return;
            }
            createBorrowTicket();
        });
        
        btnReset.setOnAction(t -> resetForm());

        HBox buttonBox = new HBox(20, btnCreate, btnReturn, btnReset);
        buttonBox.setAlignment(Pos.CENTER);

        VBox formBox = new VBox(10, lbTitle, vbReaderId, vbBorrowDate, vbReturnDate, vbTakeNote, vbTableSelectBooks, buttonBox);
        formBox.setPadding(new Insets(20));
        formBox.setPrefWidth(300);
        formBox.setStyle("-fx-background-color: #F8F8F8; -fx-border-color: #ccc;");
        ScrollPane formScrollPane = new ScrollPane(formBox);
        formScrollPane.setFitToWidth(true);

        // Danh sach phieu muon
        tbvBorrowTicket = new TableView<>();
        tbvBorrowTicket.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tbvBorrowTicket.setPlaceholder(new Label("Không có dữ liệu!"));
        
        TableColumn<BorrowTicket, Integer> colTicketId = new TableColumn<>("Mã phiếu");
        colTicketId.setStyle("-fx-alignment:center");
        TableColumn<BorrowTicket, Integer> colReaderId = new TableColumn<>("Mã độc giả");
        colReaderId.setStyle("-fx-alignment:center");
        TableColumn<BorrowTicket, String> colBorrowDate = new TableColumn<>("Ngày mượn");
        colBorrowDate.setStyle("-fx-alignment:center");
        TableColumn<BorrowTicket, String> colReturnDate = new TableColumn<>("Hạn trả");
        colReturnDate.setStyle("-fx-alignment:center");
        TableColumn<BorrowTicket, String> colNote = new TableColumn<>("Ghi chú");
        TableColumn<BorrowTicket, String> colActualReturn = new TableColumn<>("Ngày trả thực tế");
        colActualReturn.setStyle("-fx-alignment:center");
        TableColumn<BorrowTicket, String> colStatus = new TableColumn<>("Trạng thái");
        colStatus.setStyle("-fx-alignment:center");
        
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

        tbvBorrowTicket.getColumns().addAll(colTicketId, colReaderId, colBorrowDate, colReturnDate, colActualReturn, colStatus, colNote);
        loadDataBorrowTicket();
        
        // Thanh tim kiem
        tfSearch = new TextField();
        tfSearch.setPromptText("🔍 Tìm kiếm theo ma phieu muon");
        tfSearch.setPrefWidth(300);
        tfSearch.setOnAction(t -> searchBorrowTicket());

        VBox tableBox = new VBox(10, tfSearch, tbvBorrowTicket);
        tableBox.setPadding(new Insets(20));
        HBox.setHgrow(tableBox, Priority.ALWAYS);

        // Layout chinh
        HBox mainContent = new HBox(formScrollPane, tableBox);
        BorderPane masterLayout = new BorderPane(mainContent, null, null, null, menuBar);

        btnReturn.setOnAction(t -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Trả sách");
            BorrowTicket selectedBorrowTicket = tbvBorrowTicket.getSelectionModel().getSelectedItem();
            if (selectedBorrowTicket == null){              
                alert.setContentText("Vui lòng chọn một phiếu mượn trong bảng!");
                alert.show();
                return;
            }
            if (selectedBorrowTicket.getStatus().equals("Đã trả")){
                alert.setContentText("Sách đã được trả!");
                alert.show();
                return;
            }
            VBox returnFormBox = createReturnFormBox(selectedBorrowTicket, formBox, mainContent);
            mainContent.getChildren().set(0, returnFormBox);
        });
        
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        Scene scene = new Scene(masterLayout, bounds.getWidth(), bounds.getHeight() - 30);
        stage.setScene(scene);
        stage.setTitle("Quản lý mượn - trả");
        stage.show();
    }

    private static void takeUserId(){
        try{
            Connection conn = DBConnection.getConnection();
            if (conn != null){
                String sql = "select * from docgia";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    int maTK = rs.getInt("MaDocGia");
                    cbReaderId.getItems().add(maTK);
                }           
            }
        }
        catch(Exception e){
            System.out.println("Lỗi!");
        }
    }
    
    private static void loadDataSelectBook(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Load dữ liệu bảng chọn sách");
        try{
            Connection conn = DBConnection.getConnection();
            if (conn != null){
                String sql = "select * from sach where tinhtrang = 'Còn' and soluong > 0";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                
                lstBook = new ArrayList<>();
                
                while(rs.next()){
                    int bookId = rs.getInt("MaSach");
                    String bookName = rs.getString("TenSach");
                    Book book = new Book(bookId, bookName);
                    
                    book.selectedProperty().addListener((obs, oldValue, newValue) -> {
                       int selectedBookCount = 0;
                       for (Book b : lstBook){
                           if(b.isSelected()){
                               selectedBookCount++;
                           }
                       }
                       if (selectedBookCount > 3){
                           book.setSelected(false);                          
                           alert.setTitle("Số lượng sách được mượn");
                           alert.setContentText("Chỉ được mượn tối đa 3 quyển sách.");
                           alert.show();
                       }
                    });
                    lstBook.add(book);
                    tbvSelectBook.getItems().add(book);
                }
            }
        }
        catch(Exception e){
            alert.setContentText("Lỗi!");
            alert.show();
        }
    }
    
    private static void loadDataBorrowTicket(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Load dữ liệu borrow ticket");
        try{
            Connection conn = DBConnection.getConnection();
            if (conn != null){
                String sql = "select * from phieumuon";
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                
                lstBorrowTicket.clear();
                while(rs.next()){
                    lstBorrowTicket.add(new BorrowTicket(
                            rs.getInt("MaPhieuMuon"),
                            rs.getInt("MaDocGia"),
                            rs.getDate("NgayMuon"),
                            rs.getDate("HanTra"),
                            rs.getDate("NgayTraThucTe"),
                            rs.getString("TrangThai"),
                            rs.getString("GhiChu")
                    ));
                }
                tbvBorrowTicket.setItems(lstBorrowTicket);
            }
        }
        catch(Exception e){
            alert.setContentText("Lỗi!");
            alert.show();
        }
    }
    
    private static void resetForm(){
        cbReaderId.setValue(null);
        dpBorrowDate.setValue(null);
        dpReturnDate.setValue(null);
        taTakeNote.clear();
    }
    
    
    private static void createBorrowTicket(){
        List<Book> lstBookSelected = new ArrayList<>();
        for (Book b : lstBook){
            if (b.isSelected()){
                lstBookSelected.add(b);
            }
        }
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tạo phiếu mượn");
        
        try{
            Connection conn = DBConnection.getConnection();
            if (conn != null){
                String sqlInsertBT = "insert into phieumuon(MaDocGia, NgayMuon, HanTra, TrangThai, GhiChu) values(?,?,?,'Đang mượn', ?)";
                PreparedStatement psBT = conn.prepareStatement(sqlInsertBT, Statement.RETURN_GENERATED_KEYS);
                psBT.setInt(1, cbReaderId.getValue());
                psBT.setDate(2, java.sql.Date.valueOf(dpBorrowDate.getValue()));
                psBT.setDate(3, java.sql.Date.valueOf(dpReturnDate.getValue()));
                psBT.setString(4, taTakeNote.getText());
                psBT.executeUpdate();
                
                ResultSet rs = psBT.getGeneratedKeys();
                int borrowTicketId = -1;
                if (rs.next()){
                    borrowTicketId = rs.getInt(1);
                }
                
                String sqlInsertBTD = "insert into chitietphieumuon(MaPhieuMuon, MaSach, SoLuong) values(?,?,1)";
                PreparedStatement psBTD = conn.prepareStatement(sqlInsertBTD);
                for (Book b : lstBookSelected){
                    psBTD.setInt(1, borrowTicketId);
                    psBTD.setInt(2, b.getId());
                    psBTD.executeUpdate();
                    
                    String sqlUpdateQuantity = "update sach set SoLuong = Soluong - 1 where MaSach = ?";
                    PreparedStatement psUQ = conn.prepareStatement(sqlUpdateQuantity);
                    psUQ.setInt(1, b.getId());
                    psUQ.executeUpdate();
                }
                loadDataBorrowTicket();  
                alert.setContentText("Tạo phiếu mươn thành công!");
                alert.show();
            }
        }
        catch(Exception e){
            alert.setContentText("Lỗi!");
            alert.show();
        }
    }
    
    private static VBox createReturnFormBox(BorrowTicket borrowTicket, VBox formBox, HBox mainContent){
        Label lbTitle = new Label("🔄 Phiếu xác nhận trả sách");
        lbTitle.setStyle("-fx-text-fill: #1D774E; -fx-font-size:20");

        LocalDate today = LocalDate.now();
        LocalDate returnDate = ((java.sql.Date)borrowTicket.getReturnDate()).toLocalDate();
        final int dayLate = Math.max((int) java.time.temporal.ChronoUnit.DAYS.between(returnDate, today), 0);
        
        Label lbDayLate = new Label("Số ngày trễ: " + dayLate);
        Label lbDayLateFine = new Label("Tiền phạt trễ: " + (dayLate * 20000) + " VNĐ");
        
        Label lbReturnBook = new Label("Danh sách sách hỏng (Tích nếu có):");
        
        tbvReturnBook = new TableView<>();
        tbvReturnBook.setPrefWidth(200);
        tbvReturnBook.setEditable(true);
        tbvReturnBook.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        TableColumn<Book, Integer> colBookId = new TableColumn<>("Mã sách");
        TableColumn<Book, String> colBookName = new TableColumn<>("Tên sách");
        TableColumn<Book, Boolean> colCheck = new TableColumn<>("Chọn");
        
        colBookId.setCellValueFactory((p) -> {
            Book book = p.getValue();
            int bookId = book.getId();
            return new ReadOnlyObjectWrapper<>(bookId);
        });
        
        colBookName.setCellValueFactory((p) -> {
            Book book = p.getValue();
            String bookName = book.getName();
            return new ReadOnlyObjectWrapper<>(bookName);
        });
        
        colCheck.setCellValueFactory(p -> p.getValue().selectedProperty());
        colCheck.setCellFactory(p -> new CheckBoxTableCell<>());
        
        tbvReturnBook.getColumns().addAll(colCheck, colBookId, colBookName);
        loadDataReturnBook(borrowTicket);
        
        Button btnConfirm = createActionButton("Xác nhận trả", "#228B22");
        Button btnCancel = createActionButton("Hủy", "#808080");
        
        btnConfirm.setOnAction(t -> {
            int damagedBookCount = 0;
            for (Book book : lstReturnBook){
                if (book.isSelected()){
                    damagedBookCount++;
                }
            }
            
            int dayLateFine = dayLate * 20000;
            int damagedBookFine = damagedBookCount * 50000;
            int totalFine = dayLateFine + damagedBookFine;
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Trả sách");
            
            try{
                Connection conn = DBConnection.getConnection();
                
                if (conn != null){
                    String sql = "update phieumuon set NgayTraThucTe = ?, TrangThai = 'Đã trả' where MaPhieuMuon = ?";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
                    ps.setInt(2, borrowTicket.getTicketId());
                    int kq  = ps.executeUpdate();
                    
                    if (totalFine > 0){
                        String sqlInsertFT = "insert into phieuphat(MaPhieuMuon, NgayPhat, SoNgayTre, TienPhatTre, SoSachHong, TienPhatHong, TongTienPhat, TrangThai) values(?,?,?,?,?,?,?,'Chưa đóng')";
                        PreparedStatement psFT = conn.prepareStatement(sqlInsertFT);
                        psFT.setInt(1, borrowTicket.getTicketId());
                        psFT.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
                        psFT.setInt(3, dayLate);
                        psFT.setInt(4, dayLateFine);
                        psFT.setInt(5, damagedBookCount);
                        psFT.setInt(6, damagedBookFine);
                        psFT.setInt(7, totalFine);
                        psFT.executeUpdate();
                    }
                    
                    if (kq > 0){
                        alert.setContentText("Trả sách thành công!");
                        alert.show();
                        loadDataBorrowTicket();
                        mainContent.getChildren().set(0, formBox);
                    }
                    else{
                        alert.setContentText("Trả sách thất bại!");
                        alert.show();
                    }          
                }
            }
            catch(Exception e){
                alert.setContentText("Lỗi!");
                alert.show();
            }
        });
        
        btnCancel.setOnAction(t -> {
            mainContent.getChildren().set(0, formBox);
        });
        
        HBox buttonBox = new HBox(20, btnConfirm, btnCancel);
        buttonBox.setAlignment(Pos.CENTER);
        
        VBox returnFormBox = new VBox(10, lbTitle, lbDayLate, lbDayLateFine, lbReturnBook, tbvReturnBook, buttonBox);
        returnFormBox.setPadding(new Insets(20));
        returnFormBox.setStyle("-fx-background-color: #F8F8F8; -fx-border-color: #ccc;");
        returnFormBox.setPrefWidth(300);
        return returnFormBox;
    }
    
    private static void loadDataReturnBook(BorrowTicket borrowTicket){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Load dữ liệu sách trả");
        
        try{
            Connection conn = DBConnection.getConnection();
            if (conn != null){
                String sql = "select a.MaSach, a.TenSach from sach a, chitietphieumuon b where a.MaSach = b.MaSach and b.MaPhieuMuon = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, borrowTicket.getTicketId());
                ResultSet rs = ps.executeQuery();
                
                lstReturnBook.clear();
                while (rs.next()){
                    lstReturnBook.add(new Book(
                                rs.getInt("MaSach"),
                                rs.getString("TenSach")
                    ));              
                }
                tbvReturnBook.setItems(lstReturnBook);
            }
        }
        catch(Exception e){
            alert.setContentText("Lỗi!");
            alert.show();
        }
    }
    
    private static void searchBorrowTicket() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        String keyword = tfSearch.getText().trim();
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM phieumuon WHERE MaPhieuMuon LIKE ? OR TrangThai LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            String likeKeyword = "%" + keyword + "%";
            ps.setString(1, likeKeyword);
            ps.setString(2, likeKeyword);
            ResultSet rs = ps.executeQuery();

            ObservableList<BorrowTicket> lstSearch = FXCollections.observableArrayList();
            while (rs.next()) {
                BorrowTicket borrowTicket = new BorrowTicket(
                    rs.getInt("MaPhieuMuon"),
                    rs.getInt("MaDocGia"),
                    rs.getDate("NgayMuon"),
                    rs.getDate("HanTra"),
                    rs.getDate("NgayTraThucTe"),
                    rs.getString("TrangThai"),
                    rs.getString("GhiChu")
                );
                lstSearch.add(borrowTicket);
            }
            tbvBorrowTicket.setItems(lstSearch);
            if (lstSearch.isEmpty()) {
                alert.setTitle("Kết quả tìm kiếm");
                alert.setContentText("Không tìm thấy phiếu mượn!");
                alert.show();
            }
        } catch (Exception e) {
            System.out.println("Lỗi tìm kiếm!");
        }
    }
    
    private static Button createActionButton(String text, String color) {
        Button btn = new Button(text);
        btn.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white;");
        btn.setPrefWidth(120);
        return btn;
    }
}