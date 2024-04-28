package com.example.BookSaleProject.Model.Entity;

import java.sql.Date;


public class Bill {
    
    @Override
    public String toString() {
        return "Bill [id=" + id + ", cartProBox=" + cartProBox + ", date=" + date + "]";
    }
    public Bill() {
    }
    private int id;
    private CartProBox cartProBox;
    private Date date;
    public Bill(int id, CartProBox cartProBox, Date date) {
        this.id = id;
        this.cartProBox = cartProBox;
        this.date = date;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public CartProBox getCartProBox() {
        return cartProBox;
    }
    public void setCartProBox(CartProBox cartProBox) {
        this.cartProBox = cartProBox;
    }

    
}
