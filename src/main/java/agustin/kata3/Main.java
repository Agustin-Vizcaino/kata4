package agustin.kata3;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    private static final String path = "./src/main/resources/geonames-all-cities-with-a-population-1000.csv";
    private static MainWindow window;
    public static void main(String[] args) throws IOException {
        HashMap<String,City> citiesMap;
        List<City> citiesList = CityDataReader.readCitiesFromPath(path);
        if (citiesList != null) {
            citiesMap = (HashMap<String,City>) cityHasher(citiesList);
            analyze(citiesMap);
        } else {
            System.out.println("Error interpreting/reading data");
        }
    }

    public static Map<String,City> cityHasher(List<City> cities) {
        //System.out.println(cities);
        return new HashMap<>(cities
                .stream()
                .collect(Collectors
                        //All my respect to whoever thought to add the third argument to resolve clashes
                        .toMap(City::getFullName, city -> city, (city1, city2) -> {
                            if (city1.getPopulation() > city2.getPopulation()) return city1;
                            return city2;
                        })));
    }

    public static void analyze(HashMap<String,City> citiesMap) {
        for (String key : citiesMap.keySet()) {
            System.out.println(citiesMap.get(key).toString());
        }
        System.out.println("\nTotal number of registered, non-duplicate cities above 1M inhabitants: " + citiesMap.size());
    }

    public static void generateWindow() {
        window = new MainWindow();
    }

}