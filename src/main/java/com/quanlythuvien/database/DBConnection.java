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
    static String url="jdbc:mysql://localhost:3306/thuvien";
    static String user="root";
    static String pass="root";
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, user, pass);
    }
}
