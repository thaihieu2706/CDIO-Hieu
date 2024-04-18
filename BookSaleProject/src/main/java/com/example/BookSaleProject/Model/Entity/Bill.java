package com.example.BookSaleProject.Model.Entity;

import java.sql.Date;


public class Bill {
    @Override
    public String toString() {
        return "Bill [id=" + id + ", user=" + user + ", date=" + date + "]";
    }
    public Bill(int id, User user, Date date) {
        this.id = id;
        this.user = user;
        this.date = date;
    }
    public Bill() {
    }
    private int id;
    private User user;
    private Date date;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    
}
