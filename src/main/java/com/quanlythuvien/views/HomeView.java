package com.quanlythuvien.views;


import com.quanlythuvien.models.CurrentAccount;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.quanlythuvien.utils.menuBarComponent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.quanlythuvien.models.Statistical;

public class HomeView{
    private static CurrentAccount currentAccount = new CurrentAccount();
    public void start(Stage stage) {
        // Thanh menu
        VBox menuBar = menuBarComponent.createMenuBar(stage);

        // Thẻ thống kê
        String tongSoSach = String.valueOf(Statistical.tkSach());
        String docGiaDaDK = String.valueOf(Statistical.tkDocGia());
        String luotMuonHN = String.valueOf(Statistical.tkLuotMuonHomNay());
        String phieuMuonDHD = String.valueOf(Statistical.tkPhieuMuonDangHD());
        String sachDangMuon = String.valueOf(Statistical.tkSachDangMuon());
        String sachQuaHan = String.valueOf(Statistical.tkSachQuaHan());
        
        VBox card1 = createHeaderCard("📚", "Tổng số sách", tongSoSach);
        VBox card2 = createHeaderCard("👥", "Độc giả đã đăng ký", docGiaDaDK);
        VBox card3 = createHeaderCard("📅", "Lượt mượn hôm nay", luotMuonHN);
        VBox card4 = createHeaderCard("📄", "Phiếu mượn đang hoạt động", phieuMuonDHD);
        VBox card5 = createHeaderCard("📕", "Sách đang mượn", sachDangMuon);
        VBox card6 = createHeaderCard("⏰", "Sách quá hạn", sachQuaHan);
        
        HBox cardsSection1 = new HBox(20, card1, card2, card3);
        HBox cardsSection2 = new HBox(20, card4, card5, card6);
        cardsSection1.setAlignment(Pos.CENTER);
        cardsSection2.setAlignment(Pos.CENTER);

        // Lời chào và thời gian
        Label welcome = new Label();
        if (currentAccount.getRole().equals("Admin")){
            welcome.setText("👋 Xin chào, Admin");
        }
        else{
            welcome.setText("👋 Xin chào, thủ thư " + currentAccount.getUserName());
        }
        welcome.setStyle("-fx-font-size: 20; -fx-text-fill: #2C3E50");
        Label time = new Label("📆 Hôm nay: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        time.setStyle("-fx-font-size: 20; -fx-text-fill: grey");
        VBox greeting = new VBox(10, welcome, time);
        greeting.setAlignment(Pos.CENTER_LEFT);
        greeting.setPadding(new Insets(0,0,0,30));
        
        
        //list sach
        HBox listsach1 = new HBox(50);
        ImageView sach1 = new ImageView(new Image(getClass()
                .getResourceAsStream("/Images/sach_nhungtamlongcaoca.jpg")));
        sach1.setFitWidth(120);
        sach1.setFitHeight(200);
        
        ImageView sach2 = new ImageView(new Image(getClass()
                .getResourceAsStream("/Images/sach_tuoitredanggia.jpg")));
        sach2.setFitWidth(120);
        sach2.setFitHeight(200);
        
        ImageView sach3 = new ImageView(new Image(getClass()
                .getResourceAsStream("/Images/sach_demen.jpg")));
        sach3.setFitWidth(120);
        sach3.setFitHeight(200);
        
        ImageView sach4 = new ImageView(new Image(getClass()
                .getResourceAsStream("/Images/sach_dieubimat.jpg")));
        sach4.setFitWidth(120);
        sach4.setFitHeight(200);
        
        ImageView sach5 = new ImageView(new Image(getClass()
                .getResourceAsStream("/Images/sach_dongian.jpg")));
        sach5.setFitWidth(120);
        sach5.setFitHeight(200);
        
        ImageView sach6 = new ImageView(new Image(getClass()
                .getResourceAsStream("/Images/sach_hoangtube.jpg")));
        sach6.setFitWidth(120);
        sach6.setFitHeight(200);
        
        listsach1.setAlignment(Pos.CENTER);
        listsach1.getChildren().addAll(sach1, sach2, sach3, sach4, sach5, sach6);
        
        //listsach 2
        HBox listsach2 = new HBox(50);
        ImageView sach7 = new ImageView(new Image(getClass()
                .getResourceAsStream("/Images/sach_medieuky.jpg")));
        sach7.setFitWidth(120);
        sach7.setFitHeight(200);
        
        ImageView sach8 = new ImageView(new Image(getClass()
                .getResourceAsStream("/Images/sach_ngoinha.jpg")));
        sach8.setFitWidth(120);
        sach8.setFitHeight(200);
        
        ImageView sach9 = new ImageView(new Image(getClass()
                .getResourceAsStream("/Images/sach_songbungno.jpg")));
        sach9.setFitWidth(120);
        sach9.setFitHeight(200);
        
        ImageView sach10 = new ImageView(new Image(getClass()
                .getResourceAsStream("/Images/sach_sonnam.jpg")));
        sach10.setFitWidth(120);
        sach10.setFitHeight(200);
        
        ImageView sach11 = new ImageView(new Image(getClass()
                .getResourceAsStream("/Images/sach_timbinhyentronggiadinh.png")));
        sach11.setFitWidth(120);
        sach11.setFitHeight(200);
        
        ImageView sach12 = new ImageView(new Image(getClass()
                .getResourceAsStream("/Images/sach_tuduy.jpg")));
        sach12.setFitWidth(120);
        sach12.setFitHeight(200);
        
        listsach2.setAlignment(Pos.CENTER);
        listsach2.getChildren().addAll(sach7, sach8, sach9, sach10, sach11, sach12);
        
        Label deco1 = new Label("GỢI Ý HÔM NAY");
        deco1.setStyle("-fx-font-size: 22");
        
        Label deco2 = new Label("SÁCH ĐƯỢC YÊU THÍCH GẦN ĐÂY");
        deco2.setStyle("-fx-font-size: 22");
        
        ImageView imageView = new ImageView(new Image(getClass()
                .getResourceAsStream("/Images/library1.jpg")));
        imageView.setFitWidth(1000);
        imageView.setFitHeight(550);
       
        VBox mainContent = new VBox(greeting, cardsSection1, cardsSection2, imageView, deco1, listsach1, deco2, listsach2);
        mainContent.setAlignment(Pos.CENTER);
        mainContent.setStyle("-fx-background-color: white;");
        mainContent.setPadding(new Insets(20));
        mainContent.setSpacing(20);

        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color:transparent;");

        // Layout chinh
        BorderPane rootLayout = new BorderPane();
        rootLayout.setLeft(menuBar);
        rootLayout.setCenter(scrollPane);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        Scene scene = new Scene(rootLayout, bounds.getWidth(), bounds.getHeight() - 30);
        stage.setScene(scene);
        stage.setTitle("Trang chủ - Hệ thống Quản lý Thư viện");
        stage.show();
    }
    

    private VBox createHeaderCard(String icon, String label, String value) {
        Label iconLabel = new Label(icon);
        iconLabel.setFont(Font.font(30));
        Label title = new Label(label);
        title.setFont(Font.font(13));
        title.setTextFill(Color.WHITE);
        Label quantity = new Label(value);
        quantity.setFont(Font.font(18));
        quantity.setTextFill(Color.WHITE);
        VBox card = new VBox(5, iconLabel, title, quantity);
        card.setAlignment(Pos.CENTER);
        card.setPrefSize(190, 100);
        card.setStyle("-fx-background-color: #00927A;-fx-border-radius: 10; -fx-background-radius: 10; -fx-border-color: #E6F4EC;");
        card.setPadding(new Insets(10));
        return card;
    }
}
