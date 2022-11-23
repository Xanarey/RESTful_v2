package controller;

import model.User;
import service.UserService;
import utils.JsonConverter;
import utils.RequestParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = "/api/v1/users/*")
public class UserRestControllerV1 extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder result = RequestParser.requestParser(request);
        if (result.toString().equals("*")) {
            List<User> userList = userService.getAllUsers();
            JsonConverter.getJsonStringFromObject(response, userList);
        } else {
            User user = userService.getById(Long.parseLong(result.toString()));
            JsonConverter.getJsonStringFromObject(response, user);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) JsonConverter.getObjectFromJsonString(request, User.class);
        userService.createUser(user);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) JsonConverter.getObjectFromJsonString(request, User.class);
        userService.updateUser(user);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder result = RequestParser.requestParser(request);
        userService.deleteById(Long.parseLong(result.toString()));
    }

}
