package controller;

import lombok.SneakyThrows;
import model.User;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import service.UserService;
import utils.JsonConverter;
import utils.RequestParser;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserRestControllerV1Test extends Mockito{

    @Mock
    private UserService userService;
    private UserRestControllerV1 userRestControllerV1;
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);

    public UserRestControllerV1Test() {
        MockitoAnnotations.openMocks(this);
        this.userRestControllerV1 = new UserRestControllerV1(userService);
    }

    @BeforeEach// проверить конвертер

    @SneakyThrows
    @Test
    public void doGet() {

            // <-- List<User> users getAll() -->

        List<User> userList = new ArrayList<>();
        User a = new User();
        a.setId(1L);
        a.setName("Ann");
        User b = new User();
        b.setId(2L);
        b.setName("Tom");
        userList.add(a);
        userList.add(b);

        when(userService.getAllUsers()).thenReturn(userList);
        when(request.getRequestURI()).thenReturn("/api/v1/users/*");

        List<User> users = new ArrayList<>();

        StringBuilder resultAll = RequestParser.requestParser(request);
        if (resultAll.toString().equals("*"))
            users = userService.getAllUsers();

        assertEquals(userList, users);

            // <-- User getById(Long id) -->

        User c = new User();
        c.setId(3L);
        c.setName("Harry");

        when(userService.getById(3L)).thenReturn(c);
        when(request.getRequestURI()).thenReturn("/api/v1/users/3");

        User d = new User();

        StringBuilder resultOne = RequestParser.requestParser(request);
        if (resultOne.toString().equals("3"))
            d = userService.getById(3L);

        assertEquals(c, d);


    }

    @Test
    public void doPost() throws IOException {
        User a = new User();
        a.setId(1L);
        a.setName("Tom");



        String jsonString = JsonConverter.getJsonStringFromObject(a);

        when(JsonConverter.getObjectFromJsonString(request, User.class)).thenReturn(a); // как запихнуть строку в реквест ?


    }

    @Test
    public void doPut() {
    }

    @Test
    public void doDelete() {
    }
}