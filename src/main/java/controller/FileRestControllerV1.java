package controller;

import com.google.gson.Gson;
import model.File;
import model.User;
import service.FileService;
import utils.JsonConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FileServlet", urlPatterns = "/api/v1/files/*")
public class FileRestControllerV1 extends HttpServlet {

    private final FileService fileService = new FileService();
    private final JsonConverter jsonConverter = new JsonConverter();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") != null) {
            File file = fileService.getById(Long.parseLong(request.getParameter("id")));
            jsonConverter.getJsonStringFromObject(response, file);
        }
        if (request.getParameter("id") == null) {
            List<File> fileList = fileService.getAllFiles();
            jsonConverter.getJsonStringFromObject(response, fileList);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File file = (File) jsonConverter.getObjectFromJsonString(request, File.class);
        fileService.createFile(file);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File file = (File) jsonConverter.getObjectFromJsonString(request, File.class);
        fileService.createFile(file);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        fileService.deleteById(Long.parseLong(request.getParameter("id")));
    }

}
