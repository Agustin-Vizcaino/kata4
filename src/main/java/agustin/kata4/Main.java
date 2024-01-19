package agustin.kata4;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static final String path = "./src/main/resources/geonames-all-cities-with-a-population-1000.csv";
    private static MainWindow window;
    public static void main(String[] args) throws IOException {
        LinkedHashMap<String,City> citiesMap = null;
        List<City> citiesList = CityDataReader.readCitiesFromPath(path);
        if (citiesList != null) {
            citiesMap = cityHasher(citiesList);
            analyze(citiesMap);
            generateWindow();
            setupBarChart(citiesMap);
            window.setVisible(true);
        } else {
            System.out.println("Error interpreting/reading data");
        }
        //sendDataset(citiesList);
    }

    public static LinkedHashMap<String, City> cityHasher(List<City> cities) {
        return cities.stream()
                .collect(LinkedHashMap::new,
                        (map, city) -> map.merge(city.getFullName(), city, (city1, city2) ->
                                city1.compare(city2) >= 1 ? city1 : city2),
                        LinkedHashMap::putAll);
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

    public static void setupBarChart(Map data) {
        BasicBarChart popBarChart = new JFCPopulationBarChart();
        popBarChart.create(data);
        window.addBarChart(popBarChart);
    }

}