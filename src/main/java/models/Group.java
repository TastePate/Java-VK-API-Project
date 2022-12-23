package models;
import jakarta.persistence.*;
import org.hibernate.collection.spi.PersistentSet;

import java.util.*;

@Entity
@Table(name = "users_groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "screen_name", length = Integer.MAX_VALUE)
    private String screenName;

    @Column(name = "name", length = Integer.MAX_VALUE)
    private String name;

    @Column(name = "members_count")
    private Integer membersCount;

    @OneToMany(mappedBy = "group")
    private PersistentSet<User> usersFromGroup;

    public Group(String screenName, String name, Integer membersCount) {
        this.screenName = screenName;
        this.name = name;
        this.membersCount = membersCount;
    }

    public Group() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMembersCount() {
        return membersCount;
    }

    public void setMembersCount(Integer membersCount) {
        this.membersCount = membersCount;
    }

    public PersistentSet<User> getUsersFromGroup() {
        return usersFromGroup;
    }

    public void setUsersFromGroup(PersistentSet<User> usersFromGroup) {
        this.usersFromGroup = usersFromGroup;
    }

    public void addUserToGroup(User user) {
        this.usersFromGroup.add(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group that = (Group) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(screenName, that.screenName)) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(membersCount, that.membersCount)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (screenName != null ? screenName.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (membersCount != null ? membersCount.hashCode() : 0);
        return result;
    }
}
