 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlythuvien.models;
import javafx.beans.property.*;
import java.util.Date;
/**
 *
 * @author admin
 */
public class BorrowTicket {
    private IntegerProperty ticketId;
    private IntegerProperty readerId;
    private ObjectProperty<Date> borrowDate;
    private ObjectProperty<Date> returnDate;
    private ObjectProperty<Date> actualReturnDate;
    private StringProperty note;
    private StringProperty status;

    public BorrowTicket(int ticketId, int readerId, Date borrowDate, Date returnDate, Date actualReturnDate, String status, String note) {
        this.ticketId = new SimpleIntegerProperty(ticketId);
        this.readerId = new SimpleIntegerProperty(readerId);
        this.borrowDate = new SimpleObjectProperty<>(borrowDate);
        this.returnDate = new SimpleObjectProperty<>(returnDate);
        this.actualReturnDate = new SimpleObjectProperty<>(actualReturnDate); 
        this.status = new SimpleStringProperty(status);
        this.note = new SimpleStringProperty(note);
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
}


