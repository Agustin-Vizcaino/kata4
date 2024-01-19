package agustin.kata4;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CityDataReader {

    private static final int popThreshold = 1000000;
    private CityDataReader() {}

    //Here we see we made a mistake and produced slight technical debt- Main is not affected by the change from CSV to
    //database, but CityDataReader needs to switch from calling DataFileReader to DataBaseReader, and from
    //receiving a BufferedReader to a Stream
    public static List<City> readCitiesFromPath(String path) throws IOException {
        BufferedReader reader = DataFileReader.readPath(path);

        if (reader != null) {
            //En teoría, no debería lanzar excepción debido al centinela
            return reader.lines()
                    .skip(1)
                    .map(line -> {
                        String[] fields = City.toCityFormat(line);
                        //System.out.println(fields[4]);
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
