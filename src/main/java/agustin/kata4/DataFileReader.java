package agustin.kata4;

import java.io.*;
import java.util.stream.Stream;

public abstract class DataFileReader {
    private DataFileReader() {
    }

    public static BufferedReader readPath(String path) {
        try {
            File file = (File) new DataFileLoader().loadPath(path);
            if (file.exists()) return new BufferedReader(new FileReader(file));
            throw new IOException("File does not exist");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
