package com.example.BookSaleProject.Model.Entity;

public class Cart {
    private int id;
    private int idCustomer;
    public Cart(int id, int idCustomer) {
        this.id = id;
        this.idCustomer = idCustomer;
    }
    public Cart() {
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
    @Override
    public String toString() {
        return "Cart [id=" + id + ", idCustomer=" + idCustomer + "]";
    }

}
