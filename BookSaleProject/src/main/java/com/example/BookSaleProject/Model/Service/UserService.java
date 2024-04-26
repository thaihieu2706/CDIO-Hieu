package com.example.BookSaleProject.Model.Service;

import java.util.ArrayList;
import java.util.function.Predicate;

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
        getAllUser();
        for (User user : userList) {
            if(user.getEmail().equals(email))
                return user;
        }
        return null;
    }

    public boolean toLogin(User user){
        getAllUser();
        for (User user1 : userList) {
            if(user1.getEmail().equals(user.getEmail()) && user1.getPassword().equals(user.getPassword()))
            {
                return true;
            }
        }
        return false;
    }

    private static final Predicate<String> EMAIL_VALIDATOR = email -> email.matches("^[\\w\\-\\.]+@([\\w-]+\\.)+[\\w-]{2,}$");

    private static final Predicate<String> USERNAME_VALIDATOR = username -> username.matches("^[a-zA-Z0-9_]+$");

    private static final Predicate<String> PASSWORD_VALIDATOR = password -> password.length() >= 8;

    private static final Predicate<String> PHONE_VALIDATOR = phone -> phone.matches("^\\d{10}$");

    // private static final Predicate<String> NATION_VALIDATOR = nation -> nation.matches("^[a-zA-Z]+$");

    public ArrayList<String> getInvalidAttributes(User user) {
        ArrayList<String> invalidAttributes = new ArrayList<>();
        if (!EMAIL_VALIDATOR.test(user.getEmail())) {
            invalidAttributes.add("Email Error");
        }
        if (!USERNAME_VALIDATOR.test(user.getUsername())) {
            invalidAttributes.add("Username Error");
        }
        if (!PASSWORD_VALIDATOR.test(user.getPassword())) {
            invalidAttributes.add("Password Error");
        }
        if (!PHONE_VALIDATOR.test(user.getSdt())) {
            invalidAttributes.add("Phone Error");
        }
        // if (!NATION_VALIDATOR.test(user.getNation())) {
        //     invalidAttributes.add("Nation Error");
        // }
        return invalidAttributes;
    }
}
