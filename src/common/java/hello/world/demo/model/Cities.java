package hello.world.demo.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cities {
    private final List<City> data;


    public Cities() {
        data = parseCsvFile(new File("src/client/java/hello/world/demo/view/res/coordinates.cities/worldcities.csv"));
    }


    public Stream<City> stream() {
        return  data.stream();
    }

    private static List<City> parseCsvFile(final File csvFile) {
        try {
            return Files.lines(csvFile.toPath()).skip(1) // skip header of csv file
                    .map(lineToCity) // convert line to
                    .filter(elem -> elem != null) // filter null values due to parsing errors
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("File " + csvFile + " not found.");
        }
    }

    private static Function<String, City> lineToCity = (line) -> {
        try {
            final String[] fields = line.split(",");
            final String city = fields[0].substring(1,fields[0].length() - 1);
            final double latitude = Double.parseDouble(fields[2].substring(1, fields[2].length() - 1));
            final double longitude = Double.parseDouble(fields[3].substring(1, fields[3].length() - 1));
            final String country = fields[4].substring(1,fields[4].length() - 1);
            return new City(city, country, latitude, longitude);
        } catch (Exception e) {
            return null;
        }
    };

    public  double getLongitude(String cityName) {
        return data.stream().filter(city -> city.getName().equals(cityName)).map(City::getLongitude).findFirst().orElseThrow();
    }

    public  double getLatitude(String cityName) {
        return data.stream().filter(city -> city.getName().equals(cityName)).map(City::getLatitude).findFirst().orElseThrow();
    }

    public  City getCity(String cityName) {
        return data.stream().filter(city -> city.getName().equals(cityName)).findFirst().orElseThrow();
    }

    public List<City> getData() {
        return data;
    }
}
