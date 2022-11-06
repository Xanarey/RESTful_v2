package controller;

import model.User;
import service.UserService;
import utils.JsonConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(
        name = "UserServlet",
        urlPatterns = "/api/v1/users/*"
)
public class UserRestControllerV1 extends HttpServlet {

    private final UserService userService = new UserService();
    private final JsonConverter jsonConverter = new JsonConverter();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") != null) {
            User user = userService.getById(Long.parseLong(request.getParameter("id")));
            jsonConverter.getJsonStringFromObject(response, user);
        }
        if (request.getParameter("id") == null) {
            List<User> userList = userService.getAllUsers();
            jsonConverter.getJsonStringFromObject(response, userList);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) jsonConverter.getObjectFromJsonString(request, User.class);
        userService.createUser(user);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) jsonConverter.getObjectFromJsonString(request, User.class);
        userService.updateUser(user);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userService.deleteById(Long.parseLong(request.getParameter("id")));
    }
}
