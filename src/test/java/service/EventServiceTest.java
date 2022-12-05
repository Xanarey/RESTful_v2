package service;


import model.Event;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import repository.hibernate.HibernateEventRepoImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EventServiceTest {

    @Mock
    private HibernateEventRepoImpl eventRepo;
    private final EventService eventService;

    public EventServiceTest() {
        MockitoAnnotations.openMocks(this);
        this.eventService = new EventService(eventRepo);
    }

    @Test
    public void getById() {
        Event event = new Event();
        event.setCreated("12.12.12");
        event.setUpdated("13.13.13");
        Mockito.when(eventRepo.getById(1L)).thenReturn(event);
        Event event1 = eventService.getById(1L);

        assertNotNull(event1);
        assertEquals("12.12.12", event1.getCreated());
        assertEquals("13.13.13", event1.getUpdated());
    }

    @Test
    public void getAllEvents() {
        List<Event> eventList = new ArrayList<>();
        Event eventR1 = new Event();
        eventR1.setCreated("12.12.12");
        eventR1.setUpdated("13.13.13");
        Event eventR2 = new Event();
        eventR2.setCreated("14.14.14");
        eventR2.setUpdated("15.15.15");
        eventList.add(eventR1);
        eventList.add(eventR2);
        Mockito.when(eventRepo.getAll()).thenReturn(eventList);
        List<Event> events = eventService.getAllEvents();

        assertNotNull(events);
        assertEquals(2, events.size());
        assertEquals("14.14.14", events.get(1).getCreated());
    }

    @Test
    public void updateEvent() {
        Event eventR1 = new Event();
        eventR1.setCreated("12.12.12");
        eventR1.setUpdated("13.13.13");
        Mockito.when(eventRepo.update(eventR1)).thenReturn(eventR1);

        assertNotNull(eventR1);
        assertEquals("12.12.12", eventR1.getCreated());
        assertEquals("13.13.13", eventR1.getUpdated());

        eventR1.setCreated("14.14.14");
        eventR1.setUpdated("15.15.15");

        assertNotNull(eventR1);
        assertEquals("14.14.14", eventR1.getCreated());
        assertEquals("15.15.15", eventR1.getUpdated());
    }

    @Test
    public void createEvent() {
        Event eventR1 = new Event();
        eventR1.setCreated("12.12.12");
        eventR1.setUpdated("13.13.13");

        Mockito.when(eventRepo.insert(eventR1)).thenReturn(eventR1);
        Event event = eventService.createEvent(eventR1);

        assertNotNull(event);
        assertEquals("12.12.12", event.getCreated());
        assertEquals("13.13.13", event.getUpdated());
    }

    @Test
    public void deleteById() {
        Mockito.doNothing().when(eventRepo).deleteById(1L);

        eventService.deleteById(1L);
        Mockito.verify(eventRepo).deleteById(1L);
    }
}