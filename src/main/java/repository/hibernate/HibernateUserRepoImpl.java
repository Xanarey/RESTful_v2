package repository.hibernate;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.UserRepo;
import repository.jdbc.JdbcImpl.JdbcUserRepoImpl;
import utils.HibernateUtil;

import java.util.List;

public class HibernateUserRepoImpl implements UserRepo {

    private final JdbcUserRepoImpl jdbcUserRepo = new JdbcUserRepoImpl();

    @Override
    public User getById(Long id) {
        return jdbcUserRepo.getById(id);
    }

    @Override
    public List<User> getAll() {
        return jdbcUserRepo.getAll();
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = HibernateUtil.getSession()){
            session.beginTransaction();
            session.remove(session.get(User.class, id));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User insert(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()){
            transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User update(User user) {
        insert(user);
        return user;
    }

}
