package com.example.BookSaleProject.Model.Entity;

public class CartProBox {
    private int idCart;
    private int idBook;
    private int SL;
    public CartProBox(int idCart, int idBook, int sL) {
        this.idCart = idCart;
        this.idBook = idBook;
        SL = sL;
    }
    public CartProBox() {
    }
    public int getIdCart() {
        return idCart;
    }
    public int getIdBook() {
        return idBook;
    }
    public int getSL() {
        return SL;
    }
    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }
    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }
    public void setSL(int sL) {
        SL = sL;
    }
    @Override
    public String toString() {
        return "CartProBox [idCart=" + idCart + ", idBook=" + idBook + ", SL=" + SL + "]";
    }
}
