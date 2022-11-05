package repository.hibernate;

import model.Event;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.EventRepo;
import utils.HibernateUtil;

import java.util.Collections;
import java.util.List;

public class HibernateEventRepoImpl implements EventRepo {

    @Override
    public Event getById(Long id) {
        Event event = new Event();
        try (Session session = HibernateUtil.getSession()){
            event = session.get(Event.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return event;
    }

    @Override
    public List<Event> getAll() {
        List<Event> eventList;
        Transaction transaction;
        try(Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            eventList = session.createQuery("FROM Event ", Event.class).list();
            transaction.commit();
        } catch (Throwable t) {
            return Collections.emptyList();
        }
        return eventList;
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = HibernateUtil.getSession()){
            session.beginTransaction();
            session.remove(session.get(Event.class, id));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Event insert(Event event) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()){
            transaction = session.beginTransaction();
            session.merge(event);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }
        return event;
    }

    @Override
    public Event update(Event event) {
        insert(event);
        return event;
    }
}
