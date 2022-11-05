package controller.FileTest;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(
        name = "UploadFileServlet",
        urlPatterns = "/uploadFile"
)
@MultipartConfig(location = "C:/Users/Пользователь/Desktop/upload")
public class FileUserUploadServlet extends HttpServlet {

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        for (Part part : req.getParts()) {
//            if (part.getName().equals("upload-file")) {
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
