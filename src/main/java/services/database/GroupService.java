package services.database;

import dao.GroupDao;
import dao.UserDao;
import models.Group;
import models.User;

import java.util.List;

public class GroupService {
    private GroupDao groupDao = new GroupDao();

    public GroupService() {
    }
    
    public void saveGroup(Group group) {
        groupDao.save(group);
    }

    public void deleteGroup(Group group) {
        groupDao.delete(group);
    }

    public void updateGroup(Group group) {
        groupDao.update(group);
    }
    public List<Group> findAll() {
        return groupDao.findAll();
    }

    public Group findGroupById(int id) {
        return groupDao.findGroupById(id);
    }
    public void clearTable() {
        groupDao.clearTable();
    }
}
