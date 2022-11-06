package controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(
        name = "FileManagerServlet",
        urlPatterns = "/api/v1/fileManager/*"
)
public class FileManagerRestControllerV1 extends HttpServlet {

}
