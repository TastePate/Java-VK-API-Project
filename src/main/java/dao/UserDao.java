package dao;

import models.Group;
import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;
import java.util.List;

public class UserDao {
    public User findById(int id) {
        return HibernateSessionFactoryUtil
                .getSession()
                .get(User.class, id);
    }

    public void save(User user) {
        Session session = HibernateSessionFactoryUtil
                .getSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void update(User user) {
        Session session = HibernateSessionFactoryUtil
                .getSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void delete(User user) {
        Session session = HibernateSessionFactoryUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    public Group findUserById(int id) {
        return HibernateSessionFactoryUtil
                .getSession()
                .get(Group.class, id);
    }

    public List<User> findAll() {
        return (List<User>)  HibernateSessionFactoryUtil
                .getSession()
                .createQuery("From User")
                .list();
    }

    public void clearTable() {
        HibernateSessionFactoryUtil
                .getSession()
                .createQuery("delete from User");
    }
}
