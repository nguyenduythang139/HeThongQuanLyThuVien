 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlythuvien.models;
import javafx.beans.property.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author admin
 */
public class BorrowTicket {
    private final IntegerProperty ticketId;
    private final IntegerProperty readerId;
    private final ObjectProperty<Date> borrowDate;
    private final ObjectProperty<Date> returnDate;
    private final ObjectProperty<Date> actualReturnDate;
    private final StringProperty note;
    private final StringProperty status;
    private final List<BorrowDetail> borrowDetail;
    

    public BorrowTicket(int ticketId, int readerId, Date borrowDate, Date returnDate, String note, List<BorrowDetail> borrowDetail) {
        this.ticketId = new SimpleIntegerProperty(ticketId);
        this.readerId = new SimpleIntegerProperty(readerId);
        this.borrowDate = new SimpleObjectProperty<>(borrowDate);
        this.returnDate = new SimpleObjectProperty<>(returnDate);
        this.actualReturnDate = new SimpleObjectProperty<>(null); 
        this.note = new SimpleStringProperty(note);
        this.status = new SimpleStringProperty("Đang mượn");
        this.borrowDetail = borrowDetail;
    }

    public int getTicketId() { return ticketId.get(); }
    public void setTicketId(int ticketId) { this.ticketId.set(ticketId); }
    
    public int getReaderId() { return readerId.get(); }
    public void setReaderId(int readerId) { this.readerId.set(readerId); }
    
    public Date getBorrowDate() { return borrowDate.get(); }
    public void setBorrowDate(Date borrowDate) { this.borrowDate.set(borrowDate); }
    
    public Date getReturnDate() { return returnDate.get(); }
    public void setReturnDate(Date returnDate) { this.returnDate.set(returnDate); }
    
    public Date getActualReturnDate() { return actualReturnDate.get(); }
    public void setActualReturnDate(Date actualReturnDate) { this.actualReturnDate.set(actualReturnDate); }
    
    public String getNote() { return note.get(); }
    public void setNote(String note) { this.note.set(note); }
    
    public String getStatus() { return status.get(); }
    public void setStatus(String status) { this.status.set(status); }

    public List<BorrowDetail> getBorrowDetail() { return borrowDetail; }
    
    public String getBookName(){
        StringBuilder bookNames = new StringBuilder();
        for (BorrowDetail bd : borrowDetail){
            if (bookNames.length() > 0) bookNames.append(", ");
            bookNames.append(bd.getBookName());
        }
        return bookNames.toString();
    }
}


