package agustin.kata3;

import java.util.List;

public class City {
    //Dataset source: https://public.opendatasoft.com/explore/dataset/geonames-all-cities-with-a-population-1000/table/?disjunctive.cou_name_en&sort=name
    private int gID;
    private String name;

    private String countryCode;

    private String country;
    private int population;

    public int getgID() {
        return gID;
    }

    public String getName() {
        return name;
    }

    public String getFullName() { return name + "-" + countryCode ;}

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountry() {
        return country;
    }

    public int getPopulation() {
        return population;
    }

    //Patrón FactoryMethod
    public static City makeCity(String[] data) {
        return new City(data);
    }

    //Inicializar valores si la entrada es incorrecta
    {
        gID = 0;
        name = "null";
        countryCode = "null";
        country = "null";
        population = 0;

    }

    //This class accepts a String[] that contains gID, name, country and population
    private City(String[] data) {
        initialize(data);
    }

    protected int initialize(String[] data) {
        if (data.length == 5) {
            try {
                gID = Integer.parseInt(data[0]);
                name = data[1];
                countryCode = data[2];
                country = data[3];
                population = Integer.parseInt(data[4]);
                return 1;
            } catch (Exception e) {
                return 0;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Name: " + getFullName() + ", population: " + getPopulation();
    }

    public static String[] toCityFormat(String line) {
        String[] fields = line.split(";");
        return new String[] { fields[0], fields[1], fields[6], fields[7], fields[13] };
    }
}
