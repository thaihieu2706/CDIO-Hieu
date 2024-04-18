package com.example.BookSaleProject.Model.Entity;

public class BillProBox {
    @Override
    public String toString() {
        return "BillProBox [id=" + id + ", bill=" + bill + ", book=" + book + ", sL=" + sL + ", Total=" + Total + "]";
    }
    public BillProBox(int id, Bill bill, Book book, int sL, double total) {
        this.id = id;
        this.bill = bill;
        this.book = book;
        this.sL = sL;
        Total = total;
    }
    public BillProBox() {
    }
    private int id;
    private Bill bill;
    private Book book;
    private int sL;
    private double Total;
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
    public int getsL() {
        return sL;
    }
    public void setsL(int sL) {
        this.sL = sL;
    }
    public double getTotal() {
        return Total;
    }
    public void setTotal(double total) {
        Total = total;
    }

	

}
