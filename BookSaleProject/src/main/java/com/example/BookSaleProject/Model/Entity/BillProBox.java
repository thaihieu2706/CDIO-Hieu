package com.example.BookSaleProject.Model.Entity;

public class BillProBox {
    private int id;
    private int idBill;
    private int idBook;
    private int sL;
    private double Total;
    public BillProBox(int id, int idBill, int idBook, int sL, double total) {
        this.id = id;
        this.idBill = idBill;
        this.idBook = idBook;
        this.sL = sL;
        Total = total;
    }
    public BillProBox() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdBill() {
        return idBill;
    }
    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }
    public int getIdBook() {
        return idBook;
    }
    public void setIdBook(int idBook) {
        this.idBook = idBook;
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
    @Override
    public String toString() {
        return "BillProBox [id=" + id + ", idBill=" + idBill + ", idBook=" + idBook + ", sL=" + sL + ", Total=" + Total
                + "]";
    }
	

}
