package controller;

import lombok.SneakyThrows;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.FileService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FileServlet", urlPatterns = "/api/v1/files/*")

public class FileRestControllerV1 extends HttpServlet {

    private final FileService fileService = new FileService();

    DiskFileItemFactory factory = new DiskFileItemFactory();
    ServletFileUpload upload = new ServletFileUpload(factory);
    String ud = "C:/Users/Пользователь/Desktop/RESTful_v2/src/main/resources/upload";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (ServletFileUpload.isMultipartContent(request)) {
            factory.setSizeThreshold(1024 * 1024);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));


            upload.setFileSizeMax(1024 * 1024 * 5);
            upload.setSizeMax(1024 * 1024 * 5 * 5);
            String uploadPath = getServletContext().getRealPath("")
                    + File.separator + ud;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
        }

        if (ServletFileUpload.isMultipartContent(request)) {
            //...
            List<FileItem> formItems = upload.parseRequest(request);
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = ud + File.separator + fileName;
                        File storeFile = new File(filePath);
                        item.write(storeFile);
                        request.setAttribute("message", "File "
                                + fileName + " has uploaded successfully!");
                    }
                }
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
