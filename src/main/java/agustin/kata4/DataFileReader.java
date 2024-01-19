package agustin.kata4;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataFileReader implements BasicDataReader {
    public DataFileReader() {
    }

    @Override
    public List<String> readPath(String path) {
        try {
            File file = (File) new DataFileLoader().loadPath(path);
            if (file.exists()) {
                return new BufferedReader(new FileReader(file))
                    .lines()
                    .skip(1)
                    .map(line -> {
                        String[] lineFields = line.split(";");
                        return lineFields[0] + ";" + lineFields[1] + ";" + lineFields[6] + ";" + lineFields[7] + ";" + lineFields[13];
                    })
                    .collect(Collectors.toList());
            }
            throw new IOException("File does not exist");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
