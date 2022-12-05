package service;

import model.Event;
import repository.EventRepo;
import repository.hibernate.HibernateEventRepoImpl;

import java.util.List;

public class EventService {

    private final EventRepo eventRepo;

    public EventService() {
        eventRepo = new HibernateEventRepoImpl();
    }

    public EventService(EventRepo eventRepo) {
        this.eventRepo = eventRepo;
    }

    public Event getById(Long id) {
        return eventRepo.getById(id);
    }

    public List<Event> getAllEvents() {return eventRepo.getAll();}

    public Event updateEvent(Event event) {return eventRepo.update(event);}

    public Event createEvent(Event event) {return eventRepo.insert(event);}

    public void deleteById(Long id) {eventRepo.deleteById(id);}

}
