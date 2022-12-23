package services.csv;

import services.database.UserService;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

public class CSVWriterService {
    private final String FILE_NAME;
    private final UserService userService;

    public CSVWriterService(String fileName){
        FILE_NAME = fileName;
        userService = new UserService();
    }

    public void writeCSV() {
        var users = userService
                .findAllUsers()
                .stream()
                .map(userService::getDataForCSV)
                .collect(Collectors.toSet());
        try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_NAME))){
            writer.writeAll(users);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
