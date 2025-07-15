/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlythuvien.database;
import java.sql.*;
/**
 *
 * @author admin
 */
public class DBConnection {
    public static Connection getConnection(){
        Connection conn = null;
        try{
            String url = "jdbc:mysql://localhost:3306/thuvien";
            String user = "root";
            String pass = "root";
            return conn = DriverManager.getConnection(url, user, pass);
        }
        catch (Exception e){
            System.out.println("Ket noi CSDL that bai!");
        }
        return conn;
    }
}
