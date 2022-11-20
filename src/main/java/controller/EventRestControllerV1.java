package controller;

import model.Event;
import model.User;
import service.EventService;
import utils.JsonConverter;
import utils.RequestParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EventServlet", urlPatterns = "/api/v1/events/*")
public class EventRestControllerV1 extends HttpServlet {

    private final EventService eventService = new EventService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder result = RequestParser.requestParser(request);
        if (result.toString().equals("*")) {
            List<Event> eventList = eventService.getAllEvents();
            JsonConverter.getJsonStringFromObject(response, eventList);
        } else {
            Event event = eventService.getById(Long.parseLong(result.toString()));
            JsonConverter.getJsonStringFromObject(response, event);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Event event = (Event) JsonConverter.getObjectFromJsonString(request, Event.class);
        eventService.createEvent(event);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Event event = (Event) JsonConverter.getObjectFromJsonString(request, Event.class);
        eventService.createEvent(event);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        eventService.deleteById(Long.parseLong(request.getParameter("id")));
    }
}
