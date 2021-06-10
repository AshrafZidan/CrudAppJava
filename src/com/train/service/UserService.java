package com.train.service;

import com.train.dao.UserDao;
import com.train.model.User;

import java.sql.SQLException;
import java.util.List;


public class UserService {

    private UserDao userDao;

    public UserService(){
        userDao = new UserDao();

    }
    public void inserUser(User user) throws SQLException {
        userDao.inserUser(user);

    }

    public User getUserById(int id) throws SQLException {
        return userDao.getUserById(id);
    }

    public void deleteUser(int id) throws SQLException {
        userDao.deleteUser(id);
    }

    public List<User> getAllUsers() throws SQLException {

        return this.userDao.getAllUsers();
    }

    public void updatUser(User newUser) throws SQLException {
        this.userDao.updateUser(newUser);
    }
}
