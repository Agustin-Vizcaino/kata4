package agustin.kata4;

import java.io.File;

public class DataFileLoader implements BasicDataLoader {

    public DataFileLoader() {
    }

    @Override
    public Object loadPath(String path) {
        return new File(path);
    }
}
