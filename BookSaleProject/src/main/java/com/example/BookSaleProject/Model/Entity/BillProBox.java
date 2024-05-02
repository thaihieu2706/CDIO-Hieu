package com.example.BookSaleProject.Model.Entity;

import java.time.LocalDateTime;

public class BillProBox {

    private int id;
    private Bill bill;
    private CartProBox cartProBox;
    private LocalDateTime date;

    public BillProBox(int id, Bill bill, CartProBox cartProBox, LocalDateTime date) {
        this.id = id;
        this.bill = bill;
        this.cartProBox = cartProBox;
        this.date = date;
    }
    public BillProBox() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Bill getBill() {
        return bill;
    }
    public void setBill(Bill bill) {
        this.bill = bill;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public CartProBox getCartProBox() {
        return cartProBox;
    }
    public void setCartProBox(CartProBox cartProBox) {
        this.cartProBox = cartProBox;
    }
    @Override
    public String toString() {
        return "BillProBox [id=" + id + ", bill=" + bill + ", cartProBox=" + cartProBox + ", date=" + date + "]";
    }

}
