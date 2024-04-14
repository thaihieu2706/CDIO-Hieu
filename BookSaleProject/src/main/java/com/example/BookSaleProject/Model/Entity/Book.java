package com.example.BookSaleProject.Model.Entity;

import java.sql.Date;

public class Book {
    private int id;
    private String name;
    private String author;
    private BookType bookType;
    private Date date;
    private String nxb;
    private double price;
    private int SL;
    private String img;

    // Constructor
    public Book(int id, String name, String author, BookType bookType, Date date, String nxb, double price, int SL,String img) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.bookType = bookType;
        this.date = date;
        this.nxb = nxb;
        this.price = price;
        this.SL = SL;
        this.img=img;
    }

    public Book() {
    }


    // toString method
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", bookType=" + bookType +
                ", date=" + date +
                ", nxb='" + nxb + '\'' +
                ", price=" + price +
                ", SL=" + SL +
                ", img=" + img +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BookType getBookType() {
        return bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNxb() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb = nxb;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSL() {
        return SL;
    }

    public void setSL(int sL) {
        SL = sL;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
