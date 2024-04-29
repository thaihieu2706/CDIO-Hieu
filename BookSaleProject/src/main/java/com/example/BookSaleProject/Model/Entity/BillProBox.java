package com.example.BookSaleProject.Model.Entity;

import java.time.LocalDateTime;

public class BillProBox {
    public BillProBox(int id, Bill bill, Book book, int sL, LocalDateTime date) {
        this.id = id;
        this.bill = bill;
        this.book = book;
        SL = sL;
        this.date = date;
    }
    @Override
    public String toString() {
        return "BillProBox [id=" + id + ", bill=" + bill + ", book=" + book + ", SL=" + SL + ", date=" + date + "]";
    }

    private int id;
    private Bill bill;
    private Book book;
    private int SL;
    private LocalDateTime date;

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
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    public int getSL() {
        return SL;
    }
    public void setSL(int sL) {
        SL = sL;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}
