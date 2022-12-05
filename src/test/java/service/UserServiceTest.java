package service;

import model.User;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import repository.hibernate.HibernateUserRepoImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {

    @Mock
    private HibernateUserRepoImpl userRepo;
    private final UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
        this.userService = new UserService(userRepo);
    }

    @Test
    public void getById() {
        User userR = new User();
        userR.setName("Ann");
        Mockito.when(userRepo.getById(1L)).thenReturn(userR);
        User userS = userService.getById(1L);

        assertNotNull(userS);
        assertEquals("Ann", userS.getName());
    }

    @Test
    public void getAllUsers() {
        List<User> userRepoList = new ArrayList<>();
        User one = new User();
        one.setName("Carl");
        User two = new User();
        two.setName("Tom");
        userRepoList.add(one);
        userRepoList.add(two);
        Mockito.when(userRepo.getAll()).thenReturn(userRepoList);
        List<User> userList = userService.getAllUsers();

        assertNotNull(userList);
        assertEquals(2, userList.size());
        assertEquals("Tom", userList.get(1).getName());
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setName("Ann");
        Mockito.when(userRepo.update(user)).thenReturn(user);

        assertNotNull(user);
        assertEquals("Ann", user.getName());

        user.setName("Bob");
        userService.updateUser(user);

        assertNotNull(user);
        assertEquals("Bob", user.getName());
    }

    @Test
    public void createUser() {
        User user = new User();
        user.setName("Ann");

        Mockito.when(userRepo.insert(user)).thenReturn(user);
        User userS = userService.createUser(user);

        assertNotNull(userS);
        assertEquals("Ann", userS.getName());
    }

    @Test
    public void deleteById() {
        Mockito.doNothing().when(userRepo).deleteById(1L);

        userService.deleteById(1L);
        Mockito.verify(userRepo).deleteById(1L);
    }
}