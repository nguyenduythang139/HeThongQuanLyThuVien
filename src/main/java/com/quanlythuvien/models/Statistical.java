/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlythuvien.models;

import com.quanlythuvien.database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Nguyễn Hữu Phước
 */
public class Statistical {
     public static int tkSach() {
        String sql = "SELECT COUNT(*) FROM sach";
        return getCount(sql);
    }

    public static int tkDocGia() {
        String sql = "SELECT COUNT(*) FROM docgia";
        return getCount(sql);
    }

    public static int tkLuotMuonHomNay() {
        String sql = "SELECT COUNT(*) FROM phieumuon WHERE DATE(NgayMuon) = CURRENT_DATE";
        return getCount(sql);
    }

    public static int tkPhieuMuonDangHD() {
        String sql = "SELECT COUNT(*) FROM phieumuon WHERE TrangThai = 'Đang mượn'";
        return getCount(sql);
    }

    public static int tkSachDangMuon() {
        String sql = "SELECT SUM(SoLuong) FROM chitietphieumuon";
        return getCount(sql);
    }

    public static int tkSachQuaHan() {
        String sql = "SELECT COUNT(*) FROM phieuphat";
        return getCount(sql);
    }

    private static int getCount(String sql) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
