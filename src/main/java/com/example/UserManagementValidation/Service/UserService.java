package com.example.UserManagementValidation.Service;

import com.example.UserManagementValidation.Dao.UserRepository;
import com.example.UserManagementValidation.Model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }

    public User getUserById(String id){
        List<User> newUserList = userRepository.getAllUsers();

        for(User user : newUserList){
            if(user.getUserId().equals(id)){
                return user;
            }
        }
        return null;
    }

    public String saveUser(User user){
        userRepository.save(user);
        return "User Saved Successfully!!!";
    }

    public String updateUser(String id, User newUser){
        List<User> newUserList = userRepository.getAllUsers();

        for(User user : newUserList){
            if(user.getUserId().equals(id)){
                userRepository.save(newUser);
            }
        }
        return "user Updated!!!";
    }

    public String deleteUser(String id){
        boolean deleted = false;
        deleted = userRepository.remove(id);
        if(deleted){
            return "User Deleted!!!";
        }
        return "There is no user exist related to this Id!!!";
    }


}