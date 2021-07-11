package com.tms.service;

import com.tms.entity.User;
import com.tms.storage.user.DbUserStorage;

import java.util.List;

public class UserService {
    private final DbUserStorage dbUser = new DbUserStorage();


    public boolean add(User user) {
        if (dbUser.existsByUsername(user.getUsername())) {
            return false;
        } else {
            dbUser.save(user);
            return true;
        }
    }

    public List<User> getAll() {
        return dbUser.findAll();
    }

    public boolean findByUsername(String username) {
       return dbUser.existsByUsername(username);
    }


    public void updateUsername(User user, String username) {
        dbUser.updateUsernameById(user, username);
    }

    public void updatePassword(User user, String password) {
        dbUser.updatePasswordById(user, password);
    }

    public void deleteById(int id) {
        dbUser.deleteById(id);
    }

    public boolean existById(int id) {
        return dbUser.existsById(id);
    }

    public void deleteByUsername(String username) {
        dbUser.deleteByUsername(username);
    }

    public User getByUsername(String username) {
        return dbUser.getByUsername(username);
    }


    public boolean validPassUname(String password, String username) {
        return dbUser.validPassUname(password, username);
    }

    public boolean validUname(String username) {
        return dbUser.validUname(username);
    }

    public boolean validPass(String password) {
        return dbUser.validPass(password);
    }

    public boolean validUserData(String name, String username, String password){
        return dbUser.validUserData(name, username, password);
    }
}
