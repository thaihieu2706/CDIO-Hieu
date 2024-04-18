package com.example.BookSaleProject.Model.Entity;

public class Rate {
    public Rate(int id, User user, Book book) {
        this.id = id;
        this.user = user;
        this.book = book;
    }
    public Rate() {
    }
    @Override
    public String toString() {
        return "Rate [id=" + id + ", user=" + user + ", book=" + book + "]";
    }
    private int id;
    private User user;
    private Book book;
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
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
}
