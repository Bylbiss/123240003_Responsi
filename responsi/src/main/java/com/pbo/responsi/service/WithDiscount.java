/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pbo.responsi.service;

/**
 *
 * @author Lab Informatika
 */
public class WithDiscount implements DiscountStrategy{
    @Override
    public double calculateDiscount(double totalAmount) {
        return totalAmount * (12/100);
    }

    @Override
    public String getDiscountName() {
        return "Discount 12%";
    }
}
