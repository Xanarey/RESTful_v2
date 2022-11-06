package repository.hibernate;

import dao.jdbc.jdbcImpl.JdbcEventRepoImpl;
import model.Event;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.EventRepo;
import utils.HibernateUtil;

import java.util.List;

public class HibernateEventRepoImpl implements EventRepo {

    private final JdbcEventRepoImpl jdbcEventRepo = new JdbcEventRepoImpl();

    @Override
    public Event getById(Long id) {
        return jdbcEventRepo.getById(id);
    }

    @Override
    public List<Event> getAll() {
        return jdbcEventRepo.getAll();
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
