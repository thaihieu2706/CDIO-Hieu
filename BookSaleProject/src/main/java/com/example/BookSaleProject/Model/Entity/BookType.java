package com.example.BookSaleProject.Model.Entity;

public class BookType {
    private int id;
    private String name;
    public BookType(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public BookType() {
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
    @Override
    public String toString() {
        return "BookType [id=" + id + ", name=" + name + "]";
    }
}
