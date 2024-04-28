package com.example.BookSaleProject.Model.Entity;



public class Bill {
    
    
    @Override
    public String toString() {
        return "Bill [id=" + id + ", idUser=" + idUser + "]";
    }
    public Bill(int id, int idUser) {
        this.id = id;
        this.idUser = idUser;
    }
    public Bill() {
    }
    private int id;
    private int idUser;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public int getIdUser() {
        return idUser;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    

    
}
