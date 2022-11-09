package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet(name = "FileDownloadServlet", urlPatterns = "/api/v1/fileDownload/*")
@MultipartConfig(location = "C:/Users/Пользователь/Desktop/fileDesktop")
public class FileDownloadRestControllerV1 extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        for (Part part: request.getParts()) part.write(part.getSubmittedFileName());
    }
}
