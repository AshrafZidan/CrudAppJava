package com.train.controller;
import com.train.model.User;
import com.train.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UserServlet",value = "/user")
public class UserServlet extends HttpServlet {
    private UserService userService;

    public  UserServlet(){
        this.userService = new UserService();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("actionType");
//        String url = request.getRequestURI();
            if (action == null){
                action="";
            }
          switch (action){
            case "addNew":
                showForm(request , response);
                break;
            case "insert":
                try {
                    insertUser(request , response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "delete":
                try {
                    deleteUser(request , response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "update":
                try {
                    updateUser(request , response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "edit":
                try {
                    showEditForm(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                try {
                    listUser(request,response);
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
                break;

        }
    }

    private void listUser (HttpServletRequest req , HttpServletResponse res) throws SQLException, IOException, ServletException {
    List<User> users = this.userService.getAllUsers();
    req.setAttribute("listUsers", users);
    RequestDispatcher dispatcher = req.getRequestDispatcher("user-list.jsp");
    dispatcher.forward(req , res);

    }
    private void updateUser(HttpServletRequest req , HttpServletResponse res) throws SQLException, IOException {
        int id= Integer.parseInt(req.getParameter("userId"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        User newUser = new User(id , name , email , country);
        this.userService.updatUser(newUser);
        res.sendRedirect("user");
    }

    private void deleteUser(HttpServletRequest req , HttpServletResponse res) throws SQLException, IOException {
        int id= Integer.parseInt(req.getParameter("userId"));

        this.userService.deleteUser(id);
        res.sendRedirect("user");
    }

    private void insertUser(HttpServletRequest req , HttpServletResponse res) throws SQLException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        User newUser = new User(name , email , country);

        this.userService.inserUser(newUser);
        res.sendRedirect("user");
    }

    private  void showForm(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(req , res);

    }

    private  void showEditForm(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException, SQLException {
        int id= Integer.parseInt(req.getParameter("userId"));
        User existingUser = userService.getUserById(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("user-form.jsp");
        req.setAttribute("user", existingUser);
        dispatcher.forward(req , res);

    }
}
