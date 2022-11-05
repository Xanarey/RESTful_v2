package controller.FileTest;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(
        name = "LoadFileServlet",
        urlPatterns = "/loadFile"
)
@MultipartConfig(location = "C:/Users/Пользователь/Desktop/filestorage")
public class FileUserLoadServlet extends HttpServlet {

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        for (Part part : req.getParts()) {
//            if (part.getName().equals("load-file")) {
//                InputStream inputStream = part.getInputStream();
//                InputStreamReader isr = new InputStreamReader(inputStream);
//                String uploadFile = new BufferedReader(isr)
//                        .lines()
//                        .collect(Collectors.joining("\n"));
//                log(uploadFile);
//            } else {
//                part.write(part.getSubmittedFileName());
//            }
//        }
//        resp.sendRedirect(req.getHeader("referer"));
//    }
}