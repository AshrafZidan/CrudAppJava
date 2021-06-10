package com.train.dao;

import com.train.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private String jdbcUrl = "jdbc:mysql://localhost:3306/demo?allowPublicKeyRetrieval=true&useSSL=false";
    private String jdbcUserName = "root";
    private String jdbcPassword = "root";

    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String INSERT_USER = "INSERT INTO users " + "(name , email , country) values (? , ? , ?)";
    private static final String DELETE_USERS_SQL = "delete from users where id = ?";
    private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =? where id = ?;";
    private static final String SELECT_USER_BY_ID = "select id,name,email,country from users where id =?";

    String myDriver = "com.mysql.jdbc.Driver";
//    String myDriver = "com.mysql.cj.jdbc.Driver";
//  String myDriver =  "oracle.jdbc.driver.OracleDriver";
    private Connection getConnection(){
        Connection connection = null;
            try {
                Class.forName(myDriver);
                connection = DriverManager.getConnection(jdbcUrl, jdbcUserName, jdbcPassword);
            }catch (SQLException e){
                e.printStackTrace();
            }
            catch (ClassNotFoundException e){
                e.printStackTrace();
            }

        return connection;
    }

    public void inserUser(User user) throws SQLException {
        Connection connection = getConnection();


        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_USER);
            statement.setString(1,user.getName());
            statement.setString(2,user.getEmail());
            statement.setString(3,user.getCountry());

                statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            connection.close();
        }
    }

    public boolean updateUser(User user) throws SQLException {
        Connection connection = getConnection();
        boolean rowUpdated = false;


        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);
            statement.setString(1,user.getName());
            statement.setString(2,user.getEmail());
            statement.setString(3,user.getCountry());
            statement.setInt(4,user.getId());
            rowUpdated = statement.executeUpdate() > 0;
//            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            connection.close();
        }
        return rowUpdated;

    }

    public User getUserById(int id) throws SQLException {
        User user= null;
        Connection connection = getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");

                user = new User(id , name , email ,country);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            connection.close();
        }
        return user;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        Connection connection = getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");

                users.add(new User(id, name , email ,country));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {


            connection.close();
        }
        return users;
    }

    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (
             Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);
             ) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}
