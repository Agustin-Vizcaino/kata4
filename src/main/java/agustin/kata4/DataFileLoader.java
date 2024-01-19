package agustin.kata4;

import java.io.File;

public abstract class DataFileLoader {

    private DataFileLoader() {}

    public static File loadPath(String path) {
        return new File(path);
    }
}
