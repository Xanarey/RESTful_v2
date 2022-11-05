package controller.FileTest;

import model.File;
import service.FileService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "FileGetByIdServlet",
        urlPatterns = "/FileGetByIdServlet"
)
public class FileGetByIdServlet extends HttpServlet {

    private final FileService fileService = new FileService();

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fileID = request.getParameter("id");
        if (fileID != null) {
            Long id = Long.parseLong(fileID);
            File file = fileService.getById(id);
            request.setAttribute("fileRecord", file);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/fileView/fileGetAll-record.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
