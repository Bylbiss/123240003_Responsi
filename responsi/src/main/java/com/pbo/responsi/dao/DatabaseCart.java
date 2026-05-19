/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pbo.responsi.dao;

import com.pbo.responsi.database.DatabaseConnection;
import com.pbo.responsi.dto.CartItemDTO;
import com.pbo.responsi.model.CartRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.UIManager;

/**
 *
 * @author Lab Informatika
 */
public class DatabaseCart implements CartRepository {

    @Override
    public List<CartItemDTO> findAll() {
        List<CartItemDTO> carts = new ArrayList<>();
        String sql = "SELECT * FROM keranjang";
        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                CartItemDTO cartList = new CartItemDTO(rs.getInt("id_barang"), rs.getString("nama_barang"), rs.getInt("harga"), rs.getInt("qty"));
                carts.add(cartList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carts;
    }

    @Override
    public void save(CartItemDTO item) {
        String sql = "INSERT INTO keranjang (nama_barang, harga, qty) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, item.getName());
            pstmt.setInt(2, (int) item.getPrice());
            pstmt.setInt(3, item.getQuantity());
            pstmt.executeUpdate();
            return;
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }

    @Override
    public void updateQuantity(String name, int newQty) {
        String sql = "UPDATE keranjang SET nama_barang = ? , qty = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, newQty);
//           pstmt.setInt (3,)
            pstmt.executeUpdate();
            return;
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }

    @Override
    public void delete(String name) {
        String sql = "DELETE FROM keranjang WHERE nama = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            return;
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }
}
