package com.example.BookSaleProject.Model.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BookSaleProject.Model.Entity.User;
import com.example.BookSaleProject.Model.Repository.UserRepository;

@Service
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
    
    public User login(String email){
        userList.clear();
        userList = userRepository.getAllUser();
        for (User user : userList) {
            if(user.getEmail().equals(email))
                return user;
        }
        return null;
    }

    public boolean toLogin(User user){
        getAllUser();
        for (User user1 : userList) {
            if(user1.getEmail().equals(user.getEmail())&&user1.getPassword().equals(user.getPassword()))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        UserService u = new UserService();
        System.out.println(u.toLogin(new User(0, null, "john@123", "john.doe@example.com", null, null, 0)));
    }
}
