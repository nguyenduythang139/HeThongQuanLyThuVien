/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlythuvien.models;

import java.util.Date;
import javafx.beans.property.*;

/**
 *
 * @author admin
 */
public class Fine {
    private IntegerProperty fineId;
    private IntegerProperty ticketId;
    private ObjectProperty<Date> fineDate;
    private IntegerProperty dayLate;
    private IntegerProperty dayLateFine;
    private IntegerProperty damagedBook;
    private IntegerProperty damagedBookFine;
    private IntegerProperty totalFine;
    private StringProperty statusFine;
    
    public Fine(int fineId, int ticketId, Date fineDate, int dayLate, int dayLateFine, 
            int damagedBook, int damagedBookFine, int totalFine, String statusFine){
        this.fineId = new SimpleIntegerProperty(fineId);
        this.ticketId = new SimpleIntegerProperty(ticketId);
        this.fineDate = new SimpleObjectProperty<Date>(fineDate);
        this.dayLate = new SimpleIntegerProperty(dayLate);
        this.dayLateFine = new SimpleIntegerProperty(dayLateFine);
        this.damagedBook = new SimpleIntegerProperty(damagedBook);
        this.damagedBookFine = new SimpleIntegerProperty(damagedBookFine);
        this.totalFine = new SimpleIntegerProperty(totalFine);
        this.statusFine = new SimpleStringProperty(statusFine);
    }
    
    public int getFineId() { return this.fineId.get(); }
    public void setFindId(int findId) { this.fineId.set(findId); }
    
    public int getTicketId() { return this.ticketId.get(); }
    public void setTicketId(int ticketId) { this.ticketId.set(ticketId); }
    
    public Date getFineDate() { return this.fineDate.get(); }
    public void setFineDate(Date fineDate) { this.fineDate.set(fineDate); }
    
    public int getDayLate() { return this.dayLate.get(); }
    public void setDayLate(int dayLate) { this.dayLate.set(dayLate); }
    
    public int getDayLateFine() { return this.dayLateFine.get(); }
    public void setDayLateFine(int dayLateFine) { this.dayLateFine.set(dayLateFine); }
    
    public int getDamagedBook() { return this.damagedBook.get(); }
    public void setDamagedBook(int damagedBook) { this.damagedBook.set(damagedBook); }
    
    public int getDamagedBookFine() { return this.damagedBookFine.get(); }
    public void setDamagedBookFine(int damagedBookFine) { this.damagedBookFine.set(damagedBookFine); }
    
    public int getTotalFine() { return this.totalFine.get(); }
    public void setTotalFine(int totalFine) { this.totalFine.set(totalFine); }
        
    public String getStatusFine() { return this.statusFine.get(); }
    public void setStatusFine(String statusFine) { this.statusFine.set(statusFine); }
}
