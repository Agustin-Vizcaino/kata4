package agustin.kata4;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class DataFileLoader implements BasicDataLoader {

    public DataFileLoader() {}

    @Override
    public Object loadPath(String path) {
        return new File(path);
    }
}
