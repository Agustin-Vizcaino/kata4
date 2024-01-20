package agustin.kata4;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataBaseReader implements BasicDataReader {

    @Override
    public List<String> readPath(String path) {
        try {
            List<String> returner = new ArrayList<String>();
            ResultSet db = (ResultSet) new DataBaseLoader().loadPath(path);
            while (db.next()) {
                returner.add(db.getInt("GID") + ";" +
                        db.getString("Name") + ";" +
                        db.getString("CountryCode") + ";" +
                        db.getString("Country") + ";" +
                        db.getInt("Population"));
            }
            return returner;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }
}
