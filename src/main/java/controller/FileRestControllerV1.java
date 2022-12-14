package controller;

import dto.FileDto;
import lombok.SneakyThrows;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.EventService;
import service.FileService;
import utils.FileHelper;
import utils.JsonConverter;
import utils.RequestParser;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FileServlet", urlPatterns = "/api/v1/files/*")

public class FileRestControllerV1 extends HttpServlet {

    private final FileService fileService = new FileService();
    private final EventService eventService = new EventService();

    DiskFileItemFactory factory = new DiskFileItemFactory();
    ServletFileUpload upload = new ServletFileUpload(factory);
    String uploadDirectory = "C:/Users/Пользователь/Desktop/RESTful_v2/src/main/resources/upload/";
    String fileRealName = "";

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        StringBuilder stringBuilder = RequestParser.requestParser(request);
        List<FileDto> fileDtoList = new ArrayList<>();
        if (stringBuilder.toString().equals("*")) {
            for (model.File file: fileService.getAllFiles())
                fileDtoList.add(FileDto.getEntity(file));
            JsonConverter.getJsonStringFromObject(response, fileDtoList);
        } else {
            FileDto fileDto = FileDto.getEntity(fileService.getById(Long.parseLong(stringBuilder.toString())));
            response.getWriter().println(fileDto.getUrl());
        }
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
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
            if (formItems.size() > 0) {
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

        FileHelper.insertFileInfo(request, uploadDirectory, fileRealName);
    }

    @SneakyThrows
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
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
            if (formItems.size() > 0) {
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

        FileHelper.updateFileInfo(request, uploadDirectory, fileRealName);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        StringBuilder id = RequestParser.requestParser(request);

        model.File file = fileService.getById(Long.parseLong(id.toString()));

        File deleteFile = new File(file.getUrl());
        if( deleteFile.exists() )
            deleteFile.delete() ;

        eventService.deleteById(Long.parseLong(id.toString()));
    }
}
