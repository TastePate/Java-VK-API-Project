import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import services.csv.CSVWriterService;
import services.graphs.GraphsService;
import utils.DatabaseWriter;

public class Main {
    public static void main(final String[] args) {
        DatabaseWriter db = new DatabaseWriter();
        try {
            db.writeGroupsToDatabase();
        } catch (ClientException | ApiException e) {
            throw new RuntimeException(e);
        }

        CSVWriterService csvWriterService = new CSVWriterService("C:\\Users\\TastePate\\Downloads\\test.txt");
        csvWriterService.writeCSV();

        GraphsService.main(new String[]{});
    }
}