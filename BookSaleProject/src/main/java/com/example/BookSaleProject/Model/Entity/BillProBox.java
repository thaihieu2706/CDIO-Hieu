package com.example.BookSaleProject.Model.Entity;


public class BillProBox {

    private int id;
    private Bill bill;
    private CartProBox cartProBox;
    public BillProBox(int id, Bill bill, CartProBox cartProBox) {
        this.id = id;
        this.bill = bill;
        this.cartProBox = cartProBox;
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

    public CartProBox getCartProBox() {
        return cartProBox;
    }
    public void setCartProBox(CartProBox cartProBox) {
        this.cartProBox = cartProBox;
    }
    @Override
    public String toString() {
        return "BillProBox [id=" + id + ", bill=" + bill + ", cartProBox=" + cartProBox + "]";
    }


}
