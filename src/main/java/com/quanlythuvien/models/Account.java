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
public class Account {
    private IntegerProperty id;
    private StringProperty userName;
    private StringProperty passWord;
    private StringProperty email;
    private ObjectProperty<Date> birthDate;
    private StringProperty gender;
    private IntegerProperty status;
    private StringProperty role;
    
    public Account(int id, String userName, String passWord, String email, 
            Date birthDate, String gender, int status, String role){
        this.id = new SimpleIntegerProperty(id);
        this.userName = new SimpleStringProperty(userName);
        this.passWord = new SimpleStringProperty(passWord);
        this.email = new SimpleStringProperty(email);
        this.birthDate = new SimpleObjectProperty<>(birthDate);
        this.gender = new SimpleStringProperty(gender);
        this.status = new SimpleIntegerProperty(status);
        this.role = new SimpleStringProperty(role);
    }
    
    public int getId() { return this.id.get(); }
    public void setId(int id) { this.id.set(id); }
    
    public String getUserName() { return this.userName.get(); }
    public void setUserName(String userName) {  this.userName.set(userName); }
    
    public String getPassWord() { return this.passWord.get(); }
    public void setPassWord(String passWord) {  this.passWord.set(passWord); }
    
    public String getEmail() { return this.email.get(); }
    public void setEmail(String email) {  this.email.set(email); }
    
    public Date getBirthDate() { return this.birthDate.get(); }
    public void setBirthDate(Date birthDate) { this.birthDate.set(birthDate); }
    
    public String getGender() { return this.gender.get(); }
    public void setGender(String gender) { this.gender.set(gender); }
        
    public int getStatus() { return this.status.get(); }
    public void setStatus(int status) { this.status.set(status); }
    
    public String getRole() { return this.role.get(); }
    public void setRole(String role) { this.role.set(role); }
}
