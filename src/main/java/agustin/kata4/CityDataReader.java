package agustin.kata4;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CityDataReader {

    private static final int popThreshold = 1000000;

    private CityDataReader() {
    }

    //Here we see we made a mistake and produced technical debt- Main is not affected by the change from CSV to
    //database, but this method needs to change DataFileReader to DataBaseReader
    public static List<City> readCitiesFromPath(String path) throws IOException {
        List<String> reader = new DataBaseReader().readPath(path);

        if (reader != null) {
            //En teoría, no debería lanzar excepción debido al centinela
            return reader.stream()
                    .map(line -> {
                        String[] fields = City.toCityFormat(line);
                        return City.makeCity(fields);
                    })
                    .filter(city -> city.getPopulation() >= popThreshold)
                    .sorted(City::compare)
                    .collect(Collectors.toList());
            //System.out.println(cities.size());
        }
        return null;
    }
}
