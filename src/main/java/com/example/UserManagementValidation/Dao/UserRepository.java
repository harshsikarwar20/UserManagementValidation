package com.example.UserManagementValidation.Dao;

import com.example.UserManagementValidation.Model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    List<User> UserList;

    public UserRepository() {
        UserList = new ArrayList<>();
    }

    public List<User> getAllUsers(){
        return UserList;
    }

    public void save(User user){
        UserList.add(user);
    }

    public boolean remove(String id){
        for(User user : UserList){
            if(user.getUserId().equals(id)){
                UserList.remove(user);
                return true;
            }
        }
        return false;
    }
}