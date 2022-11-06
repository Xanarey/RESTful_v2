package controller;

import model.Event;
import model.User;
import service.EventService;
import utils.JsonConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(
        name = "EventServlet",
        urlPatterns = "/api/v1/events/*"
)
public class EventRestControllerV1 extends HttpServlet {

    private final EventService eventService = new EventService();
    private final JsonConverter jsonConverter = new JsonConverter();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") != null) {
            Event event = eventService.getById(Long.parseLong(request.getParameter("id")));
            jsonConverter.getJsonStringFromObject(response, event);
        }
        if (request.getParameter("id") == null) {
            List<Event> eventList = eventService.getAllEvents();
            jsonConverter.getJsonStringFromObject(response, eventList);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Event event = (Event) jsonConverter.getObjectFromJsonString(request, Event.class);
        eventService.createEvent(event);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Event event = (Event) jsonConverter.getObjectFromJsonString(request, Event.class);
        eventService.createEvent(event);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        eventService.deleteById(Long.parseLong(request.getParameter("id")));
    }
}
