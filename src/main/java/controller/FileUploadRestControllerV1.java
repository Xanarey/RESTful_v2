package controller;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@WebServlet(name = "FileUploadServlet", urlPatterns = "/api/v1/fileUpload/*")
@MultipartConfig(location = "/Users/timurbrek/Desktop/localStorage")
public class FileUploadRestControllerV1 extends HttpServlet {

    private final String timeStamp = new SimpleDateFormat("dd.MM.yy").format(Calendar.getInstance().getTime());

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        for (Part part: request.getParts()) part.write(part.getSubmittedFileName());
    }
}

// TODO из параметров взять айдишник и отработать с эвентами и прочими вещами,
//  чтобы при скачивании через указанныый id , вносились соответствующие изменения
//  в БД + при обновлении
// TODO из параметров взять айдишник и отработать с эвентами и прочими вещами,
//  чтобы при изменении файла через указанныый id , вносились соответствующие изменения в БД