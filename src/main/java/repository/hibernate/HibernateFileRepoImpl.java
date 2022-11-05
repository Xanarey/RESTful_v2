package repository.hibernate;

import model.File;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.FileRepo;
import utils.HibernateUtil;

import java.util.Collections;
import java.util.List;

public class HibernateFileRepoImpl implements FileRepo {

    @Override
    public File getById(Long id) {
        File file = new File();
        try (Session session = HibernateUtil.getSession()){
            session.get(File.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    @Override
    public List<File> getAll() {
        List<File> fileList;
        Transaction transaction;
        try(Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            fileList = session.createQuery("FROM File", File.class).list();
            transaction.commit();
        } catch (Throwable t) {
            return Collections.emptyList();
        }
        return fileList;
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
            session.merge(file);
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
