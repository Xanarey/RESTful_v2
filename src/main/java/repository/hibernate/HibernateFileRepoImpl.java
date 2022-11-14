package repository.hibernate;

import dao.JdbcFileRepo;
import dao.jdbc.jdbcImpl.JdbcFileRepoImpl;
import model.File;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.FileRepo;
import utils.HibernateUtil;

import java.util.List;

public class HibernateFileRepoImpl implements FileRepo {

    private final JdbcFileRepo jdbcFileRepo = new JdbcFileRepoImpl();

    @Override
    public File getById(Long id) {
        return jdbcFileRepo.getById(id);
    }

    @Override
    public List<File> getAll() {
        return jdbcFileRepo.getAll();
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = HibernateUtil.getSession()){
            session.beginTransaction();
            session.remove(session.get(File.class, id));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public File insert(File file) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()){
            transaction = session.beginTransaction();
            File mergeFile = session.merge(file);
            file = mergeFile;
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }
        return file;
    }

    @Override
    public File update(File file) {
        insert(file);
        return file;
    }
}
