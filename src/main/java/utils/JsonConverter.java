package utils;

import com.google.gson.Gson;
import model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;

public class JsonConverter {

    private static final Gson GSON = new Gson();

    public static void getJsonStringFromObject(HttpServletResponse response, Object object) throws IOException {
        String jsonString = GSON.toJson(object);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json; charset=UTF-8");
        out.print(jsonString);
        out.flush();
    }

    public static Object getObjectFromJsonString(HttpServletRequest request, Object object) throws IOException {
        StringBuilder content;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
            content = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                content.append(line);
            }
        }
        String con = String.valueOf(content);
        return GSON.fromJson(con, (Type) object);
    }

    public static String getJsonStringFromObject(Object object) throws IOException {
        return GSON.toJson(object);
    }


}
