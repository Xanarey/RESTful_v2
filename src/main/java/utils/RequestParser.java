package utils;

import javax.servlet.http.HttpServletRequest;

public class RequestParser {

    public static StringBuilder requestParser(HttpServletRequest request) {
        String temp;
        String req = request.getRequestURI();
        StringBuilder result = new StringBuilder();
        for (int i = req.length() - 1; i > 0; i--) {
            temp = String.valueOf(req.charAt(i));
            if (temp.equals("/")) break;
            result.append(temp);
        }
        result.reverse();
        return result;
    }

}
