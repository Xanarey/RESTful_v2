package controller;

import dto.EventDto;
import dto.FileDto;
import dto.UserDto;
import lombok.SneakyThrows;
import model.Event;
import model.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.FileService;
import service.UserService;
import utils.RequestParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FileServlet", urlPatterns = "/api/v1/files/*")

public class FileRestControllerV1 extends HttpServlet {

    private final FileService fileService = new FileService();
    private final UserService userService = new UserService();

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder stringBuilder = RequestParser.requestParser(request);
        if (stringBuilder.toString().equals("*")) {
            List<String> list = new ArrayList<>();
            for (model.File file: fileService.getAllFiles())
                list.add(FileDto.getEntity(file).getUrl());
            response.getWriter().println(list);
        } else {
            FileDto fileDto = FileDto.getEntity(fileService.getById(Long.parseLong(stringBuilder.toString())));
            response.getWriter().println(fileDto.getUrl());
        }
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        String uploadDirectory = "C:/Users/Пользователь/Desktop/RESTful_v2/src/main/resources/upload/";
        String fileRealName = "";

        if (ServletFileUpload.isMultipartContent(request)) {
            factory.setSizeThreshold(1024 * 1024);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            upload.setFileSizeMax(1024 * 1024 * 5);
            upload.setSizeMax(1024 * 1024 * 5 * 5);
            String uploadPath = getServletContext().getRealPath("")
                    + File.separator + uploadDirectory;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
        }

        if (ServletFileUpload.isMultipartContent(request)) {
            List<FileItem> formItems = upload.parseRequest(request);
            fileRealName = formItems.get(0).getName();
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadDirectory + File.separator + fileName;
                        File storeFile = new File(filePath);
                        item.write(storeFile);
                    }
                }
            }
        }






        User user = userService.getById(Long.valueOf(request.getHeader("id")));
        model.File file = new model.File();
        Event event = new Event();


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
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





















    @SneakyThrows
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder id = RequestParser.requestParser(request);
        String url = fileService.getById(Long.parseLong(id.toString())).getUrl();

        File deleteFile = new File(url);
        if( deleteFile.exists() )
            deleteFile.delete() ;
    }

}
