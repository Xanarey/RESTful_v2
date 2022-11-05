package service;

import model.User;
import repository.UserRepo;
import repository.hibernate.HibernateUserRepoImpl;

import java.util.List;

public class UserService {

    private final UserRepo userRepo = new HibernateUserRepoImpl();

    public User getById(Long id) {
        return userRepo.getById(id);
    }

    public List<User> getAllUsers() {return userRepo.getAll();}

    public User updateUser(User user) {return userRepo.update(user);}

    public User createUser(User user) {return userRepo.insert(user);}

    public void deleteById(Long id) {userRepo.deleteById(id);}

}
