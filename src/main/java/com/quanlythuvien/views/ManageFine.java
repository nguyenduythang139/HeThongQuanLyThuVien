/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlythuvien.views;

import com.quanlythuvien.utils.menuBarComponent;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import com.quanlythuvien.models.Fine;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;

/**
 *
 * @author admin
 */
public class ManageFine {
    public void start(Stage stage){
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        VBox menuBar = menuBarComponent.createMenuBar(stage);
        
        TableView<Fine> tbvFine = new TableView<>();
        tbvFine.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tbvFine.setPlaceholder(new Label("Không có dữ liệu!"));
        
        TableColumn<Fine, Integer> colFineId = new TableColumn<>("Mã phiếu phạt");
        TableColumn<Fine, Integer> colTicketId = new TableColumn<>("Mã phiếu mượn");
        TableColumn<Fine, String> colReason = new TableColumn<>("Lý do phạt");
        TableColumn<Fine, Integer> colLateDay = new TableColumn<>("Số ngày trễ");
        TableColumn<Fine, Float> colAmount = new TableColumn<>("Mức phạt");
        TableColumn<Fine, Boolean> colPaid = new TableColumn<>("Đã đóng");
        TableColumn<Fine, String> colNote = new TableColumn<>("Ghi chú");
        
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
        
        colReason.setCellValueFactory((p) -> {
            Fine fine = p.getValue();
            String reason = fine.getReason();
            return new ReadOnlyObjectWrapper(reason);
        });
        
        colLateDay.setCellValueFactory((p) -> {
            Fine fine = p.getValue();
            int lateDay = fine.getLateDay();
            return new ReadOnlyObjectWrapper(lateDay);
        });
        
        colAmount.setCellValueFactory((p) -> {
            Fine fine = p.getValue();
            float amount = fine.getAmount();
            return new ReadOnlyObjectWrapper(amount);
        });
        
        colPaid.setCellValueFactory((p) -> {
            Fine fine = p.getValue();
            boolean paid = fine.getPaid();
            return new ReadOnlyObjectWrapper(paid);
        });
        
        colNote.setCellValueFactory((p) -> {
            Fine fine = p.getValue();
            String note = fine.getNote();
            return new ReadOnlyObjectWrapper(note);
        });
        
        tbvFine.getColumns().addAll(colFineId, colTicketId, colReason, 
                                    colLateDay, colAmount, colPaid, colNote);
        
        Button btnPaid = new Button("Xác nhận nộp phạt");
        
        TextField tfSearch = new TextField();
        tfSearch.setPromptText("🔍 Tìm kiếm mã phiếu phạt");
        
        VBox mainContent = new VBox(10, tfSearch, tbvFine, btnPaid);
        mainContent.setPadding(new Insets(20));
        
        BorderPane masterLayout = new BorderPane(mainContent, null, null, null, menuBar);
        
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        Scene scene = new Scene(masterLayout, bounds.getWidth(), bounds.getHeight() - 30);
        stage.setScene(scene);
        stage.setTitle("Quản lý nộp phạt");
        stage.show();
    }
}
