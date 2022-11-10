package controller;

import dao.jdbc.JdbcUserRepo;
import dao.jdbc.jdbcImpl.JdbcUserRepoImpl;
import model.Event;
import model.File;
import model.User;
import repository.EventRepo;
import repository.FileRepo;
import repository.UserRepo;
import repository.hibernate.HibernateEventRepoImpl;
import repository.hibernate.HibernateFileRepoImpl;
import repository.hibernate.HibernateUserRepoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@WebServlet(name = "FileUploadServlet", urlPatterns = "/api/v1/fileUpload/*")
@MultipartConfig(location = "C:/Users/Пользователь/Desktop/fileStorage")
public class FileUploadRestControllerV1 extends HttpServlet {

    private final EventRepo eventRepo = new HibernateEventRepoImpl();
    private final JdbcUserRepo jdbcUserRepo = new JdbcUserRepoImpl();
    private final FileRepo fileRepo = new HibernateFileRepoImpl();
    private final UserRepo userRepo = new HibernateUserRepoImpl();
    private final String timeStamp = new SimpleDateFormat("dd.MM.yy").format(Calendar.getInstance().getTime());

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = jdbcUserRepo.getById(Long.parseLong(request.getParameter("id")));
        Event event = Event.builder().build();
        File file = File.builder().build();

        file.setName(request.getParameter("name"));
        file.setUrl("C:/Users/Пользователь/Desktop/fileDesktop");
        file.setEvent(event);

        event.setCreated(timeStamp);
        event.setFile(file);
        event.setUser(user);

        user.getEvents().add(event);
        userRepo.update(user);



        for (Part part: request.getParts()) part.write(part.getSubmittedFileName());
    }
}

// TODO из параметров взять айдишник и отработать с эвентами и прочими вещами, чтобы при скачивании через указанныый id , вносились соответствующие изменения в БД + при обновлении
// TODO из параметров взять айдишник и отработать с эвентами и прочими вещами, чтобы при изменении файла через указанныый id , вносились соответствующие изменения в БД