/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pbo.responsi.controller;

import com.pbo.responsi.model.CartRepository;
import com.pbo.responsi.view.CartView;

/**
 *
 * @author Lab Informatika
 */
public class CartController {
    private CartRepository repository;
    private CartView view;
    public CartController(CartRepository repository, CartView view){
        this.repository = repository;
        this.view = view;
        
        view.onAdd(e -> handleAdd());
        view.onUpdate(e -> handleUpdate());
        view.onDelete(e -> handleDelete());
        view.onClear(e -> view.clearForm());
        view.onTableSelect(e - handleTableSelect());
        refreshTable;
    }
    
    private void handleAdd(){
        String namaBarang = view.getNameInput();
        Int harga = view.getPriceInput();
        Int qty = view.getQtyInput();
        if (namaBarang.isEmpty()) {
            view.showMessage("Nama Barang tidak boleh kosong");
            return;
        }
        repository.save(new CartItemDTO(namaBarang, harga, qty));
        refreshTable();
        view.clearForm();
    }
    
    private void handleUpdate(){
        String selectedName = view.getSelectedRowItemName();
        if (selectedName == ""){
            view.showMessage("Pilih data terlebih dahulu");
            return;
        }
        CartItem cart = new CartItem(selectedName, view.getNameInput());
        repository.update(cart);
        refreshTable();
        view.clearForm();
    }
    
    private void handleDelete(){
        String selectedName = view.getSelectedRowItemName();
        if (selectedName == ""){
            view.showMessage("Pilih data terlebih dahulu");
            return;
        }
        repository.delete(selectedName);
        refreshTable();
        view.clearForm();
    }
    
    private void refreshTable(){
        view.showCartItems(repository.findAll);
    }
}
