package com.example.BookSaleProject.Model.Repository;

import java.sql.*;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.BookSaleProject.Model.BaseConnection;
import com.example.BookSaleProject.Model.Entity.Bill;
import com.example.BookSaleProject.Model.Entity.BillProBox;
import com.example.BookSaleProject.Model.Entity.CartProBox;

@Repository
public class BillProBoxRepository {
    ArrayList<BillProBox> billProBoxs = new ArrayList<>();


    @Autowired
    BillRepository billRepository = new BillRepository();
    CartProBoxRepository cartProBoxRepository = new CartProBoxRepository();

    public ArrayList<BillProBox> getAll() {
        try {
            billProBoxs.clear();
            Class.forName(BaseConnection.nameClass);
            Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                    BaseConnection.password);
            Statement stml = con.createStatement();
            ResultSet resultSet = stml.executeQuery("Select * from BOOKSALE.billprobox");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Bill bill = billRepository.getById(resultSet.getInt("idBill"));
                CartProBox cartProBox = cartProBoxRepository.getById(resultSet.getInt("idCartProBox"));
                BillProBox billProBox = new BillProBox(id, bill, cartProBox);
                billProBoxs.add(billProBox);
            }
            con.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return billProBoxs;
    }

    public boolean addNew(BillProBox billProBox) {
        try {
            Class.forName(BaseConnection.nameClass);
            Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                    BaseConnection.password);
            PreparedStatement prsm = con
                    .prepareStatement("Insert into BOOKSALE.billprobox (idBill,idCartProBox) values (?,?)");
            prsm.setInt(1, billProBox.getBill().getId());
            prsm.setInt(2, billProBox.getCartProBox().getId());
            int result = prsm.executeUpdate();
            con.close();
            return result > 0;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
        return false;
    }

    public BillProBox getById(int id) {
        try {
            Class.forName(BaseConnection.nameClass);
            Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                    BaseConnection.password);
            PreparedStatement prsm = con.prepareStatement("Select * from BOOKSALE.billprobox where id=?");
            prsm.setInt(1, id);
            ResultSet resultSet = prsm.executeQuery();
            if (!resultSet.next()) {
                throw new IllegalArgumentException("Cannot Find");
            }
            Bill bill = billRepository.getById(resultSet.getInt("idBill"));
            CartProBox cartProBox = cartProBoxRepository.getById(resultSet.getInt("idCartProBox"));
            BillProBox billProBox = new BillProBox(id, bill, cartProBox);
            con.close();
            return billProBox;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
}
