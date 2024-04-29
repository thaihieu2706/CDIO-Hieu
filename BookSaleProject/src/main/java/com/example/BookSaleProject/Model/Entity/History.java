package com.example.BookSaleProject.Model.Entity;


public class History {
    @Override
    public String toString() {
        return "History [id=" + id + ", billProBox=" + billProBox + ", detail=" + detail + "]";
    }
    public History(int id, BillProBox billProBox, String detail) {
        this.id = id;
        this.billProBox = billProBox;
        this.detail = detail;
    }
    public History() {
    }
    private int id;
    private BillProBox billProBox;
    String detail;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public BillProBox getBillProBox() {
        return billProBox;
    }
    public void setBillProBox(BillProBox billProBox) {
        this.billProBox = billProBox;
    }
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    
}
