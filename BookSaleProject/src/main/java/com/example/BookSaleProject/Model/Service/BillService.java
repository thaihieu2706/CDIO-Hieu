package com.example.BookSaleProject.Model.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BookSaleProject.Model.Entity.Bill;
import com.example.BookSaleProject.Model.Entity.User;
import com.example.BookSaleProject.Model.Repository.BillRepository;

@Service
public class BillService implements IBillService {
    ArrayList<Bill> bills = new ArrayList<>();

    @Autowired
    BillRepository billRepository = new BillRepository();

    @Override
    public ArrayList<Bill> getAll() {
        this.bills = billRepository.getAll();
        if (!(bills.isEmpty())) {
            return bills;
        }
        return null;
    }

    @Override
    public boolean addNew(Bill bill) {
        if (billRepository.addNew(bill)) {
            return true;
        }
        return false;
    }

    @Override
    public Bill getById(int id) {
        getAll();
        for (Bill bill : bills) {
            if (bill.getId() == id)
                return billRepository.getById(id);
        }
        return null;
    }

    @Override
    public Bill getByIdUser(User user) {
        getAll();
        for (Bill bill : bills) {
            if (bill.getUser().getId() == user.getId())
                return bill;
        }
        return null;
    }

}
