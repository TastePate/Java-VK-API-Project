import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import utils.DatabaseWriter;

public class Main {
    public static void main(final String[] args) {
        DatabaseWriter db = new DatabaseWriter();
        try {
            db.writeGroupsToDatabase();
        } catch (ClientException | ApiException e) {
            e.printStackTrace();
        }
    }
}