package controller;

import dto.EventDto;
import dto.UserDto;
import model.Event;
import model.User;
import service.UserService;
import utils.JsonConverter;
import utils.RequestParser;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = "/api/v1/users/*")
public class UserRestControllerV1 extends HttpServlet {

    private final UserService userService;

    public UserRestControllerV1() {
        userService = new UserService();
    }

    public UserRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder result = RequestParser.requestParser(request);
        List<UserDto> userDtoList = new ArrayList<>();
        if (result.toString().equals("*")) {
            for (User user: userService.getAllUsers())
                userDtoList.add(UserDto.getEntity(user));
            JsonConverter.getJsonStringFromObject(response, userDtoList);
        } else {
            UserDto userDto = UserDto.getEntity(userService.getById(Long.parseLong(RequestParser.requestParser(request).toString())));
            List<EventDto> eventDtoList = new ArrayList<>();

            for (Event event: userService.getById(Long.parseLong(RequestParser.requestParser(request).toString())).getEvents())
                eventDtoList.add(EventDto.getEntity(event));

            userDto.setEventDtoList(eventDtoList);
            JsonConverter.getJsonStringFromObject(response, userDto);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) JsonConverter.getObjectFromJsonString(request, User.class);
        userService.createUser(user);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) JsonConverter.getObjectFromJsonString(request, User.class);
        userService.updateUser(user);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        StringBuilder result = RequestParser.requestParser(request);
        userService.deleteById(Long.parseLong(result.toString()));
    }

}
