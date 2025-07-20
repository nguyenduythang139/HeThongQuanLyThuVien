/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlythuvien.models;

/**
 *
 * @author admin
 */
public class CurrentAccount {
    private static int id;
    private static String userName;
    private static String role;
    
    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }
    
    public String getUserName() { return this.userName; }
    public void setUserName(String userName) { this.userName = userName; }
    
    public String getRole() { return this.role; }
    public void setRole(String role) { this.role = role; }
    
    public void cleanAccount(){
        this.id = 0;
        this.userName = null;
        this.role = null;
    }
}
