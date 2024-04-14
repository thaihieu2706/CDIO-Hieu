package com.example.BookSaleProject.Model.Entity;

import java.sql.Date;


public class Bill {
    private int id;
    private int idCustomer;
    private Date date;
    public Bill(int id, int idCustomer, Date date) {
        this.id = id;
        this.idCustomer = idCustomer;
        this.date = date;
    }
    public Bill() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdCustomer() {
        return idCustomer;
    }
    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return "Bill [id=" + id + ", idCustomer=" + idCustomer + ", date=" + date + "]";
    }
    
}
