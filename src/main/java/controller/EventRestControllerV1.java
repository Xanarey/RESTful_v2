package controller;

import dto.EventDto;
import model.Event;
import service.EventService;
import utils.JsonConverter;
import utils.RequestParser;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EventServlet", urlPatterns = "/api/v1/events/*")
public class EventRestControllerV1 extends HttpServlet {

    private final EventService eventService = new EventService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder result = RequestParser.requestParser(request);
        List<EventDto> eventDtoList = new ArrayList<>();
        if (result.toString().equals("*")) {
            for (Event event: eventService.getAllEvents())
                eventDtoList.add(EventDto.getEntity(event));
            JsonConverter.getJsonStringFromObject(response, eventDtoList);
        } else {
            EventDto eventDto = EventDto.getEntity(eventService.getById(Long.parseLong(RequestParser.requestParser(request).toString())));
            JsonConverter.getJsonStringFromObject(response, eventDto);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Event event = (Event) JsonConverter.getObjectFromJsonString(request, Event.class);
        eventService.createEvent(event);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Event event = (Event) JsonConverter.getObjectFromJsonString(request, Event.class);
        Event currentEvent = eventService.getById(Long.valueOf(RequestParser.requestParser(request).toString()));
        event.setId(currentEvent.getId());
        event.setUser(currentEvent.getUser());// TODO не работает как нужно
        event.setFile(currentEvent.getFile());// TODO не работает как нужно
        eventService.updateEvent(event);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        StringBuilder result = RequestParser.requestParser(request);
        eventService.deleteById(Long.parseLong(result.toString()));
    }
}
