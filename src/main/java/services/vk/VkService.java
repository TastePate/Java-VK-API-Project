package services.vk;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiAccessException;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.groups.responses.GetByIdObjectLegacyResponse;
import com.vk.api.sdk.objects.groups.responses.GetMembersResponse;
import com.vk.api.sdk.objects.users.Fields;
import com.vk.api.sdk.objects.users.responses.GetResponse;
import jakarta.persistence.Tuple;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class VkService {
    private final int id = 183339911;
    private final VkApiClient vk;
    private final String token = "vk1.a.dRr5XdZrCZiMovQTOOp0jSUe5rWwhzpck_c-yu6xt4-GV-7gYH4lZ6rcpYCytmcPWnve-1CI6sdFolGRkLcdZX9XSH8FHOx5iqhY2tiZU6qldylZ-3p9pNfi_b5AggHSH0t8y4RdsgphtIEgTdz9ClOe8ZgDt5faBBJMWyOefuEMe73K55Sfup-ca4QxXNgfIDThv1Vzee7O2kOPQuT2jQ";
    private final UserActor userActor;

    public VkService() {
        TransportClient transportClient = new HttpTransportClient();
        vk = new VkApiClient(transportClient);
        userActor = new UserActor(id, token);
    }

    private List<String> getGroupsString() throws ClientException, ApiException {
        return vk
                .groups()
                .get(userActor)
                .execute()
                .getItems()
                .stream()
                .map(String::valueOf)
                .collect(Collectors.toList());
    }

    private List<GetByIdObjectLegacyResponse> getGroupsIdResponse() throws ClientException, ApiException {
        List<String> groups = getGroupsString();
        return vk
                .groups()
                .getByIdObjectLegacy(userActor)
                .fields(com.vk.api.sdk.objects.groups.Fields.MEMBERS_COUNT)
                .groupIds(groups)
                .execute();
    }

    private GetMembersResponse getMembersResponse(int groupId){
        try {
            return vk
                    .groups()
                    .getMembers(userActor)
                    .groupId(String.valueOf(groupId))
                    .execute();
        } catch (ApiException | ClientException e) {
            System.out.println("Доступ к членам группы скрыт!!");
        }
        return null;
    }

    public String[][] getGroupsInfo() throws ClientException, ApiException {
        List<GetByIdObjectLegacyResponse> groups = getGroupsIdResponse();
        String[][] groupsInfo = new String[groups.size()][4];
        for (int i = 0, size = groups.size(); i < size; i++) {
            var group = groups.get(i);
            groupsInfo[i] = new String[]{group.getScreenName(),
                    group.getName(),
                    String.valueOf(group.getMembersCount()),
                    String.valueOf(group.getId())};
        }
        return groupsInfo;
    }

    public List<GetResponse> getUsersInGroup(int groupId) throws ClientException, ApiException {
        GetMembersResponse response = getMembersResponse(groupId);
        if (response != null) {
            List<String> membersIds = response
                    .getItems()
                    .stream()
                    .map(String::valueOf)
                    .toList();

            return vk
                    .users()
                    .get(userActor)
                    .fields(Fields.FIRST_NAME_ABL, Fields.LAST_NAME_ABL, Fields.COUNTRY, Fields.CITY, Fields.SEX)
                    .userIds(membersIds)
                    .execute();
        }
        return null;
    }

//    public void showGroups() throws ClientException, ApiException, InterruptedException {
//        for (GetByIdObjectLegacyResponse group: getGroupsIdResponse()) {
//            System.out.println(group.getName() + "↴" + group.getMembersCount());
//            GetMembersResponse response = null;
//            try {
//                response = vk
//                        .groups()
//                        .getMembers(userActor)
//                        .groupId(String.valueOf(group.getId()))
//                        .execute();
//                System.out.println(response.getCount());
//            } catch (ApiAccessException e) {
//                System.out.println("\t### Доступ к членам группы закрыт ###");
//            }
//
//            if (response == null)
//                continue;
//
//            List<String> membersIds = response
//                    .getItems()
//                    .stream()
//                    .map(String::valueOf)
//                    .toList();
//
//            var users = vk
//                    .users()
//                    .get(userActor)
//                    .userIds(membersIds)
//                    .execute();
//
//            users.forEach(user -> System.out.println("\t" + user.getId() + " " + user.getFirstName() + " " + user.getLastName()));
//            Thread.sleep(500);
//        }
//    }
}
