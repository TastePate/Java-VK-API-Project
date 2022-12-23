package dao;

import models.Group;
import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class GroupDao {
    public void save(Group group) {
        Session session = HibernateSessionFactoryUtil
                .getSession();
        Transaction tx1 = session.beginTransaction();
        session.save(group);
        tx1.commit();
        session.close();
    }

    public void update(Group group) {
        Session session = HibernateSessionFactoryUtil
                .getSession();
        Transaction tx1 = session.beginTransaction();
        session.update(group);
        tx1.commit();
        session.close();
    }

    public void delete(Group group) {
        Session session = HibernateSessionFactoryUtil.getSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(group);
        tx1.commit();
        session.close();
    }

    public Group findGroupById(int id) {
        return HibernateSessionFactoryUtil
                .getSession()
                .get(Group.class, id);
    }

    public List<Group> findAll() {
        return HibernateSessionFactoryUtil
                .getSession()
                .createQuery("From Group")
                .stream().toList();
    }

    public void clearTable() {
        HibernateSessionFactoryUtil
                .getSession()
                .createQuery("delete from Group");
    }
}