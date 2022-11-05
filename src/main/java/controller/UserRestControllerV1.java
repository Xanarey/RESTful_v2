package controller;

import com.google.gson.Gson;
import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(
        name = "UserServlet",
        urlPatterns = "/api/v1/users/*"
)
public class UserRestControllerV1 extends HttpServlet {

    private final UserService userService = new UserService();
    private final Gson GSON = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") != null) {
            User user = userService.getById(Long.parseLong(request.getParameter("id")));
            jsonConverter(response, user);
        }
        if (request.getParameter("id") == null) {
            List<User> userList = userService.getAllUsers();
            jsonConverter(response, userList);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = getUserFromJsonString(request);
        userService.createUser(user);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = getUserFromJsonString(request);
        userService.updateUser(user);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userService.deleteById(Long.parseLong(request.getParameter("id")));
    }

    private void jsonConverter(HttpServletResponse response, Object object) throws IOException {
        String jsonString = GSON.toJson(object);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json; charset=UTF-8");
        out.print(jsonString);
        out.flush();
    }

    private User getUserFromJsonString(HttpServletRequest request) throws IOException {
        StringBuilder content;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
            content = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                content.append(line);
            }
        }
        String con = String.valueOf(content);
        return GSON.fromJson(con, User.class);
    }

    // сделать общим

}
