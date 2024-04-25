package com.example.BookSaleProject.Model.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.BookSaleProject.Model.Entity.User;
import com.example.BookSaleProject.Model.Repository.UserRepository;

public class UserService implements IUserService{

    ArrayList<User> userList = new ArrayList<>();

    @Autowired
    UserRepository userRepository = new UserRepository();

    @Override
    public ArrayList<User> getAllUser() {
        this.userList = userRepository.getAllUser();
        if (!(userList.isEmpty())) {
            return userList;
        }
        return null;
    }

    @Override
    public User getUserById(int id) {
        getAllUser();
        for(User user:userList){
            if(user.getId()==id)
                return userRepository.getUserById(id);
            }
        return null;
    }

    @Override
    public boolean update(User user) {
        if (userRepository.update(user)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addNew(User user) {
        if (userRepository.addNew(user)) {
            return true;
        }
        return false;
    }
    
}
