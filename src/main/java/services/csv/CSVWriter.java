package services.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CSVWriter {
    private final String FILE_NAME;

    public CSVWriter(String fileName){
        FILE_NAME = fileName;
    }

    public void writeCSVData(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        if (!Files.exists(path))
            Files.createFile(path);
        try (CSVParser parser = getParser();
             CSVPrinter printer = new CSVPrinter(new FileWriter(fileName), CSVFormat.DEFAULT)) {
            for (CSVRecord record : parser) {
                printer.printRecord(record.get(0), record.get(1), record.get(2), record.get(3));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private CSVParser getParser() throws IOException {
        InputStreamReader input = new InputStreamReader(Files.newInputStream(Paths.get(FILE_NAME)));
        return CSVFormat.DEFAULT.parse(input);
    }
}
