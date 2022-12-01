package utils;

import model.Event;
import model.User;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileHelper {

    public static UserService userService = new UserService();
    public static void insertFileInfo(HttpServletRequest request, String uploadDirectory, String fileRealName) {
        User user = userService.getById(Long.valueOf(request.getHeader("id")));
        model.File file = new model.File();
        Event event = new Event();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String time = localDateTime.format(formatter);

        file.setName(fileRealName);
        file.setUrl(uploadDirectory + fileRealName);

        event.setCreated(time);
        event.setUpdated(null);
        event.setUser(user);
        event.setFile(file);

        user.getEvents().add(event);
        userService.updateUser(user);
    }
}
