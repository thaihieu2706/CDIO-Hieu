package com.example.BookSaleProject.Model.Repository;

import java.sql.*;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.BookSaleProject.Model.BaseConnection;
import com.example.BookSaleProject.Model.Entity.Bill;
import com.example.BookSaleProject.Model.Entity.User;

@Repository
public class BillRepository {
    ArrayList<Bill> bills = new ArrayList<>();
    
    @Autowired
    UserRepository userRepository = new UserRepository();

    public ArrayList<Bill> getAll(){
        try {
            bills.clear();
            Class.forName(BaseConnection.nameClass);
            Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                    BaseConnection.password);
            Statement stml = con.createStatement();
            ResultSet resultSet = stml.executeQuery("Select * from BOOKSALE.bill");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                User user = userRepository.getUserById(resultSet.getInt("idUser"));
                Bill bill = new Bill(id, user);
                bills.add(bill);
            }
            con.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return bills;
    }

    public Bill getById(int id){
        try {
            Class.forName(BaseConnection.nameClass);
            Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                    BaseConnection.password);
            PreparedStatement prsm = con.prepareStatement("Select * from BOOKSALE.bill where id=?");
            prsm.setInt(1, id);
            ResultSet resultSet = prsm.executeQuery();
            if(!resultSet.next()){
                throw new IllegalArgumentException("Cannot Find");
            }
            User user = userRepository.getUserById(resultSet.getInt("idUser"));
            Bill bill = new Bill(id, user);
            con.close();
            return bill;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    public Bill getByIdUser(User user){
        try {
            Class.forName(BaseConnection.nameClass);
            Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                    BaseConnection.password);
            PreparedStatement prsm = con.prepareStatement("Select * from BOOKSALE.bill where idUser=?");
            prsm.setInt(1, user.getId() );
            ResultSet resultSet = prsm.executeQuery();
            if(!resultSet.next()){
                throw new IllegalArgumentException("Cannot Find");
            }
            int id = resultSet.getInt("id");
            Bill bill = new Bill(id, user);
            con.close();
            return bill;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    public boolean addNew(Bill bill){
        try {
            Class.forName(BaseConnection.nameClass);
            Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                    BaseConnection.password);
            PreparedStatement prsm = con.prepareStatement("Insert into BOOKSALE.bill (idUser) values (?)");
            prsm.setInt(1,bill.getUser().getId());
            int result = prsm.executeUpdate();
            con.close();
            return result > 0;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return false;
    }
    
}
