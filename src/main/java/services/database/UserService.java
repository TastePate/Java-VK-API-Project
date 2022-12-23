package services.database;

import dao.UserDao;
import models.Group;
import models.User;

import java.util.List;

public class UserService {

    private UserDao usersDao = new UserDao();

    public UserService() {
    }

    public User findUser(int id) {
        return usersDao.findById(id);
    }

    public void saveUser(User user) {
        usersDao.save(user);
    }

    public void deleteUser(User user) {
        usersDao.delete(user);
    }

    public void updateUser(User user) {
        usersDao.update(user);
    }

    public List<User> findAllUsers() {
        return usersDao.findAll();
    }

    public Group findAutoById(int id) {
        return usersDao.findGroupById(id);
    }
    public void clearTable() {
        usersDao.clearTable();
    }

    public String[] getDataForCSV(User user) {
        return new String[] {String.valueOf(user.getVkid()),
                user.getFirstName(),
                user.getLastName(),
                user.getCountry(),
                user.getCity(),
                user.getSex().name(),
        };
    }
}
