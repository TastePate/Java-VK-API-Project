package utils;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import models.Group;
import models.Sex;
import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import services.database.GroupService;
import services.database.UserService;
import services.vk.VkService;

public class DatabaseWriter {
    // Массив object[] содержит 4 значения:
    // screen_name, name, members_count, groupId
    private final GroupService groupService = new GroupService();
    private final UserService userService = new UserService();
    private final VkService vk = new VkService();

    public void writeGroupsToDatabase() throws ClientException, ApiException {
        clearTables();

        String[][] groupsInfo = vk.getGroupsInfo();
        System.out.println("Начало записи групп:");
        for (String[] info : groupsInfo) {
            Group group = new Group(info[0], info[1], Integer.parseInt(info[2]));
            groupService.saveGroup(group);
            System.out.println("    Группа успешно записана!");
            var users = vk.getUsersInGroup(Integer.parseInt(info[3]));
            if (users != null) {
                System.out.println("    Начало записи пользователей из группы:");
                users.forEach(user -> {
                    User userToBatabase = new User(user.getId(),
                            user.getFirstName(),
                            user.getLastName(),
                            user.getCountry() != null ? user.getCountry().getTitle() : null,
                            user.getCity() != null ? user.getCity().getTitle() : null,
                            Sex.values()[user.getSex().ordinal()], group);
                    userService.saveUser(userToBatabase);
                    System.out.println("        Запись пользователя прошла успешно!");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ignored) {
                    }
                });
            }
        }
    }

    private void clearTables() {
        this.userService.clearTable();
        this.groupService.clearTable();
        System.out.println("Старые данные успешно удалены!");
    }
}
