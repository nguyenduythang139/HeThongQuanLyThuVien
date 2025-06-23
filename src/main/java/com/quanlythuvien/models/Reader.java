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
   public IntegerProperty idProperty() { return id; }
    
   public String getName(){ return name.get(); }
   public StringProperty nameProperty() { return name; }
   
   public String getGender(){ return gender.get(); }
   public StringProperty genderProperty() { return gender; }
   
   public Date getBirthDate(){ return birthDate.get(); }
   public ObjectProperty<Date> birthDateProperty() { return birthDate; }
   
   public String getCCCD(){ return cccd.get(); }
   public StringProperty cccdProperty() { return cccd; }
   
   public String getPhoneNumber(){ return phoneNumber.get(); }
   public StringProperty phoneNumberProperty() { return phoneNumber; }
   
   public String getEmail(){ return email.get(); }
   public StringProperty emailProperty() { return email; }
   
   public String getAddress(){ return address.get(); }
   public StringProperty addressProperty() { return address; }
   
   public String getStatus(){ return status.get(); }
   public StringProperty statusProperty() { return status; }
}
