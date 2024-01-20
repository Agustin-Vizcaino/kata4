package agustin.kata4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Stream;

public class DataBaseLoader implements BasicDataLoader {
    protected Connection connection;
    private String prevPath;
    private final String SQL = "SELECT * FROM 'City'";

    public DataBaseLoader() {
    }

    private static String urlOf(String filename) {
        return "jdbc:sqlite:" + filename;
    }

    @Override
    public Object loadPath(String path) {
        //In theory, this would work- check if path has changed first, if not, run connect, if connect
        //returns true, if is false, method continues, if connect returns false, method stops
        if (!path.equals(prevPath) && !connect(path)) return Stream.empty();
        try {
            prevPath = path;
            //I think this is a bad idea
            return queryAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean connect(String path) {
        try {
            connection = DriverManager.getConnection(urlOf(path));
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private ResultSet queryAll() throws SQLException {
        return connection
                .createStatement()
                .executeQuery(SQL);
    }
}
