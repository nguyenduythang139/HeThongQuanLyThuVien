/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlythuvien.models;

import javafx.beans.property.*;

/**
 *
 * @author admin
 */
public class Fine {
    private IntegerProperty fineId;
    private IntegerProperty ticketId;
    private StringProperty reason;
    private IntegerProperty lateDay;
    private FloatProperty amount;
    private BooleanProperty paid;
    private StringProperty note;
    
    public Fine(int fineId, int ticketId, String reason, int lateDay, float amount, boolean paid, String note){
        this.fineId = new SimpleIntegerProperty(fineId);
        this.ticketId = new SimpleIntegerProperty(ticketId);
        this.reason = new SimpleStringProperty(reason);
        this.lateDay = new SimpleIntegerProperty(lateDay);
        this.amount = new SimpleFloatProperty(amount);
        this.paid = new SimpleBooleanProperty(paid);
        this.note = new SimpleStringProperty(note);
    }
    
    public int getFineId() { return this.fineId.get(); }
    public void setFindId(int findId) { this.fineId.set(findId); }
    
    public int getTicketId() { return this.ticketId.get(); }
    public void setTicketId(int ticketId) { this.ticketId.set(ticketId); }
    
    public String getReason() { return this.reason.get(); }
    public void setReason(String reason) { this.reason.set(reason); }
    
    public int getLateDay() { return this.lateDay.get(); }
    public void setlateDay(int lateDay) { this.lateDay.set(lateDay); }
    
    public float getAmount() { return this.amount.get(); }
    public void setAmount(float amount) { this.amount.set(amount); }
    
    public boolean getPaid() { return this.paid.get(); }
    public void setPaid(boolean paid) { this.paid.set(paid); }
    
    public String getNote() { return this.note.get(); }
    public void setNote(String note) { this.note.set(note); }
}
