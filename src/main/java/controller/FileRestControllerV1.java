package controller;

import model.Event;
import model.File;
import service.FileService;
import utils.JsonConverter;
import utils.RequestParser;

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
        StringBuilder result = RequestParser.requestParser(request);
        if (result.toString().equals("*")) {
            List<File> fileList = fileService.getAllFiles();
            jsonConverter.getJsonStringFromObject(response, fileList);
        } else {
            File file = fileService.getById(Long.parseLong(result.toString()));
            jsonConverter.getJsonStringFromObject(response, file);
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
        fileService.updateFile(file);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        fileService.deleteById(Long.parseLong(request.getParameter("id")));
    }

}
