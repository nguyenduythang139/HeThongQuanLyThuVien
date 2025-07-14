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
public class Reader {
    private IntegerProperty id;
    private StringProperty name;
    private StringProperty gender;
    private ObjectProperty<Date> birthDate;
    private StringProperty cccd;
    private StringProperty phoneNumber;
    private StringProperty email;
    private StringProperty address;
    private StringProperty status;

    public Reader(int id, String name, String gender, Date birthDate, String cccd, String phoneNumber, String email, String address, String status) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.gender = new SimpleStringProperty(gender);
        this.birthDate = new SimpleObjectProperty(birthDate);
        this.cccd = new SimpleStringProperty(cccd);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.email = new SimpleStringProperty(email);
        this.address = new SimpleStringProperty(address);
        this.status = new SimpleStringProperty(status);
    }

   public int getId(){ return id.get(); }
   public void setId(int id) { this.id.set(id); }
    
   public String getName(){ return name.get(); }
   public void setName(String name) { this.name.set(name); }
   
   public String getGender(){ return gender.get(); }
   public void setGender(String gender) { this.gender.set(gender); }
   
   public Date getBirthDate(){ return birthDate.get(); }
   public void setBirthDate(Date birthDate) { this.birthDate.set(birthDate); }
   
   public String getCCCD(){ return cccd.get(); }
   public void setCCCD(String cccd) { this.cccd.set(cccd); }
   
   public String getPhoneNumber(){ return phoneNumber.get(); }
   public void setPhoneNumber(String phoneNumber) { this.phoneNumber.set(phoneNumber); }
   
   public String getEmail(){ return email.get(); }
   public void setEmail(String email) { this.email.set(email); }
   
   public String getAddress(){ return address.get(); }
   public void setAddress(String address) { this.address.set(address); }
   
   public String getStatus(){ return status.get(); }
   public void setStatus(String status) { this.status.set(status); }
}
